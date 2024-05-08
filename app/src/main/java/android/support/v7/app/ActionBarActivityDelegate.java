package android.support.v7.app;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.appcompat.R;
import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

abstract class ActionBarActivityDelegate {
    static final String METADATA_UI_OPTIONS = "android.support.UI_OPTIONS";
    private static final String TAG = "ActionBarActivityDelegate";
    private ActionBar mActionBar;
    final ActionBarActivity mActivity;
    final WindowCallback mDefaultWindowCallback = new WindowCallback() {
        public boolean onMenuItemSelected(int featureId, MenuItem menuItem) {
            return ActionBarActivityDelegate.this.mActivity.onMenuItemSelected(featureId, menuItem);
        }

        public boolean onCreatePanelMenu(int featureId, Menu menu) {
            return ActionBarActivityDelegate.this.mActivity.superOnCreatePanelMenu(featureId, menu);
        }

        public boolean onPreparePanel(int featureId, View menuView, Menu menu) {
            return ActionBarActivityDelegate.this.mActivity.superOnPreparePanel(featureId, menuView, menu);
        }

        public void onPanelClosed(int featureId, Menu menu) {
            ActionBarActivityDelegate.this.mActivity.onPanelClosed(featureId, menu);
        }

        public boolean onMenuOpened(int featureId, Menu menu) {
            return ActionBarActivityDelegate.this.mActivity.onMenuOpened(featureId, menu);
        }

        public ActionMode startActionMode(ActionMode.Callback callback) {
            return ActionBarActivityDelegate.this.startSupportActionModeFromWindow(callback);
        }

        public View onCreatePanelView(int featureId) {
            return ActionBarActivityDelegate.this.mActivity.onCreatePanelView(featureId);
        }
    };
    boolean mHasActionBar;
    private boolean mIsDestroyed;
    boolean mIsFloating;
    private MenuInflater mMenuInflater;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private WindowCallback mWindowCallback;

    /* access modifiers changed from: package-private */
    public abstract void addContentView(View view, ViewGroup.LayoutParams layoutParams);

    /* access modifiers changed from: package-private */
    public abstract ActionBar createSupportActionBar();

    /* access modifiers changed from: package-private */
    public abstract View createView(String str, @NonNull Context context, @NonNull AttributeSet attributeSet);

    /* access modifiers changed from: package-private */
    public abstract boolean dispatchKeyEvent(KeyEvent keyEvent);

    /* access modifiers changed from: package-private */
    public abstract int getHomeAsUpIndicatorAttrId();

    /* access modifiers changed from: package-private */
    public abstract boolean onBackPressed();

    /* access modifiers changed from: package-private */
    public abstract void onConfigurationChanged(Configuration configuration);

    /* access modifiers changed from: package-private */
    public abstract void onContentChanged();

    /* access modifiers changed from: package-private */
    public abstract boolean onCreatePanelMenu(int i, Menu menu);

    /* access modifiers changed from: package-private */
    public abstract boolean onKeyShortcut(int i, KeyEvent keyEvent);

    /* access modifiers changed from: package-private */
    public abstract boolean onMenuOpened(int i, Menu menu);

    /* access modifiers changed from: package-private */
    public abstract void onPanelClosed(int i, Menu menu);

    /* access modifiers changed from: package-private */
    public abstract void onPostCreate(Bundle bundle);

    /* access modifiers changed from: package-private */
    public abstract void onPostResume();

    /* access modifiers changed from: package-private */
    public abstract boolean onPreparePanel(int i, View view, Menu menu);

    /* access modifiers changed from: package-private */
    public abstract void onStop();

    /* access modifiers changed from: package-private */
    public abstract void onTitleChanged(CharSequence charSequence);

    /* access modifiers changed from: package-private */
    public abstract void setContentView(int i);

    /* access modifiers changed from: package-private */
    public abstract void setContentView(View view);

    /* access modifiers changed from: package-private */
    public abstract void setContentView(View view, ViewGroup.LayoutParams layoutParams);

    /* access modifiers changed from: package-private */
    public abstract void setSupportActionBar(Toolbar toolbar);

    /* access modifiers changed from: package-private */
    public abstract void setSupportProgress(int i);

    /* access modifiers changed from: package-private */
    public abstract void setSupportProgressBarIndeterminate(boolean z);

    /* access modifiers changed from: package-private */
    public abstract void setSupportProgressBarIndeterminateVisibility(boolean z);

    /* access modifiers changed from: package-private */
    public abstract void setSupportProgressBarVisibility(boolean z);

    /* access modifiers changed from: package-private */
    public abstract ActionMode startSupportActionMode(ActionMode.Callback callback);

    /* access modifiers changed from: package-private */
    public abstract ActionMode startSupportActionModeFromWindow(ActionMode.Callback callback);

    /* access modifiers changed from: package-private */
    public abstract void supportInvalidateOptionsMenu();

