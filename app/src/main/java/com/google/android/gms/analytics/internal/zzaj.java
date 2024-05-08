package com.google.android.gms.analytics.internal;

import com.google.android.gms.analytics.internal.zzp;

public class zzaj extends zzp<zzak> {

    private static class zza extends zzc implements zzp.zza<zzak> {
        private final zzak zzIH = new zzak();

        public zza(zze zze) {
            super(zze);
        }

        public void zzc(String str, int i) {
            if ("ga_sessionTimeout".equals(str)) {
                this.zzIH.zzIJ = i;
            } else {
                zzd("int configuration name not recognized", str);
            }
        }

        public void zzc(String str, boolean z) {
            int i = 1;
            if ("ga_autoActivityTracking".equals(str)) {
                zzak zzak = this.zzIH;
                if (!z) {
                    i = 0;
                }
                zzak.zzIK = i;
            } else if ("ga_anonymizeIp".equals(str)) {
                zzak zzak2 = this.zzIH;
                if (!z) {
                    i = 0;
                }
                zzak2.zzIL = i;
            } else if ("ga_reportUncaughtExceptions".equals(str)) {
                zzak zzak3 = this.zzIH;
                if (!z) {
                    i = 0;
                }
                zzak3.zzIM = i;
            } else {
                zzd("bool configuration name not recognized", str);
            }
        }

        public void zzj(String str, String str2) {
            this.zzIH.zzIN.put(str, str2);
        }

        /* renamed from: zzjq */
        public zzak zzhO() {
            return this.zzIH;
        }

        public void zzk(String str, String str2) {
            if ("ga_trackingId".equals(str)) {
                this.zzIH.zzEm = str2;
            } else if ("ga_sampleFrequency".equals(str)) {
                try {
                    this.zzIH.zzII = Double.parseDouble(str2);
                } catch (NumberFormatException e) {
                    zzc("Error parsing ga_sampleFrequency value", str2, e);
                }
            } else {
                zzd("string configuration name not recognized", str);
            }
        }
    }

    public zzaj(zze zze) {
        super(zze, new zza(zze));
    }
}
