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
package org.eclipse.oneofour.asdu.message;

import org.eclipse.oneofour.ProtocolOptions;
import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.types.ASDU;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;

import io.netty.buffer.ByteBuf;

@ASDU ( id = 70, name = "M_EI_NA_1" )
public class EndOfInitialization extends AbstractInformationObjectMessage
{
    private static final int CAUSE_MASK = 0b01111111;

    public static final byte CAUSE_LOCAL_POWER_ON = 0x00;

    public static final byte CAUSE_LOCAL_MANUAL_RESET = 0x01;

    public static final byte CAUSE_REMOTE_RESET = 0x02;

    private final byte causeOfInitialization;

    private final boolean localParameterChange;

    public EndOfInitialization ( final ASDUHeader header, final byte causeOfInitialization, final boolean localParameterChange )
    {
        this ( header, InformationObjectAddress.DEFAULT, causeOfInitialization, localParameterChange );
    }

    public EndOfInitialization ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final byte causeOfInitialization, final boolean localParameterChange )
    {
        super ( header, informationObjectAddress );
        this.causeOfInitialization = causeOfInitialization;
        this.localParameterChange = localParameterChange;
    }

    public byte getCauseOfInitialization ()
    {
        return this.causeOfInitialization;
    }

    public boolean isLocalParameterChange ()
    {
        return this.localParameterChange;
    }

    public static EndOfInitialization parse ( final ProtocolOptions options, final byte length, final ASDUHeader header, final ByteBuf data )
    {
        final InformationObjectAddress informationObjectAddress = InformationObjectAddress.parse ( options, data );
        final byte b = data.readByte ();

        final byte causeOfInitialization = (byte) ( b & CAUSE_MASK );
        final boolean localParameterChange = ( b & ~CAUSE_MASK ) > 0;

        final EndOfInitialization result = new EndOfInitialization ( header, informationObjectAddress, causeOfInitialization, localParameterChange );
        return result;
    }

    @Override
    public void encode ( final ProtocolOptions options, final ByteBuf out )
    {
        EncodeHelper.encodeHeader ( this, options, null, this.header, out );
        this.informationObjectAddress.encode ( options, out );

        final byte b = (byte) ( this.causeOfInitialization & CAUSE_MASK | ( this.localParameterChange ? 0x01 : 0x00 ) );
        out.writeByte ( b );
    }
}
