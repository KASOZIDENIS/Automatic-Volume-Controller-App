package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.internal.view.SupportMenu;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class zza {

    /* renamed from: com.google.android.gms.common.internal.safeparcel.zza$zza  reason: collision with other inner class name */
    public static class C0004zza extends RuntimeException {
        public C0004zza(String str, Parcel parcel) {
            super(str + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
        }
    }

    public static String[] zzA(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        String[] createStringArray = parcel.createStringArray();
        parcel.setDataPosition(zza + dataPosition);
        return createStringArray;
    }

    public static ArrayList<Integer> zzB(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        int readInt = parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList.add(Integer.valueOf(parcel.readInt()));
        }
        parcel.setDataPosition(dataPosition + zza);
        return arrayList;
    }

    public static ArrayList<String> zzC(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(zza + dataPosition);
        return createStringArrayList;
    }

    public static Parcel zzD(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.appendFrom(parcel, dataPosition, zza);
        parcel.setDataPosition(zza + dataPosition);
        return obtain;
    }

    public static Parcel[] zzE(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        Parcel[] parcelArr = new Parcel[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            int readInt2 = parcel.readInt();
            if (readInt2 != 0) {
                int dataPosition2 = parcel.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, readInt2);
                parcelArr[i2] = obtain;
                parcel.setDataPosition(readInt2 + dataPosition2);
            } else {
                parcelArr[i2] = null;
            }
        }
        parcel.setDataPosition(dataPosition + zza);
        return parcelArr;
    }

    public static int zzK(Parcel parcel) {
        return parcel.readInt();
    }

    public static int zzL(Parcel parcel) {
        int zzK = zzK(parcel);
        int zza = zza(parcel, zzK);
        int dataPosition = parcel.dataPosition();
        if (zzaV(zzK) != 20293) {
            throw new C0004zza("Expected object header. Got 0x" + Integer.toHexString(zzK), parcel);
        }
        int i = dataPosition + zza;
        if (i >= dataPosition && i <= parcel.dataSize()) {
            return i;
        }
        throw new C0004zza("Size read is invalid start=" + dataPosition + " end=" + i, parcel);
    }

    public static int zza(Parcel parcel, int i) {
        return (i & SupportMenu.CATEGORY_MASK) != -65536 ? (i >> 16) & SupportMenu.USER_MASK : parcel.readInt();
    }

    public static <T extends Parcelable> T zza(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        T t = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(zza + dataPosition);
        return t;
    }

    private static void zza(Parcel parcel, int i, int i2) {
        int zza = zza(parcel, i);
        if (zza != i2) {
            throw new C0004zza("Expected size " + i2 + " got " + zza + " (0x" + Integer.toHexString(zza) + ")", parcel);
        }
    }

    private static void zza(Parcel parcel, int i, int i2, int i3) {
        if (i2 != i3) {
            throw new C0004zza("Expected size " + i3 + " got " + i2 + " (0x" + Integer.toHexString(i2) + ")", parcel);
        }
    }

    public static void zza(Parcel parcel, int i, List list, ClassLoader classLoader) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza != 0) {
            parcel.readList(list, classLoader);
            parcel.setDataPosition(zza + dataPosition);
        }
    }

    public static int zzaV(int i) {
        return 65535 & i;
    }

    public static void zzb(Parcel parcel, int i) {
        parcel.setDataPosition(zza(parcel, i) + parcel.dataPosition());
    }

    public static <T> T[] zzb(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        T[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(zza + dataPosition);
        return createTypedArray;
    }

    public static <T> ArrayList<T> zzc(Parcel parcel, int i, Parcelable.Creator<T> creator) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        ArrayList<T> createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(zza + dataPosition);
        return createTypedArrayList;
    }

    public static boolean zzc(Parcel parcel, int i) {
        zza(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    public static Boolean zzd(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        if (zza == 0) {
            return null;
        }
        zza(parcel, i, zza, 4);
        return Boolean.valueOf(parcel.readInt() != 0);
    }

    public static byte zze(Parcel parcel, int i) {
        zza(parcel, i, 4);
        return (byte) parcel.readInt();
    }

    public static short zzf(Parcel parcel, int i) {
        zza(parcel, i, 4);
        return (short) parcel.readInt();
    }

    public static int zzg(Parcel parcel, int i) {
        zza(parcel, i, 4);
        return parcel.readInt();
    }

    public static Integer zzh(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        if (zza == 0) {
            return null;
        }
        zza(parcel, i, zza, 4);
        return Integer.valueOf(parcel.readInt());
    }

    public static long zzi(Parcel parcel, int i) {
        zza(parcel, i, 8);
        return parcel.readLong();
    }

    public static Long zzj(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        if (zza == 0) {
            return null;
        }
        zza(parcel, i, zza, 8);
        return Long.valueOf(parcel.readLong());
    }

    public static BigInteger zzk(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(zza + dataPosition);
        return new BigInteger(createByteArray);
    }

    public static float zzl(Parcel parcel, int i) {
        zza(parcel, i, 4);
        return parcel.readFloat();
    }

    public static double zzm(Parcel parcel, int i) {
        zza(parcel, i, 8);
        return parcel.readDouble();
    }

    public static BigDecimal zzn(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        int readInt = parcel.readInt();
        parcel.setDataPosition(zza + dataPosition);
        return new BigDecimal(new BigInteger(createByteArray), readInt);
    }

    public static String zzo(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(zza + dataPosition);
        return readString;
    }

    public static IBinder zzp(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(zza + dataPosition);
        return readStrongBinder;
    }

    public static Bundle zzq(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(zza + dataPosition);
        return readBundle;
    }

    public static byte[] zzr(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(zza + dataPosition);
        return createByteArray;
    }

    public static byte[][] zzs(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        byte[][] bArr = new byte[readInt][];
        for (int i2 = 0; i2 < readInt; i2++) {
            bArr[i2] = parcel.createByteArray();
        }
        parcel.setDataPosition(dataPosition + zza);
        return bArr;
    }

    public static boolean[] zzt(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        boolean[] createBooleanArray = parcel.createBooleanArray();
        parcel.setDataPosition(zza + dataPosition);
        return createBooleanArray;
    }

    public static int[] zzu(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        int[] createIntArray = parcel.createIntArray();
        parcel.setDataPosition(zza + dataPosition);
        return createIntArray;
    }

    public static long[] zzv(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        long[] createLongArray = parcel.createLongArray();
        parcel.setDataPosition(zza + dataPosition);
        return createLongArray;
    }

    public static BigInteger[] zzw(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        BigInteger[] bigIntegerArr = new BigInteger[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            bigIntegerArr[i2] = new BigInteger(parcel.createByteArray());
        }
        parcel.setDataPosition(dataPosition + zza);
        return bigIntegerArr;
    }

    public static float[] zzx(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        float[] createFloatArray = parcel.createFloatArray();
        parcel.setDataPosition(zza + dataPosition);
        return createFloatArray;
    }

    public static double[] zzy(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        double[] createDoubleArray = parcel.createDoubleArray();
        parcel.setDataPosition(zza + dataPosition);
        return createDoubleArray;
    }

    public static BigDecimal[] zzz(Parcel parcel, int i) {
        int zza = zza(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        BigDecimal[] bigDecimalArr = new BigDecimal[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            byte[] createByteArray = parcel.createByteArray();
            bigDecimalArr[i2] = new BigDecimal(new BigInteger(createByteArray), parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + zza);
        return bigDecimalArr;
    }
}
