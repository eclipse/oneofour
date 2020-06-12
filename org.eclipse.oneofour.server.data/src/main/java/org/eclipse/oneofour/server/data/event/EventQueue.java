/*******************************************************************************
 * Copyright (c) 2014, 2016 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.oneofour.server.data.event;

import java.util.List;

import org.eclipse.oneofour.asdu.types.ASDUAddress;
import org.eclipse.oneofour.asdu.types.CauseOfTransmission;
import org.eclipse.oneofour.asdu.types.InformationEntry;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.Value;

public class EventQueue<T>
{
    private final EventBuffer<T> buffer;

    private final MessageBuilderFactory<T> factory;

    public EventQueue ( final int numberOfDuplicates, final MessageBuilderFactory<T> factory )
    {
        this.buffer = new EventBuffer<T> ( numberOfDuplicates );
        this.factory = factory;
    }

    public void append ( final CauseOfTransmission causeOfTransmission, final ASDUAddress asduAddress, final InformationObjectAddress address, final Value<T> value )
    {
        this.buffer.append ( causeOfTransmission, asduAddress, address, value );
    }

    public void append ( final CauseOfTransmission causeOfTransmission, final ASDUAddress asduAddress, final InformationObjectAddress startAddress, final List<Value<T>> values )
    {
        this.buffer.append ( causeOfTransmission, asduAddress, startAddress, values );
    }

    public void append ( final CauseOfTransmission causeOfTransmission, final ASDUAddress asduAddress, final List<InformationEntry<T>> values )
    {
        this.buffer.append ( causeOfTransmission, asduAddress, values );
    }

    public int getCauseCounter ( final CauseOfTransmission causeOfTransmission, final ASDUAddress asduAddress )
    {
        return this.buffer.getCauseCounter ( causeOfTransmission, asduAddress );
    }

    public Object poll ()
    {
        return this.buffer.poll ( this.factory.create () );
    }

}
