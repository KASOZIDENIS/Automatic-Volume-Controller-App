package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.common.stats.zzb;
import com.google.android.gms.internal.zzs;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AdvertisingIdClient {
    private final Context mContext;
    com.google.android.gms.common.zza zzmf;
    zzs zzmg;
    boolean zzmh;
    Object zzmi;
    zza zzmj;
    final long zzmk;

    public static final class Info {
        private final String zzmp;
        private final boolean zzmq;

        public Info(String advertisingId, boolean limitAdTrackingEnabled) {
            this.zzmp = advertisingId;
            this.zzmq = limitAdTrackingEnabled;
        }

        public String getId() {
            return this.zzmp;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.zzmq;
        }

        public String toString() {
            return "{" + this.zzmp + "}" + this.zzmq;
        }
    }

    static class zza extends Thread {
        private WeakReference<AdvertisingIdClient> zzml;
        private long zzmm;
        CountDownLatch zzmn = new CountDownLatch(1);
        boolean zzmo = false;

        public zza(AdvertisingIdClient advertisingIdClient, long j) {
            this.zzml = new WeakReference<>(advertisingIdClient);
            this.zzmm = j;
            start();
        }

        private void disconnect() {
            AdvertisingIdClient advertisingIdClient = (AdvertisingIdClient) this.zzml.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.zzmo = true;
            }
        }

        public void cancel() {
            this.zzmn.countDown();
        }

        public void run() {
            try {
                if (!this.zzmn.await(this.zzmm, TimeUnit.MILLISECONDS)) {
                    disconnect();
                }
            } catch (InterruptedException e) {
                disconnect();
            }
        }

        public boolean zzae() {
            return this.zzmo;
        }
    }

    public AdvertisingIdClient(Context context) {
        this(context, 30000);
    }

    public AdvertisingIdClient(Context context, long timeoutInMillis) {
        this.zzmi = new Object();
        zzv.zzr(context);
        this.mContext = context;
        this.zzmh = false;
        this.zzmk = timeoutInMillis;
    }

    public static Info getAdvertisingIdInfo(Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1);
        try {
            advertisingIdClient.zzb(false);
            return advertisingIdClient.getInfo();
        } finally {
            advertisingIdClient.finish();
        }
    }

    static zzs zza(Context context, com.google.android.gms.common.zza zza2) throws IOException {
        try {
            return zzs.zza.zzb(zza2.zzku());
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        }
    }

    private void zzad() {
        synchronized (this.zzmi) {
            if (this.zzmj != null) {
                this.zzmj.cancel();
                try {
                    this.zzmj.join();
                } catch (InterruptedException e) {
                }
            }
            if (this.zzmk > 0) {
                this.zzmj = new zza(this, this.zzmk);
            }
        }
    }

    static com.google.android.gms.common.zza zzj(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE, 0);
            try {
                GooglePlayServicesUtil.zzM(context);
                com.google.android.gms.common.zza zza2 = new com.google.android.gms.common.zza();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (zzb.zznb().zza(context, intent, (ServiceConnection) zza2, 1)) {
                    return zza2;
                }
                throw new IOException("Connection failure");
            } catch (GooglePlayServicesNotAvailableException e) {
                throw new IOException(e);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finish() {
        /*
            r3 = this;
            java.lang.String r0 = "Calling this from your main thread can lead to deadlock"
            com.google.android.gms.common.internal.zzv.zzbJ(r0)
            monitor-enter(r3)
            android.content.Context r0 = r3.mContext     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x000e
            com.google.android.gms.common.zza r0 = r3.zzmf     // Catch:{ all -> 0x002a }
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
        L_0x000f:
            return
        L_0x0010:
            boolean r0 = r3.zzmh     // Catch:{ IllegalArgumentException -> 0x002d }
            if (r0 == 0) goto L_0x001f
            com.google.android.gms.common.stats.zzb r0 = com.google.android.gms.common.stats.zzb.zznb()     // Catch:{ IllegalArgumentException -> 0x002d }
            android.content.Context r1 = r3.mContext     // Catch:{ IllegalArgumentException -> 0x002d }
            com.google.android.gms.common.zza r2 = r3.zzmf     // Catch:{ IllegalArgumentException -> 0x002d }
            r0.zza((android.content.Context) r1, (android.content.ServiceConnection) r2)     // Catch:{ IllegalArgumentException -> 0x002d }
        L_0x001f:
            r0 = 0
            r3.zzmh = r0     // Catch:{ all -> 0x002a }
            r0 = 0
            r3.zzmg = r0     // Catch:{ all -> 0x002a }
            r0 = 0
            r3.zzmf = r0     // Catch:{ all -> 0x002a }
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            goto L_0x000f
        L_0x002a:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            throw r0
        L_0x002d:
            r0 = move-exception
            java.lang.String r1 = "AdvertisingIdClient"
            java.lang.String r2 = "AdvertisingIdClient unbindService failed."
            android.util.Log.i(r1, r2, r0)     // Catch:{ all -> 0x002a }
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.identifier.AdvertisingIdClient.finish():void");
    }

    public Info getInfo() throws IOException {
        Info info;
        zzv.zzbJ("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (!this.zzmh) {
                synchronized (this.zzmi) {
                    if (this.zzmj == null || !this.zzmj.zzae()) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    zzb(false);
                    if (!this.zzmh) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                } catch (RemoteException e) {
                    Log.i("AdvertisingIdClient", "GMS remote exception ", e);
                    throw new IOException("Remote exception");
                } catch (Exception e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
            zzv.zzr(this.zzmf);
            zzv.zzr(this.zzmg);
            info = new Info(this.zzmg.getId(), this.zzmg.zzc(true));
        }
        zzad();
        return info;
    }

    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzb(true);
    }

    /* access modifiers changed from: protected */
    public void zzb(boolean z) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzv.zzbJ("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzmh) {
                finish();
            }
            this.zzmf = zzj(this.mContext);
            this.zzmg = zza(this.mContext, this.zzmf);
            this.zzmh = true;
            if (z) {
                zzad();
            }
        }
    }
}
