package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

class zzdc {
    private Context mContext;
    private Tracker zzEt;
    private GoogleAnalytics zzEv;

    static class zza implements Logger {
        zza() {
        }

        private static int zzhL(int i) {
            switch (i) {
                case 2:
                    return 0;
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                default:
                    return 3;
            }
        }

        public void error(Exception exception) {
            zzbg.zzb("", exception);
        }

        public void error(String message) {
            zzbg.zzak(message);
        }

        public int getLogLevel() {
            return zzhL(zzbg.getLogLevel());
        }

        public void info(String message) {
            zzbg.zzal(message);
        }

        public void setLogLevel(int logLevel) {
            zzbg.zzan("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
        }

        public void verbose(String message) {
            zzbg.zzam(message);
        }

        public void warn(String message) {
            zzbg.zzan(message);
        }
    }

    zzdc(Context context) {
        this.mContext = context;
    }

    private synchronized void zzdZ(String str) {
        if (this.zzEv == null) {
            this.zzEv = GoogleAnalytics.getInstance(this.mContext);
            this.zzEv.setLogger(new zza());
            this.zzEt = this.zzEv.newTracker(str);
        }
    }

    public Tracker zzdY(String str) {
        zzdZ(str);
        return this.zzEt;
    }
}
