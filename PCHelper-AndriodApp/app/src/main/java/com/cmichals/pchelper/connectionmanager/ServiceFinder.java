package com.cmichals.pchelper.connectionmanager;

import android.content.Context;
import android.net.nsd.NsdManager;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Chet Michals on 4/23/2017.
 */

public class ServiceFinder {

    private static String SERVICE_TYPE = "_json.tcp.local";
    static Object FIND_SERVICE_SEMAPHORE = new Object();

    public Socket find(Context context) throws IOException {
        NsdManager mNsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
        NsdManager.DiscoveryListener discoveryListener = new Discover(mNsdManager);
        mNsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
        try {
            FIND_SERVICE_SEMAPHORE.notify();
            FIND_SERVICE_SEMAPHORE.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Socket returnSocket = new Socket(Resolver.resolvedHost, Resolver.resovledPort);
        return returnSocket;
    }
}
