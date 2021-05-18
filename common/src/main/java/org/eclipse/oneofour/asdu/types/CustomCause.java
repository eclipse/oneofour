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
package org.eclipse.oneofour.asdu.types;

class CustomCause implements Cause
{
    private final short value;

    private String label;

    CustomCause ( final int value )
    {
        this.value = (short)value;
    }

    CustomCause ( final int value, final String label )
    {
        this.value = (short)value;
        this.label = label;
    }

    @Override
    public short getValue ()
    {
        return this.value;
    }

    @Override
    public String toString ()
    {
        return this.label != null ? String.format ( "[%s:%s]", this.value, this.label ) : String.format ( "[%s]", this.value );
    }
}
