package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.internal.zzv;

public class PlayLoggerContext implements SafeParcelable {
    public static final zze CREATOR = new zze();
    public final String packageName;
    public final int versionCode;
    public final int zzayB;
    public final int zzayC;
    public final String zzayD;
    public final String zzayE;
    public final boolean zzayF;
    public final String zzayG;
    public final boolean zzayH;

    public PlayLoggerContext(int versionCode2, String packageName2, int packageVersionCode, int logSource, String uploadAccountName, String loggingId, boolean logAndroidId, String logSourceName, boolean isAnonymous) {
        this.versionCode = versionCode2;
        this.packageName = packageName2;
        this.zzayB = packageVersionCode;
        this.zzayC = logSource;
        this.zzayD = uploadAccountName;
        this.zzayE = loggingId;
        this.zzayF = logAndroidId;
        this.zzayG = logSourceName;
        this.zzayH = isAnonymous;
    }

    @Deprecated
    public PlayLoggerContext(String packageName2, int packageVersionCode, int logSource, String uploadAccountName, String loggingId, boolean logAndroidId) {
        this.versionCode = 1;
        this.packageName = (String) zzv.zzr(packageName2);
        this.zzayB = packageVersionCode;
        this.zzayC = logSource;
        this.zzayG = null;
        this.zzayD = uploadAccountName;
        this.zzayE = loggingId;
        this.zzayF = logAndroidId;
        this.zzayH = false;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlayLoggerContext)) {
            return false;
        }
        PlayLoggerContext playLoggerContext = (PlayLoggerContext) object;
        return this.packageName.equals(playLoggerContext.packageName) && this.zzayB == playLoggerContext.zzayB && this.zzayC == playLoggerContext.zzayC && zzu.equal(this.zzayG, playLoggerContext.zzayG) && zzu.equal(this.zzayD, playLoggerContext.zzayD) && zzu.equal(this.zzayE, playLoggerContext.zzayE) && this.zzayF == playLoggerContext.zzayF && this.zzayH == playLoggerContext.zzayH;
    }

    public int hashCode() {
        return zzu.hashCode(this.packageName, Integer.valueOf(this.zzayB), Integer.valueOf(this.zzayC), this.zzayG, this.zzayD, this.zzayE, Boolean.valueOf(this.zzayF), Boolean.valueOf(this.zzayH));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("package=").append(this.packageName).append(',');
        sb.append("versionCode=").append(this.versionCode).append(',');
        sb.append("logSource=").append(this.zzayC).append(',');
        sb.append("logSourceName=").append(this.zzayG).append(',');
        sb.append("uploadAccount=").append(this.zzayD).append(',');
        sb.append("loggingId=").append(this.zzayE).append(',');
        sb.append("logAndroidId=").append(this.zzayF).append(',');
        sb.append("isAnonymous=").append(this.zzayH);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        zze.zza(this, out, flags);
    }
}
