package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzv;
import java.util.ArrayList;
import java.util.List;

public class zzko {
    private static final zza[] zzawt = new zza[0];
    private static zzko zzawu;
    private final Application zzawv;
    private zzkv zzaww;
    private final List<zza> zzawx = new ArrayList();
    private zzky zzawy;

    public interface zza {
        void zza(zzkv zzkv);

        void zza(zzkv zzkv, Activity activity);
    }

    private zzko(Application application) {
        zzv.zzr(application);
        this.zzawv = application;
    }

    public static zzko zzal(Context context) {
        zzko zzko;
        zzv.zzr(context);
        Application application = (Application) context.getApplicationContext();
        zzv.zzr(application);
        synchronized (zzko.class) {
            if (zzawu == null) {
                zzawu = new zzko(application);
            }
            zzko = zzawu;
        }
        return zzko;
    }

    private zza[] zzur() {
        zza[] zzaArr;
        synchronized (this.zzawx) {
            zzaArr = this.zzawx.isEmpty() ? zzawt : (zza[]) this.zzawx.toArray(new zza[this.zzawx.size()]);
        }
        return zzaArr;
    }

    public void zza(zza zza2) {
        zzv.zzr(zza2);
        synchronized (this.zzawx) {
            this.zzawx.remove(zza2);
            this.zzawx.add(zza2);
        }
    }

    public void zzaa(boolean z) {
        if (Build.VERSION.SDK_INT < 14) {
            Log.i("com.google.android.gms.measurement.ScreenViewService", "AutoScreeViewTracking is not supported on API 14 or earlier devices");
        } else if (zzuq() == z) {
        } else {
            if (z) {
                this.zzawy = new zzky(this);
                this.zzawv.registerActivityLifecycleCallbacks(this.zzawy);
                return;
            }
            this.zzawv.unregisterActivityLifecycleCallbacks(this.zzawy);
            this.zzawy = null;
        }
    }

    public void zzb(zzkv zzkv, Activity activity) {
        zzv.zzr(zzkv);
        zza[] zzaArr = null;
        if (zzkv.isMutable()) {
            if (activity instanceof zzkn) {
                ((zzkn) activity).zzb(zzkv);
            }
            if (this.zzaww != null) {
                zzkv.zzgJ(this.zzaww.zzaJ());
                zzkv.zzdd(this.zzaww.zzuL());
            }
            zza[] zzur = zzur();
            for (zza zza2 : zzur) {
                zza2.zza(zzkv, activity);
            }
            zzkv.zzuP();
            if (!TextUtils.isEmpty(zzkv.zzuL())) {
                zzaArr = zzur;
            } else {
                return;
            }
        }
        if (this.zzaww == null || this.zzaww.zzaJ() != zzkv.zzaJ()) {
            zzup();
            this.zzaww = zzkv;
            if (zzaArr == null) {
                zzaArr = zzur();
            }
            for (zza zza3 : zzaArr) {
                zza3.zza(zzkv);
            }
            return;
        }
        this.zzaww = zzkv;
    }

    public zzkv zzuo() {
        return this.zzaww;
    }

    public void zzup() {
        this.zzaww = null;
    }

    public boolean zzuq() {
        return this.zzawy != null;
    }
}
