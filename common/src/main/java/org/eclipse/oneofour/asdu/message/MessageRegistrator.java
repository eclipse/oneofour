/*******************************************************************************
 * Copyright (c) 2014, 2016 IBH SYSTEMS GmbH and others.
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

import org.eclipse.oneofour.asdu.MessageManager;

public class MessageRegistrator
{
    public void register ( final MessageManager manager )
    {
        manager.registerClass ( InterrogationCommand.class );
        manager.registerClass ( ReadCommand.class );
        manager.registerClass ( SinglePointInformationSequence.class );
        manager.registerClass ( SinglePointInformationSingle.class );
        manager.registerClass ( SinglePointInformationTimeSingle.class );

        manager.registerClass ( DoublePointInformationSequence.class );
        manager.registerClass ( DoublePointInformationSingle.class );
        manager.registerClass ( DoublePointInformationTimeSingle.class );

        manager.registerClass ( MeasuredValueScaledSequence.class );
        manager.registerClass ( MeasuredValueScaledSingle.class );
        manager.registerClass ( MeasuredValueScaledTimeSingle.class );

        manager.registerClass ( MeasuredValueNormalizedSequence.class );
        manager.registerClass ( MeasuredValueNormalizedSingle.class );
        manager.registerClass ( MeasuredValueNormalizedTimeSingle.class );

        manager.registerClass ( MeasuredValueShortFloatingPointSequence.class );
        manager.registerClass ( MeasuredValueShortFloatingPointSingle.class );
        manager.registerClass ( MeasuredValueShortFloatingPointTimeSingle.class );

        manager.registerClass ( SingleCommand.class );
        manager.registerClass ( DoubleCommand.class );
        manager.registerClass ( SetPointCommandShortFloatingPoint.class );
        manager.registerClass ( SetPointCommandNormalizedValue.class );
        manager.registerClass ( SetPointCommandScaledValue.class );

        manager.registerClass ( SingleCommandTime.class );
        manager.registerClass ( SetPointCommandShortFloatingPointTime.class );
        manager.registerClass ( SetPointCommandNormalizedValueTime.class );
        manager.registerClass ( SetPointCommandScaledValueTime.class );

        manager.registerClass ( EndOfInitialization.class );
    }
}
