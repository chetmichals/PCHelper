package com.cmichals.pchelper.connectionmanager;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

/**
 * Created by Chet Michals on 4/23/2017.
 */

class Discover implements NsdManager.DiscoveryListener {

    private static final String CLASSNAME = Discover.class.getSimpleName();

    private NsdManager mNsdManager;
    private Resolver resolver;

    public Discover(NsdManager mNsdManager) {
        this.mNsdManager = mNsdManager;
    }

    @Override
    public void onStartDiscoveryFailed(String serviceType, int errorCode) {
        Log.e(CLASSNAME, "Discovery start failed: Error code:" + errorCode);
        //mNsdManager.stopServiceDiscovery(this);
    }

    @Override
    public void onStopDiscoveryFailed(String serviceType, int errorCode) {
        Log.e(CLASSNAME, "Discovery stop failed: Error code:" + errorCode);
        mNsdManager.stopServiceDiscovery(this);
    }

    @Override
    public void onDiscoveryStarted(String serviceType) {
        Log.d(CLASSNAME, "Service discovery started");
    }

    @Override
    public void onDiscoveryStopped(String serviceType) {
        Log.i(CLASSNAME, "Discovery stopped: " + serviceType);
    }

    @Override
    public void onServiceFound(NsdServiceInfo serviceInfo) {
        // A service was found!  Do something with it.
        Log.d(CLASSNAME, "Service discovery success: " + serviceInfo.getServiceName());
        Log.d(CLASSNAME, "Service machine: " + serviceInfo.getHost() + ":" + serviceInfo.getPort());
        resolver = new Resolver();
        mNsdManager.resolveService(serviceInfo, resolver);
    }

    @Override
    public void onServiceLost(NsdServiceInfo serviceInfo) {
        Log.e(CLASSNAME, "service lost " + serviceInfo.getServiceName() + "|" + serviceInfo.getHost() + ":" + serviceInfo.getPort());
    }
}
