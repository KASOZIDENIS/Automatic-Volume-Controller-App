package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public abstract class zzh implements zzg {
    protected MotionEvent zzlk;
    protected DisplayMetrics zzll;
    protected zzm zzlm;
    private zzn zzln;

    protected zzh(Context context, zzm zzm, zzn zzn) {
        this.zzlm = zzm;
        this.zzln = zzn;
        try {
            this.zzll = context.getResources().getDisplayMetrics();
        } catch (UnsupportedOperationException e) {
            this.zzll = new DisplayMetrics();
            this.zzll.density = 1.0f;
        }
    }

    private String zza(Context context, String str, boolean z) {
        byte[] zzu;
        try {
            synchronized (this) {
                zzt();
                if (z) {
                    zzc(context);
                } else {
                    zzb(context);
                }
                zzu = zzu();
            }
            return zzu.length == 0 ? Integer.toString(5) : zza(zzu, str);
        } catch (NoSuchAlgorithmException e) {
            return Integer.toString(7);
        } catch (UnsupportedEncodingException e2) {
            return Integer.toString(7);
        } catch (IOException e3) {
            return Integer.toString(3);
        }
    }

    private void zzt() {
        this.zzln.reset();
    }

    private byte[] zzu() throws IOException {
        return this.zzln.zzD();
    }

    public String zza(Context context) {
        return zza(context, (String) null, false);
    }

    public String zza(Context context, String str) {
        return zza(context, str, true);
    }

    /* access modifiers changed from: package-private */
    public String zza(byte[] bArr, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
        byte[] array;
        if (bArr.length > 239) {
            zzt();
            zza(20, 1);
            bArr = zzu();
        }
        if (bArr.length < 239) {
            byte[] bArr2 = new byte[(239 - bArr.length)];
            new SecureRandom().nextBytes(bArr2);
            array = ByteBuffer.allocate(240).put((byte) bArr.length).put(bArr).put(bArr2).array();
        } else {
            array = ByteBuffer.allocate(240).put((byte) bArr.length).put(bArr).array();
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(array);
        byte[] array2 = ByteBuffer.allocate(256).put(instance.digest()).put(array).array();
        byte[] bArr3 = new byte[256];
        new zzf().zza(array2, bArr3);
        if (str != null && str.length() > 0) {
            zza(str, bArr3);
        }
        return this.zzlm.zza(bArr3, true);
    }

    public void zza(int i, int i2, int i3) {
        if (this.zzlk != null) {
            this.zzlk.recycle();
        }
        this.zzlk = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * this.zzll.density, ((float) i2) * this.zzll.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void zza(int i, long j) throws IOException {
        this.zzln.zzb(i, j);
    }

    /* access modifiers changed from: protected */
    public void zza(int i, String str) throws IOException {
        this.zzln.zzb(i, str);
    }

    public void zza(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.zzlk != null) {
                this.zzlk.recycle();
            }
            this.zzlk = MotionEvent.obtain(motionEvent);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(String str, byte[] bArr) throws UnsupportedEncodingException {
        if (str.length() > 32) {
            str = str.substring(0, 32);
        }
        new zznp(str.getBytes("UTF-8")).zzu(bArr);
    }

    /* access modifiers changed from: protected */
    public String zzb(String str) {
        if (str == null || !str.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
            return str;
        }
        UUID fromString = UUID.fromString(str);
        byte[] bArr = new byte[16];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putLong(fromString.getMostSignificantBits());
        wrap.putLong(fromString.getLeastSignificantBits());
        return this.zzlm.zza(bArr, true);
    }

    /* access modifiers changed from: protected */
    public abstract void zzb(Context context);

    /* access modifiers changed from: protected */
    public abstract void zzc(Context context);
}
