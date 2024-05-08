package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FieldMappingDictionary implements SafeParcelable {
    public static final zzc CREATOR = new zzc();
    private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zzUI;
    private final ArrayList<Entry> zzUJ;
    private final String zzUK;
    private final int zzzH;

    public static class Entry implements SafeParcelable {
        public static final zzd CREATOR = new zzd();
        final String className;
        final int versionCode;
        final ArrayList<FieldMapPair> zzUL;

        Entry(int versionCode2, String className2, ArrayList<FieldMapPair> fieldMapping) {
            this.versionCode = versionCode2;
            this.className = className2;
            this.zzUL = fieldMapping;
        }

        Entry(String className2, Map<String, FastJsonResponse.Field<?, ?>> fieldMap) {
            this.versionCode = 1;
            this.className = className2;
            this.zzUL = zzA(fieldMap);
        }

        private static ArrayList<FieldMapPair> zzA(Map<String, FastJsonResponse.Field<?, ?>> map) {
            if (map == null) {
                return null;
            }
            ArrayList<FieldMapPair> arrayList = new ArrayList<>();
            for (String next : map.keySet()) {
                arrayList.add(new FieldMapPair(next, map.get(next)));
            }
            return arrayList;
        }

        public int describeContents() {
            zzd zzd = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            zzd zzd = CREATOR;
            zzd.zza(this, out, flags);
        }

        /* access modifiers changed from: package-private */
        public HashMap<String, FastJsonResponse.Field<?, ?>> zzmP() {
            HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<>();
            int size = this.zzUL.size();
            for (int i = 0; i < size; i++) {
                FieldMapPair fieldMapPair = this.zzUL.get(i);
                hashMap.put(fieldMapPair.zzgk, fieldMapPair.zzUM);
            }
            return hashMap;
        }
    }

    public static class FieldMapPair implements SafeParcelable {
        public static final zzb CREATOR = new zzb();
        final int versionCode;
        final FastJsonResponse.Field<?, ?> zzUM;
        final String zzgk;

        FieldMapPair(int versionCode2, String key, FastJsonResponse.Field<?, ?> value) {
            this.versionCode = versionCode2;
            this.zzgk = key;
            this.zzUM = value;
        }

        FieldMapPair(String key, FastJsonResponse.Field<?, ?> value) {
            this.versionCode = 1;
            this.zzgk = key;
            this.zzUM = value;
        }

        public int describeContents() {
            zzb zzb = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            zzb zzb = CREATOR;
            zzb.zza(this, out, flags);
        }
    }

    FieldMappingDictionary(int versionCode, ArrayList<Entry> serializedDictionary, String rootClassName) {
        this.zzzH = versionCode;
        this.zzUJ = null;
        this.zzUI = zzc(serializedDictionary);
        this.zzUK = (String) zzv.zzr(rootClassName);
        zzmL();
    }

    public FieldMappingDictionary(Class<? extends FastJsonResponse> rootClazz) {
        this.zzzH = 1;
        this.zzUJ = null;
        this.zzUI = new HashMap<>();
        this.zzUK = rootClazz.getCanonicalName();
    }

    private static HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zzc(ArrayList<Entry> arrayList) {
        HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> hashMap = new HashMap<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Entry entry = arrayList.get(i);
            hashMap.put(entry.className, entry.zzmP());
        }
        return hashMap;
    }

    public int describeContents() {
        zzc zzc = CREATOR;
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.zzzH;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String next : this.zzUI.keySet()) {
            sb.append(next).append(":\n");
            Map map = this.zzUI.get(next);
            for (String str : map.keySet()) {
                sb.append("  ").append(str).append(": ");
                sb.append(map.get(str));
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        zzc zzc = CREATOR;
        zzc.zza(this, out, flags);
    }

    public void zza(Class<? extends FastJsonResponse> cls, Map<String, FastJsonResponse.Field<?, ?>> map) {
        this.zzUI.put(cls.getCanonicalName(), map);
    }

    public boolean zzb(Class<? extends FastJsonResponse> cls) {
        return this.zzUI.containsKey(cls.getCanonicalName());
    }

    public Map<String, FastJsonResponse.Field<?, ?>> zzbX(String str) {
        return this.zzUI.get(str);
    }

    public void zzmL() {
        for (String str : this.zzUI.keySet()) {
            Map map = this.zzUI.get(str);
            for (String str2 : map.keySet()) {
                ((FastJsonResponse.Field) map.get(str2)).zza(this);
            }
        }
    }

    public void zzmM() {
        for (String next : this.zzUI.keySet()) {
            Map map = this.zzUI.get(next);
            HashMap hashMap = new HashMap();
            for (String str : map.keySet()) {
                hashMap.put(str, ((FastJsonResponse.Field) map.get(str)).zzmB());
            }
            this.zzUI.put(next, hashMap);
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Entry> zzmN() {
        ArrayList<Entry> arrayList = new ArrayList<>();
        for (String next : this.zzUI.keySet()) {
            arrayList.add(new Entry(next, this.zzUI.get(next)));
        }
        return arrayList;
    }

    public String zzmO() {
        return this.zzUK;
    }
}
