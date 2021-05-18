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
package org.eclipse.oneofour.server.data.model;

import java.util.Optional;

import org.eclipse.oneofour.server.data.BackgroundIterator;

public interface BackgroundModel
{
    public static final BackgroundModel NONE = new BackgroundModel () {

        @Override
        public Optional<BackgroundIterator> createBackgroundIterator ()
        {
            return Optional.empty ();
        }

        @Override
        public Runnable dispose ()
        {
            return () -> {
            };
        }
    };

    /**
     * Create a new background iterator
     *
     * @return a new optional background iterator instance, never returns null
     */
    public Optional<BackgroundIterator> createBackgroundIterator ();

    public Runnable dispose ();
}
