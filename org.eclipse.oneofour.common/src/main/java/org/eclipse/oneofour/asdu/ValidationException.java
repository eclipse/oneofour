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
package org.eclipse.oneofour.asdu;

public class ValidationException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ValidationException ()
    {
    }

    public ValidationException ( final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace )
    {
        super ( message, cause, enableSuppression, writableStackTrace );
    }

    public ValidationException ( final String message, final Throwable cause )
    {
        super ( message, cause );
    }

    public ValidationException ( final String message )
    {
        super ( message );
    }

    public ValidationException ( final Throwable cause )
    {
        super ( cause );
    }

}
