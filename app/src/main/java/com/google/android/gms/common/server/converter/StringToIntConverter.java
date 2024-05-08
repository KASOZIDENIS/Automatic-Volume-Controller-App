package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class StringToIntConverter implements SafeParcelable, FastJsonResponse.zza<String, Integer> {
    public static final zzb CREATOR = new zzb();
    private final HashMap<String, Integer> zzUt;
    private final HashMap<Integer, String> zzUu;
    private final ArrayList<Entry> zzUv;
    private final int zzzH;

    public static final class Entry implements SafeParcelable {
        public static final zzc CREATOR = new zzc();
        final int versionCode;
        final String zzUw;
        final int zzUx;

        Entry(int versionCode2, String stringValue, int intValue) {
            this.versionCode = versionCode2;
            this.zzUw = stringValue;
            this.zzUx = intValue;
        }

        Entry(String stringValue, int intValue) {
            this.versionCode = 1;
            this.zzUw = stringValue;
            this.zzUx = intValue;
        }

        public int describeContents() {
            zzc zzc = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            zzc zzc = CREATOR;
            zzc.zza(this, out, flags);
        }
    }

    public StringToIntConverter() {
        this.zzzH = 1;
        this.zzUt = new HashMap<>();
        this.zzUu = new HashMap<>();
        this.zzUv = null;
    }

    StringToIntConverter(int versionCode, ArrayList<Entry> serializedMap) {
        this.zzzH = versionCode;
        this.zzUt = new HashMap<>();
        this.zzUu = new HashMap<>();
        this.zzUv = null;
        zzb(serializedMap);
    }

    private void zzb(ArrayList<Entry> arrayList) {
        Iterator<Entry> it = arrayList.iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            zzg(next.zzUw, next.zzUx);
        }
    }

    public int describeContents() {
        zzb zzb = CREATOR;
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.zzzH;
    }

    public void writeToParcel(Parcel out, int flags) {
        zzb zzb = CREATOR;
        zzb.zza(this, out, flags);
    }

    /* renamed from: zzb */
    public String convertBack(Integer num) {
        String str = this.zzUu.get(num);
        return (str != null || !this.zzUt.containsKey("gms_unknown")) ? str : "gms_unknown";
    }

    public StringToIntConverter zzg(String str, int i) {
        this.zzUt.put(str, Integer.valueOf(i));
        this.zzUu.put(Integer.valueOf(i), str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Entry> zzmv() {
        ArrayList<Entry> arrayList = new ArrayList<>();
        for (String next : this.zzUt.keySet()) {
            arrayList.add(new Entry(next, this.zzUt.get(next).intValue()));
        }
        return arrayList;
    }

    public int zzmw() {
        return 7;
    }

    public int zzmx() {
        return 0;
    }
}
