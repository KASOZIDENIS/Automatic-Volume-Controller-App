package android.support.v7.internal.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

class TintDrawableWrapper extends DrawableWrapper {
    private int mCurrentColor;
    private final PorterDuff.Mode mTintMode;
    private final ColorStateList mTintStateList;

    public TintDrawableWrapper(Drawable drawable, ColorStateList tintStateList) {
        this(drawable, tintStateList, TintManager.DEFAULT_MODE);
    }

    public TintDrawableWrapper(Drawable drawable, ColorStateList tintStateList, PorterDuff.Mode tintMode) {
        super(drawable);
        this.mTintStateList = tintStateList;
        this.mTintMode = tintMode;
    }

    public boolean isStateful() {
        return (this.mTintStateList != null && this.mTintStateList.isStateful()) || super.isStateful();
    }

    public boolean setState(int[] stateSet) {
        return updateTint(stateSet) || super.setState(stateSet);
    }

    private boolean updateTint(int[] state) {
        int color;
        if (this.mTintStateList == null || (color = this.mTintStateList.getColorForState(state, this.mCurrentColor)) == this.mCurrentColor) {
            return false;
        }
        if (color != 0) {
            setColorFilter(color, this.mTintMode);
        } else {
            clearColorFilter();
        }
        this.mCurrentColor = color;
        return true;
    }
}
