package org.dbdoclet.trafo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class TrafoActivator implements BundleActivator {

    private BundleContext context;

    public void start(BundleContext context) throws Exception {
        System.out.println("Starting TrafoActivator...");
        this.context = context;
    }

    public void stop(BundleContext context) throws Exception {
        this.context = null;
    }

    public BundleContext getContext() {
        return context;
    }

}
