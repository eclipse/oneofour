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
import org.eclipse.oneofour.asdu.types.InformationStructure;

import io.netty.buffer.ByteBuf;

public final class EncodeHelper
{
    private EncodeHelper ()
    {
    }

    public static void encodeHeader ( final byte typeId, final InformationStructure informationStructure, final ProtocolOptions options, final Integer size, final ASDUHeader header, final ByteBuf out )
    {
        final byte lengthInformation;
        if ( size == null )
        {
            lengthInformation = informationStructure.encode ( 1 );
        }
        else
        {
            lengthInformation = informationStructure.encode ( size );
        }

        out.writeByte ( typeId );
        out.writeByte ( lengthInformation );
        header.getCauseOfTransmission ().encode ( options, out );
        header.getAsduAddress ().encode ( options, out );
    }

    public static void encodeHeader ( final Object message, final ProtocolOptions options, final Integer size, final ASDUHeader header, final ByteBuf out )
    {
        final ASDU asdu = message.getClass ().getAnnotation ( ASDU.class );
        if ( asdu == null )
        {
            throw new IllegalArgumentException ( String.format ( "Message type %s does not have @%s annotation", message.getClass (), ASDU.class.getName () ) );
        }

        encodeHeader ( asdu.id (), asdu.informationStructure (), options, size, header, out );
    }
}
