/*******************************************************************************
 * Copyright (c) 2016 IBH SYSTEMS GmbH and others.
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
 *     Red Hat Inc - enhancements
 *******************************************************************************/
package org.eclipse.oneofour.server.data.model;

import static java.util.Collections.singletonList;

import java.util.List;

import org.eclipse.oneofour.asdu.types.ASDUAddress;
import org.eclipse.oneofour.asdu.types.CauseOfTransmission;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstantChangeModel implements ChangeModel
{
    public interface Context
    {
        public void notifyChangeBoolean ( final CauseOfTransmission cause, ASDUAddress asduAddress, InformationObjectAddress startAddress, List<Value<Boolean>> values );

        public void notifyChangeFloat ( final CauseOfTransmission cause, ASDUAddress asduAddress, InformationObjectAddress startAddress, List<Value<Float>> values );

        public void notifyChangeShort ( final CauseOfTransmission cause, ASDUAddress asduAddress, InformationObjectAddress startAddress, List<Value<Short>> values );
    }

    private final static Logger logger = LoggerFactory.getLogger ( InstantChangeModel.class );

    private final Context context;

    public InstantChangeModel ( final Context context )
    {
        this.context = context;
    }

    @Override
    public Runnable dispose ()
    {
        return () -> {
        };
    }

    @SuppressWarnings ( "unchecked" )
    @Override
    public void notifyChange ( final CauseOfTransmission cause, final ASDUAddress asduAddress, final InformationObjectAddress informationObjectAddress, final Value<?> iecValue )
    {
        final Object rawValue = iecValue.getValue ();

        logger.trace ( "Notify raw value: {} ({})", rawValue, rawValue != null ? rawValue.getClass () : null );

        if ( rawValue instanceof Boolean )
        {
            this.context.notifyChangeBoolean ( cause, asduAddress, informationObjectAddress, singletonList ( (Value<Boolean>)iecValue ) );
        }
        else if ( rawValue instanceof Float )
        {
            this.context.notifyChangeFloat ( cause, asduAddress, informationObjectAddress, singletonList ( (Value<Float>)iecValue ) );
        }
        else if ( rawValue instanceof Short )
        {
            this.context.notifyChangeShort ( cause, asduAddress, informationObjectAddress, singletonList ( (Value<Short>)iecValue ) );
        }
    }

}
