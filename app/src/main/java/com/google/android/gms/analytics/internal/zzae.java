package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzv;
import java.util.Map;

public class zzae extends zzd {
    private static String zzIp = "3";
    private static String zzIq = "01VDIWEA?";
    private static zzae zzIr;

    public zzae(zze zze) {
        super(zze);
    }

    public static zzae zziV() {
        return zzIr;
    }

    public void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        String str2 = zzx.zzHf.get();
        if (Log.isLoggable(str2, i)) {
            Log.println(i, str2, zzc(str, obj, obj2, obj3));
        }
        if (i >= 5) {
            zzb(i, str, obj, obj2, obj3);
        }
    }

    public void zza(zzaa zzaa, String str) {
        if (str == null) {
            str = "no reason provided";
        }
        zzd("Discarding hit. " + str, zzaa != null ? zzaa.toString() : "no hit data");
    }

    public synchronized void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        int i2 = 0;
        synchronized (this) {
            zzv.zzr(str);
            if (i >= 0) {
                i2 = i;
            }
            String str2 = zzIp + zzIq.charAt(i2 >= zzIq.length() ? zzIq.length() - 1 : i2) + (zzgI().zzhQ() ? zzgI().zzhP() ? 'P' : 'C' : zzgI().zzhP() ? 'p' : 'c') + String.valueOf(zze.zzgZ()) + ":" + zzc(str, zzh(obj), zzh(obj2), zzh(obj3));
            if (str2.length() > 1024) {
                str2 = str2.substring(0, 1024);
            }
            zzah zzgW = zzgD().zzgW();
            if (zzgW != null) {
                zzgW.zzji().zzaS(str2);
            }
        }
    }

    public void zze(Map<String, String> map, String str) {
        String str2;
        if (str == null) {
            str = "no reason provided";
        }
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry next : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append((String) next.getKey());
                sb.append('=');
                sb.append((String) next.getValue());
            }
            str2 = sb.toString();
        } else {
            str2 = "no hit data";
        }
        zzd("Discarding hit. " + str, str2);
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
        synchronized (zzae.class) {
            zzIr = this;
        }
    }

    /* access modifiers changed from: protected */
    public String zzh(Object obj) {
        if (obj == null) {
            return null;
        }
        Object l = obj instanceof Integer ? new Long((long) ((Integer) obj).intValue()) : obj;
        if (!(l instanceof Long)) {
            return l instanceof Boolean ? String.valueOf(l) : l instanceof Throwable ? l.getClass().getCanonicalName() : "-";
        }
        if (Math.abs(((Long) l).longValue()) < 100) {
            return String.valueOf(l);
        }
        String str = String.valueOf(l).charAt(0) == '-' ? "-" : "";
        String valueOf = String.valueOf(Math.abs(((Long) l).longValue()));
        return str + Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1))) + "..." + str + Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
    }
}
