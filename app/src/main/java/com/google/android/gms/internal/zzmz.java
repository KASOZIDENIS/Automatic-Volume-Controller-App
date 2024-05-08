package com.google.android.gms.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzmy;
import com.google.android.gms.tagmanager.zzbg;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class zzmz implements Runnable {
    private final Context mContext;
    private final zzmx zzaEE;
    private final zzmo zzaHH;
    private final zzmy zzaHO;
    private final zzmt zzaHP;

    public zzmz(Context context, zzmo zzmo, zzmy zzmy) {
        this(context, zzmo, zzmy, new zzmx(), new zzmt());
    }

    zzmz(Context context, zzmo zzmo, zzmy zzmy, zzmx zzmx, zzmt zzmt) {
        zzv.zzr(context);
        zzv.zzr(zzmy);
        this.mContext = context;
        this.zzaHH = zzmo;
        this.zzaHO = zzmy;
        this.zzaEE = zzmx;
        this.zzaHP = zzmt;
    }

    public zzmz(Context context, zzmo zzmo, zzmy zzmy, String str) {
        this(context, zzmo, zzmy, new zzmx(), new zzmt());
        this.zzaHP.zzem(str);
    }

    public void run() {
        zzdF();
    }

    /* access modifiers changed from: package-private */
    public boolean zzaM(String str) {
        return this.mContext.getPackageManager().checkPermission(str, this.mContext.getPackageName()) == 0;
    }

    /* access modifiers changed from: package-private */
    public void zzdF() {
        String zzu;
        if (!zzyO()) {
            this.zzaHO.zza(zzmy.zza.NOT_AVAILABLE);
            return;
        }
        zzbg.zzam("NetworkLoader: Starting to load resource from Network.");
        zzmw zzyM = this.zzaEE.zzyM();
        try {
            zzu = this.zzaHP.zzu(this.zzaHH.zzyl());
            InputStream zzev = zzyM.zzev(zzu);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzhy.zza(zzev, (OutputStream) byteArrayOutputStream);
                this.zzaHO.zzq(byteArrayOutputStream.toByteArray());
                zzyM.close();
                zzbg.zzam("NetworkLoader: Resource loaded.");
            } catch (IOException e) {
                zzbg.zzb("NetworkLoader: Error when parsing downloaded resources from url: " + zzu + " " + e.getMessage(), e);
                this.zzaHO.zza(zzmy.zza.SERVER_ERROR);
            }
        } catch (FileNotFoundException e2) {
            zzbg.zzak("NetworkLoader: No data is retrieved from the given url: " + zzu);
            this.zzaHO.zza(zzmy.zza.SERVER_ERROR);
        } catch (IOException e3) {
            zzbg.zzb("NetworkLoader: Error when loading resource from url: " + zzu + " " + e3.getMessage(), e3);
            this.zzaHO.zza(zzmy.zza.IO_ERROR);
        } finally {
            zzyM.close();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzyO() {
        if (!zzaM("android.permission.INTERNET")) {
            zzbg.zzak("Missing android.permission.INTERNET. Please add the following declaration to your AndroidManifest.xml: <uses-permission android:name=\"android.permission.INTERNET\" />");
            return false;
        } else if (!zzaM("android.permission.ACCESS_NETWORK_STATE")) {
            zzbg.zzak("Missing android.permission.ACCESS_NETWORK_STATE. Please add the following declaration to your AndroidManifest.xml: <uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />");
            return false;
        } else {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
            zzbg.zzan("NetworkLoader: No network connectivity - Offline");
            return false;
        }
    }
}
