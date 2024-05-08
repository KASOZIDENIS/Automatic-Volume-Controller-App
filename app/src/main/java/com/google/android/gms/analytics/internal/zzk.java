package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.AnalyticsReceiver;
import com.google.android.gms.analytics.AnalyticsService;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzgo;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzkg;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzkp;
import com.google.android.gms.internal.zzkq;
import java.util.HashMap;
import java.util.Map;

class zzk extends zzd {
    private boolean mStarted;
    private boolean zzGA;
    private final zzi zzGr;
    private final zzag zzGs;
    private final zzaf zzGt;
    private final zzh zzGu;
    private long zzGv = Long.MIN_VALUE;
    private final zzs zzGw;
    private final zzs zzGx;
    private final zzai zzGy;
    private long zzGz;

    protected zzk(zze zze, zzf zzf) {
        super(zze);
        zzv.zzr(zzf);
        this.zzGt = zzf.zzk(zze);
        this.zzGr = zzf.zzm(zze);
        this.zzGs = zzf.zzn(zze);
        this.zzGu = zzf.zzo(zze);
        this.zzGy = new zzai(zzgG());
        this.zzGw = new zzs(zze) {
            public void run() {
                zzk.this.zzht();
            }
        };
        this.zzGx = new zzs(zze) {
            public void run() {
                zzk.this.zzhu();
            }
        };
    }

    private void zza(zzg zzg, zzkq zzkq) {
        zzv.zzr(zzg);
        zzv.zzr(zzkq);
        zza zza = new zza(zzgD());
        zza.zzat(zzg.zzhb());
        zza.enableAdvertisingIdCollection(zzg.zzhc());
        zzkg zzfP = zza.zzfP();
        zzgp zzgp = (zzgp) zzfP.zze(zzgp.class);
        zzgp.zzay("data");
        zzgp.zzF(true);
        zzfP.zzb(zzkq);
        zzgo zzgo = (zzgo) zzfP.zze(zzgo.class);
        zzkp zzkp = (zzkp) zzfP.zze(zzkp.class);
        for (Map.Entry next : zzg.zzhe().entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if ("an".equals(str)) {
                zzkp.setAppName(str2);
            } else if ("av".equals(str)) {
                zzkp.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                zzkp.setAppId(str2);
            } else if ("aiid".equals(str)) {
                zzkp.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                zzgp.zzaz(str2);
            } else {
                zzgo.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", zzg.zzhb(), zzkq);
        zzfP.zzK(zzgL().zzjd());
        zzfP.zzud();
    }

    private boolean zzaM(String str) {
        return getContext().checkCallingOrSelfPermission(str) == 0;
    }

    private boolean zzhA() {
        if (this.zzGA) {
            return false;
        }
        return (!zzgI().zzhP() || zzgI().zzhQ()) && zzhG() > 0;
    }

    private void zzhB() {
        zzu zzgK = zzgK();
        if (zzgK.zziz() && !zzgK.zzaK()) {
            long zzhm = zzhm();
            if (zzhm != 0 && Math.abs(zzgG().currentTimeMillis() - zzhm) <= zzgI().zzhZ()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzgI().zzhY()));
                zzgK.zziA();
            }
        }
    }

    private void zzhC() {
        long min;
        zzhB();
        long zzhG = zzhG();
        long zzjf = zzgL().zzjf();
        if (zzjf != 0) {
            min = zzhG - Math.abs(zzgG().currentTimeMillis() - zzjf);
            if (min <= 0) {
                min = Math.min(zzgI().zzhW(), zzhG);
            }
        } else {
            min = Math.min(zzgI().zzhW(), zzhG);
        }
        zza("Dispatch scheduled (ms)", Long.valueOf(min));
        if (this.zzGw.zzaK()) {
            this.zzGw.zzs(Math.max(1, min + this.zzGw.zziw()));
            return;
        }
        this.zzGw.zzr(min);
    }

    private void zzhD() {
        zzhE();
        zzhF();
    }

