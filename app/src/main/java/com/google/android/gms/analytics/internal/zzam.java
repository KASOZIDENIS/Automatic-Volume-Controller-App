package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class zzam extends zzd {
    protected boolean zzEA;
    protected int zzGV;
    protected String zzHY;
    protected String zzHZ;
    protected boolean zzIP;
    protected boolean zzIQ;
    protected boolean zzIR;
    protected int zzIb;

    public zzam(zze zze) {
        super(zze);
    }

    private static int zzba(String str) {
        String lowerCase = str.toLowerCase();
        if ("verbose".equals(lowerCase)) {
            return 0;
        }
        if ("info".equals(lowerCase)) {
            return 1;
        }
        if ("warning".equals(lowerCase)) {
            return 2;
        }
        return "error".equals(lowerCase) ? 3 : -1;
    }

    public int getLogLevel() {
        zzgR();
        return this.zzGV;
    }

    /* access modifiers changed from: package-private */
    public void zza(zzz zzz) {
        int zzba;
        zzaF("Loading global XML config values");
        if (zzz.zziD()) {
            String zziE = zzz.zziE();
            this.zzHY = zziE;
            zzb("XML config - app name", zziE);
        }
        if (zzz.zziF()) {
            String zziG = zzz.zziG();
            this.zzHZ = zziG;
            zzb("XML config - app version", zziG);
        }
        if (zzz.zziH() && (zzba = zzba(zzz.zziI())) >= 0) {
            this.zzGV = zzba;
            zza("XML config - log level", Integer.valueOf(zzba));
        }
        if (zzz.zziJ()) {
            int zziK = zzz.zziK();
            this.zzIb = zziK;
            this.zzIQ = true;
            zzb("XML config - dispatch period (sec)", Integer.valueOf(zziK));
        }
        if (zzz.zziL()) {
            boolean zziM = zzz.zziM();
            this.zzEA = zziM;
            this.zzIR = true;
            zzb("XML config - dry run", Boolean.valueOf(zziM));
        }
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
        zzjC();
    }

    public String zziE() {
        zzgR();
        return this.zzHY;
    }

    public String zziG() {
        zzgR();
        return this.zzHZ;
    }

    public boolean zziH() {
        zzgR();
        return this.zzIP;
    }

    public boolean zziJ() {
        zzgR();
        return this.zzIQ;
    }

    public boolean zziL() {
        zzgR();
        return this.zzIR;
    }

    public boolean zziM() {
        zzgR();
        return this.zzEA;
    }

    public int zzjB() {
        zzgR();
        return this.zzIb;
    }

    /* access modifiers changed from: protected */
    public void zzjC() {
        ApplicationInfo applicationInfo;
        int i;
        zzz zzz;
        Context context = getContext();
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 129);
        } catch (PackageManager.NameNotFoundException e) {
            zzd("PackageManager doesn't know about the app package", e);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            zzaI("Couldn't get ApplicationInfo to load global config");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null && (i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource")) > 0 && (zzz = (zzz) new zzy(zzgD()).zzS(i)) != null) {
            zza(zzz);
        }
    }
}
