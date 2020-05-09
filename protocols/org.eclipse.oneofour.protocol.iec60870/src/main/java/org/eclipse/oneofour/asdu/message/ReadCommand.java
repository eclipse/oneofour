/*******************************************************************************
 * Copyright (c) 2014 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.oneofour.asdu.message;

import org.eclipse.oneofour.ProtocolOptions;
import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.types.ASDU;
import org.eclipse.oneofour.asdu.types.Cause;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;

import io.netty.buffer.ByteBuf;

@ASDU ( id = 102, name = "C_RD_NA_1" )
public class ReadCommand extends AbstractInformationObjectMessage implements MirrorableMessage<ReadCommand>
{
    public ReadCommand ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress )
    {
        super ( header, informationObjectAddress );
    }

    public static ReadCommand parse ( final ProtocolOptions options, final byte length, final ASDUHeader header, final ByteBuf data )
    {
        return new ReadCommand ( header, InformationObjectAddress.parse ( options, data ) );
    }

    @Override
    public void encode ( final ProtocolOptions options, final ByteBuf out )
    {
        EncodeHelper.encodeHeader ( this, options, null, this.header, out );
        this.informationObjectAddress.encode ( options, out );
    }

    @Override
    public ReadCommand mirror ( final Cause cause, final boolean positive )
    {
        return new ReadCommand ( this.header.clone ( cause, positive ), this.informationObjectAddress );
    }
}
