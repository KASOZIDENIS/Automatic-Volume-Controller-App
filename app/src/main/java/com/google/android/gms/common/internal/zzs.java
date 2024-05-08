package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;

public interface zzs extends IInterface {

    public static abstract class zza extends Binder implements zzs {

        /* renamed from: com.google.android.gms.common.internal.zzs$zza$zza  reason: collision with other inner class name */
        private static class C0009zza implements zzs {
            private IBinder zzlW;

            C0009zza(IBinder iBinder) {
                this.zzlW = iBinder;
            }

            public IBinder asBinder() {
                return this.zzlW;
            }

            public zzd zza(zzd zzd, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.ISignInButtonCreator");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.zzlW.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzat(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzs zzV(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzs)) ? new C0009zza(iBinder) : (zzs) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
                    zzd zza = zza(zzd.zza.zzat(data.readStrongBinder()), data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(zza != null ? zza.asBinder() : null);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.common.internal.ISignInButtonCreator");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    zzd zza(zzd zzd, int i, int i2) throws RemoteException;
}
