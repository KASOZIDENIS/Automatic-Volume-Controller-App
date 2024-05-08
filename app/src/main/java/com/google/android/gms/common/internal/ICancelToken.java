package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICancelToken extends IInterface {

    public static abstract class zza extends Binder implements ICancelToken {

        /* renamed from: com.google.android.gms.common.internal.ICancelToken$zza$zza  reason: collision with other inner class name */
        private static class C0003zza implements ICancelToken {
            private IBinder zzlW;

            C0003zza(IBinder iBinder) {
                this.zzlW = iBinder;
            }

            public IBinder asBinder() {
                return this.zzlW;
            }

            public void cancel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.ICancelToken");
                    this.zzlW.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ICancelToken zzR(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ICancelToken)) ? new C0003zza(iBinder) : (ICancelToken) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 2:
                    data.enforceInterface("com.google.android.gms.common.internal.ICancelToken");
                    cancel();
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.common.internal.ICancelToken");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void cancel() throws RemoteException;
}
