package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzu;

public final class Status implements Result, SafeParcelable {
    public static final StatusCreator CREATOR = new StatusCreator();
    public static final Status zzQU = new Status(0);
    public static final Status zzQV = new Status(14);
    public static final Status zzQW = new Status(8);
    public static final Status zzQX = new Status(15);
    public static final Status zzQY = new Status(16);
    private final PendingIntent mPendingIntent;
    private final int zzOJ;
    private final String zzQZ;
    private final int zzzH;

    public Status(int statusCode) {
        this(statusCode, (String) null);
    }

    Status(int versionCode, int statusCode, String statusMessage, PendingIntent pendingIntent) {
        this.zzzH = versionCode;
        this.zzOJ = statusCode;
        this.zzQZ = statusMessage;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int statusCode, String statusMessage) {
        this(1, statusCode, statusMessage, (PendingIntent) null);
    }

    public Status(int statusCode, String statusMessage, PendingIntent pendingIntent) {
        this(1, statusCode, statusMessage, pendingIntent);
    }

    private String zzkv() {
        return this.zzQZ != null ? this.zzQZ : CommonStatusCodes.getStatusCodeString(this.zzOJ);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.zzzH == status.zzzH && this.zzOJ == status.zzOJ && zzu.equal(this.zzQZ, status.zzQZ) && zzu.equal(this.mPendingIntent, status.mPendingIntent);
    }

    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.zzOJ;
    }

    public String getStatusMessage() {
        return this.zzQZ;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.zzzH;
    }

    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public int hashCode() {
        return zzu.hashCode(Integer.valueOf(this.zzzH), Integer.valueOf(this.zzOJ), this.zzQZ, this.mPendingIntent);
    }

    public boolean isCanceled() {
        return this.zzOJ == 16;
    }

    public boolean isInterrupted() {
        return this.zzOJ == 14;
    }

    public boolean isSuccess() {
        return this.zzOJ <= 0;
    }

    public void startResolutionForResult(Activity activity, int requestCode) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), requestCode, (Intent) null, 0, 0, 0);
        }
    }

    public String toString() {
        return zzu.zzq(this).zzg("statusCode", zzkv()).zzg("resolution", this.mPendingIntent).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        StatusCreator.zza(this, out, flags);
    }

    /* access modifiers changed from: package-private */
    public PendingIntent zzlf() {
        return this.mPendingIntent;
    }
}
