package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzhn;

public interface zzho extends IInterface {

    public static abstract class zza extends Binder implements zzho {

        /* renamed from: com.google.android.gms.internal.zzho$zza$zza  reason: collision with other inner class name */
        private static class C0018zza implements zzho {
            private IBinder zzlW;

            C0018zza(IBinder iBinder) {
                this.zzlW = iBinder;
            }

            public IBinder asBinder() {
                return this.zzlW;
            }

            public void zza(zzhn zzhn) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.service.ICommonService");
                    if (zzhn != null) {
                        iBinder = zzhn.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzlW.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static zzho zzZ(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzho)) ? new C0018zza(iBinder) : (zzho) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.common.internal.service.ICommonService");
                    zza(zzhn.zza.zzY(data.readStrongBinder()));
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.common.internal.service.ICommonService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void zza(zzhn zzhn) throws RemoteException;
}
