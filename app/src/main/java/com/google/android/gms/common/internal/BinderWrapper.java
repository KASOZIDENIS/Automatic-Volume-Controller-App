package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new Parcelable.Creator<BinderWrapper>() {
        /* renamed from: zzF */
        public BinderWrapper createFromParcel(Parcel parcel) {
            return new BinderWrapper(parcel);
        }

        /* renamed from: zzaM */
        public BinderWrapper[] newArray(int i) {
            return new BinderWrapper[i];
        }
    };
    private IBinder zzSU;

    public BinderWrapper() {
        this.zzSU = null;
    }

    public BinderWrapper(IBinder binder) {
        this.zzSU = null;
        this.zzSU = binder;
    }

    private BinderWrapper(Parcel in) {
        this.zzSU = null;
        this.zzSU = in.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStrongBinder(this.zzSU);
    }
}
