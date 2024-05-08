package com.google.android.gms.common.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.zzv;

public final class zzi<L> {
    private volatile L mListener;
    private final zzi<L>.zza zzQQ;

    private final class zza extends Handler {
        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            boolean z = true;
            if (msg.what != 1) {
                z = false;
            }
            zzv.zzQ(z);
            zzi.this.zzb((zzb) msg.obj);
        }
    }

    public interface zzb<L> {
        void zzk(L l);

        void zzkJ();
    }

    zzi(Looper looper, L l) {
        this.zzQQ = new zza(looper);
        this.mListener = zzv.zzb(l, (Object) "Listener must not be null");
    }

    public void clear() {
        this.mListener = null;
    }

    public void zza(zzb<? super L> zzb2) {
        zzv.zzb(zzb2, (Object) "Notifier must not be null");
        this.zzQQ.sendMessage(this.zzQQ.obtainMessage(1, zzb2));
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzb<? super L> zzb2) {
        L l = this.mListener;
        if (l == null) {
            zzb2.zzkJ();
            return;
        }
        try {
            zzb2.zzk(l);
        } catch (RuntimeException e) {
            zzb2.zzkJ();
            throw e;
        }
    }
}
