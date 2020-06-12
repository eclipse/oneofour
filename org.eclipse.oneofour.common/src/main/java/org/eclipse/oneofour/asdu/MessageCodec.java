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
package org.eclipse.oneofour.asdu;

import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.ProtocolOptions;

import io.netty.buffer.ByteBuf;

public interface MessageCodec
{
    public Object parse ( ProtocolOptions options, byte length, ASDUHeader header, ByteBuf data );

    public void encode ( ProtocolOptions options, Object msg, ByteBuf out );
}
