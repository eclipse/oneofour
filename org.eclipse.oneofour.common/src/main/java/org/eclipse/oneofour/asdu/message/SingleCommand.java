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

@ASDU ( id = 45, name = "C_SC_NA_1", informationStructure = InformationStructure.SINGLE )
public class SingleCommand extends AbstractSingleCommand implements ValueCommandMessage
{
    public SingleCommand ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final boolean state, final byte type, final boolean execute )
    {
        super ( header, informationObjectAddress, state ? CommandValue.TRUE () : CommandValue.FALSE (), false, type, execute );
    }

    public SingleCommand ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress, final boolean state )
    {
        this ( header, informationObjectAddress, state, (byte)0, true );
    }

    @Override
    public SingleCommand mirror ( final Cause cause, final boolean positive )
    {
        return new SingleCommand ( this.header.clone ( cause, positive ), this.informationObjectAddress, isState (), getType (), isExecute () );
    }

    public static SingleCommand parse ( final ProtocolOptions options, final byte length, final ASDUHeader header, final ByteBuf data )
    {
        final InformationObjectAddress address = InformationObjectAddress.parse ( options, data );

        final byte b = data.readByte ();

        final boolean state = ( b & 0b00000001 ) > 0;
        final byte type = (byte) ( ( b & 0b011111100 ) >> 2 );
        final boolean execute = ! ( ( b & 0b100000000 ) > 0 );

        return new SingleCommand ( header, address, state, type, execute );
    }

}
