package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzv;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzm extends Fragment implements DialogInterface.OnCancelListener, LoaderManager.LoaderCallbacks<ConnectionResult> {
    private boolean zzRa;
    private int zzRb = -1;
    private ConnectionResult zzRc;
    private final Handler zzRd = new Handler(Looper.getMainLooper());
    private final SparseArray<zzb> zzRe = new SparseArray<>();

    static class zza extends Loader<ConnectionResult> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        public final GoogleApiClient zzRg;
        private boolean zzRl;
        private ConnectionResult zzRm;

        public zza(Context context, GoogleApiClient googleApiClient) {
            super(context);
            this.zzRg = googleApiClient;
        }

        private void zzh(ConnectionResult connectionResult) {
            this.zzRm = connectionResult;
            if (isStarted() && !isAbandoned()) {
                deliverResult(connectionResult);
            }
        }

        public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
            super.dump(prefix, fd, writer, args);
            this.zzRg.dump(prefix, fd, writer, args);
        }

        public void onConnected(Bundle connectionHint) {
            this.zzRl = false;
            zzh(ConnectionResult.zzOI);
        }

        public void onConnectionFailed(ConnectionResult result) {
            this.zzRl = true;
            zzh(result);
        }

        public void onConnectionSuspended(int cause) {
        }

        /* access modifiers changed from: protected */
        public void onReset() {
            this.zzRm = null;
            this.zzRl = false;
            this.zzRg.unregisterConnectionCallbacks(this);
            this.zzRg.unregisterConnectionFailedListener(this);
            this.zzRg.disconnect();
        }

        /* access modifiers changed from: protected */
        public void onStartLoading() {
            super.onStartLoading();
            this.zzRg.registerConnectionCallbacks(this);
            this.zzRg.registerConnectionFailedListener(this);
            if (this.zzRm != null) {
                deliverResult(this.zzRm);
            }
            if (!this.zzRg.isConnected() && !this.zzRg.isConnecting() && !this.zzRl) {
                this.zzRg.connect();
            }
        }

        /* access modifiers changed from: protected */
        public void onStopLoading() {
            this.zzRg.disconnect();
        }

        public boolean zzli() {
            return this.zzRl;
        }
    }

    private static class zzb {
        public final GoogleApiClient zzRg;
        public final GoogleApiClient.OnConnectionFailedListener zzRh;

        private zzb(GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zzRg = googleApiClient;
            this.zzRh = onConnectionFailedListener;
        }
    }

    private class zzc implements Runnable {
        private final int zzRj;
        private final ConnectionResult zzRk;

        public zzc(int i, ConnectionResult connectionResult) {
            this.zzRj = i;
            this.zzRk = connectionResult;
        }

        public void run() {
            if (this.zzRk.hasResolution()) {
                try {
                    this.zzRk.startResolutionForResult(zzm.this.getActivity(), ((zzm.this.getActivity().getSupportFragmentManager().getFragments().indexOf(zzm.this) + 1) << 16) + 1);
                } catch (IntentSender.SendIntentException e) {
                    zzm.this.zzlg();
                }
            } else if (GooglePlayServicesUtil.isUserRecoverableError(this.zzRk.getErrorCode())) {
                GooglePlayServicesUtil.showErrorDialogFragment(this.zzRk.getErrorCode(), zzm.this.getActivity(), zzm.this, 2, zzm.this);
            } else {
                zzm.this.zza(this.zzRj, this.zzRk);
            }
        }
    }

    /* access modifiers changed from: private */
    public void zza(int i, ConnectionResult connectionResult) {
        Log.w("GmsSupportLoaderLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        zzb zzb2 = this.zzRe.get(i);
        if (zzb2 != null) {
            zzax(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zzb2.zzRh;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
        zzlg();
    }

    public static zzm zzb(FragmentActivity fragmentActivity) {
        zzv.zzbI("Must be called from main thread of process");
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            zzm zzm = (zzm) supportFragmentManager.findFragmentByTag("GmsSupportLoaderLifecycleFragment");
            if (zzm != null && !zzm.isRemoving()) {
                return zzm;
            }
            zzm zzm2 = new zzm();
            supportFragmentManager.beginTransaction().add((Fragment) zzm2, "GmsSupportLoaderLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return zzm2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag GmsSupportLoaderLifecycleFragment is not a SupportLoaderLifecycleFragment", e);
        }
    }

    private void zzb(int i, ConnectionResult connectionResult) {
        if (!this.zzRa) {
            this.zzRa = true;
            this.zzRb = i;
            this.zzRc = connectionResult;
            this.zzRd.post(new zzc(i, connectionResult));
        }
    }

    /* access modifiers changed from: private */
    public void zzlg() {
        this.zzRa = false;
        this.zzRb = -1;
        this.zzRc = null;
        LoaderManager loaderManager = getLoaderManager();
        for (int i = 0; i < this.zzRe.size(); i++) {
            int keyAt = this.zzRe.keyAt(i);
            zza zzaz = zzaz(keyAt);
            if (zzaz != null && zzaz.zzli()) {
                loaderManager.destroyLoader(keyAt);
                loaderManager.initLoader(keyAt, (Bundle) null, this);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        if (com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) == 0) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        if (r5 == -1) goto L_0x0006;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r4, int r5, android.content.Intent r6) {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            switch(r4) {
                case 1: goto L_0x0017;
                case 2: goto L_0x000c;
                default: goto L_0x0005;
            }
        L_0x0005:
            r0 = r1
        L_0x0006:
            if (r0 == 0) goto L_0x001b
            r3.zzlg()
        L_0x000b:
            return
        L_0x000c:
            android.support.v4.app.FragmentActivity r2 = r3.getActivity()
            int r2 = com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable(r2)
            if (r2 != 0) goto L_0x0005
            goto L_0x0006
        L_0x0017:
            r2 = -1
            if (r5 != r2) goto L_0x0005
            goto L_0x0006
        L_0x001b:
            int r0 = r3.zzRb
            com.google.android.gms.common.ConnectionResult r1 = r3.zzRc
            r3.zza((int) r0, (com.google.android.gms.common.ConnectionResult) r1)
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.zzm.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzRe.size()) {
                int keyAt = this.zzRe.keyAt(i2);
                zza zzaz = zzaz(keyAt);
                if (zzaz == null || this.zzRe.valueAt(i2).zzRg == zzaz.zzRg) {
                    getLoaderManager().initLoader(keyAt, (Bundle) null, this);
                } else {
                    getLoaderManager().restartLoader(keyAt, (Bundle) null, this);
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(this.zzRb, new ConnectionResult(13, (PendingIntent) null));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.zzRa = savedInstanceState.getBoolean("resolving_error", false);
            this.zzRb = savedInstanceState.getInt("failed_client_id", -1);
            if (this.zzRb >= 0) {
                this.zzRc = new ConnectionResult(savedInstanceState.getInt("failed_status"), (PendingIntent) savedInstanceState.getParcelable("failed_resolution"));
            }
        }
    }

    public Loader<ConnectionResult> onCreateLoader(int id, Bundle args) {
        return new zza(getActivity(), this.zzRe.get(id).zzRg);
    }

    public void onLoaderReset(Loader<ConnectionResult> loader) {
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("resolving_error", this.zzRa);
        if (this.zzRb >= 0) {
            outState.putInt("failed_client_id", this.zzRb);
            outState.putInt("failed_status", this.zzRc.getErrorCode());
            outState.putParcelable("failed_resolution", this.zzRc.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        if (!this.zzRa) {
            for (int i = 0; i < this.zzRe.size(); i++) {
                getLoaderManager().initLoader(this.zzRe.keyAt(i), (Bundle) null, this);
            }
        }
    }

    public void zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzv.zzb(googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzv.zza(this.zzRe.indexOfKey(i) < 0, (Object) "Already managing a GoogleApiClient with id " + i);
        this.zzRe.put(i, new zzb(googleApiClient, onConnectionFailedListener));
        if (getActivity() != null) {
            LoaderManager.enableDebugLogging(false);
            getLoaderManager().initLoader(i, (Bundle) null, this);
        }
    }

    /* renamed from: zza */
    public void onLoadFinished(Loader<ConnectionResult> loader, ConnectionResult connectionResult) {
        if (!connectionResult.isSuccess()) {
            zzb(loader.getId(), connectionResult);
        }
    }

    public void zzax(int i) {
        this.zzRe.remove(i);
        getLoaderManager().destroyLoader(i);
    }

    public GoogleApiClient zzay(int i) {
        zza zzaz;
        if (getActivity() == null || (zzaz = zzaz(i)) == null) {
            return null;
        }
        return zzaz.zzRg;
    }

    /* access modifiers changed from: package-private */
    public zza zzaz(int i) {
        try {
            return (zza) getLoaderManager().getLoader(i);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Unknown loader in SupportLoaderLifecycleFragment", e);
        }
    }
}
