package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.zzg;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zzo;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzmd;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.signin.internal.AuthAccountResult;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public class zze implements zzh {
    private final Context mContext;
    private final Api.zzb<? extends zzmd, zzme> zzPK;
    /* access modifiers changed from: private */
    public final zzg zzPQ;
    /* access modifiers changed from: private */
    public final Lock zzPR;
    private ConnectionResult zzPS;
    private int zzPT;
    private int zzPU = 0;
    private boolean zzPV = false;
    private int zzPW;
    private final Bundle zzPX = new Bundle();
    private final Set<Api.zzc> zzPY = new HashSet();
    /* access modifiers changed from: private */
    public zzmd zzPZ;
    private int zzQa;
    private boolean zzQb;
    private boolean zzQc;
    private zzo zzQd;
    private boolean zzQe;
    private boolean zzQf;
    private final com.google.android.gms.common.internal.zze zzQg;
    private final Map<Api<?>, Integer> zzQh;

    private static class zza extends com.google.android.gms.signin.internal.zzb {
        private final WeakReference<zze> zzQi;

        zza(zze zze) {
            this.zzQi = new WeakReference<>(zze);
        }

        public void zza(final ConnectionResult connectionResult, AuthAccountResult authAccountResult) {
            final zze zze = (zze) this.zzQi.get();
            if (zze != null) {
                zze.zzPQ.zzQx.post(new Runnable() {
                    public void run() {
                        zze.zzc(connectionResult);
                    }
                });
            }
        }
    }

    private static class zzb extends zzr.zza {
        private final WeakReference<zze> zzQi;

        zzb(zze zze) {
            this.zzQi = new WeakReference<>(zze);
        }

        public void zzb(final ResolveAccountResponse resolveAccountResponse) {
            final zze zze = (zze) this.zzQi.get();
            if (zze != null) {
                zze.zzPQ.zzQx.post(new Runnable() {
                    public void run() {
                        zze.zza(resolveAccountResponse);
                    }
                });
            }
        }
    }

    private static class zzc implements GoogleApiClient.zza {
        private final WeakReference<zze> zzQi;
        private final Api<?> zzQo;
        private final int zzQp;

        public zzc(zze zze, Api<?> api, int i) {
            this.zzQi = new WeakReference<>(zze);
            this.zzQo = api;
            this.zzQp = i;
        }

        public void zza(ConnectionResult connectionResult) {
            boolean z = false;
            zze zze = (zze) this.zzQi.get();
            if (zze != null) {
                if (Looper.myLooper() == zze.zzPQ.getLooper()) {
                    z = true;
                }
                zzv.zza(z, (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
                zze.zzPR.lock();
                try {
                    if (zze.zzat(0)) {
                        if (!connectionResult.isSuccess()) {
                            zze.zzb(connectionResult, this.zzQo, this.zzQp);
                        }
                        if (zze.zzkP()) {
                            zze.zzkQ();
                        }
                        zze.zzPR.unlock();
                    }
                } finally {
                    zze.zzPR.unlock();
                }
            }
        }

        public void zzb(ConnectionResult connectionResult) {
            boolean z = true;
            zze zze = (zze) this.zzQi.get();
            if (zze != null) {
                if (Looper.myLooper() != zze.zzPQ.getLooper()) {
                    z = false;
                }
                zzv.zza(z, (Object) "onReportAccountValidation must be called on the GoogleApiClient handler thread");
                zze.zzPR.lock();
                try {
                    if (zze.zzat(1)) {
                        if (!connectionResult.isSuccess()) {
                            zze.zzb(connectionResult, this.zzQo, this.zzQp);
                        }
                        if (zze.zzkP()) {
                            zze.zzkS();
                        }
                        zze.zzPR.unlock();
                    }
                } finally {
                    zze.zzPR.unlock();
                }
            }
        }
    }

    private class zzd implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private zzd() {
        }

        public void onConnected(Bundle connectionHint) {
            zze.this.zzPZ.zza(new zzb(zze.this));
        }

        public void onConnectionFailed(ConnectionResult result) {
            zze.this.zzPR.lock();
            try {
                if (zze.this.zze(result)) {
                    zze.this.zzkV();
                    zze.this.zzkT();
                } else {
                    zze.this.zzf(result);
                }
            } finally {
                zze.this.zzPR.unlock();
            }
        }

        public void onConnectionSuspended(int cause) {
        }
    }

    public zze(zzg zzg, com.google.android.gms.common.internal.zze zze, Map<Api<?>, Integer> map, Api.zzb<? extends zzmd, zzme> zzb2, Lock lock, Context context) {
        this.zzPQ = zzg;
        this.zzQg = zze;
        this.zzQh = map;
        this.zzPK = zzb2;
        this.zzPR = lock;
        this.mContext = context;
    }

    private void zzO(boolean z) {
        if (this.zzPZ != null) {
            if (this.zzPZ.isConnected()) {
                if (z) {
                    this.zzPZ.zzwe();
                }
                this.zzPZ.disconnect();
            }
            this.zzQd = null;
        }
    }

    /* access modifiers changed from: private */
    public void zza(ResolveAccountResponse resolveAccountResponse) {
        ConnectionResult zzmn = resolveAccountResponse.zzmn();
        this.zzPR.lock();
        try {
            if (zzat(0)) {
                if (zzmn.isSuccess()) {
                    this.zzQd = resolveAccountResponse.zzmm();
                    this.zzQc = true;
                    this.zzQe = resolveAccountResponse.zzmo();
                    this.zzQf = resolveAccountResponse.zzmp();
                    zzkR();
                } else if (zze(zzmn)) {
                    zzkV();
                    if (this.zzPW == 0) {
                        zzkT();
                    }
                } else {
                    zzf(zzmn);
                }
                this.zzPR.unlock();
            }
        } finally {
            this.zzPR.unlock();
        }
    }

    private boolean zza(int i, int i2, ConnectionResult connectionResult) {
        if (i2 != 1 || zzd(connectionResult)) {
            return this.zzPS == null || i < this.zzPT;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean zzat(int i) {
        if (this.zzPU == i) {
            return true;
        }
        Log.wtf("GoogleApiClientConnecting", "GoogleApiClient connecting is in step " + zzau(this.zzPU) + " but received callback for step " + zzau(i));
        zzf(new ConnectionResult(8, (PendingIntent) null));
        return false;
    }

    private String zzau(int i) {
        switch (i) {
            case 0:
                return "STEP_GETTING_SERVICE_BINDINGS";
            case 1:
                return "STEP_VALIDATING_ACCOUNT";
            case 2:
                return "STEP_AUTHENTICATING";
            case 3:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    /* access modifiers changed from: private */
    public void zzb(ConnectionResult connectionResult, Api<?> api, int i) {
        if (i != 2) {
            int priority = api.zzkC().getPriority();
            if (zza(priority, i, connectionResult)) {
                this.zzPS = connectionResult;
                this.zzPT = priority;
            }
        }
        this.zzPQ.zzQA.put(api.zzkF(), connectionResult);
    }

    /* access modifiers changed from: private */
    public void zzc(ConnectionResult connectionResult) {
        this.zzPR.lock();
        try {
            if (zzat(2)) {
                if (connectionResult.isSuccess()) {
                    zzkT();
                } else if (zze(connectionResult)) {
                    zzkV();
                    zzkT();
                } else {
                    zzf(connectionResult);
                }
                this.zzPR.unlock();
            }
        } finally {
            this.zzPR.unlock();
        }
    }

    private static boolean zzd(ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || GooglePlayServicesUtil.zzar(connectionResult.getErrorCode()) != null;
    }

    /* access modifiers changed from: private */
    public boolean zze(ConnectionResult connectionResult) {
        if (this.zzQa != 2) {
            return this.zzQa == 1 && !connectionResult.hasResolution();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void zzf(ConnectionResult connectionResult) {
        boolean z = false;
        this.zzPV = false;
        this.zzPQ.zzQB.clear();
        if (!connectionResult.hasResolution()) {
            z = true;
        }
        zzO(z);
        zzas(3);
        if (!this.zzPQ.zzlb() || !GooglePlayServicesUtil.zze(this.mContext, connectionResult.getErrorCode())) {
            this.zzPQ.zzld();
            this.zzPQ.zzQs.zzj(connectionResult);
        }
        this.zzPQ.zzQs.zzmf();
    }

    /* access modifiers changed from: private */
    public boolean zzkP() {
        this.zzPW--;
        if (this.zzPW > 0) {
            return false;
        }
        if (this.zzPS == null) {
            return true;
        }
        zzf(this.zzPS);
        return false;
    }

    /* access modifiers changed from: private */
    public void zzkQ() {
        if (this.zzQb) {
            zzkR();
        } else {
            zzkT();
        }
    }

    private void zzkR() {
        if (this.zzQc && this.zzPW == 0) {
            this.zzPU = 1;
            this.zzPW = this.zzPQ.zzQz.size();
            for (Api.zzc next : this.zzPQ.zzQz.keySet()) {
                if (!this.zzPQ.zzQA.containsKey(next)) {
                    this.zzPQ.zzQz.get(next).zza(this.zzQd);
                } else if (zzkP()) {
                    zzkS();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzkS() {
        this.zzPU = 2;
        this.zzPQ.zzQB = zzkW();
        this.zzPZ.zza(this.zzQd, this.zzPQ.zzQB, new zza(this));
    }

    /* access modifiers changed from: private */
    public void zzkT() {
        Set<Scope> set = this.zzPQ.zzQB;
        Set<Scope> zzkW = set.isEmpty() ? zzkW() : set;
        this.zzPU = 3;
        this.zzPW = this.zzPQ.zzQz.size();
        for (Api.zzc next : this.zzPQ.zzQz.keySet()) {
            if (!this.zzPQ.zzQA.containsKey(next)) {
                this.zzPQ.zzQz.get(next).zza(this.zzQd, zzkW);
            } else if (zzkP()) {
                zzkU();
            }
        }
    }

    private void zzkU() {
        this.zzPQ.zzla();
        if (this.zzPZ != null) {
            if (this.zzQe) {
                this.zzPZ.zza(this.zzQd, this.zzQf);
            }
            zzO(false);
        }
        for (Api.zzc<?> zzc2 : this.zzPQ.zzQA.keySet()) {
            this.zzPQ.zzQz.get(zzc2).disconnect();
        }
        if (this.zzPV) {
            this.zzPV = false;
            zzas(-1);
            return;
        }
        this.zzPQ.zzQs.zzg(this.zzPX.isEmpty() ? null : this.zzPX);
    }

    /* access modifiers changed from: private */
    public void zzkV() {
        this.zzQb = false;
        this.zzPQ.zzQB.clear();
        for (Api.zzc next : this.zzPY) {
            if (!this.zzPQ.zzQA.containsKey(next)) {
                this.zzPQ.zzQA.put(next, new ConnectionResult(17, (PendingIntent) null));
            }
        }
    }

    private Set<Scope> zzkW() {
        HashSet hashSet = new HashSet(this.zzQg.zzlG());
        Map<Api<?>, zze.zza> zzlI = this.zzQg.zzlI();
        for (Api next : zzlI.keySet()) {
            if (!this.zzPQ.zzQA.containsKey(next.zzkF())) {
                hashSet.addAll(zzlI.get(next).zzPP);
            }
        }
        return hashSet;
    }

    public void begin() {
        this.zzPQ.zzQs.zzmg();
        this.zzPQ.zzQA.clear();
        this.zzPV = false;
        this.zzQb = false;
        this.zzPS = null;
        this.zzPU = 0;
        this.zzQa = 2;
        this.zzQc = false;
        this.zzQe = false;
        boolean z = false;
        for (Api next : this.zzQh.keySet()) {
            Api.zza zza2 = this.zzPQ.zzQz.get(next.zzkF());
            int intValue = this.zzQh.get(next).intValue();
            zza2.zza((GoogleApiClient.zza) new zzc(this, next, intValue));
            boolean z2 = (next.zzkC().getPriority() == 1) | z;
            if (zza2.zzjM()) {
                this.zzQb = true;
                if (intValue < this.zzQa) {
                    this.zzQa = intValue;
                }
                if (intValue != 0) {
                    this.zzPY.add(next.zzkF());
                }
            }
            z = z2;
        }
        if (z) {
            this.zzQb = false;
        }
        if (this.zzQb) {
            this.zzQg.zza(Integer.valueOf(this.zzPQ.getSessionId()));
            zzd zzd2 = new zzd();
            this.zzPZ = (zzmd) this.zzPK.zza(this.mContext, this.zzPQ.getLooper(), this.zzQg, this.zzQg.zzlM(), zzd2, zzd2);
            this.zzPZ.connect();
        }
        this.zzPW = this.zzPQ.zzQz.size();
        for (Api.zza connect : this.zzPQ.zzQz.values()) {
            connect.connect();
        }
    }

    public void connect() {
        this.zzPV = false;
    }

    public String getName() {
        return "CONNECTING";
    }

    public void onConnected(Bundle connectionHint) {
        if (zzat(3)) {
            if (connectionHint != null) {
                this.zzPX.putAll(connectionHint);
            }
            if (zzkP()) {
                zzkU();
            }
        }
    }

    public <A extends Api.zza, R extends Result, T extends zza.C0001zza<R, A>> T zza(T t) {
        this.zzPQ.zzQt.add(t);
        return t;
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
        if (zzat(3)) {
            zzb(connectionResult, api, i);
            if (zzkP()) {
                zzkU();
            }
        }
    }

    public void zzas(int i) {
        if (i == -1) {
            Iterator it = this.zzPQ.zzQt.iterator();
            while (it.hasNext()) {
                zzg.zze zze = (zzg.zze) it.next();
                if (zze.zzkI() != 1) {
                    zze.cancel();
                    it.remove();
                }
            }
            this.zzPQ.zzkX();
            if (this.zzPS != null || this.zzPQ.zzQt.isEmpty()) {
                this.zzPQ.zzQA.clear();
                this.zzPS = null;
                zzO(true);
            } else {
                this.zzPV = true;
                return;
            }
        }
        this.zzPQ.zzg(this.zzPS);
    }

    public <A extends Api.zza, T extends zza.C0001zza<? extends Result, A>> T zzb(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
