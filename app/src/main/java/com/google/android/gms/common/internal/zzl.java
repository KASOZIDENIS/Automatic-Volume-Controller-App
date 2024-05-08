package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class zzl extends zzk implements Handler.Callback {
    private final Handler mHandler;
    /* access modifiers changed from: private */
    public final HashMap<zza, zzb> zzTM = new HashMap<>();
    /* access modifiers changed from: private */
    public final com.google.android.gms.common.stats.zzb zzTN;
    private final long zzTO;
    /* access modifiers changed from: private */
    public final Context zzoh;

    private static final class zza {
        private final ComponentName zzTP;
        private final String zzso;

        public zza(ComponentName componentName) {
            this.zzso = null;
            this.zzTP = (ComponentName) zzv.zzr(componentName);
        }

        public zza(String str) {
            this.zzso = zzv.zzbS(str);
            this.zzTP = null;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof zza)) {
                return false;
            }
            zza zza = (zza) o;
            return zzu.equal(this.zzso, zza.zzso) && zzu.equal(this.zzTP, zza.zzTP);
        }

        public int hashCode() {
            return zzu.hashCode(this.zzso, this.zzTP);
        }

        public String toString() {
            return this.zzso == null ? this.zzTP.flattenToString() : this.zzso;
        }

        public Intent zzmi() {
            return this.zzso != null ? new Intent(this.zzso).setPackage("com.google.android.gms") : new Intent().setComponent(this.zzTP);
        }
    }

    private final class zzb {
        /* access modifiers changed from: private */
        public int mState = 2;
        /* access modifiers changed from: private */
        public IBinder zzSU;
        /* access modifiers changed from: private */
        public ComponentName zzTP;
        private final zza zzTQ = new zza();
        /* access modifiers changed from: private */
        public final Set<ServiceConnection> zzTR = new HashSet();
        private boolean zzTS;
        /* access modifiers changed from: private */
        public final zza zzTT;

        public class zza implements ServiceConnection {
            public zza() {
            }

            public void onServiceConnected(ComponentName component, IBinder binder) {
                synchronized (zzl.this.zzTM) {
                    IBinder unused = zzb.this.zzSU = binder;
                    ComponentName unused2 = zzb.this.zzTP = component;
                    for (ServiceConnection onServiceConnected : zzb.this.zzTR) {
                        onServiceConnected.onServiceConnected(component, binder);
                    }
                    int unused3 = zzb.this.mState = 1;
                }
            }

            public void onServiceDisconnected(ComponentName component) {
                synchronized (zzl.this.zzTM) {
                    IBinder unused = zzb.this.zzSU = null;
                    ComponentName unused2 = zzb.this.zzTP = component;
                    for (ServiceConnection onServiceDisconnected : zzb.this.zzTR) {
                        onServiceDisconnected.onServiceDisconnected(component);
                    }
                    int unused3 = zzb.this.mState = 2;
                }
            }
        }

        public zzb(zza zza2) {
            this.zzTT = zza2;
        }

        public IBinder getBinder() {
            return this.zzSU;
        }

        public ComponentName getComponentName() {
            return this.zzTP;
        }

        public int getState() {
            return this.mState;
        }

        public boolean isBound() {
            return this.zzTS;
        }

        public void zza(ServiceConnection serviceConnection, String str) {
            zzl.this.zzTN.zza(zzl.this.zzoh, serviceConnection, str, this.zzTT.zzmi());
            this.zzTR.add(serviceConnection);
        }

        public boolean zza(ServiceConnection serviceConnection) {
            return this.zzTR.contains(serviceConnection);
        }

        public void zzb(ServiceConnection serviceConnection, String str) {
            zzl.this.zzTN.zzb(zzl.this.zzoh, serviceConnection);
            this.zzTR.remove(serviceConnection);
        }

        public void zzbM(String str) {
            this.zzTS = zzl.this.zzTN.zza(zzl.this.zzoh, str, this.zzTT.zzmi(), (ServiceConnection) this.zzTQ, 129);
            if (this.zzTS) {
                this.mState = 3;
            } else {
                zzl.this.zzTN.zza(zzl.this.zzoh, (ServiceConnection) this.zzTQ);
            }
        }

        public void zzbN(String str) {
            zzl.this.zzTN.zza(zzl.this.zzoh, (ServiceConnection) this.zzTQ);
            this.zzTS = false;
            this.mState = 2;
        }

        public boolean zzmj() {
            return this.zzTR.isEmpty();
        }
    }

    zzl(Context context) {
        this.zzoh = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.zzTN = com.google.android.gms.common.stats.zzb.zznb();
        this.zzTO = 5000;
    }

    private boolean zza(zza zza2, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzv.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzTM) {
            zzb zzb2 = this.zzTM.get(zza2);
            if (zzb2 != null) {
                this.mHandler.removeMessages(0, zzb2);
                if (!zzb2.zza(serviceConnection)) {
                    zzb2.zza(serviceConnection, str);
                    switch (zzb2.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzb2.getComponentName(), zzb2.getBinder());
                            break;
                        case 2:
                            zzb2.zzbM(str);
                            break;
                    }
                } else {
                    throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + zza2);
                }
            } else {
                zzb2 = new zzb(zza2);
                zzb2.zza(serviceConnection, str);
                zzb2.zzbM(str);
                this.zzTM.put(zza2, zzb2);
            }
            isBound = zzb2.isBound();
        }
        return isBound;
    }

    private void zzb(zza zza2, ServiceConnection serviceConnection, String str) {
        zzv.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzTM) {
            zzb zzb2 = this.zzTM.get(zza2);
            if (zzb2 == null) {
                throw new IllegalStateException("Nonexistent connection status for service config: " + zza2);
            } else if (!zzb2.zza(serviceConnection)) {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + zza2);
            } else {
                zzb2.zzb(serviceConnection, str);
                if (zzb2.zzmj()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zzb2), this.zzTO);
                }
            }
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                zzb zzb2 = (zzb) msg.obj;
                synchronized (this.zzTM) {
                    if (zzb2.zzmj()) {
                        if (zzb2.isBound()) {
                            zzb2.zzbN("GmsClientSupervisor");
                        }
                        this.zzTM.remove(zzb2.zzTT);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zza(componentName), serviceConnection, str);
    }

    public boolean zza(String str, ServiceConnection serviceConnection, String str2) {
        return zza(new zza(str), serviceConnection, str2);
    }

    public void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zza(componentName), serviceConnection, str);
    }

    public void zzb(String str, ServiceConnection serviceConnection, String str2) {
        zzb(new zza(str), serviceConnection, str2);
    }
}
