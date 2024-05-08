package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.v7.internal.widget.ActivityChooserView;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.signin.internal.zzg;
import com.google.android.gms.signin.internal.zzh;
import java.util.concurrent.Executors;

public final class zzmb {
    public static final Api<zzme> API = new Api<>("SignIn.API", zzKi, zzKh, new Scope[0]);
    public static final Api.zzc<zzh> zzKh = new Api.zzc<>();
    public static final Api.zzb<zzh, zzme> zzKi = new Api.zzb<zzh, zzme>() {
        public int getPriority() {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }

        public zzh zza(Context context, Looper looper, zze zze, zzme zzme, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new zzh(context, looper, zze, zzme == null ? zzme.zzaBD : zzme, connectionCallbacks, onConnectionFailedListener, Executors.newSingleThreadExecutor());
        }
    };
    public static final zzmc zzaBC = new zzg();
}
