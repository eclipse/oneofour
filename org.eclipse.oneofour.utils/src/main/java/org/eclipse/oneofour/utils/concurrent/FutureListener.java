/*******************************************************************************
 * Copyright (c) 2006, 2010 TH4 SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     TH4 SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.oneofour.utils.concurrent;

import java.util.concurrent.Future;

public interface FutureListener<T>
{
    public void complete ( Future<T> future );
}
