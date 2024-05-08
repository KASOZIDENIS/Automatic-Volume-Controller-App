package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.common.stats.zzb;
import java.util.Collections;

public class zzh extends zzd {
    /* access modifiers changed from: private */
    public final zza zzGa = new zza();
    private zzab zzGb;
    private final zzs zzGc;
    private zzai zzGd;

    protected class zza implements ServiceConnection {
        private volatile zzab zzGf;
        private volatile boolean zzGg;

        protected zza() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            r4.zzGe.zzaJ("Service connect failed to get IAnalyticsService");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x005a, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            notifyAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x005e, code lost:
            throw r0;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [B:3:0x0008, B:9:0x0015] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
            /*
                r4 = this;
                java.lang.String r0 = "AnalyticsServiceConnection.onServiceConnected"
                com.google.android.gms.common.internal.zzv.zzbI(r0)
                monitor-enter(r4)
                if (r6 != 0) goto L_0x0014
                com.google.android.gms.analytics.internal.zzh r0 = com.google.android.gms.analytics.internal.zzh.this     // Catch:{ all -> 0x005a }
                java.lang.String r1 = "Service connected with null binder"
                r0.zzaJ(r1)     // Catch:{ all -> 0x005a }
                r4.notifyAll()     // Catch:{ all -> 0x0046 }
                monitor-exit(r4)     // Catch:{ all -> 0x0046 }
            L_0x0013:
                return
            L_0x0014:
                r0 = 0
                java.lang.String r1 = r6.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x0051 }
                java.lang.String r2 = "com.google.android.gms.analytics.internal.IAnalyticsService"
                boolean r2 = r2.equals(r1)     // Catch:{ RemoteException -> 0x0051 }
                if (r2 == 0) goto L_0x0049
                com.google.android.gms.analytics.internal.zzab r0 = com.google.android.gms.analytics.internal.zzab.zza.zzG(r6)     // Catch:{ RemoteException -> 0x0051 }
                com.google.android.gms.analytics.internal.zzh r1 = com.google.android.gms.analytics.internal.zzh.this     // Catch:{ RemoteException -> 0x0051 }
                java.lang.String r2 = "Bound to IAnalyticsService interface"
                r1.zzaF(r2)     // Catch:{ RemoteException -> 0x0051 }
            L_0x002c:
                if (r0 != 0) goto L_0x005f
                com.google.android.gms.common.stats.zzb r0 = com.google.android.gms.common.stats.zzb.zznb()     // Catch:{ IllegalArgumentException -> 0x007c }
                com.google.android.gms.analytics.internal.zzh r1 = com.google.android.gms.analytics.internal.zzh.this     // Catch:{ IllegalArgumentException -> 0x007c }
                android.content.Context r1 = r1.getContext()     // Catch:{ IllegalArgumentException -> 0x007c }
                com.google.android.gms.analytics.internal.zzh r2 = com.google.android.gms.analytics.internal.zzh.this     // Catch:{ IllegalArgumentException -> 0x007c }
                com.google.android.gms.analytics.internal.zzh$zza r2 = r2.zzGa     // Catch:{ IllegalArgumentException -> 0x007c }
                r0.zza((android.content.Context) r1, (android.content.ServiceConnection) r2)     // Catch:{ IllegalArgumentException -> 0x007c }
            L_0x0041:
                r4.notifyAll()     // Catch:{ all -> 0x0046 }
                monitor-exit(r4)     // Catch:{ all -> 0x0046 }
                goto L_0x0013
            L_0x0046:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0046 }
                throw r0
            L_0x0049:
                com.google.android.gms.analytics.internal.zzh r2 = com.google.android.gms.analytics.internal.zzh.this     // Catch:{ RemoteException -> 0x0051 }
                java.lang.String r3 = "Got binder with a wrong descriptor"
                r2.zze(r3, r1)     // Catch:{ RemoteException -> 0x0051 }
                goto L_0x002c
            L_0x0051:
                r1 = move-exception
                com.google.android.gms.analytics.internal.zzh r1 = com.google.android.gms.analytics.internal.zzh.this     // Catch:{ all -> 0x005a }
                java.lang.String r2 = "Service connect failed to get IAnalyticsService"
                r1.zzaJ(r2)     // Catch:{ all -> 0x005a }
                goto L_0x002c
            L_0x005a:
                r0 = move-exception
                r4.notifyAll()     // Catch:{ all -> 0x0046 }
                throw r0     // Catch:{ all -> 0x0046 }
            L_0x005f:
                boolean r1 = r4.zzGg     // Catch:{ all -> 0x005a }
                if (r1 != 0) goto L_0x0079
                com.google.android.gms.analytics.internal.zzh r1 = com.google.android.gms.analytics.internal.zzh.this     // Catch:{ all -> 0x005a }
                java.lang.String r2 = "onServiceConnected received after the timeout limit"
                r1.zzaI(r2)     // Catch:{ all -> 0x005a }
                com.google.android.gms.analytics.internal.zzh r1 = com.google.android.gms.analytics.internal.zzh.this     // Catch:{ all -> 0x005a }
                com.google.android.gms.internal.zzkk r1 = r1.zzgJ()     // Catch:{ all -> 0x005a }
                com.google.android.gms.analytics.internal.zzh$zza$1 r2 = new com.google.android.gms.analytics.internal.zzh$zza$1     // Catch:{ all -> 0x005a }
                r2.<init>(r0)     // Catch:{ all -> 0x005a }
                r1.zze((java.lang.Runnable) r2)     // Catch:{ all -> 0x005a }
                goto L_0x0041
            L_0x0079:
                r4.zzGf = r0     // Catch:{ all -> 0x005a }
                goto L_0x0041
            L_0x007c:
                r0 = move-exception
                goto L_0x0041
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzh.zza.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        public void onServiceDisconnected(final ComponentName name) {
            zzv.zzbI("AnalyticsServiceConnection.onServiceDisconnected");
            zzh.this.zzgJ().zze((Runnable) new Runnable() {
                public void run() {
                    zzh.this.onServiceDisconnected(name);
                }
            });
        }

        public zzab zzhi() {
            zzab zzab = null;
            zzh.this.zzgF();
            Intent intent = new Intent("com.google.android.gms.analytics.service.START");
            intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
            Context context = zzh.this.getContext();
            intent.putExtra("app_package_name", context.getPackageName());
            zzb zznb = zzb.zznb();
            synchronized (this) {
                this.zzGf = null;
                this.zzGg = true;
                boolean zza = zznb.zza(context, intent, (ServiceConnection) zzh.this.zzGa, 129);
                zzh.this.zza("Bind to service requested", Boolean.valueOf(zza));
                if (!zza) {
                    this.zzGg = false;
                } else {
                    try {
                        wait(zzh.this.zzgI().zzil());
                    } catch (InterruptedException e) {
                        zzh.this.zzaI("Wait for service connect was interrupted");
                    }
                    this.zzGg = false;
                    zzab = this.zzGf;
                    this.zzGf = null;
                    if (zzab == null) {
                        zzh.this.zzaJ("Successfully bound to service but never got onServiceConnected callback");
                    }
                }
            }
            return zzab;
        }
    }

    protected zzh(zze zze) {
        super(zze);
        this.zzGd = new zzai(zze.zzgG());
        this.zzGc = new zzs(zze) {
            public void run() {
                zzh.this.zzhh();
            }
        };
    }

    private void onDisconnect() {
        zzfZ().zzgz();
    }

    /* access modifiers changed from: private */
    public void onServiceDisconnected(ComponentName name) {
        zzgF();
        if (this.zzGb != null) {
            this.zzGb = null;
            zza("Disconnected from device AnalyticsService", name);
            onDisconnect();
        }
    }

