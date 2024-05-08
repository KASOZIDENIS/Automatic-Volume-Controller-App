package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzc;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.tagmanager.zzcb;
import com.google.android.gms.tagmanager.zzt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Container {
    private final Context mContext;
    private final String zzaCk;
    private final DataLayer zzaCl;
    private zzcp zzaCm;
    private Map<String, FunctionCallMacroCallback> zzaCn = new HashMap();
    private Map<String, FunctionCallTagCallback> zzaCo = new HashMap();
    private volatile long zzaCp;
    private volatile String zzaCq = "";

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    private class zza implements zzt.zza {
        private zza() {
        }

        public Object zzc(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzdx = Container.this.zzdx(str);
            if (zzdx == null) {
                return null;
            }
            return zzdx.getValue(str, map);
        }
    }

    private class zzb implements zzt.zza {
        private zzb() {
        }

        public Object zzc(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzdy = Container.this.zzdy(str);
            if (zzdy != null) {
                zzdy.execute(str, map);
            }
            return zzdf.zzxV();
        }
    }

    Container(Context context, DataLayer dataLayer, String containerId, long lastRefreshTime, zzc.zzj resource) {
        this.mContext = context;
        this.zzaCl = dataLayer;
        this.zzaCk = containerId;
        this.zzaCp = lastRefreshTime;
        zza(resource.zzhh);
        if (resource.zzhg != null) {
            zza(resource.zzhg);
        }
    }

    Container(Context context, DataLayer dataLayer, String containerId, long lastRefreshTime, zzmq.zzc resource) {
        this.mContext = context;
        this.zzaCl = dataLayer;
        this.zzaCk = containerId;
        this.zzaCp = lastRefreshTime;
        zza(resource);
    }

    private void zza(zzc.zzf zzf) {
        if (zzf == null) {
            throw new NullPointerException();
        }
        try {
            zza(zzmq.zzb(zzf));
        } catch (zzmq.zzg e) {
            zzbg.zzak("Not loading resource: " + zzf + " because it is invalid: " + e.toString());
        }
    }

    private void zza(zzmq.zzc zzc) {
        this.zzaCq = zzc.getVersion();
        zzmq.zzc zzc2 = zzc;
        zza(new zzcp(this.mContext, zzc2, this.zzaCl, new zza(), new zzb(), zzdA(this.zzaCq)));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzaCl.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzaCk));
        }
    }

    private synchronized void zza(zzcp zzcp) {
        this.zzaCm = zzcp;
    }

    private void zza(zzc.zzi[] zziArr) {
        ArrayList arrayList = new ArrayList();
        for (zzc.zzi add : zziArr) {
            arrayList.add(add);
        }
        zzwt().zzt(arrayList);
    }

    private synchronized zzcp zzwt() {
        return this.zzaCm;
    }

    public boolean getBoolean(String key) {
        zzcp zzwt = zzwt();
        if (zzwt == null) {
            zzbg.zzak("getBoolean called for closed container.");
            return zzdf.zzxT().booleanValue();
        }
        try {
            return zzdf.zzk(zzwt.zzdV(key).getObject()).booleanValue();
        } catch (Exception e) {
            zzbg.zzak("Calling getBoolean() threw an exception: " + e.getMessage() + " Returning default value.");
            return zzdf.zzxT().booleanValue();
        }
    }

    public String getContainerId() {
        return this.zzaCk;
    }

    public double getDouble(String key) {
        zzcp zzwt = zzwt();
        if (zzwt == null) {
            zzbg.zzak("getDouble called for closed container.");
            return zzdf.zzxS().doubleValue();
        }
        try {
            return zzdf.zzj(zzwt.zzdV(key).getObject()).doubleValue();
        } catch (Exception e) {
            zzbg.zzak("Calling getDouble() threw an exception: " + e.getMessage() + " Returning default value.");
            return zzdf.zzxS().doubleValue();
        }
    }

    public long getLastRefreshTime() {
        return this.zzaCp;
    }

    public long getLong(String key) {
        zzcp zzwt = zzwt();
        if (zzwt == null) {
            zzbg.zzak("getLong called for closed container.");
            return zzdf.zzxR().longValue();
        }
        try {
            return zzdf.zzi(zzwt.zzdV(key).getObject()).longValue();
        } catch (Exception e) {
            zzbg.zzak("Calling getLong() threw an exception: " + e.getMessage() + " Returning default value.");
            return zzdf.zzxR().longValue();
        }
    }

    public String getString(String key) {
        zzcp zzwt = zzwt();
        if (zzwt == null) {
            zzbg.zzak("getString called for closed container.");
            return zzdf.zzxV();
        }
        try {
            return zzdf.zzg(zzwt.zzdV(key).getObject());
        } catch (Exception e) {
            zzbg.zzak("Calling getString() threw an exception: " + e.getMessage() + " Returning default value.");
            return zzdf.zzxV();
        }
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String customMacroName, FunctionCallMacroCallback customMacroCallback) {
        if (customMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.zzaCn) {
            this.zzaCn.put(customMacroName, customMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(String customTagName, FunctionCallTagCallback customTagCallback) {
        if (customTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.zzaCo) {
            this.zzaCo.put(customTagName, customTagCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public void release() {
        this.zzaCm = null;
    }

    public void unregisterFunctionCallMacroCallback(String customMacroName) {
        synchronized (this.zzaCn) {
            this.zzaCn.remove(customMacroName);
        }
    }

    public void unregisterFunctionCallTagCallback(String customTagName) {
        synchronized (this.zzaCo) {
            this.zzaCo.remove(customTagName);
        }
    }

    /* access modifiers changed from: package-private */
    public zzah zzdA(String str) {
        if (zzcb.zzxl().zzxm().equals(zzcb.zza.CONTAINER_DEBUG)) {
        }
        return new zzbo();
    }

    /* access modifiers changed from: package-private */
    public FunctionCallMacroCallback zzdx(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzaCn) {
            functionCallMacroCallback = this.zzaCn.get(str);
        }
        return functionCallMacroCallback;
    }

    /* access modifiers changed from: package-private */
    public FunctionCallTagCallback zzdy(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzaCo) {
            functionCallTagCallback = this.zzaCo.get(str);
        }
        return functionCallTagCallback;
    }

    /* access modifiers changed from: package-private */
    public void zzdz(String str) {
        zzwt().zzdz(str);
    }

    /* access modifiers changed from: package-private */
    public String zzws() {
        return this.zzaCq;
    }
}
