package com.google.android.gms.tagmanager;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class zzdb extends zzak {
    private static final String ID = com.google.android.gms.internal.zza.TIMER_LISTENER.toString();
    private static final String NAME = zzb.NAME.toString();
    private static final String zzaFQ = zzb.INTERVAL.toString();
    private static final String zzaFR = zzb.LIMIT.toString();
    private static final String zzaFS = zzb.UNIQUE_TRIGGER_ID.toString();
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public DataLayer zzaCl;
    /* access modifiers changed from: private */
    public boolean zzaFT;
    /* access modifiers changed from: private */
    public boolean zzaFU;
    private final HandlerThread zzaFV;
    /* access modifiers changed from: private */
    public final Set<String> zzaFW = new HashSet();

    private final class zza implements Runnable {
        private final long zzIG = System.currentTimeMillis();
        private final String zzaFX;
        private final String zzaFY;
        private final long zzaFZ;
        private long zzaGa;
        private final long zzaqe;

        zza(String str, String str2, long j, long j2) {
            this.zzaFX = str;
            this.zzaFY = str2;
            this.zzaqe = j;
            this.zzaFZ = j2;
        }

        public void run() {
            if (this.zzaFZ <= 0 || this.zzaGa < this.zzaFZ) {
                this.zzaGa++;
                if (zzbA()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    zzdb.this.zzaCl.push(DataLayer.mapOf("event", this.zzaFX, "gtm.timerInterval", String.valueOf(this.zzaqe), "gtm.timerLimit", String.valueOf(this.zzaFZ), "gtm.timerStartTime", String.valueOf(this.zzIG), "gtm.timerCurrentTime", String.valueOf(currentTimeMillis), "gtm.timerElapsedTime", String.valueOf(currentTimeMillis - this.zzIG), "gtm.timerEventNumber", String.valueOf(this.zzaGa), "gtm.triggers", this.zzaFY));
                }
                zzdb.this.mHandler.postDelayed(this, this.zzaqe);
            } else if (!"0".equals(this.zzaFY)) {
                zzdb.this.zzaFW.remove(this.zzaFY);
            }
        }

        /* access modifiers changed from: protected */
        public boolean zzbA() {
            if (zzdb.this.zzaFU) {
                return zzdb.this.zzaFT;
            }
            KeyguardManager keyguardManager = (KeyguardManager) zzdb.this.mContext.getSystemService("keyguard");
            PowerManager powerManager = (PowerManager) zzdb.this.mContext.getSystemService("power");
            for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) zzdb.this.mContext.getSystemService("activity")).getRunningAppProcesses()) {
                if (Process.myPid() == next.pid && next.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && powerManager.isScreenOn()) {
                    return true;
                }
            }
            return false;
        }
    }

    public zzdb(Context context, DataLayer dataLayer) {
        super(ID, zzaFQ, NAME);
        this.mContext = context;
        this.zzaCl = dataLayer;
        this.zzaFV = new HandlerThread("Google GTM SDK Timer", 10);
        this.zzaFV.start();
        this.mHandler = new Handler(this.zzaFV.getLooper());
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        long j;
        long j2;
        String zzg = zzdf.zzg(map.get(NAME));
        String zzg2 = zzdf.zzg(map.get(zzaFS));
        String zzg3 = zzdf.zzg(map.get(zzaFQ));
        String zzg4 = zzdf.zzg(map.get(zzaFR));
        try {
            j = Long.parseLong(zzg3);
        } catch (NumberFormatException e) {
            j = 0;
        }
        try {
            j2 = Long.parseLong(zzg4);
        } catch (NumberFormatException e2) {
            j2 = 0;
        }
        if (j > 0 && !TextUtils.isEmpty(zzg)) {
            if (zzg2 == null || zzg2.isEmpty()) {
                zzg2 = "0";
            }
            if (!this.zzaFW.contains(zzg2)) {
                if (!"0".equals(zzg2)) {
                    this.zzaFW.add(zzg2);
                }
                this.mHandler.postDelayed(new zza(zzg, zzg2, j, j2), j);
            }
        }
        return zzdf.zzxW();
    }

    public boolean zzwn() {
        return false;
    }
}
