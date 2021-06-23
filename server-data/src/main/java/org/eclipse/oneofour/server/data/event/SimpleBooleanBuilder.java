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
package org.eclipse.oneofour.server.data.event;

import org.eclipse.oneofour.asdu.ASDUHeader;
import org.eclipse.oneofour.asdu.message.SinglePointInformationSequence;
import org.eclipse.oneofour.asdu.message.SinglePointInformationSingle;
import org.eclipse.oneofour.asdu.message.SinglePointInformationTimeSingle;

public class SimpleBooleanBuilder implements MessageBuilderFactory<Boolean>
{
    private final boolean withTimestamps;

    public SimpleBooleanBuilder ( final boolean withTimestamps )
    {
        this.withTimestamps = withTimestamps;
    }

    @Override
    public MessageBuilder<Boolean, ?> create ()
    {
        return new AbstractMessageBuilder<Boolean, Object> ( Boolean.class, 20, 20, this.withTimestamps ? 10 : -1 ) {
            @Override
            public Object build ()
            {
                validateStart ();

                final ASDUHeader header = new ASDUHeader ( this.causeOfTransmission, this.asduAddress );

                if ( isWithTimestamps () )
                {
                    return SinglePointInformationTimeSingle.create ( header, this.entries );
                }
                else if ( isContinuous () )
                {
                    return SinglePointInformationSequence.create ( getStartAddress (), header, getValues () );
                }
                else
                {
                    return SinglePointInformationSingle.create ( header, this.entries );
                }
            }
        };
    }
}
