package com.cmichals.pchelper.connectionmanager;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

import java.net.InetAddress;

/**
 * Created by Chet Michals on 4/23/2017.
 */

class Resolver implements NsdManager.ResolveListener {

    static int resovledPort = -1;
    static InetAddress resolvedHost = null;

    private static final String CLASSNAME = Discover.class.getSimpleName();

    @Override
    public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
        Log.e(CLASSNAME, "Resolve failed: " + errorCode);
    }

    @Override
    public void onServiceResolved(NsdServiceInfo serviceInfo) {
        try {
            ServiceFinder.FIND_SERVICE_SEMAPHORE.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(CLASSNAME, "Resolve succeeded: " + serviceInfo.getHost() + ":" + serviceInfo.getPort());
        resolvedHost = serviceInfo.getHost();
        resovledPort = serviceInfo.getPort();
        ServiceFinder.FIND_SERVICE_SEMAPHORE.notify();
    }
}
