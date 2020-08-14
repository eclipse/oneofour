/*******************************************************************************
 * Copyright (c) 2016 Red Hat Inc and others.
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.oneofour;

import static org.eclipse.oneofour.asdu.types.ASDUAddress.fromArray;
import static org.eclipse.oneofour.asdu.types.ASDUAddress.fromString;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AsduAddressTest
{
    @Test
    public void test1 ()
    {
        assertEquals ( fromString ( "0-1" ), fromString ( "0-1" ) );
    }

    @Test
    public void test2 ()
    {
        assertEquals ( fromArray ( new int[] { 0, 1 } ), fromString ( "0-1" ) );
    }

    @Test
    public void test3 ()
    {
        assertEquals ( 1, fromString ( "0-1" ).getAddress () );
        assertEquals ( 2, fromString ( "0-2" ).getAddress () );
        assertEquals ( 256, fromString ( "1-0" ).getAddress () );
    }
}
