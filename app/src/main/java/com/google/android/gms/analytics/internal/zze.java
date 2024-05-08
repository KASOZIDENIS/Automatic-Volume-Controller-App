package com.google.android.gms.analytics.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzht;
import com.google.android.gms.internal.zzhv;
import com.google.android.gms.internal.zzkk;
import java.lang.Thread;

public class zze {
    private static zze zzFG;
    private final Context mContext;
    private final Context zzFH;
    private final zzq zzFI;
    private final zzae zzFJ;
    private final zzkk zzFK;
    private final zzb zzFL;
    private final zzu zzFM;
    private final zzam zzFN;
    private final zzah zzFO;
    private final GoogleAnalytics zzFP;
    private final zzm zzFQ;
    private final zza zzFR;
    private final zzj zzFS;
    private final zzt zzFT;
    private final zzht zznR;

    protected zze(zzf zzf) {
        Context applicationContext = zzf.getApplicationContext();
        zzv.zzb(applicationContext, (Object) "Application context can't be null");
        zzv.zzb(applicationContext instanceof Application, (Object) "getApplicationContext didn't return the application");
        Context zzgT = zzf.zzgT();
        zzv.zzr(zzgT);
        this.mContext = applicationContext;
        this.zzFH = zzgT;
        this.zznR = zzf.zzh(this);
        this.zzFI = zzf.zzg(this);
        zzae zzf2 = zzf.zzf(this);
        zzf2.zzfV();
        this.zzFJ = zzf2;
        if (zzgI().zzhP()) {
            zzgH().zzaH("Google Analytics 4.5.0/" + zzgZ() + " is starting up.");
        } else {
            zzgH().zzaH("Google Analytics 4.5.0/" + zzgZ() + " is starting up. " + "To enable debug logging on a device run:\n" + "  adb shell setprop log.tag.GAv4 DEBUG\n" + "  adb logcat -s GAv4");
        }
        zzah zzq = zzf.zzq(this);
        zzq.zzfV();
        this.zzFO = zzq;
        zzam zze = zzf.zze(this);
        zze.zzfV();
        this.zzFN = zze;
        zzb zzl = zzf.zzl(this);
        zzm zzd = zzf.zzd(this);
        zza zzc = zzf.zzc(this);
        zzj zzb = zzf.zzb(this);
        zzt zza = zzf.zza(this);
        zzkk zzK = zzf.zzK(applicationContext);
        zzK.zza(zzgS());
        this.zzFK = zzK;
        GoogleAnalytics zzi = zzf.zzi(this);
        zzd.zzfV();
        this.zzFQ = zzd;
        zzc.zzfV();
        this.zzFR = zzc;
        zzb.zzfV();
        this.zzFS = zzb;
        zza.zzfV();
        this.zzFT = zza;
        zzu zzp = zzf.zzp(this);
        zzp.zzfV();
        this.zzFM = zzp;
        zzl.zzfV();
        this.zzFL = zzl;
        if (zzgI().zzhP()) {
            zzgH().zzb("Device AnalyticsService version", zzfZ().zzgy());
        }
        zzi.zzfV();
        this.zzFP = zzi;
        zzl.start();
    }

    public static zze zzJ(Context context) {
        zzv.zzr(context);
        if (zzFG == null) {
            synchronized (zze.class) {
                if (zzFG == null) {
                    zzht zznd = zzhv.zznd();
                    long elapsedRealtime = zznd.elapsedRealtime();
                    zze zze = new zze(new zzf(context.getApplicationContext()));
                    zzFG = zze;
                    GoogleAnalytics.zzfX();
                    long elapsedRealtime2 = zznd.elapsedRealtime() - elapsedRealtime;
                    long longValue = zzx.zzHT.get().longValue();
                    if (elapsedRealtime2 > longValue) {
                        zze.zzgH().zzc("Slow initialization (ms)", Long.valueOf(elapsedRealtime2), Long.valueOf(longValue));
                    }
                }
            }
        }
        return zzFG;
    }

    private void zza(zzd zzd) {
        zzv.zzb(zzd, (Object) "Analytics service not created/initialized");
        zzv.zzb(zzd.isInitialized(), (Object) "Analytics service not initialized");
    }

    static int zzgZ() {
        return 7327;
    }

    public Context getContext() {
        return this.mContext;
    }

    public zzb zzfZ() {
        zza(this.zzFL);
        return this.zzFL;
    }

    public void zzgF() {
        zzkk.zzgF();
    }

    public zzht zzgG() {
        return this.zznR;
    }

    public zzae zzgH() {
        zza(this.zzFJ);
        return this.zzFJ;
    }

    public zzq zzgI() {
        return this.zzFI;
    }

    public zzkk zzgJ() {
        zzv.zzr(this.zzFK);
        return this.zzFK;
    }

    public zzu zzgK() {
        zza(this.zzFM);
        return this.zzFM;
    }

    public zzah zzgL() {
        zza(this.zzFO);
        return this.zzFO;
    }

    public zzj zzgO() {
        zza(this.zzFS);
        return this.zzFS;
    }

    public zzt zzgP() {
        return this.zzFT;
    }

    /* access modifiers changed from: protected */
    public Thread.UncaughtExceptionHandler zzgS() {
        return new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable error) {
                zzae zzgU = zze.this.zzgU();
                if (zzgU != null) {
                    zzgU.zze("Job execution failed", error);
                }
            }
        };
    }

    public Context zzgT() {
        return this.zzFH;
    }

    public zzae zzgU() {
        return this.zzFJ;
    }

    public GoogleAnalytics zzgV() {
        zzv.zzr(this.zzFP);
        zzv.zzb(this.zzFP.isInitialized(), (Object) "Analytics instance not initialized");
        return this.zzFP;
    }

    public zzah zzgW() {
        if (this.zzFO == null || !this.zzFO.isInitialized()) {
            return null;
        }
        return this.zzFO;
    }

    public zza zzgX() {
        zza(this.zzFR);
        return this.zzFR;
    }

    public zzm zzgY() {
        zza(this.zzFQ);
        return this.zzFQ;
    }

    public zzam zzga() {
        zza(this.zzFN);
        return this.zzFN;
    }
}
