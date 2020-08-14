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
package org.eclipse.oneofour.apci;

public class Supervisory extends APCIBase
{
    private final int receiveSequenceNumber;

    public Supervisory ( final int receiveSequenceNumber )
    {
        super ( APCIType.S );
        this.receiveSequenceNumber = receiveSequenceNumber;
    }

    public int getReceiveSequenceNumber ()
    {
        return this.receiveSequenceNumber;
    }

    @Override
    public String toString ()
    {
        return String.format ( "[APCI:S:%s]", this.receiveSequenceNumber );
    }
}
