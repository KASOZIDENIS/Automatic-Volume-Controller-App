package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.ContainerHolder;

class zzo implements ContainerHolder {
    private Status zzKr;
    private final Looper zzPx;
    private boolean zzWX;
    private Container zzaCs;
    private Container zzaCt;
    private zzb zzaCu;
    private zza zzaCv;
    private TagManager zzaCw;

    public interface zza {
        void zzdB(String str);

        String zzwu();

        void zzww();
    }

    private class zzb extends Handler {
        private final ContainerHolder.ContainerAvailableListener zzaCx;

        public zzb(ContainerHolder.ContainerAvailableListener containerAvailableListener, Looper looper) {
            super(looper);
            this.zzaCx = containerAvailableListener;
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    zzdD((String) msg.obj);
                    return;
                default:
                    zzbg.zzak("Don't know how to handle this message.");
                    return;
            }
        }

        public void zzdC(String str) {
            sendMessage(obtainMessage(1, str));
        }

        /* access modifiers changed from: protected */
        public void zzdD(String str) {
            this.zzaCx.onContainerAvailable(zzo.this, str);
        }
    }

    public zzo(Status status) {
        this.zzKr = status;
        this.zzPx = null;
    }

    public zzo(TagManager tagManager, Looper looper, Container container, zza zza2) {
        this.zzaCw = tagManager;
        this.zzPx = looper == null ? Looper.getMainLooper() : looper;
        this.zzaCs = container;
        this.zzaCv = zza2;
        this.zzKr = Status.zzQU;
        tagManager.zza(this);
    }

    private void zzwv() {
        if (this.zzaCu != null) {
            this.zzaCu.zzdC(this.zzaCt.zzws());
        }
    }

    public synchronized Container getContainer() {
        Container container = null;
        synchronized (this) {
            if (this.zzWX) {
                zzbg.zzak("ContainerHolder is released.");
            } else {
                if (this.zzaCt != null) {
                    this.zzaCs = this.zzaCt;
                    this.zzaCt = null;
                }
                container = this.zzaCs;
            }
        }
        return container;
    }

    /* access modifiers changed from: package-private */
    public String getContainerId() {
        if (!this.zzWX) {
            return this.zzaCs.getContainerId();
        }
        zzbg.zzak("getContainerId called on a released ContainerHolder.");
        return "";
    }

    public Status getStatus() {
        return this.zzKr;
    }

    public synchronized void refresh() {
        if (this.zzWX) {
            zzbg.zzak("Refreshing a released ContainerHolder.");
        } else {
            this.zzaCv.zzww();
        }
    }

    public synchronized void release() {
        if (this.zzWX) {
            zzbg.zzak("Releasing a released ContainerHolder.");
        } else {
            this.zzWX = true;
            this.zzaCw.zzb(this);
            this.zzaCs.release();
            this.zzaCs = null;
            this.zzaCt = null;
            this.zzaCv = null;
            this.zzaCu = null;
        }
    }

    public synchronized void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener listener) {
        if (this.zzWX) {
            zzbg.zzak("ContainerHolder is released.");
        } else if (listener == null) {
            this.zzaCu = null;
        } else {
            this.zzaCu = new zzb(listener, this.zzPx);
            if (this.zzaCt != null) {
                zzwv();
            }
        }
    }

    public synchronized void zza(Container container) {
        if (!this.zzWX) {
            if (container == null) {
                zzbg.zzak("Unexpected null container.");
            } else {
                this.zzaCt = container;
                zzwv();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzdB(String str) {
        if (this.zzWX) {
            zzbg.zzak("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzaCv.zzdB(str);
        }
    }

    public synchronized void zzdz(String str) {
        if (!this.zzWX) {
            this.zzaCs.zzdz(str);
        }
    }

    /* access modifiers changed from: package-private */
    public String zzwu() {
        if (!this.zzWX) {
            return this.zzaCv.zzwu();
        }
        zzbg.zzak("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }
}
