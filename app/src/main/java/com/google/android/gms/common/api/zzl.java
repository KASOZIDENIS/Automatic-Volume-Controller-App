package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzv;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzl extends Fragment implements DialogInterface.OnCancelListener {
    /* access modifiers changed from: private */
    public boolean mStarted;
    /* access modifiers changed from: private */
    public boolean zzRa;
    /* access modifiers changed from: private */
    public int zzRb = -1;
    /* access modifiers changed from: private */
    public ConnectionResult zzRc;
    /* access modifiers changed from: private */
    public final Handler zzRd = new Handler(Looper.getMainLooper());
    private final SparseArray<zza> zzRe = new SparseArray<>();

    private class zza implements GoogleApiClient.OnConnectionFailedListener {
        public final int zzRf;
        public final GoogleApiClient zzRg;
        public final GoogleApiClient.OnConnectionFailedListener zzRh;

        public zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zzRf = i;
            this.zzRg = googleApiClient;
            this.zzRh = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
            writer.append("#").print(this.zzRf);
            writer.append(" ");
            this.zzRg.dump(prefix, fd, writer, args);
        }

        public void onConnectionFailed(ConnectionResult result) {
            zzl.this.zzRd.post(new zzb(this.zzRf, result));
        }

        public void zzlh() {
            this.zzRg.unregisterConnectionFailedListener(this);
            this.zzRg.disconnect();
        }
    }

    private class zzb implements Runnable {
        private final int zzRj;
        private final ConnectionResult zzRk;

        public zzb(int i, ConnectionResult connectionResult) {
            this.zzRj = i;
            this.zzRk = connectionResult;
        }

        public void run() {
            if (zzl.this.mStarted && !zzl.this.zzRa) {
                boolean unused = zzl.this.zzRa = true;
                int unused2 = zzl.this.zzRb = this.zzRj;
                ConnectionResult unused3 = zzl.this.zzRc = this.zzRk;
                if (this.zzRk.hasResolution()) {
                    try {
                        this.zzRk.startResolutionForResult(zzl.this.getActivity(), ((zzl.this.getActivity().getSupportFragmentManager().getFragments().indexOf(zzl.this) + 1) << 16) + 1);
                    } catch (IntentSender.SendIntentException e) {
                        zzl.this.zzlg();
                    }
                } else if (GooglePlayServicesUtil.isUserRecoverableError(this.zzRk.getErrorCode())) {
                    GooglePlayServicesUtil.showErrorDialogFragment(this.zzRk.getErrorCode(), zzl.this.getActivity(), zzl.this, 2, zzl.this);
                } else {
                    zzl.this.zza(this.zzRj, this.zzRk);
                }
            }
        }
    }

    public static zzl zza(FragmentActivity fragmentActivity) {
        zzv.zzbI("Must be called from main thread of process");
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            zzl zzl = (zzl) supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
            if (zzl != null && !zzl.isRemoving()) {
                return zzl;
            }
            zzl zzl2 = new zzl();
            supportFragmentManager.beginTransaction().add((Fragment) zzl2, "GmsSupportLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return zzl2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", e);
        }
    }

    /* access modifiers changed from: private */
    public void zza(int i, ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        zza zza2 = this.zzRe.get(i);
        if (zza2 != null) {
            zzax(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zza2.zzRh;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
        zzlg();
    }

    /* access modifiers changed from: private */
    public void zzlg() {
        int i = 0;
        this.zzRa = false;
        this.zzRb = -1;
        this.zzRc = null;
        while (true) {
            int i2 = i;
            if (i2 < this.zzRe.size()) {
                this.zzRe.valueAt(i2).zzRg.connect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzRe.size()) {
                this.zzRe.valueAt(i2).dump(prefix, fd, writer, args);
                i = i2 + 1;
            } else {
                return;
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.zzl.onActivityResult(int, int, android.content.Intent):void");
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
        this.mStarted = true;
        if (!this.zzRa) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.zzRe.size()) {
                    this.zzRe.valueAt(i2).zzRg.connect();
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void onStop() {
        int i = 0;
        super.onStop();
        this.mStarted = false;
        while (true) {
            int i2 = i;
            if (i2 < this.zzRe.size()) {
                this.zzRe.valueAt(i2).zzRg.disconnect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzv.zzb(googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzv.zza(this.zzRe.indexOfKey(i) < 0, (Object) "Already managing a GoogleApiClient with id " + i);
        this.zzRe.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && !this.zzRa) {
            googleApiClient.connect();
        }
    }

    public void zzax(int i) {
        zza zza2 = this.zzRe.get(i);
        this.zzRe.remove(i);
        if (zza2 != null) {
            zza2.zzlh();
        }
    }
}
