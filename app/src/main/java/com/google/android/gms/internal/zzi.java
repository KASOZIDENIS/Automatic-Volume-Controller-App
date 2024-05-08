package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.google.android.gms.internal.zzo;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;

public abstract class zzi extends zzh {
    private static long startTime = 0;
    private static String zzlA;
    private static String zzlB;
    private static zzo zzlC;
    static boolean zzlD = false;
    private static Method zzlo;
    private static Method zzlp;
    private static Method zzlq;
    private static Method zzlr;
    private static Method zzls;
    private static Method zzlt;
    private static Method zzlu;
    private static Method zzlv;
    private static Method zzlw;
    private static Method zzlx;
    private static Method zzly;
    private static String zzlz;

    static class zza extends Exception {
        public zza() {
        }

        public zza(Throwable th) {
            super(th);
        }
    }

    protected zzi(Context context, zzm zzm, zzn zzn) {
        super(context, zzm, zzn);
    }

    static String zza(Context context, zzm zzm) throws zza {
        if (zzlA != null) {
            return zzlA;
        }
        if (zzlr == null) {
            throw new zza();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) zzlr.invoke((Object) null, new Object[]{context});
            if (byteBuffer == null) {
                throw new zza();
            }
            zzlA = zzm.zza(byteBuffer.array(), true);
            return zzlA;
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    static ArrayList<Long> zza(MotionEvent motionEvent, DisplayMetrics displayMetrics) throws zza {
        if (zzls == null || motionEvent == null) {
            throw new zza();
        }
        try {
            return (ArrayList) zzls.invoke((Object) null, new Object[]{motionEvent, displayMetrics});
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    protected static synchronized void zza(String str, Context context, zzm zzm) {
        synchronized (zzi.class) {
            if (!zzlD) {
                try {
                    zzlC = new zzo(zzm, (SecureRandom) null);
                    zzlz = str;
                    zzi(context);
                    startTime = zzw().longValue();
                    zzlD = true;
                } catch (zza | UnsupportedOperationException e) {
                }
            }
        }
    }

    static String zzb(Context context, zzm zzm) throws zza {
        if (zzlB != null) {
            return zzlB;
        }
        if (zzlu == null) {
            throw new zza();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) zzlu.invoke((Object) null, new Object[]{context});
            if (byteBuffer == null) {
                throw new zza();
            }
            zzlB = zzm.zza(byteBuffer.array(), true);
            return zzlB;
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    private static String zzb(byte[] bArr, String str) throws zza {
        try {
            return new String(zzlC.zzc(bArr, str), "UTF-8");
        } catch (zzo.zza e) {
            throw new zza(e);
        } catch (UnsupportedEncodingException e2) {
            throw new zza(e2);
        }
    }

    static String zzd(Context context) throws zza {
        if (zzlt == null) {
            throw new zza();
        }
        try {
            String str = (String) zzlt.invoke((Object) null, new Object[]{context});
            if (str != null) {
                return str;
            }
            throw new zza();
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    static ArrayList<Long> zze(Context context) throws zza {
        if (zzlv == null) {
            throw new zza();
        }
        try {
            ArrayList<Long> arrayList = (ArrayList) zzlv.invoke((Object) null, new Object[]{context});
            if (arrayList != null && arrayList.size() == 2) {
                return arrayList;
            }
            throw new zza();
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    static int[] zzf(Context context) throws zza {
        if (zzlw == null) {
            throw new zza();
        }
        try {
            return (int[]) zzlw.invoke((Object) null, new Object[]{context});
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    static int zzg(Context context) throws zza {
        if (zzlx == null) {
            throw new zza();
        }
        try {
            return ((Integer) zzlx.invoke((Object) null, new Object[]{context})).intValue();
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    static int zzh(Context context) throws zza {
        if (zzly == null) {
            throw new zza();
        }
        try {
            return ((Integer) zzly.invoke((Object) null, new Object[]{context})).intValue();
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    private static void zzi(Context context) throws zza {
        File file;
        File createTempFile;
        try {
            byte[] zzc = zzlC.zzc(zzq.getKey());
            byte[] zzc2 = zzlC.zzc(zzc, zzq.zzE());
            File cacheDir = context.getCacheDir();
            if (cacheDir == null && (cacheDir = context.getDir("dex", 0)) == null) {
                throw new zza();
            }
            file = cacheDir;
            createTempFile = File.createTempFile("ads", ".jar", file);
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            fileOutputStream.write(zzc2, 0, zzc2.length);
            fileOutputStream.close();
            DexClassLoader dexClassLoader = new DexClassLoader(createTempFile.getAbsolutePath(), file.getAbsolutePath(), (String) null, context.getClassLoader());
            Class loadClass = dexClassLoader.loadClass(zzb(zzc, zzq.zzJ()));
            Class loadClass2 = dexClassLoader.loadClass(zzb(zzc, zzq.zzX()));
            Class loadClass3 = dexClassLoader.loadClass(zzb(zzc, zzq.zzR()));
            Class loadClass4 = dexClassLoader.loadClass(zzb(zzc, zzq.zzN()));
            Class loadClass5 = dexClassLoader.loadClass(zzb(zzc, zzq.zzZ()));
            Class loadClass6 = dexClassLoader.loadClass(zzb(zzc, zzq.zzL()));
            Class loadClass7 = dexClassLoader.loadClass(zzb(zzc, zzq.zzV()));
            Class loadClass8 = dexClassLoader.loadClass(zzb(zzc, zzq.zzT()));
            Class loadClass9 = dexClassLoader.loadClass(zzb(zzc, zzq.zzH()));
            Class loadClass10 = dexClassLoader.loadClass(zzb(zzc, zzq.zzP()));
            Class loadClass11 = dexClassLoader.loadClass(zzb(zzc, zzq.zzF()));
            zzlo = loadClass.getMethod(zzb(zzc, zzq.zzK()), new Class[0]);
            zzlp = loadClass2.getMethod(zzb(zzc, zzq.zzY()), new Class[0]);
            zzlq = loadClass3.getMethod(zzb(zzc, zzq.zzS()), new Class[0]);
            zzlr = loadClass4.getMethod(zzb(zzc, zzq.zzO()), new Class[]{Context.class});
            zzls = loadClass5.getMethod(zzb(zzc, zzq.zzaa()), new Class[]{MotionEvent.class, DisplayMetrics.class});
            zzlt = loadClass6.getMethod(zzb(zzc, zzq.zzM()), new Class[]{Context.class});
            zzlu = loadClass7.getMethod(zzb(zzc, zzq.zzW()), new Class[]{Context.class});
            zzlv = loadClass8.getMethod(zzb(zzc, zzq.zzU()), new Class[]{Context.class});
            zzlw = loadClass9.getMethod(zzb(zzc, zzq.zzI()), new Class[]{Context.class});
            zzlx = loadClass10.getMethod(zzb(zzc, zzq.zzQ()), new Class[]{Context.class});
            zzly = loadClass11.getMethod(zzb(zzc, zzq.zzG()), new Class[]{Context.class});
            String name = createTempFile.getName();
            createTempFile.delete();
            new File(file, name.replace(".jar", ".dex")).delete();
        } catch (FileNotFoundException e) {
            throw new zza(e);
        } catch (IOException e2) {
            throw new zza(e2);
        } catch (ClassNotFoundException e3) {
            throw new zza(e3);
        } catch (zzo.zza e4) {
            throw new zza(e4);
        } catch (NoSuchMethodException e5) {
            throw new zza(e5);
        } catch (NullPointerException e6) {
            throw new zza(e6);
        } catch (Throwable th) {
            String name2 = createTempFile.getName();
            createTempFile.delete();
            new File(file, name2.replace(".jar", ".dex")).delete();
            throw th;
        }
    }

    static String zzv() throws zza {
        if (zzlz != null) {
            return zzlz;
        }
        throw new zza();
    }

    static Long zzw() throws zza {
        if (zzlo == null) {
            throw new zza();
        }
        try {
            return (Long) zzlo.invoke((Object) null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    static String zzx() throws zza {
        if (zzlq == null) {
            throw new zza();
        }
        try {
            return (String) zzlq.invoke((Object) null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    static Long zzy() throws zza {
        if (zzlp == null) {
            throw new zza();
        }
        try {
            return (Long) zzlp.invoke((Object) null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new zza(e);
        } catch (InvocationTargetException e2) {
            throw new zza(e2);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a3 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:6:0x0010] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzb(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 1
            java.lang.String r1 = zzx()     // Catch:{ zza -> 0x00b8, IOException -> 0x00a3 }
            r6.zza((int) r0, (java.lang.String) r1)     // Catch:{ zza -> 0x00b8, IOException -> 0x00a3 }
        L_0x0008:
            r0 = 2
            java.lang.String r1 = zzv()     // Catch:{ zza -> 0x00b5, IOException -> 0x00a3 }
            r6.zza((int) r0, (java.lang.String) r1)     // Catch:{ zza -> 0x00b5, IOException -> 0x00a3 }
        L_0x0010:
            java.lang.Long r0 = zzw()     // Catch:{ zza -> 0x00b3, IOException -> 0x00a3 }
            long r0 = r0.longValue()     // Catch:{ zza -> 0x00b3, IOException -> 0x00a3 }
            r2 = 25
            r6.zza((int) r2, (long) r0)     // Catch:{ zza -> 0x00b3, IOException -> 0x00a3 }
            long r2 = startTime     // Catch:{ zza -> 0x00b3, IOException -> 0x00a3 }
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x0034
            r2 = 17
            long r4 = startTime     // Catch:{ zza -> 0x00b3, IOException -> 0x00a3 }
            long r0 = r0 - r4
            r6.zza((int) r2, (long) r0)     // Catch:{ zza -> 0x00b3, IOException -> 0x00a3 }
            r0 = 23
            long r2 = startTime     // Catch:{ zza -> 0x00b3, IOException -> 0x00a3 }
            r6.zza((int) r0, (long) r2)     // Catch:{ zza -> 0x00b3, IOException -> 0x00a3 }
        L_0x0034:
            java.util.ArrayList r1 = zze(r7)     // Catch:{ zza -> 0x00b1, IOException -> 0x00a3 }
            r2 = 31
            r0 = 0
            java.lang.Object r0 = r1.get(r0)     // Catch:{ zza -> 0x00b1, IOException -> 0x00a3 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ zza -> 0x00b1, IOException -> 0x00a3 }
            long r4 = r0.longValue()     // Catch:{ zza -> 0x00b1, IOException -> 0x00a3 }
            r6.zza((int) r2, (long) r4)     // Catch:{ zza -> 0x00b1, IOException -> 0x00a3 }
            r2 = 32
            r0 = 1
            java.lang.Object r0 = r1.get(r0)     // Catch:{ zza -> 0x00b1, IOException -> 0x00a3 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ zza -> 0x00b1, IOException -> 0x00a3 }
            long r0 = r0.longValue()     // Catch:{ zza -> 0x00b1, IOException -> 0x00a3 }
            r6.zza((int) r2, (long) r0)     // Catch:{ zza -> 0x00b1, IOException -> 0x00a3 }
        L_0x0058:
            r0 = 33
            java.lang.Long r1 = zzy()     // Catch:{ zza -> 0x00af, IOException -> 0x00a3 }
            long r2 = r1.longValue()     // Catch:{ zza -> 0x00af, IOException -> 0x00a3 }
            r6.zza((int) r0, (long) r2)     // Catch:{ zza -> 0x00af, IOException -> 0x00a3 }
        L_0x0065:
            r0 = 27
            com.google.android.gms.internal.zzm r1 = r6.zzlm     // Catch:{ zza -> 0x00ad, IOException -> 0x00a3 }
            java.lang.String r1 = zza((android.content.Context) r7, (com.google.android.gms.internal.zzm) r1)     // Catch:{ zza -> 0x00ad, IOException -> 0x00a3 }
            r6.zza((int) r0, (java.lang.String) r1)     // Catch:{ zza -> 0x00ad, IOException -> 0x00a3 }
        L_0x0070:
            r0 = 29
            com.google.android.gms.internal.zzm r1 = r6.zzlm     // Catch:{ zza -> 0x00ab, IOException -> 0x00a3 }
            java.lang.String r1 = zzb((android.content.Context) r7, (com.google.android.gms.internal.zzm) r1)     // Catch:{ zza -> 0x00ab, IOException -> 0x00a3 }
            r6.zza((int) r0, (java.lang.String) r1)     // Catch:{ zza -> 0x00ab, IOException -> 0x00a3 }
        L_0x007b:
            int[] r0 = zzf(r7)     // Catch:{ zza -> 0x00a9, IOException -> 0x00a3 }
            r1 = 5
            r2 = 0
            r2 = r0[r2]     // Catch:{ zza -> 0x00a9, IOException -> 0x00a3 }
            long r2 = (long) r2     // Catch:{ zza -> 0x00a9, IOException -> 0x00a3 }
            r6.zza((int) r1, (long) r2)     // Catch:{ zza -> 0x00a9, IOException -> 0x00a3 }
            r1 = 6
            r2 = 1
            r0 = r0[r2]     // Catch:{ zza -> 0x00a9, IOException -> 0x00a3 }
            long r2 = (long) r0     // Catch:{ zza -> 0x00a9, IOException -> 0x00a3 }
            r6.zza((int) r1, (long) r2)     // Catch:{ zza -> 0x00a9, IOException -> 0x00a3 }
        L_0x008f:
            int r0 = zzg(r7)     // Catch:{ zza -> 0x00a7, IOException -> 0x00a3 }
            r1 = 12
            long r2 = (long) r0     // Catch:{ zza -> 0x00a7, IOException -> 0x00a3 }
            r6.zza((int) r1, (long) r2)     // Catch:{ zza -> 0x00a7, IOException -> 0x00a3 }
        L_0x0099:
            int r0 = zzh(r7)     // Catch:{ zza -> 0x00a5, IOException -> 0x00a3 }
            r1 = 3
            long r2 = (long) r0     // Catch:{ zza -> 0x00a5, IOException -> 0x00a3 }
            r6.zza((int) r1, (long) r2)     // Catch:{ zza -> 0x00a5, IOException -> 0x00a3 }
        L_0x00a2:
            return
        L_0x00a3:
            r0 = move-exception
            goto L_0x00a2
        L_0x00a5:
            r0 = move-exception
            goto L_0x00a2
        L_0x00a7:
            r0 = move-exception
            goto L_0x0099
        L_0x00a9:
            r0 = move-exception
            goto L_0x008f
        L_0x00ab:
            r0 = move-exception
            goto L_0x007b
        L_0x00ad:
            r0 = move-exception
            goto L_0x0070
        L_0x00af:
            r0 = move-exception
            goto L_0x0065
        L_0x00b1:
            r0 = move-exception
            goto L_0x0058
        L_0x00b3:
            r0 = move-exception
            goto L_0x0034
        L_0x00b5:
            r0 = move-exception
            goto L_0x0010
        L_0x00b8:
            r0 = move-exception
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzi.zzb(android.content.Context):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x005d A[ExcHandler: IOException (e java.io.IOException), Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzc(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 2
            java.lang.String r1 = zzv()     // Catch:{ zza -> 0x0065, IOException -> 0x005d }
            r6.zza((int) r0, (java.lang.String) r1)     // Catch:{ zza -> 0x0065, IOException -> 0x005d }
        L_0x0008:
            r0 = 1
            java.lang.String r1 = zzx()     // Catch:{ zza -> 0x0063, IOException -> 0x005d }
            r6.zza((int) r0, (java.lang.String) r1)     // Catch:{ zza -> 0x0063, IOException -> 0x005d }
        L_0x0010:
            r0 = 25
            java.lang.Long r1 = zzw()     // Catch:{ zza -> 0x0061, IOException -> 0x005d }
            long r2 = r1.longValue()     // Catch:{ zza -> 0x0061, IOException -> 0x005d }
            r6.zza((int) r0, (long) r2)     // Catch:{ zza -> 0x0061, IOException -> 0x005d }
        L_0x001d:
            android.view.MotionEvent r0 = r6.zzlk     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            android.util.DisplayMetrics r1 = r6.zzll     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            java.util.ArrayList r1 = zza((android.view.MotionEvent) r0, (android.util.DisplayMetrics) r1)     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            r2 = 14
            r0 = 0
            java.lang.Object r0 = r1.get(r0)     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            long r4 = r0.longValue()     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            r6.zza((int) r2, (long) r4)     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            r2 = 15
            r0 = 1
            java.lang.Object r0 = r1.get(r0)     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            long r4 = r0.longValue()     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            r6.zza((int) r2, (long) r4)     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            int r0 = r1.size()     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            r2 = 3
            if (r0 < r2) goto L_0x005c
            r2 = 16
            r0 = 2
            java.lang.Object r0 = r1.get(r0)     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            long r0 = r0.longValue()     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
            r6.zza((int) r2, (long) r0)     // Catch:{ zza -> 0x005f, IOException -> 0x005d }
        L_0x005c:
            return
        L_0x005d:
            r0 = move-exception
            goto L_0x005c
        L_0x005f:
            r0 = move-exception
            goto L_0x005c
        L_0x0061:
            r0 = move-exception
            goto L_0x001d
        L_0x0063:
            r0 = move-exception
            goto L_0x0010
        L_0x0065:
            r0 = move-exception
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzi.zzc(android.content.Context):void");
    }
}
