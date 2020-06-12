/*******************************************************************************
 * Copyright (c) 2016 Red Hat Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.oneofour.server.data.event;

import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.message.MeasuredValueScaledSequence;
import org.eclipse.oneofour.asdu.message.MeasuredValueScaledSingle;
import org.eclipse.oneofour.asdu.message.MeasuredValueScaledTimeSingle;

public class SimpleScaledBuilder implements MessageBuilderFactory<Short>
{
    private final boolean withTimestamps;

    public SimpleScaledBuilder ( final boolean withTimestamps )
    {
        this.withTimestamps = withTimestamps;
    }

    @Override
    public MessageBuilder<Short, ?> create ()
    {
        return new AbstractMessageBuilder<Short, Object> ( Short.class, 20, 20, this.withTimestamps ? 10 : -1 ) {
            @Override
            public Object build ()
            {
                validateStart ();

                final ASDUHeader header = new ASDUHeader ( this.causeOfTransmission, this.asduAddress );

                if ( isWithTimestamps () )
                {
                    return MeasuredValueScaledTimeSingle.create ( header, this.entries );
                }
                else if ( isContinuous () )
                {
                    return MeasuredValueScaledSequence.create ( getStartAddress (), header, getValues () );
                }
                else
                {
                    return MeasuredValueScaledSingle.create ( header, this.entries );
                }
            }
        };
    }

}
