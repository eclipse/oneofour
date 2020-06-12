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
package org.eclipse.oneofour.server.data.event;

import org.eclipse.oneofour.asdu.types.ASDUAddress;
import org.eclipse.oneofour.asdu.types.CauseOfTransmission;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.Value;

public interface MessageBuilder<T, M>
{
    public void start ( CauseOfTransmission causeOfTransmission, ASDUAddress asduAddress );

    public boolean addEntry ( InformationObjectAddress address, Value<T> value );

    public M build ();

    public boolean accepts ( Value<?> value );
}
