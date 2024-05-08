package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LogEvent implements SafeParcelable {
    public static final zzc CREATOR = new zzc();
    public final String tag;
    public final int versionCode;
    public final long zzayr;
    public final byte[] zzays;
    public final Bundle zzayt;

    LogEvent(int versionCode2, long eventTime, String tag2, byte[] sourceExtensionBytes, Bundle keyValuePairs) {
        this.versionCode = versionCode2;
        this.zzayr = eventTime;
        this.tag = tag2;
        this.zzays = sourceExtensionBytes;
        this.zzayt = keyValuePairs;
    }

    public LogEvent(long eventTime, String tag2, byte[] sourceExtensionBytes, String... extras) {
        this.versionCode = 1;
        this.zzayr = eventTime;
        this.tag = tag2;
        this.zzays = sourceExtensionBytes;
        this.zzayt = zzd(extras);
    }

    private static Bundle zzd(String... strArr) {
        Bundle bundle = null;
        if (strArr != null) {
            if (strArr.length % 2 != 0) {
                throw new IllegalArgumentException("extras must have an even number of elements");
            }
            int length = strArr.length / 2;
            if (length != 0) {
                bundle = new Bundle(length);
                for (int i = 0; i < length; i++) {
                    bundle.putString(strArr[i * 2], strArr[(i * 2) + 1]);
                }
            }
        }
        return bundle;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("tag=").append(this.tag).append(",");
        sb.append("eventTime=").append(this.zzayr).append(",");
        if (this.zzayt != null && !this.zzayt.isEmpty()) {
            sb.append("keyValues=");
            for (String str : this.zzayt.keySet()) {
                sb.append("(").append(str).append(",");
                sb.append(this.zzayt.getString(str)).append(")");
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        zzc.zza(this, out, flags);
    }
}
