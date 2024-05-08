package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;

public final class zzhd extends Drawable implements Drawable.Callback {
    private int mFrom;
    private long zzGX;
    private Drawable zzSA;
    private boolean zzSB;
    private boolean zzSC;
    private boolean zzSD;
    private int zzSE;
    private boolean zzSl;
    private int zzSs;
    private int zzSt;
    private int zzSu;
    private int zzSv;
    private int zzSw;
    private boolean zzSx;
    private zzb zzSy;
    private Drawable zzSz;

    private static final class zza extends Drawable {
        /* access modifiers changed from: private */
        public static final zza zzSF = new zza();
        private static final C0016zza zzSG = new C0016zza();

        /* renamed from: com.google.android.gms.internal.zzhd$zza$zza  reason: collision with other inner class name */
        private static final class C0016zza extends Drawable.ConstantState {
            private C0016zza() {
            }

            public int getChangingConfigurations() {
                return 0;
            }

            public Drawable newDrawable() {
                return zza.zzSF;
            }
        }

        private zza() {
        }

        public void draw(Canvas canvas) {
        }

        public Drawable.ConstantState getConstantState() {
            return zzSG;
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int alpha) {
        }

        public void setColorFilter(ColorFilter cf) {
        }
    }

    static final class zzb extends Drawable.ConstantState {
        int zzSH;
        int zzSI;

        zzb(zzb zzb) {
            if (zzb != null) {
                this.zzSH = zzb.zzSH;
                this.zzSI = zzb.zzSI;
            }
        }

        public int getChangingConfigurations() {
            return this.zzSH;
        }

        public Drawable newDrawable() {
            return new zzhd(this);
        }
    }

    public zzhd(Drawable drawable, Drawable drawable2) {
        this((zzb) null);
        drawable = drawable == null ? zza.zzSF : drawable;
        this.zzSz = drawable;
        drawable.setCallback(this);
        this.zzSy.zzSI |= drawable.getChangingConfigurations();
        drawable2 = drawable2 == null ? zza.zzSF : drawable2;
        this.zzSA = drawable2;
        drawable2.setCallback(this);
        this.zzSy.zzSI |= drawable2.getChangingConfigurations();
    }

    zzhd(zzb zzb2) {
        this.zzSs = 0;
        this.zzSu = MotionEventCompat.ACTION_MASK;
        this.zzSw = 0;
        this.zzSl = true;
        this.zzSy = new zzb(zzb2);
    }

    public boolean canConstantState() {
        if (!this.zzSB) {
            this.zzSC = (this.zzSz.getConstantState() == null || this.zzSA.getConstantState() == null) ? false : true;
            this.zzSB = true;
        }
        return this.zzSC;
    }

    public void draw(Canvas canvas) {
        boolean z = true;
        boolean z2 = false;
        switch (this.zzSs) {
            case 1:
                this.zzGX = SystemClock.uptimeMillis();
                this.zzSs = 2;
                break;
            case 2:
                if (this.zzGX >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zzGX)) / ((float) this.zzSv);
                    if (uptimeMillis < 1.0f) {
                        z = false;
                    }
                    if (z) {
                        this.zzSs = 0;
                    }
                    this.zzSw = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) (this.zzSt - this.mFrom))) + ((float) this.mFrom));
                    break;
                }
                break;
        }
        z2 = z;
        int i = this.zzSw;
        boolean z3 = this.zzSl;
        Drawable drawable = this.zzSz;
        Drawable drawable2 = this.zzSA;
        if (z2) {
            if (!z3 || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.zzSu) {
                drawable2.setAlpha(this.zzSu);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.zzSu - i);
        }
        drawable.draw(canvas);
        if (z3) {
            drawable.setAlpha(this.zzSu);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zzSu);
        }
        invalidateSelf();
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zzSy.zzSH | this.zzSy.zzSI;
    }

    public Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zzSy.zzSH = getChangingConfigurations();
        return this.zzSy;
    }

    public int getIntrinsicHeight() {
        return Math.max(this.zzSz.getIntrinsicHeight(), this.zzSA.getIntrinsicHeight());
    }

    public int getIntrinsicWidth() {
        return Math.max(this.zzSz.getIntrinsicWidth(), this.zzSA.getIntrinsicWidth());
    }

    public int getOpacity() {
        if (!this.zzSD) {
            this.zzSE = Drawable.resolveOpacity(this.zzSz.getOpacity(), this.zzSA.getOpacity());
            this.zzSD = true;
        }
        return this.zzSE;
    }

    public void invalidateDrawable(Drawable who) {
        Drawable.Callback callback;
        if (zzic.zzne() && (callback = getCallback()) != null) {
            callback.invalidateDrawable(this);
        }
    }

    public Drawable mutate() {
        if (!this.zzSx && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zzSz.mutate();
            this.zzSA.mutate();
            this.zzSx = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        this.zzSz.setBounds(bounds);
        this.zzSA.setBounds(bounds);
    }

    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        Drawable.Callback callback;
        if (zzic.zzne() && (callback = getCallback()) != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    public void setAlpha(int alpha) {
        if (this.zzSw == this.zzSu) {
            this.zzSw = alpha;
        }
        this.zzSu = alpha;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter cf) {
        this.zzSz.setColorFilter(cf);
        this.zzSA.setColorFilter(cf);
    }

    public void startTransition(int durationMillis) {
        this.mFrom = 0;
        this.zzSt = this.zzSu;
        this.zzSw = 0;
        this.zzSv = durationMillis;
        this.zzSs = 1;
        invalidateSelf();
    }

    public void unscheduleDrawable(Drawable who, Runnable what) {
        Drawable.Callback callback;
        if (zzic.zzne() && (callback = getCallback()) != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    public Drawable zzlA() {
        return this.zzSA;
    }
}
