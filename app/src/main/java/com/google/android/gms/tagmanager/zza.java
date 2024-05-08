package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.zzht;
import com.google.android.gms.internal.zzhv;
import java.io.IOException;

class zza {
    private static Object zzaBX = new Object();
    private static zza zzaBY;
    private volatile boolean mClosed;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Thread zzCg;
    private volatile AdvertisingIdClient.Info zzFp;
    private volatile long zzaBT;
    private volatile long zzaBU;
    private volatile long zzaBV;
    private C0026zza zzaBW;
    private final zzht zznR;

    /* renamed from: com.google.android.gms.tagmanager.zza$zza  reason: collision with other inner class name */
    public interface C0026zza {
        AdvertisingIdClient.Info zzwm();
    }

    private zza(Context context) {
        this(context, (C0026zza) null, zzhv.zznd());
    }

    zza(Context context, C0026zza zza, zzht zzht) {
        this.zzaBT = 900000;
        this.zzaBU = 30000;
        this.mClosed = false;
        this.zzaBW = new C0026zza() {
            public AdvertisingIdClient.Info zzwm() {
                try {
                    return AdvertisingIdClient.getAdvertisingIdInfo(zza.this.mContext);
                } catch (IllegalStateException e) {
                    zzbg.zzan("IllegalStateException getting Advertising Id Info");
                    return null;
                } catch (GooglePlayServicesRepairableException e2) {
                    zzbg.zzan("GooglePlayServicesRepairableException getting Advertising Id Info");
                    return null;
                } catch (IOException e3) {
                    zzbg.zzan("IOException getting Ad Id Info");
                    return null;
                } catch (GooglePlayServicesNotAvailableException e4) {
                    zzbg.zzan("GooglePlayServicesNotAvailableException getting Advertising Id Info");
                    return null;
                } catch (Exception e5) {
                    zzbg.zzan("Unknown exception. Could not get the Advertising Id Info.");
                    return null;
                }
            }
        };
        this.zznR = zzht;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        if (zza != null) {
            this.zzaBW = zza;
        }
        this.zzCg = new Thread(new Runnable() {
            public void run() {
                zza.this.zzwk();
            }
        });
    }

    static zza zzan(Context context) {
        if (zzaBY == null) {
            synchronized (zzaBX) {
                if (zzaBY == null) {
                    zzaBY = new zza(context);
                    zzaBY.start();
                }
            }
        }
        return zzaBY;
    }

    /* access modifiers changed from: private */
    public void zzwk() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            try {
                this.zzFp = this.zzaBW.zzwm();
                Thread.sleep(this.zzaBT);
            } catch (InterruptedException e) {
                zzbg.zzal("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    private void zzwl() {
        if (this.zznR.currentTimeMillis() - this.zzaBV >= this.zzaBU) {
            interrupt();
            this.zzaBV = this.zznR.currentTimeMillis();
        }
    }

    /* access modifiers changed from: package-private */
    public void interrupt() {
        this.zzCg.interrupt();
    }

    public boolean isLimitAdTrackingEnabled() {
        zzwl();
        if (this.zzFp == null) {
            return true;
        }
        return this.zzFp.isLimitAdTrackingEnabled();
    }

    /* access modifiers changed from: package-private */
    public void start() {
        this.zzCg.start();
    }

    public String zzwj() {
        zzwl();
        if (this.zzFp == null) {
            return null;
        }
        return this.zzFp.getId();
    }
}
