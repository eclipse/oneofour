/*******************************************************************************
 * Copyright (c) 2014, 2016 IBH SYSTEMS GmbH and others.
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
package org.eclipse.oneofour.server.data;

import org.eclipse.oneofour.apci.MessageSource;

import io.netty.channel.ChannelHandlerContext;

public class ContextChannelWriter implements ChannelWriter
{
    private final ChannelHandlerContext ctx;

    ContextChannelWriter ( final ChannelHandlerContext ctx )
    {
        this.ctx = ctx;
    }

    @Override
    public void write ( final Object message )
    {
        this.ctx.write ( message );
    }

    @Override
    public void notifyMoreData ()
    {
        this.ctx.writeAndFlush ( MessageSource.NOTIFY_TOKEN );
    }

    @Override
    public void flush ()
    {
        this.ctx.flush ();
    }
}