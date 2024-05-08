package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.images.zza;
import com.google.android.gms.internal.zzhg;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    /* access modifiers changed from: private */
    public static final Object zzRR = new Object();
    /* access modifiers changed from: private */
    public static HashSet<Uri> zzRS = new HashSet<>();
    private static ImageManager zzRT;
    private static ImageManager zzRU;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final ExecutorService zzRV = Executors.newFixedThreadPool(4);
    /* access modifiers changed from: private */
    public final zzb zzRW;
    /* access modifiers changed from: private */
    public final zzhg zzRX;
    /* access modifiers changed from: private */
    public final Map<zza, ImageReceiver> zzRY;
    /* access modifiers changed from: private */
    public final Map<Uri, ImageReceiver> zzRZ;
    /* access modifiers changed from: private */
    public final Map<Uri, Long> zzSa;

    private final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        /* access modifiers changed from: private */
        public final ArrayList<zza> zzSb = new ArrayList<>();

        ImageReceiver(Uri uri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public void onReceiveResult(int resultCode, Bundle resultData) {
            ImageManager.this.zzRV.execute(new zzc(this.mUri, (ParcelFileDescriptor) resultData.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        public void zzb(zza zza) {
            com.google.android.gms.common.internal.zzb.zzbI("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zzSb.add(zza);
        }

        public void zzc(zza zza) {
            com.google.android.gms.common.internal.zzb.zzbI("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zzSb.remove(zza);
        }

        public void zzlz() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    private static final class zza {
        static int zza(ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }

    private static final class zzb extends zzhp<zza.C0002zza, Bitmap> {
        public zzb(Context context) {
            super(zzT(context));
        }

        private static int zzT(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            return (int) (((float) (((!((context.getApplicationInfo().flags & AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START) != 0) || !zzic.zzne()) ? activityManager.getMemoryClass() : zza.zza(activityManager)) * AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START)) * 0.33f);
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public int sizeOf(zza.C0002zza zza, Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public void entryRemoved(boolean z, zza.C0002zza zza, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zza, bitmap, bitmap2);
        }
    }

    private final class zzc implements Runnable {
        private final Uri mUri;
        private final ParcelFileDescriptor zzSd;

        public zzc(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.zzSd = parcelFileDescriptor;
        }

        public void run() {
            com.google.android.gms.common.internal.zzb.zzbJ("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.zzSd != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.zzSd.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    Log.e("ImageManager", "OOM while loading bitmap for uri: " + this.mUri, e);
                    z = true;
                }
                try {
                    this.zzSd.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(new zzf(this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                Log.w("ImageManager", "Latch interrupted while posting " + this.mUri);
            }
        }
    }

    private final class zzd implements Runnable {
        private final zza zzSe;

        public zzd(zza zza) {
            this.zzSe = zza;
        }

        public void run() {
            com.google.android.gms.common.internal.zzb.zzbI("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zzRY.get(this.zzSe);
            if (imageReceiver != null) {
                ImageManager.this.zzRY.remove(this.zzSe);
                imageReceiver.zzc(this.zzSe);
            }
            zza.C0002zza zza = this.zzSe.zzSg;
            if (zza.uri == null) {
                this.zzSe.zza(ImageManager.this.mContext, ImageManager.this.zzRX, true);
                return;
            }
            Bitmap zza2 = ImageManager.this.zza(zza);
            if (zza2 != null) {
                this.zzSe.zza(ImageManager.this.mContext, zza2, true);
                return;
            }
            Long l = (Long) ImageManager.this.zzSa.get(zza.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zzSe.zza(ImageManager.this.mContext, ImageManager.this.zzRX, true);
                    return;
                }
                ImageManager.this.zzSa.remove(zza.uri);
            }
            this.zzSe.zza(ImageManager.this.mContext, ImageManager.this.zzRX);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.zzRZ.get(zza.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(zza.uri);
                ImageManager.this.zzRZ.put(zza.uri, imageReceiver2);
            }
            imageReceiver2.zzb(this.zzSe);
            if (!(this.zzSe instanceof zza.zzc)) {
                ImageManager.this.zzRY.put(this.zzSe, imageReceiver2);
            }
            synchronized (ImageManager.zzRR) {
                if (!ImageManager.zzRS.contains(zza.uri)) {
                    ImageManager.zzRS.add(zza.uri);
                    imageReceiver2.zzlz();
                }
            }
        }
    }

    private static final class zze implements ComponentCallbacks2 {
        private final zzb zzRW;

        public zze(zzb zzb) {
            this.zzRW = zzb;
        }

        public void onConfigurationChanged(Configuration newConfig) {
        }

        public void onLowMemory() {
            this.zzRW.evictAll();
        }

        public void onTrimMemory(int level) {
            if (level >= 60) {
                this.zzRW.evictAll();
            } else if (level >= 20) {
                this.zzRW.trimToSize(this.zzRW.size() / 2);
            }
        }
    }

    private final class zzf implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private boolean zzSf;
        private final CountDownLatch zzns;

        public zzf(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zzSf = z;
            this.zzns = countDownLatch;
        }

        private void zza(ImageReceiver imageReceiver, boolean z) {
            ArrayList zza = imageReceiver.zzSb;
            int size = zza.size();
            for (int i = 0; i < size; i++) {
                zza zza2 = (zza) zza.get(i);
                if (z) {
                    zza2.zza(ImageManager.this.mContext, this.mBitmap, false);
                } else {
                    ImageManager.this.zzSa.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    zza2.zza(ImageManager.this.mContext, ImageManager.this.zzRX, false);
                }
                if (!(zza2 instanceof zza.zzc)) {
                    ImageManager.this.zzRY.remove(zza2);
                }
            }
        }

        public void run() {
            com.google.android.gms.common.internal.zzb.zzbI("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.zzRW != null) {
                if (this.zzSf) {
                    ImageManager.this.zzRW.evictAll();
                    System.gc();
                    this.zzSf = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                } else if (z) {
                    ImageManager.this.zzRW.put(new zza.C0002zza(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zzRZ.remove(this.mUri);
            if (imageReceiver != null) {
                zza(imageReceiver, z);
            }
            this.zzns.countDown();
            synchronized (ImageManager.zzRR) {
                ImageManager.zzRS.remove(this.mUri);
            }
        }
    }

    private ImageManager(Context context, boolean withMemoryCache) {
        this.mContext = context.getApplicationContext();
        if (withMemoryCache) {
            this.zzRW = new zzb(this.mContext);
            if (zzic.zznh()) {
                zzlw();
            }
        } else {
            this.zzRW = null;
        }
        this.zzRX = new zzhg();
        this.zzRY = new HashMap();
        this.zzRZ = new HashMap();
        this.zzSa = new HashMap();
    }

    public static ImageManager create(Context context) {
        return zzb(context, false);
    }

    /* access modifiers changed from: private */
    public Bitmap zza(zza.C0002zza zza2) {
        if (this.zzRW == null) {
            return null;
        }
        return (Bitmap) this.zzRW.get(zza2);
    }

    public static ImageManager zzb(Context context, boolean z) {
        if (z) {
            if (zzRU == null) {
                zzRU = new ImageManager(context, true);
            }
            return zzRU;
        }
        if (zzRT == null) {
            zzRT = new ImageManager(context, false);
        }
        return zzRT;
    }

    private void zzlw() {
        this.mContext.registerComponentCallbacks(new zze(this.zzRW));
    }

    public void loadImage(ImageView imageView, int resId) {
        zza((zza) new zza.zzb(imageView, resId));
    }

    public void loadImage(ImageView imageView, Uri uri) {
        zza((zza) new zza.zzb(imageView, uri));
    }

    public void loadImage(ImageView imageView, Uri uri, int defaultResId) {
        zza.zzb zzb2 = new zza.zzb(imageView, uri);
        zzb2.zzaI(defaultResId);
        zza((zza) zzb2);
    }

    public void loadImage(OnImageLoadedListener listener, Uri uri) {
        zza((zza) new zza.zzc(listener, uri));
    }

    public void loadImage(OnImageLoadedListener listener, Uri uri, int defaultResId) {
        zza.zzc zzc2 = new zza.zzc(listener, uri);
        zzc2.zzaI(defaultResId);
        zza((zza) zzc2);
    }

    public void zza(zza zza2) {
        com.google.android.gms.common.internal.zzb.zzbI("ImageManager.loadImage() must be called in the main thread");
        new zzd(zza2).run();
    }
}
