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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class AsduAddressTest
{
    @Test
    public void test1 ()
    {
        Assertions.assertEquals ( fromString ( "0-1" ), fromString ( "0-1" ) );
    }

    @Test
    public void test2 ()
    {
        Assertions.assertEquals ( fromArray ( new int[] { 0, 1 } ), fromString ( "0-1" ) );
    }

    @Test
    public void test3 ()
    {
        Assertions.assertEquals ( 1, fromString ( "0-1" ).getAddress () );
        Assertions.assertEquals ( 2, fromString ( "0-2" ).getAddress () );
        Assertions.assertEquals ( 256, fromString ( "1-0" ).getAddress () );
    }
}