    /* access modifiers changed from: private */
    public void zza(zzab zzab) {
        zzgF();
        this.zzGb = zzab;
        zzhg();
        zzfZ().onServiceConnected();
    }

    private void zzhg() {
        this.zzGd.start();
        this.zzGc.zzr(zzgI().zzik());
    }

    /* access modifiers changed from: private */
    public void zzhh() {
        zzgF();
        if (isConnected()) {
            zzaF("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public boolean connect() {
        zzgF();
        zzgR();
        if (this.zzGb != null) {
            return true;
        }
        zzab zzhi = this.zzGa.zzhi();
        if (zzhi == null) {
            return false;
        }
        this.zzGb = zzhi;
        zzhg();
        return true;
    }

    public void disconnect() {
        zzgF();
        zzgR();
        try {
            zzb.zznb().zza(getContext(), (ServiceConnection) this.zzGa);
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        if (this.zzGb != null) {
            this.zzGb = null;
            onDisconnect();
        }
    }

    public boolean isConnected() {
        zzgF();
        zzgR();
        return this.zzGb != null;
    }

    public boolean zzb(zzaa zzaa) {
        zzv.zzr(zzaa);
        zzgF();
        zzgR();
        zzab zzab = this.zzGb;
        if (zzab == null) {
            return false;
        }
        try {
            zzab.zza(zzaa.zzhe(), zzaa.zziP(), zzaa.zziR() ? zzgI().zzid() : zzgI().zzie(), Collections.emptyList());
            zzhg();
            return true;
        } catch (RemoteException e) {
            zzaF("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
    }

    public boolean zzhf() {
        zzgF();
        zzgR();
        zzab zzab = this.zzGb;
        if (zzab == null) {
            return false;
        }
        try {
            zzab.zzgv();
            zzhg();
            return true;
        } catch (RemoteException e) {
            zzaF("Failed to clear hits from AnalyticsService");
            return false;
        }
    }
}
