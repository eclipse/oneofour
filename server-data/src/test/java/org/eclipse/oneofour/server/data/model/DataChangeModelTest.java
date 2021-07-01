/*******************************************************************************
 * Copyright (c) 2016, 2017 Red Hat Inc and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.oneofour.server.data.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.message.AbstractMessage;
import org.eclipse.oneofour.asdu.message.MeasuredValueShortFloatingPointSequence;
import org.eclipse.oneofour.asdu.message.SinglePointInformationSequence;
import org.eclipse.oneofour.asdu.types.ASDUAddress;
import org.eclipse.oneofour.asdu.types.CauseOfTransmission;
import org.eclipse.oneofour.asdu.types.CommandValue;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.StandardCause;
import org.eclipse.oneofour.asdu.types.Value;
import org.eclipse.oneofour.server.data.BackgroundIterator;
import org.eclipse.oneofour.server.data.model.MockDataListener.Event;

import org.hamcrest.Matcher;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataChangeModelTest
{
    private static final int WAIT = 250;

    private MockChangeDataModel model;

    private Thread[] threads;

    @BeforeEach
    public void before ()
    {
        this.model = new MockChangeDataModel ( null );
        this.threads = Thread.getAllStackTraces ().keySet ().toArray ( new Thread[0] );
    }

    @AfterEach
    public void after () throws Exception
    {
        this.model.stop ().await ();
        this.model = null;

        Thread.sleep ( 50 ); // thread count grace period

        final Thread[] threads = Thread.getAllStackTraces ().keySet ().toArray ( new Thread[0] );
        if ( threads.length != this.threads.length )
        {
            dumpThreads ( "Before", this.threads );
            dumpThreads ( "After", threads );
            Assertions.fail ( "Illegal thread count" );
        }
    }

    @Test
    public void test1 ()
    {
        this.model.start ();
        this.model.stop ();
    }

    @Test
    public void test1a ()
    {
        this.model.start ();
        this.model.stop ();

        // double stop should not fail
        this.model.stop ();
    }

    @Test
    public void test1b ()
    {
        // no start, stop should not fail as well
        this.model.stop ();
    }

    @Test
    public void testWriteCommandOk () throws Exception
    {
        this.model.start ();

        final MockMirrorCommand mirrorCommand = new MockMirrorCommand ();
        this.model.writeValue ( new ASDUHeader ( CauseOfTransmission.REQUEST, ASDUAddress.valueOf ( 1 ) ), InformationObjectAddress.valueOf ( 1 ), CommandValue.TRUE (), (byte)0, mirrorCommand, true );

        this.model.stop ().await ();

        // assert command

        Assertions.assertEquals ( 1, mirrorCommand.getPositive () );
        Assertions.assertEquals ( 0, mirrorCommand.getNegative () );
        Assertions.assertEquals ( 1, mirrorCommand.getTermination () );
    }

    // FIXME: clarify what exactly this test is testing
    @Test
    public void testWriteFloatCommandOk () throws Exception
    {
        this.model.start ();

        final MockMirrorCommand mirrorCommand = new MockMirrorCommand ();
        this.model.writeValue ( new ASDUHeader ( CauseOfTransmission.REQUEST, ASDUAddress.valueOf ( 1 ) ), InformationObjectAddress.valueOf ( 1 ), new CommandValue<Float> ( 1.2f, System.currentTimeMillis () ), (byte)0, mirrorCommand, true );

        this.model.stop ().await ();

        // assert command

        Assertions.assertEquals ( 1, mirrorCommand.getPositive () );
        Assertions.assertEquals ( 0, mirrorCommand.getNegative () );
        Assertions.assertEquals ( 1, mirrorCommand.getTermination () );
    }

    @Test
    public void testNotify1 () throws Exception
    {
        this.model.start ();

        final MockDataListener dataListener = new MockDataListener ();
        this.model.subscribe ( dataListener );

        this.model.notifyDataChange ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromString ( "0-1" ), InformationObjectAddress.fromString ( "2-3-4" ), MockDataListener.TRUE, true );

        this.model.stop ().await ();

        // assert notifies

        dataListener.assertEvents ( //
                new Event ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromArray ( new int[] { 0, 1 } ), InformationObjectAddress.fromArray ( new int[] { 2, 3, 4 } ), MockDataListener.TRUE ) //
        );
    }

    @Test
    public void testNotify2 () throws Exception
    {
        this.model.start ();

        final MockDataListener dataListener = new MockDataListener ();
        this.model.subscribe ( dataListener );

        this.model.notifyDataChange ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromString ( "0-1" ), InformationObjectAddress.fromString ( "2-3-4" ), MockDataListener.TRUE, true );
        this.model.notifyDataChange ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromString ( "0-1" ), InformationObjectAddress.fromString ( "2-3-5" ), MockDataListener.FALSE, true );

        Thread.sleep ( 2 * WAIT );

        this.model.stop ().await ();

        // assert notifies

        dataListener.assertEvents ( //
                new Event ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromArray ( new int[] { 0, 1 } ), InformationObjectAddress.fromArray ( new int[] { 2, 3, 4 } ), MockDataListener.TRUE ), //
                new Event ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromArray ( new int[] { 0, 1 } ), InformationObjectAddress.fromArray ( new int[] { 2, 3, 5 } ), MockDataListener.FALSE ) //
        );
    }

    private void dumpThreads ( final String string, final Thread[] threads )
    {
        System.out.println ( "==== " + string + " ====" );
        for ( final Thread t : threads )
        {
            System.out.println ( t );
        }
    }

    @Test
    public void testBackground1 () throws Exception
    {
        this.model.start ();

        final MockDataListener dataListener = new MockDataListener ();
        this.model.subscribe ( dataListener );

        this.model.notifyDataChange ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromString ( "0-1" ), InformationObjectAddress.fromString ( "2-3-4" ), MockDataListener.TRUE, true );
        this.model.notifyDataChange ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromString ( "0-2" ), InformationObjectAddress.fromString ( "2-3-4" ), Value.ok ( 1.0f ), true );

        final BackgroundIterator iter = this.model.createBackgroundIterator ();

        assertNextBackgroundMessage ( iter, 1, instanceOf ( SinglePointInformationSequence.class ) );
        assertNextBackgroundMessage ( iter, 1, instanceOf ( MeasuredValueShortFloatingPointSequence.class ) );

        // next must be null .. scan ended

        assertLastBackgroundMessage ( iter );

        // end

        this.model.stop ().await ();
    }

    @Test
    public void testBackground2 () throws Exception
    {
        this.model.start ();

        final MockDataListener dataListener = new MockDataListener ();
        this.model.subscribe ( dataListener );

        this.model.notifyDataChange ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromString ( "0-1" ), InformationObjectAddress.fromString ( "2-3-4" ), MockDataListener.TRUE, true );
        this.model.notifyDataChange ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromString ( "0-1" ), InformationObjectAddress.fromString ( "2-3-5" ), MockDataListener.FALSE, true );
        this.model.notifyDataChange ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromString ( "0-2" ), InformationObjectAddress.fromString ( "2-3-4" ), Value.ok ( 1.0f ), true );

        final BackgroundIterator iter = this.model.createBackgroundIterator ();

        assertNextBackgroundMessage ( iter, 2 );

        // background iterator should reset now

        this.model.notifyDataChange ( CauseOfTransmission.SPONTANEOUS, ASDUAddress.fromString ( "0-3" ), InformationObjectAddress.fromString ( "2-3-6" ), MockDataListener.TRUE, true );

        assertNextBackgroundMessage ( iter, 2 );

        // end

        this.model.stop ().await ();
    }

    private void assertLastBackgroundMessage ( final BackgroundIterator iter )
    {
        Assertions.assertNull ( iter.nextMessage () );
    }

    private void assertNextBackgroundMessage ( final BackgroundIterator iter, final int size )
    {
        assertNextBackgroundMessage ( iter, size, null );
    }

    private void assertNextBackgroundMessage ( final BackgroundIterator iter, final int size, final Matcher<Object> matcher )
    {
        final Object o = iter.nextMessage ();
        Assertions.assertNotNull ( o );

        assertThat ( o, instanceOf ( AbstractMessage.class ) );

        final AbstractMessage amsg = (AbstractMessage)o;
        Assertions.assertEquals ( StandardCause.BACKGROUND, amsg.getHeader ().getCauseOfTransmission ().getCause () );

        if ( matcher != null )
        {
            assertThat ( o, matcher );
        }

        if ( amsg instanceof SinglePointInformationSequence )
        {
            final SinglePointInformationSequence msg = (SinglePointInformationSequence)amsg;
            Assertions.assertEquals ( size, msg.getValues ().size () );
        }
        else if ( amsg instanceof MeasuredValueShortFloatingPointSequence )
        {
            final MeasuredValueShortFloatingPointSequence msg = (MeasuredValueShortFloatingPointSequence)amsg;
            Assertions.assertEquals ( size, msg.getValues ().size () );
        }
        else
        {
            Assertions.fail ( "Wrong messgae type: " + amsg.getClass ().getName () );
        }
    }
}
