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
package org.eclipse.oneofour.client.data;

import java.io.Serializable;

import org.eclipse.oneofour.utils.beans.AbstractPropertyChange;

public class DataModuleOptions
{
    private final boolean ignoreBackgroundScan;

    private final boolean delayStart;

    private final Byte causeSourceAddress;

    private DataModuleOptions ( final boolean ignoreBackgroundScan, final Byte causeSourceAddress )
    {
        this.ignoreBackgroundScan = ignoreBackgroundScan;
        this.causeSourceAddress = causeSourceAddress;
        this.delayStart = false;
    }

    private DataModuleOptions ( final boolean ignoreBackgroundScan, final Byte causeSourceAddress, final boolean delayStart )
    {
        this.ignoreBackgroundScan = ignoreBackgroundScan;
        this.causeSourceAddress = causeSourceAddress;
        this.delayStart = delayStart;
    }

    public boolean isIgnoreBackgroundScan ()
    {
        return this.ignoreBackgroundScan;
    }

    public Byte getCauseSourceAddress ()
    {
        return this.causeSourceAddress;
    }

    public boolean isDelayStart ()
    {
        return delayStart;
    }

    public static class Builder extends AbstractPropertyChange implements Serializable
    {
        private static final long serialVersionUID = 1L;

        public static final String PROP_IGNORE_BACKGROUND_SCAN = "ignoreBackgroundScan";

        public static final String PROP_CAUSE_SOURCE_ADDRESS = "causeSourceAddress";

        public static final String PROP_DELAY_START = "delayStart";

        private boolean ignoreBackgroundScan;

        private boolean delayStart;

        private Byte causeSourceAddress;

        public Builder ()
        {
        }

        public Builder ( final DataModuleOptions options )
        {
            this.causeSourceAddress = options.getCauseSourceAddress ();
            this.ignoreBackgroundScan = options.isIgnoreBackgroundScan ();
            this.delayStart = options.isDelayStart ();
        }

        public void setCauseSourceAddress ( final Byte causeSourceAddress )
        {
            firePropertyChange ( PROP_CAUSE_SOURCE_ADDRESS, this.causeSourceAddress, this.causeSourceAddress = causeSourceAddress );
        }

        public Byte getCauseSourceAddress ()
        {
            return this.causeSourceAddress;
        }

        public void setIgnoreBackgroundScan ( final boolean ignoreBackgroundScan )
        {
            firePropertyChange ( PROP_IGNORE_BACKGROUND_SCAN, this.ignoreBackgroundScan, this.ignoreBackgroundScan = ignoreBackgroundScan );
        }

        public boolean isIgnoreBackgroundScan ()
        {
            return this.ignoreBackgroundScan;
        }

        public void setDelayStart ( final boolean delayStart )
        {
            firePropertyChange ( PROP_DELAY_START, this.delayStart, this.delayStart = delayStart );
        }

        public boolean isDelayStart ()
        {
            return this.delayStart;
        }

        public DataModuleOptions build ()
        {
            return new DataModuleOptions ( this.ignoreBackgroundScan, this.causeSourceAddress, this.delayStart );
        }
    }
}
