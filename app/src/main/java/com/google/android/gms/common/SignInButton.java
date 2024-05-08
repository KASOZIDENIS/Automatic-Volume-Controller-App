package com.google.android.gms.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.common.internal.zzy;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.dynamic.zzg;

public final class SignInButton extends FrameLayout implements View.OnClickListener {
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private View zzPb;
    private View.OnClickListener zzPc;

    public SignInButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public SignInButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignInButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.zzPc = null;
        setStyle(0, 0);
    }

    private void zzS(Context context) {
        if (this.zzPb != null) {
            removeView(this.zzPb);
        }
        try {
            this.zzPb = zzy.zzb(context, this.mSize, this.mColor);
        } catch (zzg.zza e) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            this.zzPb = zza(context, this.mSize, this.mColor);
        }
        addView(this.zzPb);
        this.zzPb.setEnabled(isEnabled());
        this.zzPb.setOnClickListener(this);
    }

    private static Button zza(Context context, int i, int i2) {
        zzz zzz = new zzz(context);
        zzz.zza(context.getResources(), i, i2);
        return zzz;
    }

    public void onClick(View view) {
        if (this.zzPc != null && view == this.zzPb) {
            this.zzPc.onClick(this);
        }
    }

    public void setColorScheme(int colorScheme) {
        setStyle(this.mSize, colorScheme);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.zzPb.setEnabled(enabled);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.zzPc = listener;
        if (this.zzPb != null) {
            this.zzPb.setOnClickListener(this);
        }
    }

    public void setSize(int buttonSize) {
        setStyle(buttonSize, this.mColor);
    }

    public void setStyle(int buttonSize, int colorScheme) {
        zzv.zza(buttonSize >= 0 && buttonSize < 3, "Unknown button size %d", Integer.valueOf(buttonSize));
        zzv.zza(colorScheme >= 0 && colorScheme < 2, "Unknown color scheme %s", Integer.valueOf(colorScheme));
        this.mSize = buttonSize;
        this.mColor = colorScheme;
        zzS(getContext());
    }
}
