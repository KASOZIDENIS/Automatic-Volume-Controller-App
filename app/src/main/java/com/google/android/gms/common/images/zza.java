package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.internal.zzhd;
import com.google.android.gms.internal.zzhe;
import com.google.android.gms.internal.zzhf;
import com.google.android.gms.internal.zzhg;
import java.lang.ref.WeakReference;

public abstract class zza {
    final C0002zza zzSg;
    protected int zzSh = 0;
    protected int zzSi = 0;
    protected boolean zzSj = false;
    protected ImageManager.OnImageLoadedListener zzSk;
    private boolean zzSl = true;
    private boolean zzSm = false;
    private boolean zzSn = true;
    protected int zzSo;

    /* renamed from: com.google.android.gms.common.images.zza$zza  reason: collision with other inner class name */
    static final class C0002zza {
        public final Uri uri;

        public C0002zza(Uri uri2) {
            this.uri = uri2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0002zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return zzu.equal(((C0002zza) obj).uri, this.uri);
        }

        public int hashCode() {
            return zzu.hashCode(this.uri);
        }
    }

    public static final class zzb extends zza {
        private WeakReference<ImageView> zzSp;

        public zzb(ImageView imageView, int i) {
            super((Uri) null, i);
            com.google.android.gms.common.internal.zzb.zzn(imageView);
            this.zzSp = new WeakReference<>(imageView);
        }

        public zzb(ImageView imageView, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzb.zzn(imageView);
            this.zzSp = new WeakReference<>(imageView);
        }

        private void zza(ImageView imageView, Drawable drawable, boolean z, boolean z2, boolean z3) {
            boolean z4 = !z2 && !z3;
            if (z4 && (imageView instanceof zzhf)) {
                int zzlC = ((zzhf) imageView).zzlC();
                if (this.zzSi != 0 && zzlC == this.zzSi) {
                    return;
                }
            }
            boolean zzb = zzb(z, z2);
            Drawable newDrawable = (!this.zzSj || drawable == null) ? drawable : drawable.getConstantState().newDrawable();
            if (zzb) {
                newDrawable = zza(imageView.getDrawable(), newDrawable);
            }
            imageView.setImageDrawable(newDrawable);
            if (imageView instanceof zzhf) {
                zzhf zzhf = (zzhf) imageView;
                zzhf.zzi(z3 ? this.zzSg.uri : null);
                zzhf.zzaK(z4 ? this.zzSi : 0);
            }
            if (zzb) {
                ((zzhd) newDrawable).startTransition(250);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = (ImageView) this.zzSp.get();
            ImageView imageView2 = (ImageView) ((zzb) obj).zzSp.get();
            return (imageView2 == null || imageView == null || !zzu.equal(imageView2, imageView)) ? false : true;
        }

        public int hashCode() {
            return 0;
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageView imageView = (ImageView) this.zzSp.get();
            if (imageView != null) {
                zza(imageView, drawable, z, z2, z3);
            }
        }
    }

    public static final class zzc extends zza {
        private WeakReference<ImageManager.OnImageLoadedListener> zzSq;

        public zzc(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzb.zzn(onImageLoadedListener);
            this.zzSq = new WeakReference<>(onImageLoadedListener);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzc)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzc zzc = (zzc) obj;
            ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zzSq.get();
            ImageManager.OnImageLoadedListener onImageLoadedListener2 = (ImageManager.OnImageLoadedListener) zzc.zzSq.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && zzu.equal(onImageLoadedListener2, onImageLoadedListener) && zzu.equal(zzc.zzSg, this.zzSg);
        }

        public int hashCode() {
            return zzu.hashCode(this.zzSg);
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageManager.OnImageLoadedListener onImageLoadedListener;
            if (!z2 && (onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zzSq.get()) != null) {
                onImageLoadedListener.onImageLoaded(this.zzSg.uri, drawable, z3);
            }
        }
    }

    public zza(Uri uri, int i) {
        this.zzSg = new C0002zza(uri);
        this.zzSi = i;
    }

    private Drawable zza(Context context, zzhg zzhg, int i) {
        Resources resources = context.getResources();
        if (this.zzSo <= 0) {
            return resources.getDrawable(i);
        }
        zzhg.zza zza = new zzhg.zza(i, this.zzSo);
        Drawable drawable = (Drawable) zzhg.get(zza);
        if (drawable != null) {
            return drawable;
        }
        Drawable drawable2 = resources.getDrawable(i);
        if ((this.zzSo & 1) != 0) {
            drawable2 = zza(resources, drawable2);
        }
        zzhg.put(zza, drawable2);
        return drawable2;
    }

    /* access modifiers changed from: protected */
    public Drawable zza(Resources resources, Drawable drawable) {
        return zzhe.zza(resources, drawable);
    }

    /* access modifiers changed from: protected */
    public zzhd zza(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            drawable = null;
        } else if (drawable instanceof zzhd) {
            drawable = ((zzhd) drawable).zzlA();
        }
        return new zzhd(drawable, drawable2);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, Bitmap bitmap, boolean z) {
        com.google.android.gms.common.internal.zzb.zzn(bitmap);
        if ((this.zzSo & 1) != 0) {
            bitmap = zzhe.zza(bitmap);
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        if (this.zzSk != null) {
            this.zzSk.onImageLoaded(this.zzSg.uri, bitmapDrawable, true);
        }
        zza(bitmapDrawable, z, false, true);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzhg zzhg) {
        if (this.zzSn) {
            Drawable drawable = null;
            if (this.zzSh != 0) {
                drawable = zza(context, zzhg, this.zzSh);
            }
            zza(drawable, false, true, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzhg zzhg, boolean z) {
        Drawable drawable = null;
        if (this.zzSi != 0) {
            drawable = zza(context, zzhg, this.zzSi);
        }
        if (this.zzSk != null) {
            this.zzSk.onImageLoaded(this.zzSg.uri, drawable, false);
        }
        zza(drawable, z, false, false);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Drawable drawable, boolean z, boolean z2, boolean z3);

    public void zzaI(int i) {
        this.zzSi = i;
    }

    /* access modifiers changed from: protected */
    public boolean zzb(boolean z, boolean z2) {
        return this.zzSl && !z2 && (!z || this.zzSm);
    }
}
