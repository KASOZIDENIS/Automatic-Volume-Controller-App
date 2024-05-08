package com.google.android.gms.tagmanager;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.tagmanager.DataLayer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
    private static TagManager zzaFN;
    private final Context mContext;
    private final DataLayer zzaCl;
    private final zzs zzaEH;
    private final zza zzaFK;
    private final zzct zzaFL;
    private final ConcurrentMap<zzo, Boolean> zzaFM;

    public interface zza {
        zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzs zzs);
    }

    TagManager(Context context, zza containerHolderLoaderProvider, DataLayer dataLayer, zzct serviceManager) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.zzaFL = serviceManager;
        this.zzaFK = containerHolderLoaderProvider;
        this.zzaFM = new ConcurrentHashMap();
        this.zzaCl = dataLayer;
        this.zzaCl.zza((DataLayer.zzb) new DataLayer.zzb() {
            public void zzE(Map<String, Object> map) {
                Object obj = map.get("event");
                if (obj != null) {
                    TagManager.this.zzdX(obj.toString());
                }
            }
        });
        this.zzaCl.zza((DataLayer.zzb) new zzd(this.mContext));
        this.zzaEH = new zzs();
        zzxK();
    }

    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzaFN == null) {
                if (context == null) {
                    zzbg.zzak("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                zzaFN = new TagManager(context, new zza() {
                    public zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzs zzs) {
                        return new zzp(context, tagManager, looper, str, i, zzs);
                    }
                }, new DataLayer(new zzw(context)), zzcu.zzxF());
            }
            tagManager = zzaFN;
        }
        return tagManager;
    }

    /* access modifiers changed from: private */
    public void zzdX(String str) {
        for (zzo zzdz : this.zzaFM.keySet()) {
            zzdz.zzdz(str);
        }
    }

    private void zzxK() {
        if (Build.VERSION.SDK_INT >= 14) {
            this.mContext.registerComponentCallbacks(new ComponentCallbacks2() {
                public void onConfigurationChanged(Configuration configuration) {
                }

                public void onLowMemory() {
                }

                public void onTrimMemory(int i) {
                    if (i == 20) {
                        TagManager.this.dispatch();
                    }
                }
            });
        }
    }

    public void dispatch() {
        this.zzaFL.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.zzaCl;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String containerId, int defaultContainerResourceId) {
        zzp zza2 = this.zzaFK.zza(this.mContext, this, (Looper) null, containerId, defaultContainerResourceId, this.zzaEH);
        zza2.zzwx();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String containerId, int defaultContainerResourceId, Handler handler) {
        zzp zza2 = this.zzaFK.zza(this.mContext, this, handler.getLooper(), containerId, defaultContainerResourceId, this.zzaEH);
        zza2.zzwx();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String containerId, int defaultContainerResourceId) {
        zzp zza2 = this.zzaFK.zza(this.mContext, this, (Looper) null, containerId, defaultContainerResourceId, this.zzaEH);
        zza2.zzwz();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String containerId, int defaultContainerResourceId, Handler handler) {
        zzp zza2 = this.zzaFK.zza(this.mContext, this, handler.getLooper(), containerId, defaultContainerResourceId, this.zzaEH);
        zza2.zzwz();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String containerId, int defaultContainerResourceId) {
        zzp zza2 = this.zzaFK.zza(this.mContext, this, (Looper) null, containerId, defaultContainerResourceId, this.zzaEH);
        zza2.zzwy();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String containerId, int defaultContainerResourceId, Handler handler) {
        zzp zza2 = this.zzaFK.zza(this.mContext, this, handler.getLooper(), containerId, defaultContainerResourceId, this.zzaEH);
        zza2.zzwy();
        return zza2;
    }

    public void setVerboseLoggingEnabled(boolean enableVerboseLogging) {
        zzbg.setLogLevel(enableVerboseLogging ? 2 : 5);
    }

    /* access modifiers changed from: package-private */
    public void zza(zzo zzo) {
        this.zzaFM.put(zzo, true);
    }

    /* access modifiers changed from: package-private */
    public boolean zzb(zzo zzo) {
        return this.zzaFM.remove(zzo) != null;
    }

    public PendingResult<ContainerHolder> zzc(String str, int i, String str2) {
        zzp zza2 = this.zzaFK.zza(this.mContext, this, (Looper) null, str, i, this.zzaEH);
        zza2.load(str2);
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean zzl(Uri uri) {
        boolean z;
        zzcb zzxl = zzcb.zzxl();
        if (zzxl.zzl(uri)) {
            String containerId = zzxl.getContainerId();
            switch (zzxl.zzxm()) {
                case NONE:
                    for (zzo zzo : this.zzaFM.keySet()) {
                        if (zzo.getContainerId().equals(containerId)) {
                            zzo.zzdB((String) null);
                            zzo.refresh();
                        }
                    }
                    break;
                case CONTAINER:
                case CONTAINER_DEBUG:
                    for (zzo zzo2 : this.zzaFM.keySet()) {
                        if (zzo2.getContainerId().equals(containerId)) {
                            zzo2.zzdB(zzxl.zzxn());
                            zzo2.refresh();
                        } else if (zzo2.zzwu() != null) {
                            zzo2.zzdB((String) null);
                            zzo2.refresh();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
