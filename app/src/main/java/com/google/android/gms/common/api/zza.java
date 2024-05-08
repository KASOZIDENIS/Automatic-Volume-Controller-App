package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.zzg;
import com.google.android.gms.common.internal.zzv;
import java.util.concurrent.atomic.AtomicReference;

public class zza {

    /* renamed from: com.google.android.gms.common.api.zza$zza  reason: collision with other inner class name */
    public static abstract class C0001zza<R extends Result, A extends Api.zza> extends AbstractPendingResult<R> implements zzb<R>, zzg.zze<A> {
        private final Api.zzc<A> zzPn;
        private AtomicReference<zzg.zzc> zzPq = new AtomicReference<>();

        protected C0001zza(Api.zzc<A> zzc, GoogleApiClient googleApiClient) {
            super(((GoogleApiClient) zzv.zzb(googleApiClient, (Object) "GoogleApiClient must not be null")).getLooper());
            this.zzPn = (Api.zzc) zzv.zzr(zzc);
        }

        private void zza(RemoteException remoteException) {
            zzk(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
        }

        /* access modifiers changed from: protected */
        public void onResultConsumed() {
            zzg.zzc andSet = this.zzPq.getAndSet((Object) null);
            if (andSet != null) {
                andSet.zzc(this);
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zza(A a) throws RemoteException;

        public void zza(zzg.zzc zzc) {
            this.zzPq.set(zzc);
        }

        public final void zzb(A a) throws DeadObjectException {
            try {
                zza(a);
            } catch (DeadObjectException e) {
                zza((RemoteException) e);
                throw e;
            } catch (RemoteException e2) {
                zza(e2);
            }
        }

        public /* synthetic */ void zzj(Object obj) {
            super.setResult((Result) obj);
        }

        public final void zzk(Status status) {
            zzv.zzb(!status.isSuccess(), (Object) "Failed result must not be success");
            setResult(createFailedResult(status));
        }

        public final Api.zzc<A> zzkF() {
            return this.zzPn;
        }

        public int zzkI() {
            return 0;
        }
    }

    public interface zzb<R> {
        void zzj(R r);

        void zzk(Status status);
    }
}
