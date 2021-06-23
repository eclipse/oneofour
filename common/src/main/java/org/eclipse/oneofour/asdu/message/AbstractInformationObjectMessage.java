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

import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;

public abstract class AbstractInformationObjectMessage extends AbstractMessage
{
    protected final InformationObjectAddress informationObjectAddress;

    public AbstractInformationObjectMessage ( final ASDUHeader header, final InformationObjectAddress informationObjectAddress )
    {
        super ( header );
        this.informationObjectAddress = informationObjectAddress;
    }

    public InformationObjectAddress getInformationObjectAddress ()
    {
        return this.informationObjectAddress;
    }
}
