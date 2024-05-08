package com.google.android.gms.playlog.internal;

import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzoc;
import java.util.ArrayList;

public class zzb {
    private final ArrayList<zza> zzayu;
    private int zzayv;

    public static class zza {
        public final PlayLoggerContext zzayw;
        public final LogEvent zzayx;
        public final zzoc.zzd zzayy;

        private zza(PlayLoggerContext playLoggerContext, LogEvent logEvent) {
            this.zzayw = (PlayLoggerContext) zzv.zzr(playLoggerContext);
            this.zzayx = (LogEvent) zzv.zzr(logEvent);
            this.zzayy = null;
        }
    }

    public zzb() {
        this(100);
    }

    public zzb(int i) {
        this.zzayu = new ArrayList<>();
        this.zzayv = i;
    }

    private void zzvs() {
        while (getSize() > getCapacity()) {
            this.zzayu.remove(0);
        }
    }

    public void clear() {
        this.zzayu.clear();
    }

    public int getCapacity() {
        return this.zzayv;
    }

    public int getSize() {
        return this.zzayu.size();
    }

    public boolean isEmpty() {
        return this.zzayu.isEmpty();
    }

    public void zza(PlayLoggerContext playLoggerContext, LogEvent logEvent) {
        this.zzayu.add(new zza(playLoggerContext, logEvent));
        zzvs();
    }

    public ArrayList<zza> zzvr() {
        return this.zzayu;
    }
}
