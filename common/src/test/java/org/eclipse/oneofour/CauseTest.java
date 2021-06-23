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
package org.eclipse.oneofour;

import org.eclipse.oneofour.asdu.types.CauseOfTransmission;
import org.eclipse.oneofour.asdu.types.Causes;
import org.eclipse.oneofour.asdu.types.StandardCause;
import org.junit.Assert;
import org.junit.Test;

public class CauseTest
{
    /**
     * Plain compare
     */
    @Test
    public void test1 ()
    {
        final CauseOfTransmission cot1 = new CauseOfTransmission ( StandardCause.ACTIVATED );
        final CauseOfTransmission cot2 = new CauseOfTransmission ( StandardCause.ACTIVATED );
        final CauseOfTransmission cot3 = new CauseOfTransmission ( StandardCause.ACTIVATION_CONFIRM );

        Assert.assertEquals ( cot1, cot2 );
        Assert.assertNotEquals ( cot1, cot3 );
        Assert.assertNotEquals ( cot2, cot3 );
    }

    /**
     * Compare enum vs value
     */
    @Test
    public void test2 ()
    {
        final CauseOfTransmission cot1 = new CauseOfTransmission ( StandardCause.ACTIVATED );
        final CauseOfTransmission cot2 = new CauseOfTransmission ( Causes.valueOf ( StandardCause.ACTIVATED.getValue () ) );
        final CauseOfTransmission cot3 = new CauseOfTransmission ( Causes.valueOf ( StandardCause.ACTIVATION_CONFIRM.getValue () ) );

        Assert.assertEquals ( cot1, cot2 );
        Assert.assertNotEquals ( cot1, cot3 );
        Assert.assertNotEquals ( cot2, cot3 );
    }
}
