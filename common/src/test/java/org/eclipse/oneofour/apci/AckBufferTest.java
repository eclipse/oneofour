/*******************************************************************************
 * Copyright (c) 2014 IBH SYSTEMS GmbH and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.oneofour.apci;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AckBufferTest
{
    private AckBuffer buffer;

    private static final int MAX_OUTSTANDING = 2;

    private static final int MAX_SEQUENCE = 5;

    @BeforeEach
    public void setup ()
    {
        this.buffer = new AckBuffer ( MAX_OUTSTANDING, MAX_SEQUENCE );
    }

    @Test
    public void testInit ()
    {
        Assertions.assertEquals ( 0, this.buffer.getOutstandingAcks (), "Initial outstanding acks" );
    }

    @Test
    public void test1 ()
    {
        addMessage ( 0, 1 );
        addMessage ( 1, 2 );

        gotAck ( 1, 1 );
        gotAck ( 1, 1 ); // duplicate

        gotAck ( 2, 0 );
    }

    @Test
    public void testFull ()
    {
        addMessage ( 0, 1 );
        addMessage ( 1, 2 );

        // should fail with IndexOutOfBoundsException
        assertThrows( IndexOutOfBoundsException.class, () -> addMessage ( 2, 3 ) );
    }

    @Test
    public void test2 ()
    {
        addMessage ( 0, 1 );
        gotAck ( 1, 0 ); // free up, buffer should be empty

        addMessage ( 1, 1 );
        addMessage ( 2, 2 ); // the first message with rollover

        gotAck ( 2, 1 );
        gotAck ( 3, 0 ); // buffer should be empty again
    }

    @Test
    public void testManyMessages ()
    {
        for ( int i = 0; i < 1000; i++ )
        {
            addMessage ( i % MAX_SEQUENCE, 1 );
            gotAck ( ( i + 1 ) % MAX_SEQUENCE, 0 );
        }
    }

    @Test
    public void testOneMessage ()
    {
        addMessage ( 0, 1 );
        gotAck ( 1, 0 );
    }

    /**
     * A test method for the case that we never sent out anything, but already
     * got a reply
     */
    @Test
    public void testTheySendFirst ()
    {
        gotAck ( 0, 0 );
    }

    @Test
    public void testSeqError1 ()
    {
        addMessage ( 0, 1 );
        gotAck ( 0, 1 ); // this not wrong, but considered a duplicate
    }

    @Test
    public void testSeqError2 ()
    {
        addMessage ( 0, 1 );

        // ack error, should be 1
        assertThrows( IllegalStateException.class, () -> gotAck ( 2, 0 ) );
    }

    protected void addMessage ( final int expectedSequence, final int expectedOutstanding )
    {
        final int seq = this.buffer.addMessage ( new Object () );
        Assertions.assertEquals ( expectedSequence, seq, "Sequence Number" );
        Assertions.assertEquals ( expectedOutstanding, this.buffer.getOutstandingAcks (), "Outstanding acks" );
    }

    protected void gotAck ( final int ack, final int expectedOutstanding )
    {
        this.buffer.gotAck ( ack );
        Assertions.assertEquals ( expectedOutstanding, this.buffer.getOutstandingAcks (), "Outstanding acks" );
    }
}
