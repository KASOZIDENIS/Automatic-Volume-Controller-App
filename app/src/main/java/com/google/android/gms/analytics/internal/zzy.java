package com.google.android.gms.analytics.internal;

import com.google.android.gms.analytics.internal.zzp;

public class zzy extends zzp<zzz> {

    private static class zza implements zzp.zza<zzz> {
        private final zze zzFD;
        private final zzz zzHX = new zzz();

        public zza(zze zze) {
            this.zzFD = zze;
        }

        public void zzc(String str, int i) {
            if ("ga_dispatchPeriod".equals(str)) {
                this.zzHX.zzIb = i;
            } else {
                this.zzFD.zzgH().zzd("Int xml configuration name not recognized", str);
            }
        }

        public void zzc(String str, boolean z) {
            if ("ga_dryRun".equals(str)) {
                this.zzHX.zzIc = z ? 1 : 0;
                return;
            }
            this.zzFD.zzgH().zzd("Bool xml configuration name not recognized", str);
        }

        /* renamed from: zziC */
        public zzz zzhO() {
            return this.zzHX;
        }

        public void zzj(String str, String str2) {
        }

        public void zzk(String str, String str2) {
            if ("ga_appName".equals(str)) {
                this.zzHX.zzHY = str2;
            } else if ("ga_appVersion".equals(str)) {
                this.zzHX.zzHZ = str2;
            } else if ("ga_logLevel".equals(str)) {
                this.zzHX.zzIa = str2;
            } else {
                this.zzFD.zzgH().zzd("String xml configuration name not recognized", str);
            }
        }
    }

    public zzy(zze zze) {
        super(zze, new zza(zze));
    }
}
