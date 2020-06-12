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

@ASDU ( id = 50, name = "C_SE_NC_1", informationStructure = InformationStructure.SINGLE )
public class SetPointCommandShortFloatingPoint extends AbstractSetPointCommandShortFloatingPoint implements ValueCommandMessage
{
    public SetPointCommandShortFloatingPoint ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final float value, final byte type, final boolean execute )
    {
        super ( header, informationObjectAddress, new CommandValue<Float> ( value, System.currentTimeMillis () ), false, type, execute );
    }

    public SetPointCommandShortFloatingPoint ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final float value )
    {
        this ( header, informationObjectAddress, value, (byte)0, true );
    }

    @Override
    public SetPointCommandShortFloatingPoint mirror ( final Cause cause, final boolean positive )
    {
        return new SetPointCommandShortFloatingPoint ( this.header.clone ( cause, positive ), this.informationObjectAddress, this.getValue ().getValue (), this.getType (), this.isExecute () );
    }

    public static SetPointCommandShortFloatingPoint parse ( final ProtocolOptions options, final byte length, final ASDUHeader header, final ByteBuf data )
    {
        final InformationObjectAddress address = InformationObjectAddress.parse ( options, data );

        final float value = data.readFloat ();

        final byte b = data.readByte ();

        final byte type = (byte) ( b & 0b011111111 );
        final boolean execute = ! ( ( b & 0b100000000 ) > 0 );

        return new SetPointCommandShortFloatingPoint ( header, address, value, type, execute );
    }

}