    /* access modifiers changed from: package-private */
    public abstract boolean supportRequestWindowFeature(int i);

    static ActionBarActivityDelegate createDelegate(ActionBarActivity activity) {
        if (Build.VERSION.SDK_INT >= 11) {
            return new ActionBarActivityDelegateHC(activity);
        }
        return new ActionBarActivityDelegateBase(activity);
    }

    ActionBarActivityDelegate(ActionBarActivity activity) {
        this.mActivity = activity;
        this.mWindowCallback = this.mDefaultWindowCallback;
    }

    /* access modifiers changed from: package-private */
    public final ActionBar getSupportActionBar() {
        if (this.mHasActionBar && this.mActionBar == null) {
            this.mActionBar = createSupportActionBar();
        }
        return this.mActionBar;
    }

    /* access modifiers changed from: package-private */
    public final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    /* access modifiers changed from: protected */
    public final void setSupportActionBar(ActionBar actionBar) {
        this.mActionBar = actionBar;
    }

    /* access modifiers changed from: package-private */
    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new SupportMenuInflater(getActionBarThemedContext());
        }
        return this.mMenuInflater;
    }

    /* access modifiers changed from: package-private */
    public void onCreate(Bundle savedInstanceState) {
        TypedArray a = this.mActivity.obtainStyledAttributes(R.styleable.Theme);
        if (!a.hasValue(R.styleable.Theme_windowActionBar)) {
            a.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (a.getBoolean(R.styleable.Theme_windowActionBar, false)) {
            this.mHasActionBar = true;
        }
        if (a.getBoolean(R.styleable.Theme_windowActionBarOverlay, false)) {
            this.mOverlayActionBar = true;
        }
        if (a.getBoolean(R.styleable.Theme_windowActionModeOverlay, false)) {
            this.mOverlayActionMode = true;
        }
        this.mIsFloating = a.getBoolean(R.styleable.Theme_android_windowIsFloating, false);
        a.recycle();
    }

    /* access modifiers changed from: package-private */
    public boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (Build.VERSION.SDK_INT < 16) {
            return this.mActivity.onPrepareOptionsMenu(menu);
        }
        return this.mActivity.superOnPrepareOptionsPanel(view, menu);
    }

    /* access modifiers changed from: package-private */
    public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl();
    }

    /* access modifiers changed from: package-private */
    public final ActionBarDrawerToggle.Delegate getV7DrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl();
    }

    /* access modifiers changed from: package-private */
    public final String getUiOptionsFromMetadata() {
        try {
            ActivityInfo info = this.mActivity.getPackageManager().getActivityInfo(this.mActivity.getComponentName(), 128);
            if (info.metaData != null) {
                return info.metaData.getString(METADATA_UI_OPTIONS);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "getUiOptionsFromMetadata: Activity '" + this.mActivity.getClass().getSimpleName() + "' not in manifest");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final Context getActionBarThemedContext() {
        Context context = null;
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            context = ab.getThemedContext();
        }
        if (context == null) {
            return this.mActivity;
        }
        return context;
    }

    private class ActionBarDrawableToggleImpl implements ActionBarDrawerToggle.Delegate, ActionBarDrawerToggle.Delegate {
        private ActionBarDrawableToggleImpl() {
        }

        public Drawable getThemeUpIndicator() {
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), (AttributeSet) null, new int[]{ActionBarActivityDelegate.this.getHomeAsUpIndicatorAttrId()});
            Drawable result = a.getDrawable(0);
            a.recycle();
            return result;
        }

        public Context getActionBarThemedContext() {
            return ActionBarActivityDelegate.this.getActionBarThemedContext();
        }

        public boolean isNavigationVisible() {
            ActionBar ab = ActionBarActivityDelegate.this.getSupportActionBar();
            return (ab == null || (ab.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarUpIndicator(Drawable upDrawable, int contentDescRes) {
            ActionBar ab = ActionBarActivityDelegate.this.getSupportActionBar();
            if (ab != null) {
                ab.setHomeAsUpIndicator(upDrawable);
                ab.setHomeActionContentDescription(contentDescRes);
            }
        }

        public void setActionBarDescription(int contentDescRes) {
            ActionBar ab = ActionBarActivityDelegate.this.getSupportActionBar();
            if (ab != null) {
                ab.setHomeActionContentDescription(contentDescRes);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void setWindowCallback(WindowCallback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback can not be null");
        }
        this.mWindowCallback = callback;
    }

    /* access modifiers changed from: package-private */
    public final WindowCallback getWindowCallback() {
        return this.mWindowCallback;
    }

    /* access modifiers changed from: package-private */
    public final void destroy() {
        this.mIsDestroyed = true;
    }

    /* access modifiers changed from: package-private */
    public final boolean isDestroyed() {
        return this.mIsDestroyed;
    }
}
