package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzj implements Handler.Callback {
    private final Handler mHandler;
    private final zza zzTD;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzTE = new ArrayList<>();
    final ArrayList<GoogleApiClient.ConnectionCallbacks> zzTF = new ArrayList<>();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzTG = new ArrayList<>();
    private volatile boolean zzTH = false;
    private final AtomicInteger zzTI = new AtomicInteger(0);
    private boolean zzTJ = false;
    private final Object zzoe = new Object();

    public interface zza {
        boolean isConnected();

        Bundle zzjZ();
    }

    public zzj(Looper looper, zza zza2) {
        this.zzTD = zza2;
        this.mHandler = new Handler(looper, this);
    }

    public boolean handleMessage(Message msg) {
        if (msg.what == 1) {
            GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) msg.obj;
            synchronized (this.zzoe) {
                if (this.zzTH && this.zzTD.isConnected() && this.zzTE.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zzTD.zzjZ());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        return false;
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks listener) {
        boolean contains;
        zzv.zzr(listener);
        synchronized (this.zzoe) {
            contains = this.zzTE.contains(listener);
        }
        return contains;
    }

    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener listener) {
        boolean contains;
        zzv.zzr(listener);
        synchronized (this.zzoe) {
            contains = this.zzTG.contains(listener);
        }
        return contains;
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        zzv.zzr(listener);
        synchronized (this.zzoe) {
            if (this.zzTE.contains(listener)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + listener + " is already registered");
            } else {
                this.zzTE.add(listener);
            }
        }
        if (this.zzTD.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, listener));
        }
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        zzv.zzr(listener);
        synchronized (this.zzoe) {
            if (this.zzTG.contains(listener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + listener + " is already registered");
            } else {
                this.zzTG.add(listener);
            }
        }
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        zzv.zzr(listener);
        synchronized (this.zzoe) {
            if (!this.zzTE.remove(listener)) {
                Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + listener + " not found");
            } else if (this.zzTJ) {
                this.zzTF.add(listener);
            }
        }
    }

    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        zzv.zzr(listener);
        synchronized (this.zzoe) {
            if (!this.zzTG.remove(listener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + listener + " not found");
            }
        }
    }

    public void zzaP(int i) {
        this.mHandler.removeMessages(1);
        synchronized (this.zzoe) {
            this.zzTJ = true;
            ArrayList arrayList = new ArrayList(this.zzTE);
            int i2 = this.zzTI.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.zzTH || this.zzTI.get() != i2) {
                    break;
                } else if (this.zzTE.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.zzTF.clear();
            this.zzTJ = false;
        }
    }

    public void zzg(Bundle bundle) {
        boolean z = true;
        synchronized (this.zzoe) {
            zzv.zzP(!this.zzTJ);
            this.mHandler.removeMessages(1);
            this.zzTJ = true;
            if (this.zzTF.size() != 0) {
                z = false;
            }
            zzv.zzP(z);
            ArrayList arrayList = new ArrayList(this.zzTE);
            int i = this.zzTI.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.zzTH || !this.zzTD.isConnected() || this.zzTI.get() != i) {
                    break;
                } else if (!this.zzTF.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.zzTF.clear();
            this.zzTJ = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzj(com.google.android.gms.common.ConnectionResult r6) {
        /*
            r5 = this;
            android.os.Handler r0 = r5.mHandler
            r1 = 1
            r0.removeMessages(r1)
            java.lang.Object r1 = r5.zzoe
            monitor-enter(r1)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0040 }
            java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener> r2 = r5.zzTG     // Catch:{ all -> 0x0040 }
            r0.<init>(r2)     // Catch:{ all -> 0x0040 }
            java.util.concurrent.atomic.AtomicInteger r2 = r5.zzTI     // Catch:{ all -> 0x0040 }
            int r2 = r2.get()     // Catch:{ all -> 0x0040 }
            java.util.Iterator r3 = r0.iterator()     // Catch:{ all -> 0x0040 }
        L_0x001a:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0043
            java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x0040 }
            com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener r0 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r0     // Catch:{ all -> 0x0040 }
            boolean r4 = r5.zzTH     // Catch:{ all -> 0x0040 }
            if (r4 == 0) goto L_0x0032
            java.util.concurrent.atomic.AtomicInteger r4 = r5.zzTI     // Catch:{ all -> 0x0040 }
            int r4 = r4.get()     // Catch:{ all -> 0x0040 }
            if (r4 == r2) goto L_0x0034
        L_0x0032:
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
        L_0x0033:
            return
        L_0x0034:
            java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener> r4 = r5.zzTG     // Catch:{ all -> 0x0040 }
            boolean r4 = r4.contains(r0)     // Catch:{ all -> 0x0040 }
            if (r4 == 0) goto L_0x001a
            r0.onConnectionFailed(r6)     // Catch:{ all -> 0x0040 }
            goto L_0x001a
        L_0x0040:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            throw r0
        L_0x0043:
            monitor-exit(r1)     // Catch:{ all -> 0x0040 }
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzj.zzj(com.google.android.gms.common.ConnectionResult):void");
    }

    public void zzmf() {
        this.zzTH = false;
        this.zzTI.incrementAndGet();
    }

    public void zzmg() {
        this.zzTH = true;
    }

    /* access modifiers changed from: protected */
    public void zzmh() {
        synchronized (this.zzoe) {
            zzg(this.zzTD.zzjZ());
        }
    }
}