    private void zzhE() {
        if (this.zzGw.zzaK()) {
            zzaF("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzGw.cancel();
    }

    private void zzhF() {
        zzu zzgK = zzgK();
        if (zzgK.zzaK()) {
            zzgK.cancel();
        }
    }

    private void zzhr() {
        Context context = zzgD().getContext();
        if (!AnalyticsReceiver.zzH(context)) {
            zzaI("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!AnalyticsService.zzI(context)) {
            zzaJ("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzH(context)) {
            zzaI("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!CampaignTrackingService.zzI(context)) {
            zzaI("CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
    }

    /* access modifiers changed from: private */
    public void zzht() {
        zzb((zzv) new zzv() {
            public void zzc(Throwable th) {
                zzk.this.zzhz();
            }
        });
    }

    /* access modifiers changed from: private */
    public void zzhu() {
        try {
            this.zzGr.zzhl();
            zzhz();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzGx.zzr(zzgI().zzir());
    }

    /* access modifiers changed from: protected */
    public void onServiceConnected() {
        zzgF();
        if (!zzgI().zzhP()) {
            zzhw();
        }
    }

    /* access modifiers changed from: package-private */
    public void start() {
        zzgR();
        zzv.zza(!this.mStarted, (Object) "Analytics backend already started");
        this.mStarted = true;
        if (!zzgI().zzhP()) {
            zzhr();
        }
        zzgJ().zze((Runnable) new Runnable() {
            public void run() {
                zzk.this.zzhs();
            }
        });
    }

    public void zzG(boolean z) {
        zzhz();
    }

    public long zza(zzg zzg, boolean z) {
        long j;
        zzv.zzr(zzg);
        zzgR();
        zzgF();
        try {
            this.zzGr.beginTransaction();
            this.zzGr.zza(zzg.zzha(), zzg.getClientId());
            j = this.zzGr.zza(zzg.zzha(), zzg.getClientId(), zzg.zzhb());
            if (!z) {
                zzg.zzl(j);
            } else {
                zzg.zzl(1 + j);
            }
            this.zzGr.zzb(zzg);
            this.zzGr.setTransactionSuccessful();
            try {
                this.zzGr.endTransaction();
            } catch (SQLiteException e) {
                zze("Failed to end transaction", e);
            }
        } catch (SQLiteException e2) {
            zze("Failed to update Analytics property", e2);
            j = -1;
            try {
                this.zzGr.endTransaction();
            } catch (SQLiteException e3) {
                zze("Failed to end transaction", e3);
            }
        } catch (Throwable th) {
            try {
                this.zzGr.endTransaction();
            } catch (SQLiteException e4) {
                zze("Failed to end transaction", e4);
            }
            throw th;
        }
        return j;
    }

    public void zza(zzaa zzaa) {
        zzv.zzr(zzaa);
        zzkk.zzgF();
        zzgR();
        if (this.zzGA) {
            zzaG("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzaa);
        }
        zzaa zzf = zzf(zzaa);
        zzhv();
        if (this.zzGu.zzb(zzf)) {
            zzaG("Hit sent to the device AnalyticsService for delivery");
        } else if (zzgI().zzhP()) {
            zzgH().zza(zzf, "Service unavailable on package side");
        } else {
            try {
                this.zzGr.zzc(zzf);
                zzhz();
            } catch (SQLiteException e) {
                zze("Delivery failed to save hit to a database", e);
                zzgH().zza(zzf, "deliver: failed to insert hit to database");
            }
        }
    }

    public void zza(final zzv zzv, final long j) {
        zzkk.zzgF();
        zzgR();
        long j2 = -1;
        long zzjf = zzgL().zzjf();
        if (zzjf != 0) {
            j2 = Math.abs(zzgG().currentTimeMillis() - zzjf);
        }
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(j2));
        if (!zzgI().zzhP()) {
            zzhv();
        }
        try {
            if (zzhx()) {
                zzgJ().zze((Runnable) new Runnable() {
                    public void run() {
                        zzk.this.zza(zzv, j);
                    }
                });
                return;
            }
            zzgL().zzjg();
            zzhz();
            if (zzv != null) {
                zzv.zzc((Throwable) null);
            }
            if (this.zzGz != j) {
                this.zzGt.zziY();
            }
        } catch (Throwable th) {
            zze("Local dispatch failed", th);
            zzgL().zzjg();
            zzhz();
            if (zzv != null) {
                zzv.zzc(th);
            }
        }
    }

    public void zzaN(String str) {
        zzv.zzbS(str);
        zzgF();
        zzgE();
        zzkq zza = zzal.zza(zzgH(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzjh = zzgL().zzjh();
        if (str.equals(zzjh)) {
            zzaI("Ignoring duplicate install campaign");
        } else if (!TextUtils.isEmpty(zzjh)) {
            zzd("Ignoring multiple install campaigns. original, new", zzjh, str);
        } else {
            zzgL().zzaR(str);
            if (zzgL().zzje().zzt(zzgI().zziu())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzg zza2 : this.zzGr.zzp(0)) {
                zza(zza2, zza);
            }
        }
    }

    public void zzb(zzv zzv) {
        zza(zzv, this.zzGz);
    }

    /* access modifiers changed from: protected */
    public void zzc(zzg zzg) {
        zzgF();
        zzb("Sending first hit to property", zzg.zzhb());
        if (!zzgL().zzje().zzt(zzgI().zziu())) {
            String zzjh = zzgL().zzjh();
            if (!TextUtils.isEmpty(zzjh)) {
                zzkq zza = zzal.zza(zzgH(), zzjh);
                zzb("Found relevant installation campaign", zza);
                zza(zzg, zza);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public zzaa zzf(zzaa zzaa) {
        Pair<String, Long> zzjl;
        if (!TextUtils.isEmpty(zzaa.zziT()) || (zzjl = zzgL().zzji().zzjl()) == null) {
            return zzaa;
        }
        String str = ((Long) zzjl.second) + ":" + ((String) zzjl.first);
        HashMap hashMap = new HashMap(zzaa.zzhe());
        hashMap.put("_m", str);
        return zzaa.zza(this, zzaa, hashMap);
    }

    /* access modifiers changed from: package-private */
    public void zzgB() {
        zzgF();
        this.zzGz = zzgG().currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
        this.zzGr.zzfV();
        this.zzGs.zzfV();
        this.zzGu.zzfV();
    }

    public void zzgv() {
        zzkk.zzgF();
        zzgR();
        if (!zzgI().zzhP()) {
            zzaF("Delete all hits from local store");
            try {
                this.zzGr.zzhj();
                this.zzGr.zzhk();
                zzhz();
            } catch (SQLiteException e) {
                zzd("Failed to delete hits from store", e);
            }
        }
        zzhv();
        if (this.zzGu.zzhf()) {
            zzaF("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    public void zzgz() {
        zzkk.zzgF();
        zzgR();
        zzaF("Service disconnected");
    }

    public long zzhG() {
        if (this.zzGv != Long.MIN_VALUE) {
            return this.zzGv;
        }
        return zzga().zziJ() ? ((long) zzga().zzjB()) * 1000 : zzgI().zzhX();
    }

    public void zzhH() {
        zzgR();
        zzgF();
        this.zzGA = true;
        this.zzGu.disconnect();
        zzhz();
    }

    public long zzhm() {
        zzkk.zzgF();
        zzgR();
        try {
            return this.zzGr.zzhm();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public void zzhs() {
        zzgR();
        zzgL().zzjd();
        if (!zzaM("android.permission.ACCESS_NETWORK_STATE")) {
            zzaJ("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzhH();
        }
        if (!zzaM("android.permission.INTERNET")) {
            zzaJ("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzhH();
        }
        if (AnalyticsService.zzI(getContext())) {
            zzaF("AnalyticsService registered in the app manifest and enabled");
        } else if (zzgI().zzhP()) {
            zzaJ("Device AnalyticsService not registered! Hits will not be delivered reliably.");
        } else {
            zzaI("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzGA && !zzgI().zzhP() && !this.zzGr.isEmpty()) {
            zzhv();
        }
        zzhz();
    }

    /* access modifiers changed from: protected */
    public void zzhv() {
        if (!this.zzGA && zzgI().zzhR() && !this.zzGu.isConnected()) {
            if (this.zzGy.zzt(zzgI().zzim())) {
                this.zzGy.start();
                zzaF("Connecting to service");
                if (this.zzGu.connect()) {
                    zzaF("Connected to service");
                    this.zzGy.clear();
                    onServiceConnected();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062 A[LOOP:1: B:18:0x0062->B:17:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0048 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzhw() {
        /*
            r6 = this;
            com.google.android.gms.internal.zzkk.zzgF()
            r6.zzgR()
            r6.zzgE()
            com.google.android.gms.analytics.internal.zzq r0 = r6.zzgI()
            boolean r0 = r0.zzhR()
            if (r0 != 0) goto L_0x0018
            java.lang.String r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService"
            r6.zzaI(r0)
        L_0x0018:
            com.google.android.gms.analytics.internal.zzh r0 = r6.zzGu
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0026
            java.lang.String r0 = "Service not connected"
            r6.zzaF(r0)
        L_0x0025:
            return
        L_0x0026:
            com.google.android.gms.analytics.internal.zzi r0 = r6.zzGr
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0025
            java.lang.String r0 = "Dispatching local hits to device AnalyticsService"
            r6.zzaF(r0)
        L_0x0033:
            com.google.android.gms.analytics.internal.zzi r0 = r6.zzGr     // Catch:{ SQLiteException -> 0x004c }
            com.google.android.gms.analytics.internal.zzq r1 = r6.zzgI()     // Catch:{ SQLiteException -> 0x004c }
            int r1 = r1.zzia()     // Catch:{ SQLiteException -> 0x004c }
            long r2 = (long) r1     // Catch:{ SQLiteException -> 0x004c }
            java.util.List r1 = r0.zzn(r2)     // Catch:{ SQLiteException -> 0x004c }
            boolean r0 = r1.isEmpty()     // Catch:{ SQLiteException -> 0x004c }
            if (r0 == 0) goto L_0x0062
            r6.zzhz()     // Catch:{ SQLiteException -> 0x004c }
            goto L_0x0025
        L_0x004c:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from store"
            r6.zze(r1, r0)
            r6.zzhD()
            goto L_0x0025
        L_0x0056:
            r1.remove(r0)
            com.google.android.gms.analytics.internal.zzi r2 = r6.zzGr     // Catch:{ SQLiteException -> 0x007b }
            long r4 = r0.zziO()     // Catch:{ SQLiteException -> 0x007b }
            r2.zzo(r4)     // Catch:{ SQLiteException -> 0x007b }
        L_0x0062:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x0033
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            com.google.android.gms.analytics.internal.zzaa r0 = (com.google.android.gms.analytics.internal.zzaa) r0
            com.google.android.gms.analytics.internal.zzh r2 = r6.zzGu
            boolean r2 = r2.zzb((com.google.android.gms.analytics.internal.zzaa) r0)
            if (r2 != 0) goto L_0x0056
            r6.zzhz()
            goto L_0x0025
        L_0x007b:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r6.zze(r1, r0)
            r6.zzhD()
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzk.zzhw():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0205, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f9, code lost:
        if (r12.zzGu.isConnected() == false) goto L_0x0205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0103, code lost:
        if (zzgI().zzhP() != false) goto L_0x0205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0105, code lost:
        zzaF("Service connected, sending hits to the service");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x010e, code lost:
        if (r8.isEmpty() != false) goto L_0x0205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0110, code lost:
        r0 = r8.get(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011d, code lost:
        if (r12.zzGu.zzb(r0) != false) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011f, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0126, code lost:
        if (r12.zzGs.zziZ() == false) goto L_0x0199;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0128, code lost:
        r9 = r12.zzGs.zzg(r8);
        r10 = r9.iterator();
        r4 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0137, code lost:
        if (r10.hasNext() == false) goto L_0x018d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0139, code lost:
        r4 = java.lang.Math.max(r4, r10.next().longValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0148, code lost:
        r4 = java.lang.Math.max(r4, r0.zziO());
        r8.remove(r0);
        zzb("Hit sent do device AnalyticsService for delivery", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        r12.zzGr.zzo(r0.zziO());
        r3.add(java.lang.Long.valueOf(r0.zziO()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x016d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        zze("Failed to remove hit that was send for delivery", r0);
        zzhD();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        r12.zzGr.setTransactionSuccessful();
        r12.zzGr.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0182, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0183, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzhD();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r8.removeAll(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r12.zzGr.zze(r9);
        r3.addAll(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0198, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x019d, code lost:
        if (r3.isEmpty() == false) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        r12.zzGr.setTransactionSuccessful();
        r12.zzGr.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01ab, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01ac, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzhD();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01b6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        zze("Failed to remove successfully uploaded hits", r0);
        zzhD();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
        r12.zzGr.setTransactionSuccessful();
        r12.zzGr.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01cb, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01cc, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzhD();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
        r12.zzGr.setTransactionSuccessful();
        r12.zzGr.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01e3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01e4, code lost:
        zze("Failed to commit local dispatch transaction", r0);
        zzhD();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zzhx() {
        /*
            r12 = this;
            r1 = 1
            r2 = 0
            com.google.android.gms.internal.zzkk.zzgF()
            r12.zzgR()
            java.lang.String r0 = "Dispatching a batch of local hits"
            r12.zzaF(r0)
            com.google.android.gms.analytics.internal.zzh r0 = r12.zzGu
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0032
            com.google.android.gms.analytics.internal.zzq r0 = r12.zzgI()
            boolean r0 = r0.zzhP()
            if (r0 != 0) goto L_0x0032
            r0 = r1
        L_0x0020:
            com.google.android.gms.analytics.internal.zzag r3 = r12.zzGs
            boolean r3 = r3.zziZ()
            if (r3 != 0) goto L_0x0034
        L_0x0028:
            if (r0 == 0) goto L_0x0036
            if (r1 == 0) goto L_0x0036
            java.lang.String r0 = "No network or service available. Will retry later"
            r12.zzaF(r0)
        L_0x0031:
            return r2
        L_0x0032:
            r0 = r2
            goto L_0x0020
        L_0x0034:
            r1 = r2
            goto L_0x0028
        L_0x0036:
            com.google.android.gms.analytics.internal.zzq r0 = r12.zzgI()
            int r0 = r0.zzia()
            com.google.android.gms.analytics.internal.zzq r1 = r12.zzgI()
            int r1 = r1.zzib()
            int r0 = java.lang.Math.max(r0, r1)
            long r6 = (long) r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
        L_0x0052:
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ all -> 0x01ee }
            r0.beginTransaction()     // Catch:{ all -> 0x01ee }
            r3.clear()     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x00d3 }
            java.util.List r8 = r0.zzn(r6)     // Catch:{ SQLiteException -> 0x00d3 }
            boolean r0 = r8.isEmpty()     // Catch:{ SQLiteException -> 0x00d3 }
            if (r0 == 0) goto L_0x0083
            java.lang.String r0 = "Store is empty, nothing to dispatch"
            r12.zzaF(r0)     // Catch:{ SQLiteException -> 0x00d3 }
            r12.zzhD()     // Catch:{ SQLiteException -> 0x00d3 }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x0079 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x0079 }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x0079 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x0079 }
            goto L_0x0031
        L_0x0079:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzhD()
            goto L_0x0031
        L_0x0083:
            java.lang.String r0 = "Hits loaded from store. count"
            int r1 = r8.size()     // Catch:{ SQLiteException -> 0x00d3 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x00d3 }
            r12.zza(r0, r1)     // Catch:{ SQLiteException -> 0x00d3 }
            java.util.Iterator r1 = r8.iterator()     // Catch:{ all -> 0x01ee }
        L_0x0094:
            boolean r0 = r1.hasNext()     // Catch:{ all -> 0x01ee }
            if (r0 == 0) goto L_0x00f3
            java.lang.Object r0 = r1.next()     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzaa r0 = (com.google.android.gms.analytics.internal.zzaa) r0     // Catch:{ all -> 0x01ee }
            long r10 = r0.zziO()     // Catch:{ all -> 0x01ee }
            int r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0094
            java.lang.String r0 = "Database contains successfully uploaded hit"
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x01ee }
            int r3 = r8.size()     // Catch:{ all -> 0x01ee }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x01ee }
            r12.zzd(r0, r1, r3)     // Catch:{ all -> 0x01ee }
            r12.zzhD()     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x00c8 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x00c8 }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x00c8 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x00c8 }
            goto L_0x0031
        L_0x00c8:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzhD()
            goto L_0x0031
        L_0x00d3:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from persisted store"
            r12.zzd(r1, r0)     // Catch:{ all -> 0x01ee }
            r12.zzhD()     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x00e8 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x00e8 }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x00e8 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x00e8 }
            goto L_0x0031
        L_0x00e8:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzhD()
            goto L_0x0031
        L_0x00f3:
            com.google.android.gms.analytics.internal.zzh r0 = r12.zzGu     // Catch:{ all -> 0x01ee }
            boolean r0 = r0.isConnected()     // Catch:{ all -> 0x01ee }
            if (r0 == 0) goto L_0x0205
            com.google.android.gms.analytics.internal.zzq r0 = r12.zzgI()     // Catch:{ all -> 0x01ee }
            boolean r0 = r0.zzhP()     // Catch:{ all -> 0x01ee }
            if (r0 != 0) goto L_0x0205
            java.lang.String r0 = "Service connected, sending hits to the service"
            r12.zzaF(r0)     // Catch:{ all -> 0x01ee }
        L_0x010a:
            boolean r0 = r8.isEmpty()     // Catch:{ all -> 0x01ee }
            if (r0 != 0) goto L_0x0205
            r0 = 0
            java.lang.Object r0 = r8.get(r0)     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzaa r0 = (com.google.android.gms.analytics.internal.zzaa) r0     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzh r1 = r12.zzGu     // Catch:{ all -> 0x01ee }
            boolean r1 = r1.zzb((com.google.android.gms.analytics.internal.zzaa) r0)     // Catch:{ all -> 0x01ee }
            if (r1 != 0) goto L_0x0148
            r0 = r4
        L_0x0120:
            com.google.android.gms.analytics.internal.zzag r4 = r12.zzGs     // Catch:{ all -> 0x01ee }
            boolean r4 = r4.zziZ()     // Catch:{ all -> 0x01ee }
            if (r4 == 0) goto L_0x0199
            com.google.android.gms.analytics.internal.zzag r4 = r12.zzGs     // Catch:{ all -> 0x01ee }
            java.util.List r9 = r4.zzg((java.util.List<com.google.android.gms.analytics.internal.zzaa>) r8)     // Catch:{ all -> 0x01ee }
            java.util.Iterator r10 = r9.iterator()     // Catch:{ all -> 0x01ee }
            r4 = r0
        L_0x0133:
            boolean r0 = r10.hasNext()     // Catch:{ all -> 0x01ee }
            if (r0 == 0) goto L_0x018d
            java.lang.Object r0 = r10.next()     // Catch:{ all -> 0x01ee }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x01ee }
            long r0 = r0.longValue()     // Catch:{ all -> 0x01ee }
            long r4 = java.lang.Math.max(r4, r0)     // Catch:{ all -> 0x01ee }
            goto L_0x0133
        L_0x0148:
            long r10 = r0.zziO()     // Catch:{ all -> 0x01ee }
            long r4 = java.lang.Math.max(r4, r10)     // Catch:{ all -> 0x01ee }
            r8.remove(r0)     // Catch:{ all -> 0x01ee }
            java.lang.String r1 = "Hit sent do device AnalyticsService for delivery"
            r12.zzb(r1, r0)     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzi r1 = r12.zzGr     // Catch:{ SQLiteException -> 0x016d }
            long r10 = r0.zziO()     // Catch:{ SQLiteException -> 0x016d }
            r1.zzo(r10)     // Catch:{ SQLiteException -> 0x016d }
            long r0 = r0.zziO()     // Catch:{ SQLiteException -> 0x016d }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ SQLiteException -> 0x016d }
            r3.add(r0)     // Catch:{ SQLiteException -> 0x016d }
            goto L_0x010a
        L_0x016d:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r12.zze(r1, r0)     // Catch:{ all -> 0x01ee }
            r12.zzhD()     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x0182 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x0182 }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x0182 }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x0182 }
            goto L_0x0031
        L_0x0182:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzhD()
            goto L_0x0031
        L_0x018d:
            r8.removeAll(r9)     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x01b6 }
            r0.zze((java.util.List<java.lang.Long>) r9)     // Catch:{ SQLiteException -> 0x01b6 }
            r3.addAll(r9)     // Catch:{ SQLiteException -> 0x01b6 }
            r0 = r4
        L_0x0199:
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x01ee }
            if (r4 == 0) goto L_0x01d6
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x01ab }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01ab }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x01ab }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x01ab }
            goto L_0x0031
        L_0x01ab:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzhD()
            goto L_0x0031
        L_0x01b6:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove successfully uploaded hits"
            r12.zze(r1, r0)     // Catch:{ all -> 0x01ee }
            r12.zzhD()     // Catch:{ all -> 0x01ee }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x01cb }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01cb }
            com.google.android.gms.analytics.internal.zzi r0 = r12.zzGr     // Catch:{ SQLiteException -> 0x01cb }
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x01cb }
            goto L_0x0031
        L_0x01cb:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzhD()
            goto L_0x0031
        L_0x01d6:
            com.google.android.gms.analytics.internal.zzi r4 = r12.zzGr     // Catch:{ SQLiteException -> 0x01e3 }
            r4.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01e3 }
            com.google.android.gms.analytics.internal.zzi r4 = r12.zzGr     // Catch:{ SQLiteException -> 0x01e3 }
            r4.endTransaction()     // Catch:{ SQLiteException -> 0x01e3 }
            r4 = r0
            goto L_0x0052
        L_0x01e3:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzhD()
            goto L_0x0031
        L_0x01ee:
            r0 = move-exception
            com.google.android.gms.analytics.internal.zzi r1 = r12.zzGr     // Catch:{ SQLiteException -> 0x01fa }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x01fa }
            com.google.android.gms.analytics.internal.zzi r1 = r12.zzGr     // Catch:{ SQLiteException -> 0x01fa }
            r1.endTransaction()     // Catch:{ SQLiteException -> 0x01fa }
            throw r0
        L_0x01fa:
            r0 = move-exception
            java.lang.String r1 = "Failed to commit local dispatch transaction"
            r12.zze(r1, r0)
            r12.zzhD()
            goto L_0x0031
        L_0x0205:
            r0 = r4
            goto L_0x0120
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzk.zzhx():boolean");
    }

    public void zzhy() {
        zzkk.zzgF();
        zzgR();
        zzaG("Sync dispatching local hits");
        long j = this.zzGz;
        if (!zzgI().zzhP()) {
            zzhv();
        }
        do {
            try {
            } catch (Throwable th) {
                zze("Sync local dispatch failed", th);
                zzhz();
                return;
            }
        } while (zzhx());
        zzgL().zzjg();
        zzhz();
        if (this.zzGz != j) {
            this.zzGt.zziY();
        }
    }

    public void zzhz() {
        boolean z;
        zzgD().zzgF();
        zzgR();
        if (!zzhA()) {
            this.zzGt.unregister();
            zzhD();
        } else if (this.zzGr.isEmpty()) {
            this.zzGt.unregister();
            zzhD();
        } else {
            if (!zzx.zzHM.get().booleanValue()) {
                this.zzGt.zziW();
                z = this.zzGt.isConnected();
            } else {
                z = true;
            }
            if (z) {
                zzhC();
                return;
            }
            zzhD();
            zzhB();
        }
    }

    public void zzq(long j) {
        zzkk.zzgF();
        zzgR();
        if (j < 0) {
            j = 0;
        }
        this.zzGv = j;
        zzhz();
    }
}
