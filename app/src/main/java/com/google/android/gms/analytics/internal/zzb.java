package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.google.android.gms.analytics.AnalyticsReceiver;
import com.google.android.gms.analytics.AnalyticsService;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzkk;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class zzb extends zzd {
    /* access modifiers changed from: private */
    public final zzk zzFu;
    private final String zzFv = zzgC();

    public zzb(zze zze, zzf zzf) {
        super(zze);
        zzv.zzr(zzf);
        this.zzFu = zzf.zzj(zze);
    }

    private String zzgC() {
        if (!zzgI().zzhP()) {
            return "";
        }
        try {
            String valueOf = String.valueOf(getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode);
            if (valueOf.length() > 4) {
                valueOf = valueOf.substring(0, 4);
            }
            return "4.5.0-" + valueOf;
        } catch (PackageManager.NameNotFoundException e) {
            zze("Failed to get service version", e);
            return "0";
        }
    }

    /* access modifiers changed from: package-private */
    public void onServiceConnected() {
        zzgF();
        this.zzFu.onServiceConnected();
    }

    public void setLocalDispatchPeriod(final int dispatchPeriodInSeconds) {
        zzgR();
        zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(dispatchPeriodInSeconds));
        zzgJ().zze((Runnable) new Runnable() {
            public void run() {
                zzb.this.zzFu.zzq(((long) dispatchPeriodInSeconds) * 1000);
            }
        });
    }

    public void start() {
        this.zzFu.start();
    }

    public void zzG(final boolean z) {
        zza("Network connectivity status changed", Boolean.valueOf(z));
        zzgJ().zze((Runnable) new Runnable() {
            public void run() {
                zzb.this.zzFu.zzG(z);
            }
        });
    }

    public long zza(zzg zzg) {
        zzgR();
        zzv.zzr(zzg);
        zzgF();
        long zza = this.zzFu.zza(zzg, true);
        if (zza == 0) {
            this.zzFu.zzc(zzg);
        }
        return zza;
    }

    public void zza(final zzaa zzaa) {
        zzv.zzr(zzaa);
        zzgR();
        zzb("Hit delivery requested", zzaa);
        zzgJ().zze((Runnable) new Runnable() {
            public void run() {
                zzb.this.zzFu.zza(zzaa);
            }
        });
    }

    public void zza(final zzv zzv) {
        zzgR();
        zzgJ().zze((Runnable) new Runnable() {
            public void run() {
                zzb.this.zzFu.zzb(zzv);
            }
        });
    }

    public void zza(final String str, final Runnable runnable) {
        zzv.zzh(str, "campaign param can't be empty");
        zzgJ().zze((Runnable) new Runnable() {
            public void run() {
                zzb.this.zzFu.zzaN(str);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    public void zzgA() {
        zzaF("Radio powered up");
        zzgw();
    }

    /* access modifiers changed from: package-private */
    public void zzgB() {
        zzgF();
        this.zzFu.zzgB();
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
        this.zzFu.zzfV();
    }

    public void zzgv() {
        zzgR();
        zzgE();
        zzgJ().zze((Runnable) new Runnable() {
            public void run() {
                zzb.this.zzFu.zzgv();
            }
        });
    }

    public void zzgw() {
        zzgR();
        Context context = getContext();
        if (!AnalyticsReceiver.zzH(context) || !AnalyticsService.zzI(context)) {
            zza((zzv) null);
            return;
        }
        Intent intent = new Intent(context, AnalyticsService.class);
        intent.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        context.startService(intent);
    }

    public boolean zzgx() {
        zzgR();
        try {
            zzgJ().zzb(new Callable<Void>() {
                /* renamed from: zzeY */
                public Void call() throws Exception {
                    zzb.this.zzFu.zzhy();
                    return null;
                }
            }).get();
            return true;
        } catch (InterruptedException e) {
            zzd("syncDispatchLocalHits interrupted", e);
            return false;
        } catch (ExecutionException e2) {
            zze("syncDispatchLocalHits failed", e2);
            return false;
        }
    }

    public String zzgy() {
        return this.zzFv;
    }

    public void zzgz() {
        zzgR();
        zzkk.zzgF();
        this.zzFu.zzgz();
    }
}
