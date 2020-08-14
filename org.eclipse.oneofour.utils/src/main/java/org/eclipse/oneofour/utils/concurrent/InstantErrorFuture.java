/*******************************************************************************
 * Copyright (c) 2006, 2010 TH4 SYSTEMS GmbH and others.
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     TH4 SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.oneofour.utils.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A future that never has to run since the result is already known
 *
 * @author Jens Reimann
 * @param <T>
 *            the type of the future
 */
public class InstantErrorFuture<T> extends InstantFutureBase<T>
{
    private final Throwable error;

    public InstantErrorFuture ( final Throwable error )
    {
        this.error = error;
    }

    @Override
    public T get () throws InterruptedException, ExecutionException
    {
        throw new ExecutionException ( this.error );
    }

    @Override
    public T get ( final long timeout, final TimeUnit unit ) throws InterruptedException, ExecutionException, TimeoutException
    {
        throw new ExecutionException ( this.error );
    }
}