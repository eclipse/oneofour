/*******************************************************************************
 * Copyright (c) 2014 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.oneofour.asdu.message;

import io.netty.buffer.ByteBuf;

import org.eclipse.oneofour.ProtocolOptions;
import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.types.ASDU;
import org.eclipse.oneofour.asdu.types.Cause;
import org.eclipse.oneofour.asdu.types.CommandValue;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.InformationStructure;

@ASDU ( id = 49, name = "C_SE_NB_1", informationStructure = InformationStructure.SINGLE )
public class SetPointCommandScaledValue extends AbstractSetPointCommandScaledValue implements ValueCommandMessage
{
    public SetPointCommandScaledValue ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final short value, final byte type, final boolean execute )
    {
        super ( header, informationObjectAddress, new CommandValue<Short> ( value, System.currentTimeMillis () ), false, type, execute );
    }

    public SetPointCommandScaledValue ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final short value )
    {
        this ( header, informationObjectAddress, value, (byte)0, true );
    }

    @Override
    public SetPointCommandScaledValue mirror ( final Cause cause, final boolean positive )
    {
        return new SetPointCommandScaledValue ( this.header.clone ( cause, positive ), this.informationObjectAddress, this.getValue ().getValue (), this.getType (), this.isExecute () );
    }

    public static SetPointCommandScaledValue parse ( final ProtocolOptions options, final byte length, final ASDUHeader header, final ByteBuf data )
    {
        final InformationObjectAddress address = InformationObjectAddress.parse ( options, data );

        final short value = data.readShort ();

        final byte b = data.readByte ();

        final byte type = (byte) ( b & 0b011111111 );
        final boolean execute = ! ( ( b & 0b100000000 ) > 0 );

        return new SetPointCommandScaledValue ( header, address, value, type, execute );
    }
}
