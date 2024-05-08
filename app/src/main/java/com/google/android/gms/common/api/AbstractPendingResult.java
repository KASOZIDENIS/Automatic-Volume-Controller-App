package com.google.android.gms.common.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.zzv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPendingResult<R extends Result> implements PendingResult<R> {
    protected final CallbackHandler<R> mHandler;
    private final Object zzPd = new Object();
    private final ArrayList<PendingResult.BatchCallback> zzPe = new ArrayList<>();
    private ResultCallback<R> zzPf;
    private volatile R zzPg;
    private volatile boolean zzPh;
    private boolean zzPi;
    private boolean zzPj;
    private ICancelToken zzPk;
    private final CountDownLatch zzns = new CountDownLatch(1);

    public static class CallbackHandler<R extends Result> extends Handler {
        public static final int CALLBACK_ON_COMPLETE = 1;
        public static final int CALLBACK_ON_TIMEOUT = 2;

        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        /* access modifiers changed from: protected */
        public void deliverResultCallback(ResultCallback<R> callback, R result) {
            try {
                callback.onResult(result);
            } catch (RuntimeException e) {
                AbstractPendingResult.zzb(result);
                throw e;
            }
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Pair pair = (Pair) msg.obj;
                    deliverResultCallback((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((AbstractPendingResult) msg.obj).forceFailureUnlessReady(Status.zzQX);
                    return;
                default:
                    Log.wtf("AbstractPendingResult", "Don't know how to handle this message.");
                    return;
            }
        }

        public void removeTimeoutMessages() {
            removeMessages(2);
        }

        public void sendResultCallback(ResultCallback<R> callback, R result) {
            sendMessage(obtainMessage(1, new Pair(callback, result)));
        }

        public void sendTimeoutResultCallback(AbstractPendingResult<R> pendingResult, long millis) {
            sendMessageDelayed(obtainMessage(2, pendingResult), millis);
        }
    }

    protected AbstractPendingResult(Looper looper) {
        this.mHandler = new CallbackHandler<>(looper);
    }

    protected AbstractPendingResult(CallbackHandler<R> callbackHandler) {
        this.mHandler = callbackHandler;
    }

    private void zza(R r) {
        this.zzPg = r;
        this.zzPk = null;
        this.zzns.countDown();
        Status status = this.zzPg.getStatus();
        if (this.zzPf != null) {
            this.mHandler.removeTimeoutMessages();
            if (!this.zzPi) {
                this.mHandler.sendResultCallback(this.zzPf, zzkB());
            }
        }
        Iterator<PendingResult.BatchCallback> it = this.zzPe.iterator();
        while (it.hasNext()) {
            it.next().zzl(status);
        }
        this.zzPe.clear();
    }

    static void zzb(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                Log.w("AbstractPendingResult", "Unable to release " + result, e);
            }
        }
    }

    private R zzkB() {
        R r;
        boolean z = true;
        synchronized (this.zzPd) {
            if (this.zzPh) {
                z = false;
            }
            zzv.zza(z, (Object) "Result has already been consumed.");
            zzv.zza(isReady(), (Object) "Result is not ready.");
            r = this.zzPg;
            this.zzPg = null;
            this.zzPf = null;
            this.zzPh = true;
        }
        onResultConsumed();
        return r;
    }

    public final void addBatchCallback(PendingResult.BatchCallback callback) {
        zzv.zza(!this.zzPh, (Object) "Result has already been consumed.");
        synchronized (this.zzPd) {
            if (isReady()) {
                callback.zzl(this.zzPg.getStatus());
            } else {
                this.zzPe.add(callback);
            }
        }
    }

    public final R await() {
        boolean z = true;
        zzv.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread");
        if (this.zzPh) {
            z = false;
        }
        zzv.zza(z, (Object) "Result has already been consumed");
        try {
            this.zzns.await();
        } catch (InterruptedException e) {
            forceFailureUnlessReady(Status.zzQV);
        }
        zzv.zza(isReady(), (Object) "Result is not ready.");
        return zzkB();
    }

    public final R await(long time, TimeUnit units) {
        boolean z = true;
        zzv.zza(time <= 0 || Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread when time is greater than zero.");
        if (this.zzPh) {
            z = false;
        }
        zzv.zza(z, (Object) "Result has already been consumed.");
        try {
            if (!this.zzns.await(time, units)) {
                forceFailureUnlessReady(Status.zzQX);
            }
        } catch (InterruptedException e) {
            forceFailureUnlessReady(Status.zzQV);
        }
        zzv.zza(isReady(), (Object) "Result is not ready.");
        return zzkB();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r2 = this;
            java.lang.Object r1 = r2.zzPd
            monitor-enter(r1)
            boolean r0 = r2.zzPi     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x000b
            boolean r0 = r2.zzPh     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r1)     // Catch:{ all -> 0x002c }
        L_0x000c:
            return
        L_0x000d:
            com.google.android.gms.common.internal.ICancelToken r0 = r2.zzPk     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.common.internal.ICancelToken r0 = r2.zzPk     // Catch:{ RemoteException -> 0x002f }
            r0.cancel()     // Catch:{ RemoteException -> 0x002f }
        L_0x0016:
            R r0 = r2.zzPg     // Catch:{ all -> 0x002c }
            zzb(r0)     // Catch:{ all -> 0x002c }
            r0 = 0
            r2.zzPf = r0     // Catch:{ all -> 0x002c }
            r0 = 1
            r2.zzPi = r0     // Catch:{ all -> 0x002c }
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.zzQY     // Catch:{ all -> 0x002c }
            com.google.android.gms.common.api.Result r0 = r2.createFailedResult(r0)     // Catch:{ all -> 0x002c }
            r2.zza(r0)     // Catch:{ all -> 0x002c }
            monitor-exit(r1)     // Catch:{ all -> 0x002c }
            goto L_0x000c
        L_0x002c:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002c }
            throw r0
        L_0x002f:
            r0 = move-exception
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.AbstractPendingResult.cancel():void");
    }

    /* access modifiers changed from: protected */
    public abstract R createFailedResult(Status status);

    public final void forceFailureUnlessReady(Status status) {
        synchronized (this.zzPd) {
            if (!isReady()) {
                setResult(createFailedResult(status));
                this.zzPj = true;
            }
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zzPd) {
            z = this.zzPi;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzns.getCount() == 0;
    }

    /* access modifiers changed from: protected */
    public void onResultConsumed() {
    }

    /* access modifiers changed from: protected */
    public final void setCancelToken(ICancelToken cancelToken) {
        synchronized (this.zzPd) {
            this.zzPk = cancelToken;
        }
    }

    public final void setResult(R result) {
        boolean z = true;
        synchronized (this.zzPd) {
            if (this.zzPj || this.zzPi) {
                zzb(result);
                return;
            }
            zzv.zza(!isReady(), (Object) "Results have already been set");
            if (this.zzPh) {
                z = false;
            }
            zzv.zza(z, (Object) "Result has already been consumed");
            zza(result);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<R> r4) {
        /*
            r3 = this;
            boolean r0 = r3.zzPh
            if (r0 != 0) goto L_0x0015
            r0 = 1
        L_0x0005:
            java.lang.String r1 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzv.zza((boolean) r0, (java.lang.Object) r1)
            java.lang.Object r1 = r3.zzPd
            monitor-enter(r1)
            boolean r0 = r3.isCanceled()     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0017
            monitor-exit(r1)     // Catch:{ all -> 0x0028 }
        L_0x0014:
            return
        L_0x0015:
            r0 = 0
            goto L_0x0005
        L_0x0017:
            boolean r0 = r3.isReady()     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x002b
            com.google.android.gms.common.api.AbstractPendingResult$CallbackHandler<R> r0 = r3.mHandler     // Catch:{ all -> 0x0028 }
            com.google.android.gms.common.api.Result r2 = r3.zzkB()     // Catch:{ all -> 0x0028 }
            r0.sendResultCallback(r4, r2)     // Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r1)     // Catch:{ all -> 0x0028 }
            goto L_0x0014
        L_0x0028:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0028 }
            throw r0
        L_0x002b:
            r3.zzPf = r4     // Catch:{ all -> 0x0028 }
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.AbstractPendingResult.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<R> r5, long r6, java.util.concurrent.TimeUnit r8) {
        /*
            r4 = this;
            r1 = 1
            r2 = 0
            boolean r0 = r4.zzPh
            if (r0 != 0) goto L_0x0020
            r0 = r1
        L_0x0007:
            java.lang.String r3 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzv.zza((boolean) r0, (java.lang.Object) r3)
            com.google.android.gms.common.api.AbstractPendingResult$CallbackHandler<R> r0 = r4.mHandler
            if (r0 == 0) goto L_0x0022
        L_0x0010:
            java.lang.String r0 = "CallbackHandler has not been set before calling setResultCallback."
            com.google.android.gms.common.internal.zzv.zza((boolean) r1, (java.lang.Object) r0)
            java.lang.Object r1 = r4.zzPd
            monitor-enter(r1)
            boolean r0 = r4.isCanceled()     // Catch:{ all -> 0x0035 }
            if (r0 == 0) goto L_0x0024
            monitor-exit(r1)     // Catch:{ all -> 0x0035 }
        L_0x001f:
            return
        L_0x0020:
            r0 = r2
            goto L_0x0007
        L_0x0022:
            r1 = r2
            goto L_0x0010
        L_0x0024:
            boolean r0 = r4.isReady()     // Catch:{ all -> 0x0035 }
            if (r0 == 0) goto L_0x0038
            com.google.android.gms.common.api.AbstractPendingResult$CallbackHandler<R> r0 = r4.mHandler     // Catch:{ all -> 0x0035 }
            com.google.android.gms.common.api.Result r2 = r4.zzkB()     // Catch:{ all -> 0x0035 }
            r0.sendResultCallback(r5, r2)     // Catch:{ all -> 0x0035 }
        L_0x0033:
            monitor-exit(r1)     // Catch:{ all -> 0x0035 }
            goto L_0x001f
        L_0x0035:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0035 }
            throw r0
        L_0x0038:
            r4.zzPf = r5     // Catch:{ all -> 0x0035 }
            com.google.android.gms.common.api.AbstractPendingResult$CallbackHandler<R> r0 = r4.mHandler     // Catch:{ all -> 0x0035 }
            long r2 = r8.toMillis(r6)     // Catch:{ all -> 0x0035 }
            r0.sendTimeoutResultCallback(r4, r2)     // Catch:{ all -> 0x0035 }
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.AbstractPendingResult.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }
}
