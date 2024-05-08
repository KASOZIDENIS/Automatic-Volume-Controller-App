package com.google.android.gms.internal;

import java.io.IOException;

public final class zzob {
    public static final int[] zzaNV = new int[0];
    public static final long[] zzaNW = new long[0];
    public static final float[] zzaNX = new float[0];
    public static final double[] zzaNY = new double[0];
    public static final boolean[] zzaNZ = new boolean[0];
    public static final String[] zzaOa = new String[0];
    public static final byte[][] zzaOb = new byte[0][];
    public static final byte[] zzaOc = new byte[0];

    static int zzC(int i, int i2) {
        return (i << 3) | i2;
    }

    public static final int zzb(zznq zznq, int i) throws IOException {
        int i2 = 1;
        int position = zznq.getPosition();
        zznq.zzjl(i);
        while (zznq.zzzy() == i) {
            zznq.zzjl(i);
            i2++;
        }
        zznq.zzjp(position);
        return i2;
    }

    static int zzjF(int i) {
        return i & 7;
    }

    public static int zzjG(int i) {
        return i >>> 3;
    }
}
