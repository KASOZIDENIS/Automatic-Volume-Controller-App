package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.zzi;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class zzj extends zzi {
    /* access modifiers changed from: private */
    public static AdvertisingIdClient zzlE = null;
    /* access modifiers changed from: private */
    public static CountDownLatch zzlF = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public static boolean zzlG;
    private boolean zzlH;

    class zza {
        private String zzlI;
        private boolean zzlJ;

        public zza(String str, boolean z) {
            this.zzlI = str;
            this.zzlJ = z;
        }

        public String getId() {
            return this.zzlI;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.zzlJ;
        }
    }

    private static final class zzb implements Runnable {
        private Context zzlL;

        public zzb(Context context) {
            this.zzlL = context.getApplicationContext();
            if (this.zzlL == null) {
                this.zzlL = context;
            }
        }

        public void run() {
            try {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzlL);
                advertisingIdClient.start();
                synchronized (zzj.class) {
                    if (zzj.zzlE == null) {
                        AdvertisingIdClient unused = zzj.zzlE = advertisingIdClient;
                    } else {
                        advertisingIdClient.finish();
                    }
                }
            } catch (GooglePlayServicesNotAvailableException e) {
                boolean unused2 = zzj.zzlG = true;
            } catch (GooglePlayServicesRepairableException | IOException e2) {
            }
            zzj.zzlF.countDown();
        }
    }

    protected zzj(Context context, zzm zzm, zzn zzn, boolean z) {
        super(context, zzm, zzn);
        this.zzlH = z;
    }

    public static zzj zza(String str, Context context, boolean z) {
        zze zze = new zze();
        zza(str, context, zze);
        if (z) {
            synchronized (zzj.class) {
                if (zzlE == null) {
                    new Thread(new zzb(context)).start();
                }
            }
        }
        return new zzj(context, zze, new zzp(239), z);
    }

    /* access modifiers changed from: protected */
    public void zzb(Context context) {
        super.zzb(context);
        try {
            if (zzlG || !this.zzlH) {
                zza(24, zzd(context));
                return;
            }
            zza zzz = zzz();
            zza(28, zzz.isLimitAdTrackingEnabled() ? 1 : 0);
            String id = zzz.getId();
            if (id != null) {
                zza(26, 5);
                zza(24, id);
            }
        } catch (zzi.zza | IOException e) {
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzj.zza zzz() throws java.io.IOException {
        /*
            r5 = this;
            java.lang.Class<com.google.android.gms.internal.zzj> r1 = com.google.android.gms.internal.zzj.class
            monitor-enter(r1)
            java.util.concurrent.CountDownLatch r0 = zzlF     // Catch:{ InterruptedException -> 0x0018 }
            r2 = 2
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0018 }
            boolean r0 = r0.await(r2, r4)     // Catch:{ InterruptedException -> 0x0018 }
            if (r0 != 0) goto L_0x0025
            com.google.android.gms.internal.zzj$zza r0 = new com.google.android.gms.internal.zzj$zza     // Catch:{ InterruptedException -> 0x0018 }
            r2 = 0
            r3 = 0
            r0.<init>(r2, r3)     // Catch:{ InterruptedException -> 0x0018 }
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
        L_0x0017:
            return r0
        L_0x0018:
            r0 = move-exception
            com.google.android.gms.internal.zzj$zza r0 = new com.google.android.gms.internal.zzj$zza     // Catch:{ all -> 0x0022 }
            r2 = 0
            r3 = 0
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0022 }
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            goto L_0x0017
        L_0x0022:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            throw r0
        L_0x0025:
            com.google.android.gms.ads.identifier.AdvertisingIdClient r0 = zzlE     // Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x0032
            com.google.android.gms.internal.zzj$zza r0 = new com.google.android.gms.internal.zzj$zza     // Catch:{ all -> 0x0022 }
            r2 = 0
            r3 = 0
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0022 }
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            goto L_0x0017
        L_0x0032:
            com.google.android.gms.ads.identifier.AdvertisingIdClient r0 = zzlE     // Catch:{ all -> 0x0022 }
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r2 = r0.getInfo()     // Catch:{ all -> 0x0022 }
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            java.lang.String r0 = r2.getId()
            java.lang.String r1 = r5.zzb((java.lang.String) r0)
            com.google.android.gms.internal.zzj$zza r0 = new com.google.android.gms.internal.zzj$zza
            boolean r2 = r2.isLimitAdTrackingEnabled()
            r0.<init>(r1, r2)
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzj.zzz():com.google.android.gms.internal.zzj$zza");
    }
}
