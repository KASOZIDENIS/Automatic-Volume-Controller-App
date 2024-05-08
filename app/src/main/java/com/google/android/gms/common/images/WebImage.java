package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzu;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage implements SafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = new zzb();
    private final Uri zzSr;
    private final int zzma;
    private final int zzmb;
    private final int zzzH;

    WebImage(int versionCode, Uri url, int width, int height) {
        this.zzzH = versionCode;
        this.zzSr = url;
        this.zzma = width;
        this.zzmb = height;
    }

    public WebImage(Uri url) throws IllegalArgumentException {
        this(url, 0, 0);
    }

    public WebImage(Uri url, int width, int height) throws IllegalArgumentException {
        this(1, url, width, height);
        if (url == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(JSONObject json) throws IllegalArgumentException {
        this(zzg(json), json.optInt("width", 0), json.optInt("height", 0));
    }

    private static Uri zzg(JSONObject jSONObject) {
        if (!jSONObject.has("url")) {
            return null;
        }
        try {
            return Uri.parse(jSONObject.getString("url"));
        } catch (JSONException e) {
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) other;
        return zzu.equal(this.zzSr, webImage.zzSr) && this.zzma == webImage.zzma && this.zzmb == webImage.zzmb;
    }

    public int getHeight() {
        return this.zzmb;
    }

    public Uri getUrl() {
        return this.zzSr;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.zzzH;
    }

    public int getWidth() {
        return this.zzma;
    }

    public int hashCode() {
        return zzu.hashCode(this.zzSr, Integer.valueOf(this.zzma), Integer.valueOf(this.zzmb));
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.zzSr.toString());
            jSONObject.put("width", this.zzma);
            jSONObject.put("height", this.zzmb);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public String toString() {
        return String.format("Image %dx%d %s", new Object[]{Integer.valueOf(this.zzma), Integer.valueOf(this.zzmb), this.zzSr.toString()});
    }

    public void writeToParcel(Parcel out, int flags) {
        zzb.zza(this, out, flags);
    }
}
