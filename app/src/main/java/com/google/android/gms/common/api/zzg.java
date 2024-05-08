package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzhi;
import com.google.android.gms.internal.zzmd;
import com.google.android.gms.internal.zzme;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class zzg implements GoogleApiClient {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final int zzPH;
    private final int zzPI;
    final Api.zzb<? extends zzmd, zzme> zzPK;
    /* access modifiers changed from: private */
    public final Lock zzPR = new ReentrantLock();
    private final Looper zzPx;
    final Map<Api.zzc<?>, ConnectionResult> zzQA = new HashMap();
    Set<Scope> zzQB = new HashSet();
    /* access modifiers changed from: private */
    public volatile zzh zzQC;
    private ConnectionResult zzQD = null;
    private final Set<zzi<?>> zzQE = Collections.newSetFromMap(new WeakHashMap());
    final Set<zze<?>> zzQF = Collections.newSetFromMap(new ConcurrentHashMap(16, 0.75f, 2));
    private final zzc zzQG = new zzc() {
        public void zzc(zze<?> zze) {
            zzg.this.zzQF.remove(zze);
        }
    };
    private final GoogleApiClient.ConnectionCallbacks zzQH = new zzd() {
        public void onConnected(Bundle connectionHint) {
            zzg.this.zzQC.onConnected(connectionHint);
        }
    };
    private final zzj.zza zzQI = new zzj.zza() {
        public boolean isConnected() {
            return zzg.this.isConnected();
        }

        public Bundle zzjZ() {
            return null;
        }
    };
    final com.google.android.gms.common.internal.zze zzQg;
    final Map<Api<?>, Integer> zzQh;
    private final Condition zzQr;
    final zzj zzQs;
    final Queue<zze<?>> zzQt = new LinkedList();
    /* access modifiers changed from: private */
    public volatile boolean zzQu;
    /* access modifiers changed from: private */
    public long zzQv = 120000;
    /* access modifiers changed from: private */
    public long zzQw = 5000;
    final zza zzQx;
    BroadcastReceiver zzQy;
    final Map<Api.zzc<?>, Api.zza> zzQz = new HashMap();

    final class zza extends Handler {
        zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    zzg.this.zzlc();
                    return;
                case 2:
                    zzg.this.resume();
                    return;
                default:
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + msg.what);
                    return;
            }
        }
    }

    private static class zzb extends BroadcastReceiver {
        private WeakReference<zzg> zzQP;

        zzb(zzg zzg) {
            this.zzQP = new WeakReference<>(zzg);
        }

        public void onReceive(Context context, Intent intent) {
            zzg zzg;
            Uri data = intent.getData();
            String str = null;
            if (data != null) {
                str = data.getSchemeSpecificPart();
            }
            if (str != null && str.equals("com.google.android.gms") && (zzg = (zzg) this.zzQP.get()) != null) {
                zzg.resume();
            }
        }
    }

    interface zzc {
        void zzc(zze<?> zze);
    }

    public abstract class zzd implements GoogleApiClient.ConnectionCallbacks {
        public zzd() {
        }

        public void onConnectionSuspended(int cause) {
            zzg.this.zzPR.lock();
            try {
                if (!(zzg.this.zzQC instanceof zzf)) {
                    switch (cause) {
                        case 1:
                            if (!zzg.this.zzlb()) {
                                boolean unused = zzg.this.zzQu = true;
                                if (zzg.this.zzQy == null) {
                                    zzg.this.zzQy = new zzb(zzg.this);
                                    IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
                                    intentFilter.addDataScheme("package");
                                    zzg.this.mContext.getApplicationContext().registerReceiver(zzg.this.zzQy, intentFilter);
                                }
                                zzg.this.zzQx.sendMessageDelayed(zzg.this.zzQx.obtainMessage(1), zzg.this.zzQv);
                                zzg.this.zzQx.sendMessageDelayed(zzg.this.zzQx.obtainMessage(2), zzg.this.zzQw);
                                zzg.this.zzav(cause);
                                break;
                            } else {
                                zzg.this.zzPR.unlock();
                                return;
                            }
                        case 2:
                            zzg.this.zzav(cause);
                            zzg.this.connect();
                            break;
                    }
                    zzg.this.zzPR.unlock();
                }
            } finally {
                zzg.this.zzPR.unlock();
            }
        }
    }

    interface zze<A extends Api.zza> {
        void cancel();

        void forceFailureUnlessReady(Status status);

        void zza(zzc zzc);

        void zzb(A a) throws DeadObjectException;

        void zzk(Status status);

        Api.zzc<A> zzkF();

        int zzkI();
    }

    public zzg(Context context, Looper looper, com.google.android.gms.common.internal.zze zze2, Api.zzb<? extends zzmd, zzme> zzb2, Map<Api<?>, Api.ApiOptions> map, Set<GoogleApiClient.ConnectionCallbacks> set, Set<GoogleApiClient.OnConnectionFailedListener> set2, int i, int i2) {
        int i3;
        this.mContext = context;
        this.zzQs = new zzj(looper, this.zzQI);
        this.zzPx = looper;
        this.zzQx = new zza(looper);
        this.zzPH = i;
        this.zzPI = i2;
        this.zzQh = new HashMap();
        this.zzQr = this.zzPR.newCondition();
        this.zzQC = new zzf(this);
        for (GoogleApiClient.ConnectionCallbacks registerConnectionCallbacks : set) {
            this.zzQs.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (GoogleApiClient.OnConnectionFailedListener registerConnectionFailedListener : set2) {
            this.zzQs.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        Map<Api<?>, zze.zza> zzlI = zze2.zzlI();
        for (Api next : map.keySet()) {
            Api.ApiOptions apiOptions = map.get(next);
            if (zzlI.get(next) != null) {
                i3 = zzlI.get(next).zzTa ? 1 : 2;
            } else {
                i3 = 0;
            }
            this.zzQh.put(next, Integer.valueOf(i3));
            this.zzQz.put(next.zzkF(), next.zzkG() ? zza(next.zzkD(), (Object) apiOptions, context, looper, zze2, this.zzQH, zza((Api<?>) next, i3)) : zza(next.zzkC(), (Object) apiOptions, context, looper, zze2, this.zzQH, zza((Api<?>) next, i3)));
        }
        this.zzQg = zze2;
        this.zzPK = zzb2;
    }

    /* access modifiers changed from: private */
    public void resume() {
        this.zzPR.lock();
        try {
            if (zzlb()) {
                connect();
            }
        } finally {
            this.zzPR.unlock();
        }
    }

    private static <C extends Api.zza, O> C zza(Api.zzb<C, O> zzb2, Object obj, Context context, Looper looper, com.google.android.gms.common.internal.zze zze2, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return zzb2.zza(context, looper, zze2, obj, connectionCallbacks, onConnectionFailedListener);
    }

    private final GoogleApiClient.OnConnectionFailedListener zza(final Api<?> api, final int i) {
        return new GoogleApiClient.OnConnectionFailedListener() {
            public void onConnectionFailed(ConnectionResult result) {
                zzg.this.zzQC.zza(result, api, i);
            }
        };
    }

    private static <C extends Api.zzd, O> zzaa zza(Api.zze<C, O> zze2, Object obj, Context context, Looper looper, com.google.android.gms.common.internal.zze zze3, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzaa(context, looper, zze2.zzkH(), connectionCallbacks, onConnectionFailedListener, zze3, zze2.zzi(obj));
    }

    /* access modifiers changed from: private */
    public void zza(final GoogleApiClient googleApiClient, final zzk zzk, final boolean z) {
        zzhi.zzUh.zzc(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            /* renamed from: zzi */
            public void onResult(Status status) {
                if (status.isSuccess() && zzg.this.isConnected()) {
                    zzg.this.reconnect();
                }
                zzk.setResult(status);
                if (z) {
                    googleApiClient.disconnect();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void zzav(int i) {
        this.zzPR.lock();
        try {
            this.zzQC.zzas(i);
        } finally {
            this.zzPR.unlock();
        }
    }

    /* access modifiers changed from: private */
    public void zzlc() {
        this.zzPR.lock();
        try {
            if (zzld()) {
                connect();
            }
        } finally {
            this.zzPR.unlock();
        }
    }

    public ConnectionResult blockingConnect() {
        ConnectionResult connectionResult;
        zzv.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        this.zzPR.lock();
        try {
            connect();
            while (isConnecting()) {
                this.zzQr.await();
            }
            if (isConnected()) {
                connectionResult = ConnectionResult.zzOI;
            } else if (this.zzQD != null) {
                connectionResult = this.zzQD;
                this.zzPR.unlock();
            } else {
                connectionResult = new ConnectionResult(13, (PendingIntent) null);
                this.zzPR.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            connectionResult = new ConnectionResult(15, (PendingIntent) null);
        } finally {
            this.zzPR.unlock();
        }
        return connectionResult;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0058, code lost:
        if (isConnected() == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        r0 = com.google.android.gms.common.ConnectionResult.zzOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        if (r5.zzQD == null) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0066, code lost:
        r0 = r5.zzQD;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
        r5.zzPR.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r0 = new com.google.android.gms.common.ConnectionResult(13, (android.app.PendingIntent) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0076, code lost:
        r5.zzPR.unlock();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.common.ConnectionResult blockingConnect(long r6, java.util.concurrent.TimeUnit r8) {
        /*
            r5 = this;
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            if (r0 == r1) goto L_0x003c
            r0 = 1
        L_0x000b:
            java.lang.String r1 = "blockingConnect must not be called on the UI thread"
            com.google.android.gms.common.internal.zzv.zza((boolean) r0, (java.lang.Object) r1)
            java.util.concurrent.locks.Lock r0 = r5.zzPR
            r0.lock()
            r5.connect()     // Catch:{ all -> 0x007c }
            long r0 = r8.toNanos(r6)     // Catch:{ all -> 0x007c }
        L_0x001c:
            boolean r2 = r5.isConnecting()     // Catch:{ all -> 0x007c }
            if (r2 == 0) goto L_0x0054
            java.util.concurrent.locks.Condition r2 = r5.zzQr     // Catch:{ InterruptedException -> 0x003e }
            long r0 = r2.awaitNanos(r0)     // Catch:{ InterruptedException -> 0x003e }
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x001c
            com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult     // Catch:{ InterruptedException -> 0x003e }
            r1 = 14
            r2 = 0
            r0.<init>(r1, r2)     // Catch:{ InterruptedException -> 0x003e }
            java.util.concurrent.locks.Lock r1 = r5.zzPR
            r1.unlock()
        L_0x003b:
            return r0
        L_0x003c:
            r0 = 0
            goto L_0x000b
        L_0x003e:
            r0 = move-exception
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x007c }
            r0.interrupt()     // Catch:{ all -> 0x007c }
            com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult     // Catch:{ all -> 0x007c }
            r1 = 15
            r2 = 0
            r0.<init>(r1, r2)     // Catch:{ all -> 0x007c }
            java.util.concurrent.locks.Lock r1 = r5.zzPR
            r1.unlock()
            goto L_0x003b
        L_0x0054:
            boolean r0 = r5.isConnected()     // Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x0062
            com.google.android.gms.common.ConnectionResult r0 = com.google.android.gms.common.ConnectionResult.zzOI     // Catch:{ all -> 0x007c }
            java.util.concurrent.locks.Lock r1 = r5.zzPR
            r1.unlock()
            goto L_0x003b
        L_0x0062:
            com.google.android.gms.common.ConnectionResult r0 = r5.zzQD     // Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x006e
            com.google.android.gms.common.ConnectionResult r0 = r5.zzQD     // Catch:{ all -> 0x007c }
            java.util.concurrent.locks.Lock r1 = r5.zzPR
            r1.unlock()
            goto L_0x003b
        L_0x006e:
            com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult     // Catch:{ all -> 0x007c }
            r1 = 13
            r2 = 0
            r0.<init>(r1, r2)     // Catch:{ all -> 0x007c }
            java.util.concurrent.locks.Lock r1 = r5.zzPR
            r1.unlock()
            goto L_0x003b
        L_0x007c:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r5.zzPR
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.zzg.blockingConnect(long, java.util.concurrent.TimeUnit):com.google.android.gms.common.ConnectionResult");
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzv.zza(isConnected(), (Object) "GoogleApiClient is not connected yet.");
        final zzk zzk = new zzk(this.zzPx);
        if (this.zzQz.containsKey(zzhi.zzKh)) {
            zza(this, zzk, false);
        } else {
            final AtomicReference atomicReference = new AtomicReference();
            AnonymousClass5 r2 = new GoogleApiClient.ConnectionCallbacks() {
                public void onConnected(Bundle connectionHint) {
                    zzg.this.zza((GoogleApiClient) atomicReference.get(), zzk, true);
                }

                public void onConnectionSuspended(int cause) {
                }
            };
            GoogleApiClient build = new GoogleApiClient.Builder(this.mContext).addApi(zzhi.API).addConnectionCallbacks(r2).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                public void onConnectionFailed(ConnectionResult result) {
                    zzk.setResult(new Status(8));
                }
            }).setHandler(this.zzQx).build();
            atomicReference.set(build);
            build.connect();
        }
        return zzk;
    }

    public void connect() {
        this.zzPR.lock();
        try {
            this.zzQC.connect();
        } finally {
            this.zzPR.unlock();
        }
    }

    public void disconnect() {
        zzld();
        zzav(-1);
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.append(prefix).println("GoogleApiClient:");
        String str = prefix + "  ";
        writer.append(str).append("mState=").append(this.zzQC.getName());
        writer.append(" mResuming=").print(this.zzQu);
        writer.append(" mWorkQueue.size()=").print(this.zzQt.size());
        writer.append(" mUnconsumedRunners.size()=").println(this.zzQF.size());
        for (Api.zza dump : this.zzQz.values()) {
            dump.dump(str, fd, writer, args);
        }
    }

    public ConnectionResult getConnectionResult(Api<?> api) {
        ConnectionResult connectionResult;
        Api.zzc<?> zzkF = api.zzkF();
        this.zzPR.lock();
        try {
            if (!isConnected() && !zzlb()) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            } else if (this.zzQz.containsKey(zzkF)) {
                if (this.zzQz.get(zzkF).isConnected()) {
                    connectionResult = ConnectionResult.zzOI;
                } else if (this.zzQA.containsKey(zzkF)) {
                    connectionResult = this.zzQA.get(zzkF);
                    this.zzPR.unlock();
                } else {
                    Log.wtf("GoogleApiClientImpl", api.getName() + " requested in getConnectionResult" + " is not connected but is not present in the failed connections map");
                    connectionResult = new ConnectionResult(8, (PendingIntent) null);
                    this.zzPR.unlock();
                }
                return connectionResult;
            } else {
                this.zzPR.unlock();
                throw new IllegalArgumentException(api.getName() + " was never registered with GoogleApiClient");
            }
        } finally {
            this.zzPR.unlock();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public Looper getLooper() {
        return this.zzPx;
    }

    public int getSessionId() {
        return System.identityHashCode(this);
    }

    public boolean hasConnectedApi(Api<?> api) {
        Api.zza zza2 = this.zzQz.get(api.zzkF());
        if (zza2 == null) {
            return false;
        }
        return zza2.isConnected();
    }

    public boolean isConnected() {
        return this.zzQC instanceof zzd;
    }

    public boolean isConnecting() {
        return this.zzQC instanceof zze;
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks listener) {
        return this.zzQs.isConnectionCallbacksRegistered(listener);
    }

    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener listener) {
        return this.zzQs.isConnectionFailedListenerRegistered(listener);
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        this.zzQs.registerConnectionCallbacks(listener);
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        this.zzQs.registerConnectionFailedListener(listener);
    }

    public void stopAutoManage(FragmentActivity lifecycleActivity) {
        if (this.zzPH >= 0) {
            zzl.zza(lifecycleActivity).zzax(this.zzPH);
        } else if (this.zzPI >= 0) {
            zzm.zzb(lifecycleActivity).zzax(this.zzPI);
        } else {
            throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
        }
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        this.zzQs.unregisterConnectionCallbacks(listener);
    }

    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        this.zzQs.unregisterConnectionFailedListener(listener);
    }

    public <C extends Api.zza> C zza(Api.zzc<C> zzc2) {
        C c = (Api.zza) this.zzQz.get(zzc2);
        zzv.zzb(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public <A extends Api.zza, R extends Result, T extends zza.C0001zza<R, A>> T zza(T t) {
        zzv.zzb(t.zzkF() != null, (Object) "This task can not be enqueued (it's probably a Batch or malformed)");
        zzv.zzb(this.zzQz.containsKey(t.zzkF()), (Object) "GoogleApiClient is not configured to use the API required for this call.");
        this.zzPR.lock();
        try {
            return this.zzQC.zza(t);
        } finally {
            this.zzPR.unlock();
        }
    }

    public boolean zza(Api<?> api) {
        return this.zzQz.containsKey(api.zzkF());
    }

    public boolean zza(Scope scope) {
        this.zzPR.lock();
        try {
            return isConnected() && this.zzQB.contains(scope);
        } finally {
            this.zzPR.unlock();
        }
    }

    public <A extends Api.zza, T extends zza.C0001zza<? extends Result, A>> T zzb(T t) {
        zzv.zzb(t.zzkF() != null, (Object) "This task can not be executed (it's probably a Batch or malformed)");
        this.zzPR.lock();
        try {
            if (zzlb()) {
                this.zzQt.add(t);
                while (!this.zzQt.isEmpty()) {
                    zze remove = this.zzQt.remove();
                    zzb(remove);
                    remove.zzk(Status.zzQW);
                }
            } else {
                t = this.zzQC.zzb(t);
                this.zzPR.unlock();
            }
            return t;
        } finally {
            this.zzPR.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public <A extends Api.zza> void zzb(zze<A> zze2) {
        this.zzQF.add(zze2);
        zze2.zza(this.zzQG);
    }

    /* access modifiers changed from: package-private */
    public void zzg(ConnectionResult connectionResult) {
        this.zzPR.lock();
        try {
            this.zzQD = connectionResult;
            this.zzQC = new zzf(this);
            this.zzQC.begin();
            this.zzQr.signalAll();
        } finally {
            this.zzPR.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzkX() {
        for (zze next : this.zzQF) {
            next.zza((zzc) null);
            next.cancel();
        }
        this.zzQF.clear();
        for (zzi<?> clear : this.zzQE) {
            clear.clear();
        }
        this.zzQE.clear();
        this.zzQB.clear();
    }

    /* access modifiers changed from: package-private */
    public void zzkY() {
        for (Api.zza disconnect : this.zzQz.values()) {
            disconnect.disconnect();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzkZ() {
        this.zzPR.lock();
        try {
            this.zzQC = new zze(this, this.zzQg, this.zzQh, this.zzPK, this.zzPR, this.mContext);
            this.zzQC.begin();
            this.zzQr.signalAll();
        } finally {
            this.zzPR.unlock();
        }
    }

    public <L> zzi<L> zzl(L l) {
        zzv.zzb(l, (Object) "Listener must not be null");
        this.zzPR.lock();
        try {
            zzi<L> zzi = new zzi<>(this.zzPx, l);
            this.zzQE.add(zzi);
            return zzi;
        } finally {
            this.zzPR.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzla() {
        this.zzPR.lock();
        try {
            zzld();
            this.zzQC = new zzd(this);
            this.zzQC.begin();
            this.zzQr.signalAll();
        } finally {
            this.zzPR.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzlb() {
        return this.zzQu;
    }

    /* access modifiers changed from: package-private */
    public boolean zzld() {
        this.zzPR.lock();
        try {
            if (!zzlb()) {
                return false;
            }
            this.zzQu = false;
            this.zzQx.removeMessages(2);
            this.zzQx.removeMessages(1);
            if (this.zzQy != null) {
                this.mContext.getApplicationContext().unregisterReceiver(this.zzQy);
                this.zzQy = null;
            }
            this.zzPR.unlock();
            return true;
        } finally {
            this.zzPR.unlock();
        }
    }
}
