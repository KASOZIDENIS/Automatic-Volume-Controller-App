package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface zzhn extends IInterface {

    public static abstract class zza extends Binder implements zzhn {

        /* renamed from: com.google.android.gms.internal.zzhn$zza$zza  reason: collision with other inner class name */
        private static class C0017zza implements zzhn {
            private IBinder zzlW;

            C0017zza(IBinder iBinder) {
                this.zzlW = iBinder;
            }

            public IBinder asBinder() {
                return this.zzlW;
            }

            public void zzaW(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.service.ICommonCallbacks");
                    obtain.writeInt(i);
                    this.zzlW.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.common.internal.service.ICommonCallbacks");
        }

        public static zzhn zzY(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzhn)) ? new C0017zza(iBinder) : (zzhn) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.common.internal.service.ICommonCallbacks");
                    zzaW(data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.common.internal.service.ICommonCallbacks");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void zzaW(int i) throws RemoteException;
}
