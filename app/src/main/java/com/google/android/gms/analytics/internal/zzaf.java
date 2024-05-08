package com.google.android.gms.analytics.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.common.internal.zzv;

class zzaf extends BroadcastReceiver {
    static final String zzIs = zzaf.class.getName();
    private final zze zzFD;
    private boolean zzIt;
    private boolean zzIu;

    zzaf(zze zze) {
        zzv.zzr(zze);
        this.zzFD = zze;
    }

    private Context getContext() {
        return this.zzFD.getContext();
    }

    private zzb zzfZ() {
        return this.zzFD.zzfZ();
    }

    private zzae zzgH() {
        return this.zzFD.zzgH();
    }

    private void zziX() {
        zzgH();
        zzfZ();
    }

    public boolean isConnected() {
        if (!this.zzIt) {
            this.zzFD.zzgH().zzaI("Connectivity unknown. Receiver not registered");
        }
        return this.zzIu;
    }

    public boolean isRegistered() {
        return this.zzIt;
    }

    public void onReceive(Context ctx, Intent intent) {
        zziX();
        String action = intent.getAction();
        this.zzFD.zzgH().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zziZ = zziZ();
            if (this.zzIu != zziZ) {
                this.zzIu = zziZ;
                zzfZ().zzG(zziZ);
            }
        } else if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
            this.zzFD.zzgH().zzd("NetworkBroadcastReceiver received unknown action", action);
        } else if (!intent.hasExtra(zzIs)) {
            zzfZ().zzgA();
        }
    }

    public void unregister() {
        if (isRegistered()) {
            this.zzFD.zzgH().zzaF("Unregistering connectivity change receiver");
            this.zzIt = false;
            this.zzIu = false;
            try {
                getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                zzgH().zze("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    public void zziW() {
        zziX();
        if (!this.zzIt) {
            Context context = getContext();
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
            intentFilter.addCategory(context.getPackageName());
            context.registerReceiver(this, intentFilter);
            this.zzIu = zziZ();
            this.zzFD.zzgH().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzIu));
            this.zzIt = true;
        }
    }

    public void zziY() {
        if (Build.VERSION.SDK_INT > 10) {
            Context context = getContext();
            Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
            intent.addCategory(context.getPackageName());
            intent.putExtra(zzIs, true);
            context.sendOrderedBroadcast(intent, (String) null);
        }
    }

    /* access modifiers changed from: protected */
    public boolean zziZ() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (SecurityException e) {
            return false;
        }
    }
}
