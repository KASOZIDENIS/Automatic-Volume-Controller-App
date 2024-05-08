package com.google.android.gms.analytics.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzv;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class zzq {
    private final zze zzEb;
    private Boolean zzGS;
    private String zzGT;
    private Set<Integer> zzGU;

    protected zzq(zze zze) {
        zzv.zzr(zze);
        this.zzEb = zze;
    }

    public boolean zzhP() {
        return zzd.zzSV;
    }

    public boolean zzhQ() {
        if (this.zzGS == null) {
            synchronized (this) {
                if (this.zzGS == null) {
                    Context context = this.zzEb.getContext();
                    ApplicationInfo applicationInfo = context.getApplicationInfo();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                        if (activityManager != null) {
                            int myPid = Process.myPid();
                            Iterator<ActivityManager.RunningAppProcessInfo> it = activityManager.getRunningAppProcesses().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                ActivityManager.RunningAppProcessInfo next = it.next();
                                if (myPid == next.pid) {
                                    this.zzGS = Boolean.valueOf(str != null && str.equals(next.processName));
                                }
                            }
                        }
                    }
                    if (this.zzGS == null) {
                        this.zzGS = Boolean.TRUE;
                        this.zzEb.zzgH().zzaJ("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzGS.booleanValue();
    }

    public boolean zzhR() {
        return zzx.zzHe.get().booleanValue();
    }

    public int zzhS() {
        return zzx.zzHx.get().intValue();
    }

    public int zzhT() {
        return zzx.zzHB.get().intValue();
    }

    public int zzhU() {
        return zzx.zzHC.get().intValue();
    }

    public int zzhV() {
        return zzx.zzHD.get().intValue();
    }

    public long zzhW() {
        return zzx.zzHm.get().longValue();
    }

    public long zzhX() {
        return zzx.zzHl.get().longValue();
    }

    public long zzhY() {
        return zzx.zzHp.get().longValue();
    }

    public long zzhZ() {
        return zzx.zzHq.get().longValue();
    }

    public int zzia() {
        return zzx.zzHr.get().intValue();
    }

    public int zzib() {
        return zzx.zzHs.get().intValue();
    }

    public long zzic() {
        return (long) zzx.zzHF.get().intValue();
    }

    public String zzid() {
        return zzx.zzHu.get();
    }

    public String zzie() {
        return zzx.zzHt.get();
    }

    public String zzif() {
        return zzx.zzHv.get();
    }

    public String zzig() {
        return zzx.zzHw.get();
    }

    public zzl zzih() {
        return zzl.zzaO(zzx.zzHy.get());
    }

    public zzn zzii() {
        return zzn.zzaP(zzx.zzHz.get());
    }

    public Set<Integer> zzij() {
        String str = zzx.zzHE.get();
        if (this.zzGU == null || this.zzGT == null || !this.zzGT.equals(str)) {
            String[] split = TextUtils.split(str, ",");
            HashSet hashSet = new HashSet();
            for (String parseInt : split) {
                try {
                    hashSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
                } catch (NumberFormatException e) {
                }
            }
            this.zzGT = str;
            this.zzGU = hashSet;
        }
        return this.zzGU;
    }

    public long zzik() {
        return zzx.zzHN.get().longValue();
    }

    public long zzil() {
        return zzx.zzHO.get().longValue();
    }

    public long zzim() {
        return zzx.zzHR.get().longValue();
    }

    public int zzin() {
        return zzx.zzHi.get().intValue();
    }

    public int zzio() {
        return zzx.zzHk.get().intValue();
    }

    public String zzip() {
        return "google_analytics_v4.db";
    }

    public String zziq() {
        return "google_analytics2_v4.db";
    }

    public long zzir() {
        return 86400000;
    }

    public int zzis() {
        return zzx.zzHH.get().intValue();
    }

    public int zzit() {
        return zzx.zzHI.get().intValue();
    }

    public long zziu() {
        return zzx.zzHJ.get().longValue();
    }

    public long zziv() {
        return zzx.zzHS.get().longValue();
    }
}
