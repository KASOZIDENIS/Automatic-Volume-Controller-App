package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.Path;
import android.net.Uri;
import android.widget.ImageView;

public final class zzhf extends ImageView {
    private Uri zzSJ;
    private int zzSK;
    private int zzSL;
    private zza zzSM;
    private int zzSN;
    private float zzSO;

    public interface zza {
        Path zzi(int i, int i2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.zzSM != null) {
            canvas.clipPath(this.zzSM.zzi(getWidth(), getHeight()));
        }
        super.onDraw(canvas);
        if (this.zzSL != 0) {
            canvas.drawColor(this.zzSL);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth;
        int i;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        switch (this.zzSN) {
            case 1:
                i = getMeasuredHeight();
                measuredWidth = (int) (((float) i) * this.zzSO);
                break;
            case 2:
                measuredWidth = getMeasuredWidth();
                i = (int) (((float) measuredWidth) / this.zzSO);
                break;
            default:
                return;
        }
        setMeasuredDimension(measuredWidth, i);
    }

    public void zzaK(int i) {
        this.zzSK = i;
    }

    public void zzi(Uri uri) {
        this.zzSJ = uri;
    }

    public int zzlC() {
        return this.zzSK;
    }
}
