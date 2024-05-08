package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzlk;

public class zzd implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private boolean zzayA = true;
    private zzf zzayp = null;
    private final zzlk.zza zzayz;

    public zzd(zzlk.zza zza) {
        this.zzayz = zza;
    }

    public void onConnected(Bundle connectionHint) {
        this.zzayp.zzaf(false);
        if (this.zzayA && this.zzayz != null) {
            this.zzayz.zzvp();
        }
        this.zzayA = false;
    }

    public void onConnectionFailed(ConnectionResult result) {
        this.zzayp.zzaf(true);
        if (this.zzayA && this.zzayz != null) {
            if (result.hasResolution()) {
                this.zzayz.zzf(result.getResolution());
            } else {
                this.zzayz.zzvq();
            }
        }
        this.zzayA = false;
    }

    public void onConnectionSuspended(int cause) {
        this.zzayp.zzaf(true);
    }

    public void zza(zzf zzf) {
        this.zzayp = zzf;
    }

    public void zzae(boolean z) {
        this.zzayA = z;
    }
}
