package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public abstract class zzi<T extends IInterface> implements Api.zza, zzj.zza {
    public static final String[] zzTx = {"service_esmobile", "service_googleme"};
    /* access modifiers changed from: private */
    public final Context mContext;
    final Handler mHandler;
    private final Account zzJc;
    /* access modifiers changed from: private */
    public final Set<Scope> zzPP;
    private final Looper zzPx;
    private final zze zzQg;
    /* access modifiers changed from: private */
    public final zzj zzQs;
    private final zzk zzTo;
    /* access modifiers changed from: private */
    public zzq zzTp;
    /* access modifiers changed from: private */
    public boolean zzTq;
    /* access modifiers changed from: private */
    public GoogleApiClient.zza zzTr;
    private T zzTs;
    /* access modifiers changed from: private */
    public final ArrayList<zzi<T>.zzc<?>> zzTt;
    private zzi<T>.zze zzTu;
    private int zzTv;
    private final int zzTw;
    private final Object zzoe;

    private abstract class zza extends zzi<T>.zzc<Boolean> {
        public final int statusCode;
        public final Bundle zzTy;

        protected zza(int i, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.zzTy = bundle;
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzc */
        public void zzo(Boolean bool) {
            PendingIntent pendingIntent = null;
            if (bool == null) {
                zzi.this.zza(1, null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    if (!zzma()) {
                        zzi.this.zza(1, null);
                        zzi(new ConnectionResult(8, (PendingIntent) null));
                        return;
                    }
                    return;
                case 10:
                    zzi.this.zza(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    zzi.this.zza(1, null);
                    if (this.zzTy != null) {
                        pendingIntent = (PendingIntent) this.zzTy.getParcelable("pendingIntent");
                    }
                    zzi(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzi(ConnectionResult connectionResult);

        /* access modifiers changed from: protected */
        public abstract boolean zzma();

        /* access modifiers changed from: protected */
        public void zzmb() {
        }
    }

    final class zzb extends Handler {
        public zzb(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            if ((msg.what == 1 || msg.what == 5 || msg.what == 6) && !zzi.this.isConnecting()) {
                zzc zzc = (zzc) msg.obj;
                zzc.zzmb();
                zzc.unregister();
            } else if (msg.what == 3) {
                ConnectionResult connectionResult = new ConnectionResult(((Integer) msg.obj).intValue(), (PendingIntent) null);
                if (zzi.this.zzTq) {
                    zzi.this.zzTr.zza(connectionResult);
                } else {
                    zzi.this.zzQs.zzj(connectionResult);
                }
            } else if (msg.what == 4) {
                zzi.this.zza(4, null);
                zzi.this.zzQs.zzaP(((Integer) msg.obj).intValue());
                boolean unused = zzi.this.zza(4, 1, null);
            } else if (msg.what == 2 && !zzi.this.isConnected()) {
                zzc zzc2 = (zzc) msg.obj;
                zzc2.zzmb();
                zzc2.unregister();
            } else if (msg.what == 2 || msg.what == 1 || msg.what == 5 || msg.what == 6) {
                ((zzc) msg.obj).zzmc();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle this message.");
            }
        }
    }

    protected abstract class zzc<TListener> {
        private TListener mListener;
        private boolean zzTA = false;

        public zzc(TListener tlistener) {
            this.mListener = tlistener;
        }

        public void unregister() {
            zzmd();
            synchronized (zzi.this.zzTt) {
                zzi.this.zzTt.remove(this);
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzmb();

        public void zzmc() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.mListener;
                if (this.zzTA) {
                    Log.w("GmsClient", "Callback proxy " + this + " being reused. This is not safe.");
                }
            }
            if (tlistener != null) {
                try {
                    zzo(tlistener);
                } catch (RuntimeException e) {
                    zzmb();
                    throw e;
                }
            } else {
                zzmb();
            }
            synchronized (this) {
                this.zzTA = true;
            }
            unregister();
        }

        public void zzmd() {
            synchronized (this) {
                this.mListener = null;
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzo(TListener tlistener);
    }

    public static final class zzd extends zzp.zza {
        private zzi zzTB;

        public zzd(zzi zzi) {
            this.zzTB = zzi;
        }

        private void zzme() {
            this.zzTB = null;
        }

        public void zzb(int i, IBinder iBinder, Bundle bundle) {
            zzv.zzb(this.zzTB, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzTB.zza(i, iBinder, bundle);
            zzme();
        }

        public void zzc(int i, Bundle bundle) {
            zzv.zzb(this.zzTB, (Object) "onAccountValidationComplete can be called only once per call to validateAccount");
            this.zzTB.zzb(i, bundle);
            zzme();
        }
    }

    public final class zze implements ServiceConnection {
        public zze() {
        }

        public void onServiceConnected(ComponentName component, IBinder binder) {
            zzv.zzb(binder, (Object) "Expecting a valid IBinder");
            zzq unused = zzi.this.zzTp = zzq.zza.zzT(binder);
            zzi.this.zzlV();
        }

        public void onServiceDisconnected(ComponentName component) {
            zzi.this.mHandler.sendMessage(zzi.this.mHandler.obtainMessage(4, 1));
        }
    }

    protected final class zzf extends zzi<T>.zza {
        public final IBinder zzTC;

        public zzf(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzTC = iBinder;
        }

        /* access modifiers changed from: protected */
        public void zzi(ConnectionResult connectionResult) {
            zzi.this.zzQs.zzj(connectionResult);
        }

        /* access modifiers changed from: protected */
        public boolean zzma() {
            try {
                String interfaceDescriptor = this.zzTC.getInterfaceDescriptor();
                if (!zzi.this.zzer().equals(interfaceDescriptor)) {
                    Log.e("GmsClient", "service descriptor mismatch: " + zzi.this.zzer() + " vs. " + interfaceDescriptor);
                    return false;
                }
                IInterface zzD = zzi.this.zzD(this.zzTC);
                if (zzD == null || !zzi.this.zza(2, 3, zzD)) {
                    return false;
                }
                zzi.this.zzQs.zzmh();
                GooglePlayServicesUtil.zzQ(zzi.this.mContext);
                return true;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    protected final class zzg extends zzi<T>.zza {
        public zzg() {
            super(0, (Bundle) null);
        }

        /* access modifiers changed from: protected */
        public void zzi(ConnectionResult connectionResult) {
            if (zzi.this.zzTq) {
                zzi.this.zzTr.zza(connectionResult);
            } else {
                zzi.this.zzQs.zzj(connectionResult);
            }
        }

        /* access modifiers changed from: protected */
        public boolean zzma() {
            if (zzi.this.zzTq) {
                zzv.zza(zzi.this.zzTr != null, (Object) "mConnectionProgressReportCallbacks should not be null if mReportProgress");
                zzi.this.zzTr.zza(ConnectionResult.zzOI);
            } else {
                zzi.this.zza((zzo) null, (Set<Scope>) zzi.this.zzPP);
            }
            return true;
        }
    }

    protected final class zzh extends zzi<T>.zza {
        public zzh(int i, Bundle bundle) {
            super(i, bundle);
        }

        /* access modifiers changed from: protected */
        public void zzi(ConnectionResult connectionResult) {
            if (zzi.this.zzTq) {
                zzi.this.zzTr.zzb(connectionResult);
            } else {
                zzi.this.zzQs.zzj(connectionResult);
            }
        }

        /* access modifiers changed from: protected */
        public boolean zzma() {
            zzv.zza(zzi.this.zzTq && zzi.this.zzTr != null, (Object) "PostValidationCallback should not happen when mReportProgress is false ormConnectionProgressReportCallbacks is null");
            zzi.this.zzTr.zzb(ConnectionResult.zzOI);
            return true;
        }
    }

    @Deprecated
    protected zzi(Context context, Looper looper, int i, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zzoe = new Object();
        this.zzTq = false;
        this.zzTt = new ArrayList<>();
        this.zzTv = 1;
        this.mContext = (Context) zzv.zzr(context);
        this.zzPx = (Looper) zzv.zzb(looper, (Object) "Looper must not be null");
        this.zzTo = zzk.zzU(context);
        this.zzQs = new zzj(looper, this);
        this.mHandler = new zzb(looper);
        this.zzTw = i;
        this.zzJc = null;
        this.zzPP = Collections.emptySet();
        this.zzQg = new GoogleApiClient.Builder(context).zzkK();
        registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) zzv.zzr(connectionCallbacks));
        registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) zzv.zzr(onConnectionFailedListener));
    }

    protected zzi(Context context, Looper looper, int i, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, zze zze2) {
        this(context, looper, zzk.zzU(context), i, zze2, connectionCallbacks, onConnectionFailedListener);
    }

    protected zzi(Context context, Looper looper, zzk zzk, int i, zze zze2) {
        this.zzoe = new Object();
        this.zzTq = false;
        this.zzTt = new ArrayList<>();
        this.zzTv = 1;
        this.mContext = (Context) zzv.zzb(context, (Object) "Context must not be null");
        this.zzPx = (Looper) zzv.zzb(looper, (Object) "Looper must not be null");
        this.zzTo = (zzk) zzv.zzb(zzk, (Object) "Supervisor must not be null");
        this.zzQs = new zzj(looper, this);
        this.mHandler = new zzb(looper);
        this.zzTw = i;
        this.zzQg = (zze) zzv.zzr(zze2);
        this.zzJc = zze2.getAccount();
        this.zzPP = zzb(zze2.zzlH());
    }

    protected zzi(Context context, Looper looper, zzk zzk, int i, zze zze2, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzk, i, zze2);
        registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) zzv.zzr(connectionCallbacks));
        registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) zzv.zzr(onConnectionFailedListener));
    }

    /* access modifiers changed from: private */
    public void zza(int i, T t) {
        boolean z = true;
        if ((i == 3) != (t != null)) {
            z = false;
        }
        zzv.zzQ(z);
        synchronized (this.zzoe) {
            this.zzTv = i;
            this.zzTs = t;
            switch (i) {
                case 1:
                    zzlT();
                    break;
                case 2:
                    zzlS();
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.zzoe) {
            if (this.zzTv != i) {
                z = false;
            } else {
                zza(i2, t);
                z = true;
            }
        }
        return z;
    }

    private Set<Scope> zzb(Set<Scope> set) {
        Set<Scope> zza2 = zza(set);
        if (zza2 == null) {
            return zza2;
        }
        for (Scope contains : zza2) {
            if (!set.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return zza2;
    }

    private void zzlS() {
        if (this.zzTu != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + zzeq());
            this.zzTo.zzb(zzeq(), (ServiceConnection) this.zzTu, zzlR());
        }
        this.zzTu = new zze();
        if (!this.zzTo.zza(zzeq(), (ServiceConnection) this.zzTu, zzlR())) {
            Log.e("GmsClient", "unable to connect to service: " + zzeq());
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, 9));
        }
    }

    private void zzlT() {
        if (this.zzTu != null) {
            this.zzTo.zzb(zzeq(), (ServiceConnection) this.zzTu, zzlR());
            this.zzTu = null;
        }
    }

    public void connect() {
        this.zzQs.zzmg();
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (IInterface) null);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(isGooglePlayServicesAvailable)));
            return;
        }
        zza(2, (IInterface) null);
    }

    public void disconnect() {
        this.zzQs.zzmf();
        synchronized (this.zzTt) {
            int size = this.zzTt.size();
            for (int i = 0; i < size; i++) {
                this.zzTt.get(i).zzmd();
            }
            this.zzTt.clear();
        }
        zza(1, (IInterface) null);
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        int i;
        T t;
        writer.append(prefix).println("GmsClient:");
        String str = prefix + "  ";
        writer.append(str).append("mStartServiceAction=").println(zzeq());
        synchronized (this.zzoe) {
            i = this.zzTv;
            t = this.zzTs;
        }
        writer.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                writer.print("DISCONNECTED");
                break;
            case 2:
                writer.print("CONNECTING");
                break;
            case 3:
                writer.print("CONNECTED");
                break;
            case 4:
                writer.print("DISCONNECTING");
                break;
            default:
                writer.print("UNKNOWN");
                break;
        }
        writer.append(" mService=");
        if (t == null) {
            writer.println("null");
        } else {
            writer.append(zzer()).append("@").println(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzPx;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.zzoe) {
            z = this.zzTv == 3;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzoe) {
            z = this.zzTv == 2;
        }
        return z;
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        this.zzQs.registerConnectionCallbacks(listener);
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        this.zzQs.registerConnectionFailedListener(listener);
    }

    /* access modifiers changed from: protected */
    public abstract T zzD(IBinder iBinder);

    /* access modifiers changed from: protected */
    public Set<Scope> zza(Set<Scope> set) {
        return set;
    }

    /* access modifiers changed from: protected */
    public void zza(int i, IBinder iBinder, Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, new zzf(i, iBinder, bundle)));
    }

    public void zza(GoogleApiClient.zza zza2) {
        this.zzTr = (GoogleApiClient.zza) zzv.zzb(zza2, (Object) "Must provide a non-null ConnectionStatusReportCallbacks");
        this.zzTq = true;
    }

    @Deprecated
    public final void zza(zzi<T>.zzc<?> zzc2) {
        synchronized (this.zzTt) {
            this.zzTt.add(zzc2);
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, zzc2));
    }

    public void zza(zzo zzo) {
        try {
            this.zzTp.zza((zzp) new zzd(this), new ValidateAccountRequest(zzo, (Scope[]) this.zzPP.toArray(new Scope[this.zzPP.size()]), this.mContext.getPackageName(), zzlY()));
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            zzaO(1);
        } catch (RemoteException e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        }
    }

    public void zza(zzo zzo, Set<Scope> set) {
        try {
            GetServiceRequest zzf2 = new GetServiceRequest(this.zzTw).zzbL(this.mContext.getPackageName()).zzf(zzka());
            if (set != null) {
                zzf2.zza((Scope[]) set.toArray(new Scope[set.size()]));
            }
            if (zzjM()) {
                zzf2.zzb(zzlE()).zzc(zzo);
            } else if (zzlZ()) {
                zzf2.zzb(this.zzJc);
            }
            this.zzTp.zza((zzp) new zzd(this), zzf2);
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            zzaO(1);
        } catch (RemoteException e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        }
    }

    public void zzaO(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, Integer.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void zzb(int i, Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, new zzh(i, bundle)));
    }

    /* access modifiers changed from: protected */
    public abstract String zzeq();

    /* access modifiers changed from: protected */
    public abstract String zzer();

    public boolean zzjM() {
        return false;
    }

    public Bundle zzjZ() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Bundle zzka() {
        return new Bundle();
    }

    public final Account zzlE() {
        return this.zzJc != null ? this.zzJc : new Account("<<default account>>", GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
    }

    /* access modifiers changed from: protected */
    public String zzlR() {
        return this.zzQg.zzlK();
    }

    /* access modifiers changed from: protected */
    public final zze zzlU() {
        return this.zzQg;
    }

    /* access modifiers changed from: protected */
    public void zzlV() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, new zzg()));
    }

    /* access modifiers changed from: protected */
    public final void zzlW() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zzlX() throws DeadObjectException {
        T t;
        synchronized (this.zzoe) {
            if (this.zzTv == 4) {
                throw new DeadObjectException();
            }
            zzlW();
            zzv.zza(this.zzTs != null, (Object) "Client is connected but service is null");
            t = this.zzTs;
        }
        return t;
    }

    /* access modifiers changed from: protected */
    public Bundle zzlY() {
        return null;
    }

    public boolean zzlZ() {
        return false;
    }
}
