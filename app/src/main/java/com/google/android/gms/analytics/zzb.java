package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.internal.zzaa;
import com.google.android.gms.analytics.internal.zzal;
import com.google.android.gms.analytics.internal.zzc;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzg;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzgm;
import com.google.android.gms.internal.zzgn;
import com.google.android.gms.internal.zzgo;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzkg;
import com.google.android.gms.internal.zzkm;
import com.google.android.gms.internal.zzkp;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzku;
import com.google.android.gms.internal.zzkv;
import com.google.android.gms.internal.zzkw;
import com.google.android.gms.internal.zzkx;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class zzb extends zzc implements zzkm {
    private static DecimalFormat zzEl;
    private final zze zzEb;
    private final String zzEm;
    private final Uri zzEn;
    private final boolean zzEo;
    private final boolean zzEp;

    public zzb(zze zze, String str) {
        this(zze, str, true, false);
    }

    public zzb(zze zze, String str, boolean z, boolean z2) {
        super(zze);
        zzv.zzbS(str);
        this.zzEb = zze;
        this.zzEm = str;
        this.zzEo = z;
        this.zzEp = z2;
        this.zzEn = zzav(this.zzEm);
    }

    static String zza(double d) {
        if (zzEl == null) {
            zzEl = new DecimalFormat("0.######");
        }
        return zzEl.format(d);
    }

    private static void zza(Map<String, String> map, String str, double d) {
        if (d != 0.0d) {
            map.put(str, zza(d));
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i > 0 && i2 > 0) {
            map.put(str, i + "x" + i2);
        }
    }

    private static void zza(Map<String, String> map, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    private static void zza(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    static Uri zzav(String str) {
        zzv.zzbS(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    public static Map<String, String> zzc(zzkg zzkg) {
        HashMap hashMap = new HashMap();
        zzgo zzgo = (zzgo) zzkg.zzd(zzgo.class);
        if (zzgo != null) {
            for (Map.Entry next : zzgo.zzgj().entrySet()) {
                String zze = zze(next.getValue());
                if (zze != null) {
                    hashMap.put(next.getKey(), zze);
                }
            }
        }
        zzgp zzgp = (zzgp) zzkg.zzd(zzgp.class);
        if (zzgp != null) {
            zza((Map<String, String>) hashMap, "t", zzgp.zzgk());
            zza((Map<String, String>) hashMap, "cid", zzgp.getClientId());
            zza((Map<String, String>) hashMap, "uid", zzgp.zzgl());
            zza((Map<String, String>) hashMap, "sc", zzgp.zzgo());
            zza((Map<String, String>) hashMap, "sf", zzgp.zzgq());
            zza((Map<String, String>) hashMap, "ni", zzgp.zzgp());
            zza((Map<String, String>) hashMap, "adid", zzgp.zzgm());
            zza((Map<String, String>) hashMap, "ate", zzgp.zzgn());
        }
        zzkv zzkv = (zzkv) zzkg.zzd(zzkv.class);
        if (zzkv != null) {
            zza((Map<String, String>) hashMap, "cd", zzkv.zzuL());
            zza((Map<String, String>) hashMap, "a", (double) zzkv.zzaJ());
            zza((Map<String, String>) hashMap, "dr", zzkv.zzuO());
        }
        zzkt zzkt = (zzkt) zzkg.zzd(zzkt.class);
        if (zzkt != null) {
            zza((Map<String, String>) hashMap, "ec", zzkt.zzuI());
            zza((Map<String, String>) hashMap, "ea", zzkt.getAction());
            zza((Map<String, String>) hashMap, "el", zzkt.getLabel());
            zza((Map<String, String>) hashMap, "ev", (double) zzkt.getValue());
        }
        zzkq zzkq = (zzkq) zzkg.zzd(zzkq.class);
        if (zzkq != null) {
            zza((Map<String, String>) hashMap, "cn", zzkq.getName());
            zza((Map<String, String>) hashMap, "cs", zzkq.getSource());
            zza((Map<String, String>) hashMap, "cm", zzkq.zzut());
            zza((Map<String, String>) hashMap, "ck", zzkq.zzuu());
            zza((Map<String, String>) hashMap, "cc", zzkq.getContent());
            zza((Map<String, String>) hashMap, "ci", zzkq.getId());
            zza((Map<String, String>) hashMap, "anid", zzkq.zzuv());
            zza((Map<String, String>) hashMap, "gclid", zzkq.zzuw());
            zza((Map<String, String>) hashMap, "dclid", zzkq.zzux());
            zza((Map<String, String>) hashMap, "aclid", zzkq.zzuy());
        }
        zzku zzku = (zzku) zzkg.zzd(zzku.class);
        if (zzku != null) {
            zza((Map<String, String>) hashMap, "exd", zzku.getDescription());
            zza((Map<String, String>) hashMap, "exf", zzku.zzuJ());
        }
        zzkw zzkw = (zzkw) zzkg.zzd(zzkw.class);
        if (zzkw != null) {
            zza((Map<String, String>) hashMap, "sn", zzkw.zzuS());
            zza((Map<String, String>) hashMap, "sa", zzkw.getAction());
            zza((Map<String, String>) hashMap, "st", zzkw.getTarget());
        }
        zzkx zzkx = (zzkx) zzkg.zzd(zzkx.class);
        if (zzkx != null) {
            zza((Map<String, String>) hashMap, "utv", zzkx.zzuT());
            zza((Map<String, String>) hashMap, "utt", (double) zzkx.getTimeInMillis());
            zza((Map<String, String>) hashMap, "utc", zzkx.zzuI());
            zza((Map<String, String>) hashMap, "utl", zzkx.getLabel());
        }
        zzgm zzgm = (zzgm) zzkg.zzd(zzgm.class);
        if (zzgm != null) {
            for (Map.Entry next2 : zzgm.zzgh().entrySet()) {
                String zzF = zzc.zzF(((Integer) next2.getKey()).intValue());
                if (!TextUtils.isEmpty(zzF)) {
                    hashMap.put(zzF, next2.getValue());
                }
            }
        }
        zzgn zzgn = (zzgn) zzkg.zzd(zzgn.class);
        if (zzgn != null) {
            for (Map.Entry next3 : zzgn.zzgi().entrySet()) {
                String zzH = zzc.zzH(((Integer) next3.getKey()).intValue());
                if (!TextUtils.isEmpty(zzH)) {
                    hashMap.put(zzH, zza(((Double) next3.getValue()).doubleValue()));
                }
            }
        }
        zzks zzks = (zzks) zzkg.zzd(zzks.class);
        if (zzks != null) {
            ProductAction zzuE = zzks.zzuE();
            if (zzuE != null) {
                for (Map.Entry next4 : zzuE.build().entrySet()) {
                    if (((String) next4.getKey()).startsWith("&")) {
                        hashMap.put(((String) next4.getKey()).substring(1), next4.getValue());
                    } else {
                        hashMap.put(next4.getKey(), next4.getValue());
                    }
                }
            }
            int i = 1;
            for (Promotion zzaC : zzks.zzuH()) {
                hashMap.putAll(zzaC.zzaC(zzc.zzL(i)));
                i++;
            }
            int i2 = 1;
            for (Product zzaC2 : zzks.zzuF()) {
                hashMap.putAll(zzaC2.zzaC(zzc.zzJ(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry next5 : zzks.zzuG().entrySet()) {
                String zzO = zzc.zzO(i3);
                int i4 = 1;
                for (Product zzaC3 : (List) next5.getValue()) {
                    hashMap.putAll(zzaC3.zzaC(zzO + zzc.zzM(i4)));
                    i4++;
                }
                if (!TextUtils.isEmpty((CharSequence) next5.getKey())) {
                    hashMap.put(zzO + "nm", next5.getKey());
                }
                i3++;
            }
        }
        zzkr zzkr = (zzkr) zzkg.zzd(zzkr.class);
        if (zzkr != null) {
            zza((Map<String, String>) hashMap, "ul", zzkr.getLanguage());
            zza((Map<String, String>) hashMap, "sd", (double) zzkr.zzuz());
            zza(hashMap, "sr", zzkr.zzuA(), zzkr.zzuB());
            zza(hashMap, "vp", zzkr.zzuC(), zzkr.zzuD());
        }
        zzkp zzkp = (zzkp) zzkg.zzd(zzkp.class);
        if (zzkp != null) {
            zza((Map<String, String>) hashMap, "an", zzkp.zziE());
            zza((Map<String, String>) hashMap, "aid", zzkp.zzqT());
            zza((Map<String, String>) hashMap, "aiid", zzkp.zzus());
            zza((Map<String, String>) hashMap, "av", zzkp.zziG());
        }
        return hashMap;
    }

    private static String zze(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return str;
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.doubleValue() != 0.0d) {
                return zza(d.doubleValue());
            }
            return null;
        } else if (!(obj instanceof Boolean)) {
            return String.valueOf(obj);
        } else {
            if (obj != Boolean.FALSE) {
                return "1";
            }
            return null;
        }
    }

    private static String zzy(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append((String) next.getKey());
            sb.append("=");
            sb.append((String) next.getValue());
        }
        return sb.toString();
    }

    public void zzb(zzkg zzkg) {
        zzv.zzr(zzkg);
        zzv.zzb(zzkg.zzue(), (Object) "Can't deliver not submitted measurement");
        zzv.zzbJ("deliver should be called on worker thread");
        zzkg zztZ = zzkg.zztZ();
        zzgp zzgp = (zzgp) zztZ.zze(zzgp.class);
        if (TextUtils.isEmpty(zzgp.zzgk())) {
            zzgH().zze(zzc(zztZ), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(zzgp.getClientId())) {
            zzgH().zze(zzc(zztZ), "Ignoring measurement without client id");
        } else if (!this.zzEb.zzgV().getAppOptOut()) {
            double zzgq = zzgp.zzgq();
            if (zzal.zza(zzgq, zzgp.getClientId())) {
                zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(zzgq));
                return;
            }
            Map<String, String> zzc = zzc(zztZ);
            zzc.put("v", "1");
            zzc.put("_v", "ma4.5.0");
            zzc.put("tid", this.zzEm);
            if (this.zzEb.zzgV().isDryRunEnabled()) {
                zzc("Dry run is enabled. GoogleAnalytics would have sent", zzy(zzc));
                return;
            }
            HashMap hashMap = new HashMap();
            zzal.zzb((Map<String, String>) hashMap, "uid", zzgp.zzgl());
            zzkp zzkp = (zzkp) zzkg.zzd(zzkp.class);
            if (zzkp != null) {
                zzal.zzb((Map<String, String>) hashMap, "an", zzkp.zziE());
                zzal.zzb((Map<String, String>) hashMap, "aid", zzkp.zzqT());
                zzal.zzb((Map<String, String>) hashMap, "av", zzkp.zziG());
                zzal.zzb((Map<String, String>) hashMap, "aiid", zzkp.zzus());
            }
            zzc.put("_s", String.valueOf(zzfZ().zza(new zzg(0, zzgp.getClientId(), this.zzEm, !TextUtils.isEmpty(zzgp.zzgm()), 0, hashMap))));
            zzfZ().zza(new zzaa(zzgH(), zzc, zzkg.zzuc(), true));
        }
    }

    public Uri zzfR() {
        return this.zzEn;
    }
}
