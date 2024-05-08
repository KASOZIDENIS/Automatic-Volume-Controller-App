package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class zza implements ServiceConnection {
    boolean zzOG = false;
    private final BlockingQueue<IBinder> zzOH = new LinkedBlockingQueue();

    public void onServiceConnected(ComponentName name, IBinder service) {
        this.zzOH.add(service);
    }

    public void onServiceDisconnected(ComponentName name) {
    }

    public IBinder zzku() throws InterruptedException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("BlockingServiceConnection.getService() called on main thread");
        } else if (this.zzOG) {
            throw new IllegalStateException();
        } else {
            this.zzOG = true;
            return this.zzOH.take();
        }
    }
}
