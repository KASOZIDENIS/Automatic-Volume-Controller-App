package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzv;

public final class zzme implements Api.ApiOptions.Optional {
    public static final zzme zzaBD = new zza().zzwi();
    private final boolean zzaBE;
    private final boolean zzaBF;
    private final String zzaBG;
    private final GoogleApiClient.ServerAuthCodeCallbacks zzaBH;

    public static final class zza {
        private boolean zzaBI;
        private boolean zzaBJ;
        private GoogleApiClient.ServerAuthCodeCallbacks zzaBK;
        private String zzayN;

        private String zzds(String str) {
            zzv.zzr(str);
            zzv.zzb(this.zzayN == null || this.zzayN.equals(str), (Object) "two different server client ids provided");
            return str;
        }

        public zza zza(String str, GoogleApiClient.ServerAuthCodeCallbacks serverAuthCodeCallbacks) {
            this.zzaBI = true;
            this.zzaBJ = true;
            this.zzayN = zzds(str);
            this.zzaBK = (GoogleApiClient.ServerAuthCodeCallbacks) zzv.zzr(serverAuthCodeCallbacks);
            return this;
        }

        public zzme zzwi() {
            return new zzme(this.zzaBI, this.zzaBJ, this.zzayN, this.zzaBK);
        }
    }

    private zzme(boolean z, boolean z2, String str, GoogleApiClient.ServerAuthCodeCallbacks serverAuthCodeCallbacks) {
        this.zzaBE = z;
        this.zzaBF = z2;
        this.zzaBG = str;
        this.zzaBH = serverAuthCodeCallbacks;
    }

    public String zzvx() {
        return this.zzaBG;
    }

    public boolean zzwf() {
        return this.zzaBE;
    }

    public boolean zzwg() {
        return this.zzaBF;
    }

    public GoogleApiClient.ServerAuthCodeCallbacks zzwh() {
        return this.zzaBH;
    }
}
