package com.google.android.gms.signin.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.internal.zzo;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzmd;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.signin.internal.zzd;
import com.google.android.gms.signin.internal.zzf;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class zzh extends zzi<zzf> implements zzmd {
    private final zze zzQg;
    private final zzme zzSY;
    private Integer zzSZ;
    private final ExecutorService zzaBN;

    private static class zza extends zzd.zza {
        private final zzme zzSY;
        private final ExecutorService zzaBN;

        public zza(zzme zzme, ExecutorService executorService) {
            this.zzSY = zzme;
            this.zzaBN = executorService;
        }

        /* access modifiers changed from: private */
        public GoogleApiClient.ServerAuthCodeCallbacks zzwh() throws RemoteException {
            return this.zzSY.zzwh();
        }

        public void zza(final String str, final String str2, final zzf zzf) throws RemoteException {
            this.zzaBN.submit(new Runnable() {
                public void run() {
                    try {
                        zzf.zzag(zza.this.zzwh().onUploadServerAuthCode(str, str2));
                    } catch (RemoteException e) {
                        Log.e("SignInClientImpl", "RemoteException thrown when processing uploadServerAuthCode callback", e);
                    }
                }
            });
        }

        public void zza(final String str, final List<Scope> list, final zzf zzf) throws RemoteException {
            this.zzaBN.submit(new Runnable() {
                public void run() {
                    try {
                        GoogleApiClient.ServerAuthCodeCallbacks.CheckResult onCheckServerAuthorization = zza.this.zzwh().onCheckServerAuthorization(str, Collections.unmodifiableSet(new HashSet(list)));
                        zzf.zza(new CheckServerAuthResult(onCheckServerAuthorization.zzkN(), onCheckServerAuthorization.zzkO()));
                    } catch (RemoteException e) {
                        Log.e("SignInClientImpl", "RemoteException thrown when processing checkServerAuthorization callback", e);
                    }
                }
            });
        }
    }

    public zzh(Context context, Looper looper, zze zze, zzme zzme, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, ExecutorService executorService) {
        super(context, looper, 44, connectionCallbacks, onConnectionFailedListener, zze);
        this.zzQg = zze;
        this.zzSY = zzme;
        this.zzSZ = zze.zzlN();
        this.zzaBN = executorService;
    }

    public static Bundle zza(zzme zzme, Integer num, ExecutorService executorService) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzme.zzwf());
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzme.zzwg());
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzme.zzvx());
        if (zzme.zzwh() != null) {
            bundle.putParcelable("com.google.android.gms.signin.internal.signInCallbacks", new BinderWrapper(new zza(zzme, executorService).asBinder()));
        }
        if (num != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
        }
        return bundle;
    }

    public void zza(zzo zzo, Set<Scope> set, zze zze) {
        zzv.zzb(zze, (Object) "Expecting a valid ISignInCallbacks");
        try {
            ((zzf) zzlX()).zza(new AuthAccountRequest(zzo, set), zze);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when authAccount is called");
            try {
                zze.zza(new ConnectionResult(8, (PendingIntent) null), new AuthAccountResult());
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onAuthAccount should be executed from the same process, unexpected RemoteException.");
            }
        }
    }

    public void zza(zzo zzo, boolean z) {
        try {
            ((zzf) zzlX()).zza(zzo, this.zzSZ.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void zza(zzr zzr) {
        zzv.zzb(zzr, (Object) "Expecting a valid IResolveAccountCallbacks");
        try {
            ((zzf) zzlX()).zza(new ResolveAccountRequest(this.zzQg.zzlE(), this.zzSZ.intValue()), zzr);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when resolveAccount is called");
            try {
                zzr.zzb(new ResolveAccountResponse(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "IResolveAccountCallbacks#onAccountResolutionComplete should be executed from the same process, unexpected RemoteException.");
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzcI */
    public zzf zzD(IBinder iBinder) {
        return zzf.zza.zzcH(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeq() {
        return "com.google.android.gms.signin.service.START";
    }

    /* access modifiers changed from: protected */
    public String zzer() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    /* access modifiers changed from: protected */
    public Bundle zzka() {
        Bundle zza2 = zza(this.zzSY, this.zzQg.zzlN(), this.zzaBN);
        if (!getContext().getPackageName().equals(this.zzQg.zzlJ())) {
            zza2.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzQg.zzlJ());
        }
        return zza2;
    }

    public void zzwe() {
        try {
            ((zzf) zzlX()).zzhB(this.zzSZ.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }
}
