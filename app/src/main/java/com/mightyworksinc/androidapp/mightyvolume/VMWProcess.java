package com.mightyworksinc.androidapp.mightyvolume;

public class VMWProcess {
    public static int global_gain = 9;
    public static int hp_freq = 750;
    public static int noise_th = 100;
    public static int peak_gain = 9;
    public static int type_clp = 1;

    public static native int ChangeSetting();

    public static native int Free();

    public static native int NEInit(int i, int i2);

    public static native int NEProcess(byte[] bArr);

    public static native int PlayProcess(byte[] bArr, boolean z);

    public static native int SBInit(int i, int i2);

    static {
        System.loadLibrary("mw_jni");
    }
}
