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
package org.eclipse.oneofour.server.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.FutureCallback;

public abstract class FinallyFutureCallback<V> implements FutureCallback<V>
{

    private final static Logger logger = LoggerFactory.getLogger ( FinallyFutureCallback.class );

    @Override
    public void onSuccess ( final V result )
    {
        onFinally ();
    }

    @Override
    public void onFailure ( final Throwable t )
    {
        logger.debug ( "Failed", t );
        onFinally ();
    }

    public abstract void onFinally ();
}
