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
 *     Red Hat Inc - enhancements
 *******************************************************************************/
package org.eclipse.oneofour.server.data;

import java.util.List;

import org.eclipse.oneofour.asdu.types.ASDUAddress;
import org.eclipse.oneofour.asdu.types.CauseOfTransmission;
import org.eclipse.oneofour.asdu.types.InformationEntry;
import org.eclipse.oneofour.asdu.types.InformationObjectAddress;
import org.eclipse.oneofour.asdu.types.Value;

public interface DataListener
{
    public void dataChangeBoolean ( CauseOfTransmission cause, ASDUAddress asduAddress, InformationObjectAddress startAddress, List<Value<Boolean>> values );

    public void dataChangeBoolean ( CauseOfTransmission cause, ASDUAddress asduAddress, List<InformationEntry<Boolean>> values );

    public void dataChangeFloat ( CauseOfTransmission cause, ASDUAddress asduAddress, List<InformationEntry<Float>> values );

    public void dataChangeFloat ( CauseOfTransmission cause, ASDUAddress asduAddress, InformationObjectAddress startAddress, List<Value<Float>> values );

    public void dataChangeShort ( CauseOfTransmission cause, ASDUAddress asduAddress, List<InformationEntry<Short>> values );

    public void dataChangeShort ( CauseOfTransmission cause, ASDUAddress asduAddress, InformationObjectAddress startAddress, List<Value<Short>> values );
}
