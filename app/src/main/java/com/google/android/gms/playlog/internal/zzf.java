package com.google.android.gms.playlog.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzny;
import com.google.android.gms.playlog.internal.zza;
import com.google.android.gms.playlog.internal.zzb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class zzf extends zzi<zza> {
    private final String zzJd;
    private final zzd zzayI;
    private final zzb zzayJ = new zzb();
    private boolean zzayK = true;
    private final Object zzoe = new Object();

    public zzf(Context context, Looper looper, zzd zzd, zze zze) {
        super(context, looper, 24, zzd, zzd, zze);
        this.zzJd = context.getPackageName();
        this.zzayI = (zzd) zzv.zzr(zzd);
        this.zzayI.zza(this);
    }

    private void zzc(PlayLoggerContext playLoggerContext, LogEvent logEvent) {
        this.zzayJ.zza(playLoggerContext, logEvent);
    }

    private void zzd(PlayLoggerContext playLoggerContext, LogEvent logEvent) {
        try {
            zzvt();
            ((zza) zzlX()).zza(this.zzJd, playLoggerContext, logEvent);
        } catch (RemoteException e) {
            Log.e("PlayLoggerImpl", "Couldn't send log event.  Will try caching.");
            zzc(playLoggerContext, logEvent);
        } catch (IllegalStateException e2) {
            Log.e("PlayLoggerImpl", "Service was disconnected.  Will try caching.");
            zzc(playLoggerContext, logEvent);
        }
    }

    private void zzvt() {
        PlayLoggerContext playLoggerContext;
        zzb.zzP(!this.zzayK);
        if (!this.zzayJ.isEmpty()) {
            PlayLoggerContext playLoggerContext2 = null;
            try {
                ArrayList arrayList = new ArrayList();
                Iterator<zzb.zza> it = this.zzayJ.zzvr().iterator();
                while (it.hasNext()) {
                    zzb.zza next = it.next();
                    if (next.zzayy != null) {
                        ((zza) zzlX()).zza(this.zzJd, next.zzayw, zzny.zzf(next.zzayy));
                    } else {
                        if (next.zzayw.equals(playLoggerContext2)) {
                            arrayList.add(next.zzayx);
                            playLoggerContext = playLoggerContext2;
                        } else {
                            if (!arrayList.isEmpty()) {
                                ((zza) zzlX()).zza(this.zzJd, playLoggerContext2, (List<LogEvent>) arrayList);
                                arrayList.clear();
                            }
                            PlayLoggerContext playLoggerContext3 = next.zzayw;
                            arrayList.add(next.zzayx);
                            playLoggerContext = playLoggerContext3;
                        }
                        playLoggerContext2 = playLoggerContext;
                    }
                }
                if (!arrayList.isEmpty()) {
                    ((zza) zzlX()).zza(this.zzJd, playLoggerContext2, (List<LogEvent>) arrayList);
                }
                this.zzayJ.clear();
            } catch (RemoteException e) {
                Log.e("PlayLoggerImpl", "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void start() {
        /*
            r3 = this;
            java.lang.Object r1 = r3.zzoe
            monitor-enter(r1)
            boolean r0 = r3.isConnecting()     // Catch:{ all -> 0x001c }
            if (r0 != 0) goto L_0x000f
            boolean r0 = r3.isConnected()     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0011
        L_0x000f:
            monitor-exit(r1)     // Catch:{ all -> 0x001c }
        L_0x0010:
            return
        L_0x0011:
            com.google.android.gms.playlog.internal.zzd r0 = r3.zzayI     // Catch:{ all -> 0x001c }
            r2 = 1
            r0.zzae(r2)     // Catch:{ all -> 0x001c }
            r3.connect()     // Catch:{ all -> 0x001c }
            monitor-exit(r1)     // Catch:{ all -> 0x001c }
            goto L_0x0010
        L_0x001c:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.playlog.internal.zzf.start():void");
    }

    public void stop() {
        synchronized (this.zzoe) {
            this.zzayI.zzae(false);
            disconnect();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzaf(boolean z) {
        synchronized (this.zzoe) {
            boolean z2 = this.zzayK;
            this.zzayK = z;
            if (z2 && !this.zzayK) {
                zzvt();
            }
        }
    }

    public void zzb(PlayLoggerContext playLoggerContext, LogEvent logEvent) {
        synchronized (this.zzoe) {
            if (this.zzayK) {
                zzc(playLoggerContext, logEvent);
            } else {
                zzd(playLoggerContext, logEvent);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzcu */
    public zza zzD(IBinder iBinder) {
        return zza.C0022zza.zzct(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeq() {
        return "com.google.android.gms.playlog.service.START";
    }

    /* access modifiers changed from: protected */
    public String zzer() {
        return "com.google.android.gms.playlog.internal.IPlayLogService";
    }
}
