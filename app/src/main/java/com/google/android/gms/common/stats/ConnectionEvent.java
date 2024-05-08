package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ConnectionEvent implements SafeParcelable {
    public static final Parcelable.Creator<ConnectionEvent> CREATOR = new zza();
    private final long zzUR;
    private String zzUS;
    private final String zzUT;
    private final String zzUU;
    private final String zzUV;
    private final String zzUW;
    private final String zzUX;
    private final long zzUY;
    private final long zzUZ;
    private final long zzVa;
    private long zzVb;
    final int zzzH;

    ConnectionEvent(int versionCode, long timeMillis, String eventType, String callingProcess, String callingService, String targetProcess, String targetService, String stackTrace, long connKey, long elapsedRealtime, long heapAlloc) {
        this.zzzH = versionCode;
        this.zzUR = timeMillis;
        this.zzUS = eventType;
        this.zzUT = callingProcess;
        this.zzUU = callingService;
        this.zzUV = targetProcess;
        this.zzUW = targetService;
        this.zzVb = -1;
        this.zzUX = stackTrace;
        this.zzUY = connKey;
        this.zzUZ = elapsedRealtime;
        this.zzVa = heapAlloc;
    }

    public ConnectionEvent(long timeMillis, String eventType, String callingProcess, String callingService, String targetProcess, String targetService, String stackTrace, long connKey, long elapsedRealtime, long heapAlloc) {
        this(1, timeMillis, eventType, callingProcess, callingService, targetProcess, targetService, stackTrace, connKey, elapsedRealtime, heapAlloc);
    }

    public int describeContents() {
        return 0;
    }

    public long getTimeMillis() {
        return this.zzUR;
    }

    public void writeToParcel(Parcel out, int flags) {
        zza.zza(this, out, flags);
    }

    public String zzmS() {
        return this.zzUS;
    }

    public String zzmT() {
        return this.zzUT;
    }

    public String zzmU() {
        return this.zzUU;
    }

    public String zzmV() {
        return this.zzUV;
    }

    public String zzmW() {
        return this.zzUW;
    }

    public String zzmX() {
        return this.zzUX;
    }

    public long zzmY() {
        return this.zzUY;
    }

    public long zzmZ() {
        return this.zzVa;
    }

    public long zzna() {
        return this.zzUZ;
    }
}
