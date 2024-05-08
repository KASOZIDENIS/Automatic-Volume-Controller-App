package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.zzc;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzmw;
import com.google.android.gms.internal.zzmx;
import com.google.android.gms.tagmanager.zzbf;
import com.google.android.gms.tagmanager.zzcb;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class zzcl implements Runnable {
    private final Context mContext;
    private volatile String zzaCH;
    private final String zzaCk;
    private final zzmx zzaEE;
    private final String zzaEF;
    private zzbf<zzc.zzj> zzaEG;
    private volatile zzs zzaEH;
    private volatile String zzaEI;

    zzcl(Context context, String str, zzmx zzmx, zzs zzs) {
        this.mContext = context;
        this.zzaEE = zzmx;
        this.zzaCk = str;
        this.zzaEH = zzs;
        this.zzaEF = "/r?id=" + str;
        this.zzaCH = this.zzaEF;
        this.zzaEI = null;
    }

    public zzcl(Context context, String str, zzs zzs) {
        this(context, str, new zzmx(), zzs);
    }

    private boolean zzxo() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbg.zzam("...no network connectivity");
        return false;
    }

    private void zzxp() {
        if (!zzxo()) {
            this.zzaEG.zza(zzbf.zza.NOT_AVAILABLE);
            return;
        }
        zzbg.zzam("Start loading resource from network ...");
        String zzxq = zzxq();
        zzmw zzyM = this.zzaEE.zzyM();
        try {
            InputStream zzev = zzyM.zzev(zzxq);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzmq.zzc(zzev, byteArrayOutputStream);
                zzc.zzj zzb = zzc.zzj.zzb(byteArrayOutputStream.toByteArray());
                zzbg.zzam("Successfully loaded supplemented resource: " + zzb);
                if (zzb.zzhh == null && zzb.zzhg.length == 0) {
                    zzbg.zzam("No change for container: " + this.zzaCk);
                }
                this.zzaEG.zzv(zzb);
                zzyM.close();
                zzbg.zzam("Load resource from network finished.");
            } catch (IOException e) {
                zzbg.zzd("Error when parsing downloaded resources from url: " + zzxq + " " + e.getMessage(), e);
                this.zzaEG.zza(zzbf.zza.SERVER_ERROR);
            }
        } catch (FileNotFoundException e2) {
            zzbg.zzan("No data is retrieved from the given url: " + zzxq + ". Make sure container_id: " + this.zzaCk + " is correct.");
            this.zzaEG.zza(zzbf.zza.SERVER_ERROR);
        } catch (IOException e3) {
            zzbg.zzd("Error when loading resources from url: " + zzxq + " " + e3.getMessage(), e3);
            this.zzaEG.zza(zzbf.zza.IO_ERROR);
        } finally {
            zzyM.close();
        }
    }

    public void run() {
        if (this.zzaEG == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.zzaEG.zzwB();
        zzxp();
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbf<zzc.zzj> zzbf) {
        this.zzaEG = zzbf;
    }

    /* access modifiers changed from: package-private */
    public void zzdE(String str) {
        if (str == null) {
            this.zzaCH = this.zzaEF;
            return;
        }
        zzbg.zzaj("Setting CTFE URL path: " + str);
        this.zzaCH = str;
    }

    /* access modifiers changed from: package-private */
    public void zzdT(String str) {
        zzbg.zzaj("Setting previous container version: " + str);
        this.zzaEI = str;
    }

    /* access modifiers changed from: package-private */
    public String zzxq() {
        String str = this.zzaEH.zzwD() + this.zzaCH + "&v=a65833898";
        if (this.zzaEI != null && !this.zzaEI.trim().equals("")) {
            str = str + "&pv=" + this.zzaEI;
        }
        return zzcb.zzxl().zzxm().equals(zzcb.zza.CONTAINER_DEBUG) ? str + "&gtm_debug=x" : str;
    }
}
