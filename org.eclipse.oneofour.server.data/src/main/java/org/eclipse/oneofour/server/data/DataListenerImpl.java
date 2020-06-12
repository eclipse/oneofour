/*******************************************************************************
 * Copyright (c) 2014, 2016 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *     Red Hat Inc - enhancements
 *******************************************************************************/
package org.eclipse.oneofour.server.data;

import java.util.List;

import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.types.ASDUAddress;
import org.eclipse.oneofour.asdu.types.CauseOfTransmission;
import org.eclipse.oneofour.asdu.types.InformationEntry;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.Value;

public class DataListenerImpl implements DataListener
{
    private final DataModuleMessageSource source;

    public DataListenerImpl ( final DataModuleMessageSource source )
    {
        this.source = source;
    }

    @Override
    public void dataChangeBoolean ( final CauseOfTransmission cause, final ASDUAddress asduAddress, final InformationObjectAddress startAddress, final List<Value<Boolean>> values )
    {
        this.source.sendBooleanValues ( new ASDUHeader ( cause, asduAddress ), startAddress, values );
    }

    @Override
    public void dataChangeBoolean ( final CauseOfTransmission cause, final ASDUAddress asduAddress, final List<InformationEntry<Boolean>> values )
    {
        this.source.sendBooleanValues ( new ASDUHeader ( cause, asduAddress ), values );
    }

    @Override
    public void dataChangeFloat ( final CauseOfTransmission cause, final ASDUAddress asduAddress, final InformationObjectAddress startAddress, final List<Value<Float>> values )
    {
        this.source.sendFloatValues ( new ASDUHeader ( cause, asduAddress ), startAddress, values );
    }

    @Override
    public void dataChangeFloat ( final CauseOfTransmission cause, final ASDUAddress asduAddress, final List<InformationEntry<Float>> values )
    {
        this.source.sendFloatValues ( new ASDUHeader ( cause, asduAddress ), values );
    }

    @Override
    public void dataChangeShort ( final CauseOfTransmission cause, final ASDUAddress asduAddress, final InformationObjectAddress startAddress, final List<Value<Short>> values )
    {
        this.source.sendShortValues ( new ASDUHeader ( cause, asduAddress ), startAddress, values );
    }

    @Override
    public void dataChangeShort ( final CauseOfTransmission cause, final ASDUAddress asduAddress, final List<InformationEntry<Short>> values )
    {
        this.source.sendShortValues ( new ASDUHeader ( cause, asduAddress ), values );
    }

}
