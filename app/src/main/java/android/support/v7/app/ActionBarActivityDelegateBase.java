package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.support.v7.internal.view.StandaloneActionMode;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.DecorContentParent;
import android.support.v7.internal.widget.FitWindowsViewGroup;
import android.support.v7.internal.widget.TintAutoCompleteTextView;
import android.support.v7.internal.widget.TintButton;
import android.support.v7.internal.widget.TintCheckBox;
import android.support.v7.internal.widget.TintCheckedTextView;
import android.support.v7.internal.widget.TintEditText;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintMultiAutoCompleteTextView;
import android.support.v7.internal.widget.TintRadioButton;
import android.support.v7.internal.widget.TintRatingBar;
import android.support.v7.internal.widget.TintSpinner;
import android.support.v7.internal.widget.ViewStubCompat;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

class ActionBarActivityDelegateBase extends ActionBarActivityDelegate implements MenuBuilder.Callback {
    private static final String TAG = "ActionBarActivityDelegateBase";
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    private boolean mClosingActionMenu;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    /* access modifiers changed from: private */
    public int mInvalidatePanelMenuFeatures;
    /* access modifiers changed from: private */
    public boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable = new Runnable() {
        public void run() {
            if ((ActionBarActivityDelegateBase.this.mInvalidatePanelMenuFeatures & 1) != 0) {
                ActionBarActivityDelegateBase.this.doInvalidatePanelMenu(0);
            }
            if ((ActionBarActivityDelegateBase.this.mInvalidatePanelMenuFeatures & 256) != 0) {
                ActionBarActivityDelegateBase.this.doInvalidatePanelMenu(8);
            }
            boolean unused = ActionBarActivityDelegateBase.this.mInvalidatePanelMenuPosted = false;
            int unused2 = ActionBarActivityDelegateBase.this.mInvalidatePanelMenuFeatures = 0;
        }
    };
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private CharSequence mTitleToSet;
    private ViewGroup mWindowDecor;

    ActionBarActivityDelegateBase(ActionBarActivity activity) {
        super(activity);
    }

    /* access modifiers changed from: package-private */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mWindowDecor = (ViewGroup) this.mActivity.getWindow().getDecorView();
        if (NavUtils.getParentActivityName(this.mActivity) != null) {
            ActionBar ab = peekSupportActionBar();
            if (ab == null) {
                this.mEnableDefaultActionBarUp = true;
            } else {
                ab.setDefaultDisplayHomeAsUpEnabled(true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onPostCreate(Bundle savedInstanceState) {
        ensureSubDecor();
    }

    public ActionBar createSupportActionBar() {
        ensureSubDecor();
        ActionBar ab = new WindowDecorActionBar(this.mActivity, this.mOverlayActionBar);
        ab.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
        return ab;
    }

    /* access modifiers changed from: package-private */
    public void setSupportActionBar(Toolbar toolbar) {
        if (getSupportActionBar() instanceof WindowDecorActionBar) {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
        ToolbarActionBar tbab = new ToolbarActionBar(toolbar, this.mActivity.getTitle(), this.mActivity.getWindow(), this.mDefaultWindowCallback);
        setSupportActionBar((ActionBar) tbab);
        setWindowCallback(tbab.getWrappedWindowCallback());
        tbab.invalidateOptionsMenu();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        ActionBar ab;
        if (this.mHasActionBar && this.mSubDecorInstalled && (ab = getSupportActionBar()) != null) {
            ab.onConfigurationChanged(newConfig);
        }
    }

    public void onStop() {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setShowHideAnimationEnabled(false);
        }
    }

    public void onPostResume() {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setShowHideAnimationEnabled(true);
        }
    }

    public void setContentView(View v) {
        ensureSubDecor();
        ViewGroup contentParent = (ViewGroup) this.mActivity.findViewById(16908290);
        contentParent.removeAllViews();
        contentParent.addView(v);
        this.mActivity.onSupportContentChanged();
    }

    public void setContentView(int resId) {
        ensureSubDecor();
        ViewGroup contentParent = (ViewGroup) this.mActivity.findViewById(16908290);
        contentParent.removeAllViews();
        this.mActivity.getLayoutInflater().inflate(resId, contentParent);
        this.mActivity.onSupportContentChanged();
    }

    public void setContentView(View v, ViewGroup.LayoutParams lp) {
        ensureSubDecor();
        ViewGroup contentParent = (ViewGroup) this.mActivity.findViewById(16908290);
        contentParent.removeAllViews();
        contentParent.addView(v, lp);
        this.mActivity.onSupportContentChanged();
    }

    public void addContentView(View v, ViewGroup.LayoutParams lp) {
        ensureSubDecor();
        ((ViewGroup) this.mActivity.findViewById(16908290)).addView(v, lp);
        this.mActivity.onSupportContentChanged();
    }

    public void onContentChanged() {
    }

    /* access modifiers changed from: package-private */
    public final void ensureSubDecor() {
        Context themedContext;
        if (!this.mSubDecorInstalled) {
            if (this.mHasActionBar) {
                TypedValue outValue = new TypedValue();
                this.mActivity.getTheme().resolveAttribute(R.attr.actionBarTheme, outValue, true);
                if (outValue.resourceId != 0) {
                    themedContext = new ContextThemeWrapper(this.mActivity, outValue.resourceId);
                } else {
                    themedContext = this.mActivity;
                }
                this.mSubDecor = (ViewGroup) LayoutInflater.from(themedContext).inflate(R.layout.abc_screen_toolbar, (ViewGroup) null);
                this.mDecorContentParent = (DecorContentParent) this.mSubDecor.findViewById(R.id.decor_content_parent);
                this.mDecorContentParent.setWindowCallback(getWindowCallback());
                if (this.mOverlayActionBar) {
                    this.mDecorContentParent.initFeature(9);
                }
                if (this.mFeatureProgress) {
                    this.mDecorContentParent.initFeature(2);
                }
                if (this.mFeatureIndeterminateProgress) {
                    this.mDecorContentParent.initFeature(5);
                }
            } else {
                if (this.mOverlayActionMode) {
                    this.mSubDecor = (ViewGroup) LayoutInflater.from(this.mActivity).inflate(R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null);
                } else {
                    this.mSubDecor = (ViewGroup) LayoutInflater.from(this.mActivity).inflate(R.layout.abc_screen_simple, (ViewGroup) null);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    ViewCompat.setOnApplyWindowInsetsListener(this.mSubDecor, new OnApplyWindowInsetsListener() {
                        public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                            int top = insets.getSystemWindowInsetTop();
                            int newTop = ActionBarActivityDelegateBase.this.updateStatusGuard(top);
                            if (top != newTop) {
                                return insets.replaceSystemWindowInsets(insets.getSystemWindowInsetLeft(), newTop, insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
                            }
                            return insets;
                        }
                    });
                } else {
                    ((FitWindowsViewGroup) this.mSubDecor).setOnFitSystemWindowsListener(new FitWindowsViewGroup.OnFitSystemWindowsListener() {
                        public void onFitSystemWindows(Rect insets) {
                            insets.top = ActionBarActivityDelegateBase.this.updateStatusGuard(insets.top);
                        }
                    });
                }
            }
            ViewUtils.makeOptionalFitsSystemWindows(this.mSubDecor);
            ViewGroup decorContent = (ViewGroup) this.mActivity.findViewById(16908290);
            ViewGroup abcContent = (ViewGroup) this.mSubDecor.findViewById(R.id.action_bar_activity_content);
            while (decorContent.getChildCount() > 0) {
                View child = decorContent.getChildAt(0);
                decorContent.removeViewAt(0);
                abcContent.addView(child);
            }
            this.mActivity.superSetContentView((View) this.mSubDecor);
            decorContent.setId(-1);
            abcContent.setId(16908290);
            if (decorContent instanceof FrameLayout) {
                ((FrameLayout) decorContent).setForeground((Drawable) null);
            }
            if (!(this.mTitleToSet == null || this.mDecorContentParent == null)) {
                this.mDecorContentParent.setWindowTitle(this.mTitleToSet);
                this.mTitleToSet = null;
            }
            applyFixedSizeWindow();
            onSubDecorInstalled();
            this.mSubDecorInstalled = true;
            PanelFeatureState st = getPanelState(0, false);
            if (isDestroyed()) {
                return;
            }
            if (st == null || st.menu == null) {
                invalidatePanelMenu(8);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onSubDecorInstalled() {
    }

    private void applyFixedSizeWindow() {
        TypedValue tvw;
        TypedValue tvh;
        TypedArray a = this.mActivity.obtainStyledAttributes(R.styleable.Theme);
        TypedValue mFixedWidthMajor = null;
        TypedValue mFixedWidthMinor = null;
        TypedValue mFixedHeightMajor = null;
        TypedValue mFixedHeightMinor = null;
        if (a.hasValue(R.styleable.Theme_windowFixedWidthMajor)) {
            if (0 == 0) {
                mFixedWidthMajor = new TypedValue();
            }
            a.getValue(R.styleable.Theme_windowFixedWidthMajor, mFixedWidthMajor);
        }
        if (a.hasValue(R.styleable.Theme_windowFixedWidthMinor)) {
            if (0 == 0) {
                mFixedWidthMinor = new TypedValue();
            }
            a.getValue(R.styleable.Theme_windowFixedWidthMinor, mFixedWidthMinor);
        }
        if (a.hasValue(R.styleable.Theme_windowFixedHeightMajor)) {
            if (0 == 0) {
                mFixedHeightMajor = new TypedValue();
            }
            a.getValue(R.styleable.Theme_windowFixedHeightMajor, mFixedHeightMajor);
        }
        if (a.hasValue(R.styleable.Theme_windowFixedHeightMinor)) {
            if (0 == 0) {
                mFixedHeightMinor = new TypedValue();
            }
            a.getValue(R.styleable.Theme_windowFixedHeightMinor, mFixedHeightMinor);
        }
        DisplayMetrics metrics = this.mActivity.getResources().getDisplayMetrics();
        boolean isPortrait = metrics.widthPixels < metrics.heightPixels;
        int w = -1;
        int h = -1;
        if (isPortrait) {
            tvw = mFixedWidthMinor;
        } else {
            tvw = mFixedWidthMajor;
        }
        if (!(tvw == null || tvw.type == 0)) {
            if (tvw.type == 5) {
                w = (int) tvw.getDimension(metrics);
            } else if (tvw.type == 6) {
                w = (int) tvw.getFraction((float) metrics.widthPixels, (float) metrics.widthPixels);
            }
        }
        if (isPortrait) {
            tvh = mFixedHeightMajor;
        } else {
            tvh = mFixedHeightMinor;
        }
        if (!(tvh == null || tvh.type == 0)) {
            if (tvh.type == 5) {
                h = (int) tvh.getDimension(metrics);
            } else if (tvh.type == 6) {
                h = (int) tvh.getFraction((float) metrics.heightPixels, (float) metrics.heightPixels);
            }
        }
        if (!(w == -1 && h == -1)) {
            this.mActivity.getWindow().setLayout(w, h);
        }
        a.recycle();
    }

    public boolean supportRequestWindowFeature(int featureId) {
        switch (featureId) {
            case 2:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureProgress = true;
                return true;
            case 5:
                throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureIndeterminateProgress = true;
                return true;
            case 8:
                throwFeatureRequestIfSubDecorInstalled();
                this.mHasActionBar = true;
                return true;
            case 9:
                throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionBar = true;
                return true;
            case 10:
                throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionMode = true;
                return true;
            default:
                return this.mActivity.requestWindowFeature(featureId);
        }
    }

    public void onTitleChanged(CharSequence title) {
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.setWindowTitle(title);
        } else if (getSupportActionBar() != null) {
            getSupportActionBar().setWindowTitle(title);
        } else {
            this.mTitleToSet = title;
        }
    }

    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        if (featureId != 0) {
            return getWindowCallback().onCreatePanelMenu(featureId, menu);
        }
        return false;
    }

    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        if (featureId != 0) {
            return getWindowCallback().onPreparePanel(featureId, view, menu);
        }
        return false;
    }

    public void onPanelClosed(int featureId, Menu menu) {
        if (featureId == 8) {
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.dispatchMenuVisibilityChanged(false);
            }
        } else if (!isDestroyed()) {
            this.mActivity.superOnPanelClosed(featureId, menu);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId != 8) {
            return this.mActivity.superOnMenuOpened(featureId, menu);
        }
        ActionBar ab = getSupportActionBar();
        if (ab == null) {
            return true;
        }
        ab.dispatchMenuVisibilityChanged(true);
        return true;
    }

    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
        PanelFeatureState panel;
        WindowCallback cb = getWindowCallback();
        if (cb == null || isDestroyed() || (panel = findMenuPanel(menu.getRootMenu())) == null) {
            return false;
        }
        return cb.onMenuItemSelected(panel.featureId, item);
    }

    public void onMenuModeChange(MenuBuilder menu) {
        reopenMenu(menu, true);
    }

    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        ActionMode.Callback wrappedCallback = new ActionModeCallbackWrapper(callback);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            this.mActionMode = ab.startActionMode(wrappedCallback);
            if (this.mActionMode != null) {
                this.mActivity.onSupportActionModeStarted(this.mActionMode);
            }
        }
        if (this.mActionMode == null) {
            this.mActionMode = startSupportActionModeFromWindow(wrappedCallback);
        }
        return this.mActionMode;
    }

    public void supportInvalidateOptionsMenu() {
        ActionBar ab = getSupportActionBar();
        if (ab == null || !ab.invalidateOptionsMenu()) {
            invalidatePanelMenu(0);
        }
    }

    /* access modifiers changed from: package-private */
    public ActionMode startSupportActionModeFromWindow(ActionMode.Callback callback) {
        boolean z;
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        ActionMode.Callback wrappedCallback = new ActionModeCallbackWrapper(callback);
        Context context = getActionBarThemedContext();
        if (this.mActionModeView == null) {
            if (this.mIsFloating) {
                this.mActionModeView = new ActionBarContextView(context);
                this.mActionModePopup = new PopupWindow(context, (AttributeSet) null, R.attr.actionModePopupWindowStyle);
                this.mActionModePopup.setContentView(this.mActionModeView);
                this.mActionModePopup.setWidth(-1);
                TypedValue heightValue = new TypedValue();
                this.mActivity.getTheme().resolveAttribute(R.attr.actionBarSize, heightValue, true);
                this.mActionModeView.setContentHeight(TypedValue.complexToDimensionPixelSize(heightValue.data, this.mActivity.getResources().getDisplayMetrics()));
                this.mActionModePopup.setHeight(-2);
                this.mShowActionModePopup = new Runnable() {
                    public void run() {
                        ActionBarActivityDelegateBase.this.mActionModePopup.showAtLocation(ActionBarActivityDelegateBase.this.mActionModeView, 55, 0, 0);
                    }
                };
            } else {
                ViewStubCompat stub = (ViewStubCompat) this.mActivity.findViewById(R.id.action_mode_bar_stub);
                if (stub != null) {
                    stub.setLayoutInflater(LayoutInflater.from(context));
                    this.mActionModeView = (ActionBarContextView) stub.inflate();
                }
            }
        }
        if (this.mActionModeView != null) {
            this.mActionModeView.killMode();
            ActionBarContextView actionBarContextView = this.mActionModeView;
            if (this.mActionModePopup == null) {
                z = true;
            } else {
                z = false;
            }
            ActionMode mode = new StandaloneActionMode(context, actionBarContextView, wrappedCallback, z);
            if (callback.onCreateActionMode(mode, mode.getMenu())) {
                mode.invalidate();
                this.mActionModeView.initForMode(mode);
                this.mActionModeView.setVisibility(0);
                this.mActionMode = mode;
                if (this.mActionModePopup != null) {
                    this.mActivity.getWindow().getDecorView().post(this.mShowActionModePopup);
                }
                this.mActionModeView.sendAccessibilityEvent(32);
                if (this.mActionModeView.getParent() != null) {
                    ViewCompat.requestApplyInsets((View) this.mActionModeView.getParent());
                }
            } else {
                this.mActionMode = null;
            }
        }
        if (!(this.mActionMode == null || this.mActivity == null)) {
            this.mActivity.onSupportActionModeStarted(this.mActionMode);
        }
        return this.mActionMode;
    }

    public boolean onBackPressed() {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
            return true;
        }
        ActionBar ab = getSupportActionBar();
        if (ab == null || !ab.collapseActionView()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setSupportProgressBarVisibility(boolean visible) {
    }

    /* access modifiers changed from: package-private */
    public void setSupportProgressBarIndeterminateVisibility(boolean visible) {
    }

    /* access modifiers changed from: package-private */
    public void setSupportProgressBarIndeterminate(boolean indeterminate) {
    }

    /* access modifiers changed from: package-private */
    public void setSupportProgress(int progress) {
    }

    /* access modifiers changed from: package-private */
    public int getHomeAsUpIndicatorAttrId() {
        return R.attr.homeAsUpIndicator;
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyShortcut(int keyCode, KeyEvent ev) {
        ActionBar ab = getSupportActionBar();
        if (ab != null && ab.onKeyShortcut(keyCode, ev)) {
            return true;
        }
        if (this.mPreparedPanel == null || !performPanelShortcut(this.mPreparedPanel, ev.getKeyCode(), ev, 1)) {
            if (this.mPreparedPanel == null) {
                PanelFeatureState st = getPanelState(0, true);
                preparePanel(st, ev);
                boolean handled = performPanelShortcut(st, ev.getKeyCode(), ev, 1);
                st.isPrepared = false;
                if (handled) {
                    return true;
                }
            }
            return false;
        } else if (this.mPreparedPanel == null) {
            return true;
        } else {
            this.mPreparedPanel.isHandled = true;
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        return event.getAction() == 0 ? onKeyDown(keyCode, event) : onKeyUp(keyCode, event);
    }

    /* access modifiers changed from: protected */
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 4:
                PanelFeatureState st = getPanelState(0, false);
                if (st != null && st.isOpen) {
                    closePanel(st, true);
                    return true;
                }
            case 82:
                onKeyUpPanel(0, event);
                return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 82) {
            onKeyDownPanel(0, event);
            return true;
        } else if (Build.VERSION.SDK_INT < 11) {
            return onKeyShortcut(keyCode, event);
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public View createView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 21) {
            char c = 65535;
            switch (name.hashCode()) {
                case -1946472170:
                    if (name.equals("RatingBar")) {
                        c = 7;
                        break;
                    }
                    break;
                case -1455429095:
                    if (name.equals("CheckedTextView")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1346021293:
                    if (name.equals("MultiAutoCompleteTextView")) {
                        c = 6;
                        break;
                    }
                    break;
                case -339785223:
                    if (name.equals("Spinner")) {
                        c = 1;
                        break;
                    }
                    break;
                case 776382189:
                    if (name.equals("RadioButton")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1413872058:
                    if (name.equals("AutoCompleteTextView")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1601505219:
                    if (name.equals("CheckBox")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1666676343:
                    if (name.equals("EditText")) {
                        c = 0;
                        break;
                    }
                    break;
                case 2001146706:
                    if (name.equals("Button")) {
                        c = 8;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return new TintEditText(context, attrs);
                case 1:
                    return new TintSpinner(context, attrs);
                case 2:
                    return new TintCheckBox(context, attrs);
                case 3:
                    return new TintRadioButton(context, attrs);
                case 4:
                    return new TintCheckedTextView(context, attrs);
                case 5:
                    return new TintAutoCompleteTextView(context, attrs);
                case 6:
                    return new TintMultiAutoCompleteTextView(context, attrs);
                case 7:
                    return new TintRatingBar(context, attrs);
                case 8:
                    return new TintButton(context, attrs);
            }
        }
        return null;
    }

    private void openPanel(PanelFeatureState st, KeyEvent event) {
        ViewGroup.LayoutParams lp;
        if (!st.isOpen && !isDestroyed()) {
            if (st.featureId == 0) {
                Context context = this.mActivity;
                boolean isXLarge = (context.getResources().getConfiguration().screenLayout & 15) == 4;
                boolean isHoneycombApp = context.getApplicationInfo().targetSdkVersion >= 11;
                if (isXLarge && isHoneycombApp) {
                    return;
                }
            }
            WindowCallback cb = getWindowCallback();
            if (cb == null || cb.onMenuOpened(st.featureId, st.menu)) {
                WindowManager wm = (WindowManager) this.mActivity.getSystemService("window");
                if (wm != null && preparePanel(st, event)) {
                    int width = -2;
                    if (st.decorView == null || st.refreshDecorView) {
                        if (st.decorView == null) {
                            if (!initializePanelDecor(st) || st.decorView == null) {
                                return;
                            }
                        } else if (st.refreshDecorView && st.decorView.getChildCount() > 0) {
                            st.decorView.removeAllViews();
                        }
                        if (initializePanelContent(st) && st.hasPanelItems()) {
                            ViewGroup.LayoutParams lp2 = st.shownPanelView.getLayoutParams();
                            if (lp2 == null) {
                                lp2 = new ViewGroup.LayoutParams(-2, -2);
                            }
                            st.decorView.setBackgroundResource(st.background);
                            ViewParent shownPanelParent = st.shownPanelView.getParent();
                            if (shownPanelParent != null && (shownPanelParent instanceof ViewGroup)) {
                                ((ViewGroup) shownPanelParent).removeView(st.shownPanelView);
                            }
                            st.decorView.addView(st.shownPanelView, lp2);
                            if (!st.shownPanelView.hasFocus()) {
                                st.shownPanelView.requestFocus();
                            }
                        } else {
                            return;
                        }
                    } else if (!(st.createdPanelView == null || (lp = st.createdPanelView.getLayoutParams()) == null || lp.width != -1)) {
                        width = -1;
                    }
                    st.isHandled = false;
                    WindowManager.LayoutParams lp3 = new WindowManager.LayoutParams(width, -2, st.x, st.y, 1002, 8519680, -3);
                    lp3.gravity = st.gravity;
                    lp3.windowAnimations = st.windowAnimations;
                    wm.addView(st.decorView, lp3);
                    st.isOpen = true;
                    return;
                }
                return;
            }
            closePanel(st, true);
        }
    }

    private boolean initializePanelDecor(PanelFeatureState st) {
        st.setStyle(getActionBarThemedContext());
        st.decorView = new ListMenuDecorView(st.listPresenterContext);
        st.gravity = 81;
        return true;
    }

    private void reopenMenu(MenuBuilder menu, boolean toggleMenuMode) {
        if (this.mDecorContentParent == null || !this.mDecorContentParent.canShowOverflowMenu() || (ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mActivity)) && !this.mDecorContentParent.isOverflowMenuShowPending())) {
            PanelFeatureState st = getPanelState(0, true);
            st.refreshDecorView = true;
            closePanel(st, false);
            openPanel(st, (KeyEvent) null);
            return;
        }
        WindowCallback cb = getWindowCallback();
        if (this.mDecorContentParent.isOverflowMenuShowing() && toggleMenuMode) {
            this.mDecorContentParent.hideOverflowMenu();
            if (!isDestroyed()) {
                cb.onPanelClosed(8, getPanelState(0, true).menu);
            }
        } else if (cb != null && !isDestroyed()) {
            if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
                this.mWindowDecor.removeCallbacks(this.mInvalidatePanelMenuRunnable);
                this.mInvalidatePanelMenuRunnable.run();
            }
            PanelFeatureState st2 = getPanelState(0, true);
            if (st2.menu != null && !st2.refreshMenuContent && cb.onPreparePanel(0, st2.createdPanelView, st2.menu)) {
                cb.onMenuOpened(8, st2.menu);
                this.mDecorContentParent.showOverflowMenu();
            }
        }
    }

    private boolean initializePanelMenu(PanelFeatureState st) {
        Context context = this.mActivity;
        if ((st.featureId == 0 || st.featureId == 8) && this.mDecorContentParent != null) {
            TypedValue outValue = new TypedValue();
            Resources.Theme baseTheme = context.getTheme();
            baseTheme.resolveAttribute(R.attr.actionBarTheme, outValue, true);
            Resources.Theme widgetTheme = null;
            if (outValue.resourceId != 0) {
                widgetTheme = context.getResources().newTheme();
                widgetTheme.setTo(baseTheme);
                widgetTheme.applyStyle(outValue.resourceId, true);
                widgetTheme.resolveAttribute(R.attr.actionBarWidgetTheme, outValue, true);
            } else {
                baseTheme.resolveAttribute(R.attr.actionBarWidgetTheme, outValue, true);
            }
            if (outValue.resourceId != 0) {
                if (widgetTheme == null) {
                    widgetTheme = context.getResources().newTheme();
                    widgetTheme.setTo(baseTheme);
                }
                widgetTheme.applyStyle(outValue.resourceId, true);
            }
            if (widgetTheme != null) {
                Context context2 = new ContextThemeWrapper(context, 0);
                context2.getTheme().setTo(widgetTheme);
                context = context2;
            }
        }
        MenuBuilder menu = new MenuBuilder(context);
        menu.setCallback(this);
        st.setMenu(menu);
        return true;
    }

    private boolean initializePanelContent(PanelFeatureState st) {
        if (st.createdPanelView != null) {
            st.shownPanelView = st.createdPanelView;
            return true;
        } else if (st.menu == null) {
            return false;
        } else {
            if (this.mPanelMenuPresenterCallback == null) {
                this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
            }
            st.shownPanelView = (View) st.getListMenuView(this.mPanelMenuPresenterCallback);
            if (st.shownPanelView == null) {
                return false;
            }
            return true;
        }
    }

    private boolean preparePanel(PanelFeatureState st, KeyEvent event) {
        boolean isActionBarMenu;
        boolean z;
        if (isDestroyed()) {
            return false;
        }
        if (st.isPrepared) {
            return true;
        }
        if (!(this.mPreparedPanel == null || this.mPreparedPanel == st)) {
            closePanel(this.mPreparedPanel, false);
        }
        WindowCallback cb = getWindowCallback();
        if (cb != null) {
            st.createdPanelView = cb.onCreatePanelView(st.featureId);
        }
        if (st.featureId == 0 || st.featureId == 8) {
            isActionBarMenu = true;
        } else {
            isActionBarMenu = false;
        }
        if (isActionBarMenu && this.mDecorContentParent != null) {
            this.mDecorContentParent.setMenuPrepared();
        }
        if (st.createdPanelView == null) {
            if (st.menu == null || st.refreshMenuContent) {
                if (st.menu == null && (!initializePanelMenu(st) || st.menu == null)) {
                    return false;
                }
                if (isActionBarMenu && this.mDecorContentParent != null) {
                    if (this.mActionMenuPresenterCallback == null) {
                        this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
                    }
                    this.mDecorContentParent.setMenu(st.menu, this.mActionMenuPresenterCallback);
                }
                st.menu.stopDispatchingItemsChanged();
                if (!getWindowCallback().onCreatePanelMenu(st.featureId, st.menu)) {
                    st.setMenu((MenuBuilder) null);
                    if (!isActionBarMenu || this.mDecorContentParent == null) {
                        return false;
                    }
                    this.mDecorContentParent.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                    return false;
                }
                st.refreshMenuContent = false;
            }
            st.menu.stopDispatchingItemsChanged();
            if (st.frozenActionViewState != null) {
                st.menu.restoreActionViewStates(st.frozenActionViewState);
                st.frozenActionViewState = null;
            }
            if (!cb.onPreparePanel(0, st.createdPanelView, st.menu)) {
                if (isActionBarMenu && this.mDecorContentParent != null) {
                    this.mDecorContentParent.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                }
                st.menu.startDispatchingItemsChanged();
                return false;
            }
            if (KeyCharacterMap.load(event != null ? event.getDeviceId() : -1).getKeyboardType() != 1) {
                z = true;
            } else {
                z = false;
            }
            st.qwertyMode = z;
            st.menu.setQwertyMode(st.qwertyMode);
            st.menu.startDispatchingItemsChanged();
        }
        st.isPrepared = true;
        st.isHandled = false;
        this.mPreparedPanel = st;
        return true;
    }

    /* access modifiers changed from: private */
    public void checkCloseActionMenu(MenuBuilder menu) {
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.mDecorContentParent.dismissPopups();
            WindowCallback cb = getWindowCallback();
            if (cb != null && !isDestroyed()) {
                cb.onPanelClosed(8, menu);
            }
            this.mClosingActionMenu = false;
        }
    }

    /* access modifiers changed from: private */
    public void closePanel(int featureId) {
        closePanel(getPanelState(featureId, true), true);
    }

    /* access modifiers changed from: private */
    public void closePanel(PanelFeatureState st, boolean doCallback) {
        if (!doCallback || st.featureId != 0 || this.mDecorContentParent == null || !this.mDecorContentParent.isOverflowMenuShowing()) {
            WindowManager wm = (WindowManager) this.mActivity.getSystemService("window");
            if (wm != null && st.isOpen) {
                if (st.decorView != null) {
                    wm.removeView(st.decorView);
                }
                if (doCallback) {
                    callOnPanelClosed(st.featureId, st, (Menu) null);
                }
            }
            st.isPrepared = false;
            st.isHandled = false;
            st.isOpen = false;
            st.shownPanelView = null;
            st.refreshDecorView = true;
            if (this.mPreparedPanel == st) {
                this.mPreparedPanel = null;
                return;
            }
            return;
        }
        checkCloseActionMenu(st.menu);
    }

    private boolean onKeyDownPanel(int featureId, KeyEvent event) {
        if (event.getRepeatCount() == 0) {
            PanelFeatureState st = getPanelState(featureId, true);
            if (!st.isOpen) {
                return preparePanel(st, event);
            }
        }
        return false;
    }

    private void onKeyUpPanel(int featureId, KeyEvent event) {
        if (this.mActionMode == null) {
            boolean playSoundEffect = false;
            PanelFeatureState st = getPanelState(featureId, true);
            if (featureId != 0 || this.mDecorContentParent == null || !this.mDecorContentParent.canShowOverflowMenu() || ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mActivity))) {
                if (st.isOpen || st.isHandled) {
                    playSoundEffect = st.isOpen;
                    closePanel(st, true);
                } else if (st.isPrepared) {
                    boolean show = true;
                    if (st.refreshMenuContent) {
                        st.isPrepared = false;
                        show = preparePanel(st, event);
                    }
                    if (show) {
                        openPanel(st, event);
                        playSoundEffect = true;
                    }
                }
            } else if (this.mDecorContentParent.isOverflowMenuShowing()) {
                playSoundEffect = this.mDecorContentParent.hideOverflowMenu();
            } else if (!isDestroyed() && preparePanel(st, event)) {
                playSoundEffect = this.mDecorContentParent.showOverflowMenu();
            }
            if (playSoundEffect) {
                AudioManager audioManager = (AudioManager) this.mActivity.getSystemService("audio");
                if (audioManager != null) {
                    audioManager.playSoundEffect(0);
                } else {
                    Log.w(TAG, "Couldn't get audio manager");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void callOnPanelClosed(int featureId, PanelFeatureState panel, Menu menu) {
        if (menu == null) {
            if (panel == null && featureId >= 0 && featureId < this.mPanels.length) {
                panel = this.mPanels[featureId];
            }
            if (panel != null) {
                menu = panel.menu;
            }
        }
        if (panel == null || panel.isOpen) {
            getWindowCallback().onPanelClosed(featureId, menu);
        }
    }

    /* access modifiers changed from: private */
    public PanelFeatureState findMenuPanel(Menu menu) {
        PanelFeatureState[] panels = this.mPanels;
        int N = panels != null ? panels.length : 0;
        for (int i = 0; i < N; i++) {
            PanelFeatureState panel = panels[i];
            if (panel != null && panel.menu == menu) {
                return panel;
            }
        }
        return null;
    }

    private PanelFeatureState getPanelState(int featureId, boolean required) {
        PanelFeatureState[] ar = this.mPanels;
        if (ar == null || ar.length <= featureId) {
            PanelFeatureState[] nar = new PanelFeatureState[(featureId + 1)];
            if (ar != null) {
                System.arraycopy(ar, 0, nar, 0, ar.length);
            }
            ar = nar;
            this.mPanels = nar;
        }
        PanelFeatureState st = ar[featureId];
        if (st != null) {
            return st;
        }
        PanelFeatureState st2 = new PanelFeatureState(featureId);
        ar[featureId] = st2;
        return st2;
    }

    /* access modifiers changed from: package-private */
    public final boolean performPanelShortcut(PanelFeatureState st, int keyCode, KeyEvent event, int flags) {
        if (event.isSystem()) {
            return false;
        }
        boolean handled = false;
        if ((st.isPrepared || preparePanel(st, event)) && st.menu != null) {
            handled = st.menu.performShortcut(keyCode, event, flags);
        }
        if (!handled || (flags & 1) != 0 || this.mDecorContentParent != null) {
            return handled;
        }
        closePanel(st, true);
        return handled;
    }

    private void invalidatePanelMenu(int featureId) {
        this.mInvalidatePanelMenuFeatures |= 1 << featureId;
        if (!this.mInvalidatePanelMenuPosted && this.mWindowDecor != null) {
            ViewCompat.postOnAnimation(this.mWindowDecor, this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    /* access modifiers changed from: private */
    public void doInvalidatePanelMenu(int featureId) {
        PanelFeatureState st;
        PanelFeatureState st2 = getPanelState(featureId, true);
        if (st2.menu != null) {
            Bundle savedActionViewStates = new Bundle();
            st2.menu.saveActionViewStates(savedActionViewStates);
            if (savedActionViewStates.size() > 0) {
                st2.frozenActionViewState = savedActionViewStates;
            }
            st2.menu.stopDispatchingItemsChanged();
            st2.menu.clear();
        }
        st2.refreshMenuContent = true;
        st2.refreshDecorView = true;
        if ((featureId == 8 || featureId == 0) && this.mDecorContentParent != null && (st = getPanelState(0, false)) != null) {
            st.isPrepared = false;
            preparePanel(st, (KeyEvent) null);
        }
    }

    /* access modifiers changed from: private */
    public int updateStatusGuard(int insetTop) {
        int newMargin;
        int i = 0;
        boolean showStatusGuard = false;
        if (this.mActionModeView != null && (this.mActionModeView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) this.mActionModeView.getLayoutParams();
            boolean mlpChanged = false;
            if (this.mActionModeView.isShown()) {
                if (this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }
                Rect insets = this.mTempRect1;
                Rect localInsets = this.mTempRect2;
                insets.set(0, insetTop, 0, 0);
                ViewUtils.computeFitSystemWindows(this.mSubDecor, insets, localInsets);
                if (localInsets.top == 0) {
                    newMargin = insetTop;
                } else {
                    newMargin = 0;
                }
                if (mlp.topMargin != newMargin) {
                    mlpChanged = true;
                    mlp.topMargin = insetTop;
                    if (this.mStatusGuard == null) {
                        this.mStatusGuard = new View(this.mActivity);
                        this.mStatusGuard.setBackgroundColor(this.mActivity.getResources().getColor(R.color.abc_input_method_navigation_guard));
                        this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup.LayoutParams(-1, insetTop));
                    } else {
                        ViewGroup.LayoutParams lp = this.mStatusGuard.getLayoutParams();
                        if (lp.height != insetTop) {
                            lp.height = insetTop;
                            this.mStatusGuard.setLayoutParams(lp);
                        }
                    }
                }
                if (this.mStatusGuard != null) {
                    showStatusGuard = true;
                } else {
                    showStatusGuard = false;
                }
                if (!this.mOverlayActionMode && showStatusGuard) {
                    insetTop = 0;
                }
            } else if (mlp.topMargin != 0) {
                mlpChanged = true;
                mlp.topMargin = 0;
            }
            if (mlpChanged) {
                this.mActionModeView.setLayoutParams(mlp);
            }
        }
        if (this.mStatusGuard != null) {
            View view = this.mStatusGuard;
            if (!showStatusGuard) {
                i = 8;
            }
            view.setVisibility(i);
        }
        return insetTop;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("supportRequestWindowFeature() must be called before adding content");
        }
    }

    private class ActionModeCallbackWrapper implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        public ActionModeCallbackWrapper(ActionMode.Callback wrapped) {
            this.mWrapped = wrapped;
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return this.mWrapped.onCreateActionMode(mode, menu);
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(mode, menu);
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return this.mWrapped.onActionItemClicked(mode, item);
        }

        public void onDestroyActionMode(ActionMode mode) {
            this.mWrapped.onDestroyActionMode(mode);
            if (ActionBarActivityDelegateBase.this.mActionModePopup != null) {
                ActionBarActivityDelegateBase.this.mActivity.getWindow().getDecorView().removeCallbacks(ActionBarActivityDelegateBase.this.mShowActionModePopup);
                ActionBarActivityDelegateBase.this.mActionModePopup.dismiss();
            } else if (ActionBarActivityDelegateBase.this.mActionModeView != null) {
                ActionBarActivityDelegateBase.this.mActionModeView.setVisibility(8);
                if (ActionBarActivityDelegateBase.this.mActionModeView.getParent() != null) {
                    ViewCompat.requestApplyInsets((View) ActionBarActivityDelegateBase.this.mActionModeView.getParent());
                }
            }
            if (ActionBarActivityDelegateBase.this.mActionModeView != null) {
                ActionBarActivityDelegateBase.this.mActionModeView.removeAllViews();
            }
            if (ActionBarActivityDelegateBase.this.mActivity != null) {
                try {
                    ActionBarActivityDelegateBase.this.mActivity.onSupportActionModeFinished(ActionBarActivityDelegateBase.this.mActionMode);
                } catch (AbstractMethodError e) {
                }
            }
            ActionBarActivityDelegateBase.this.mActionMode = null;
        }
    }

    private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        private PanelMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            MenuBuilder rootMenu = menu.getRootMenu();
            boolean isSubMenu = rootMenu != menu;
            ActionBarActivityDelegateBase actionBarActivityDelegateBase = ActionBarActivityDelegateBase.this;
            if (isSubMenu) {
                menu = rootMenu;
            }
            PanelFeatureState panel = actionBarActivityDelegateBase.findMenuPanel(menu);
            if (panel == null) {
                return;
            }
            if (isSubMenu) {
                ActionBarActivityDelegateBase.this.callOnPanelClosed(panel.featureId, panel, rootMenu);
                ActionBarActivityDelegateBase.this.closePanel(panel, true);
                return;
            }
            ActionBarActivityDelegateBase.this.mActivity.closeOptionsMenu();
            ActionBarActivityDelegateBase.this.closePanel(panel, allMenusAreClosing);
        }

        public boolean onOpenSubMenu(MenuBuilder subMenu) {
            WindowCallback cb;
            if (subMenu != null || !ActionBarActivityDelegateBase.this.mHasActionBar || (cb = ActionBarActivityDelegateBase.this.getWindowCallback()) == null || ActionBarActivityDelegateBase.this.isDestroyed()) {
                return true;
            }
            cb.onMenuOpened(8, subMenu);
            return true;
        }
    }

    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        private ActionMenuPresenterCallback() {
        }

        public boolean onOpenSubMenu(MenuBuilder subMenu) {
            WindowCallback cb = ActionBarActivityDelegateBase.this.getWindowCallback();
            if (cb == null) {
                return true;
            }
            cb.onMenuOpened(8, subMenu);
            return true;
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            ActionBarActivityDelegateBase.this.checkCloseActionMenu(menu);
        }
    }

    private static final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        Bundle frozenMenuState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView = false;
        boolean refreshMenuContent;
        View shownPanelView;
        boolean wasLastOpen;
        int windowAnimations;
        int x;
        int y;

        PanelFeatureState(int featureId2) {
            this.featureId = featureId2;
        }

        public boolean hasPanelItems() {
            if (this.shownPanelView == null) {
                return false;
            }
            if (this.createdPanelView != null || this.listMenuPresenter.getAdapter().getCount() > 0) {
                return true;
            }
            return false;
        }

        public void clearMenuPresenters() {
            if (this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }
            this.listMenuPresenter = null;
        }

        /* access modifiers changed from: package-private */
        public void setStyle(Context context) {
            TypedValue outValue = new TypedValue();
            Resources.Theme widgetTheme = context.getResources().newTheme();
            widgetTheme.setTo(context.getTheme());
            widgetTheme.resolveAttribute(R.attr.actionBarPopupTheme, outValue, true);
            if (outValue.resourceId != 0) {
                widgetTheme.applyStyle(outValue.resourceId, true);
            }
            widgetTheme.resolveAttribute(R.attr.panelMenuListTheme, outValue, true);
            if (outValue.resourceId != 0) {
                widgetTheme.applyStyle(outValue.resourceId, true);
            } else {
                widgetTheme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
            }
            Context context2 = new ContextThemeWrapper(context, 0);
            context2.getTheme().setTo(widgetTheme);
            this.listPresenterContext = context2;
            TypedArray a = context2.obtainStyledAttributes(R.styleable.Theme);
            this.background = a.getResourceId(R.styleable.Theme_panelBackground, 0);
            this.windowAnimations = a.getResourceId(R.styleable.Theme_android_windowAnimationStyle, 0);
            a.recycle();
        }

        /* access modifiers changed from: package-private */
        public void setMenu(MenuBuilder menu2) {
            if (menu2 != this.menu) {
                if (this.menu != null) {
                    this.menu.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menu2;
                if (menu2 != null && this.listMenuPresenter != null) {
                    menu2.addMenuPresenter(this.listMenuPresenter);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public MenuView getListMenuView(MenuPresenter.Callback cb) {
            if (this.menu == null) {
                return null;
            }
            if (this.listMenuPresenter == null) {
                this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R.layout.abc_list_menu_item_layout);
                this.listMenuPresenter.setCallback(cb);
                this.menu.addMenuPresenter(this.listMenuPresenter);
            }
            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        /* access modifiers changed from: package-private */
        public Parcelable onSaveInstanceState() {
            SavedState savedState = new SavedState();
            savedState.featureId = this.featureId;
            savedState.isOpen = this.isOpen;
            if (this.menu != null) {
                savedState.menuState = new Bundle();
                this.menu.savePresenterStates(savedState.menuState);
            }
            return savedState;
        }

        /* access modifiers changed from: package-private */
        public void onRestoreInstanceState(Parcelable state) {
            SavedState savedState = (SavedState) state;
            this.featureId = savedState.featureId;
            this.wasLastOpen = savedState.isOpen;
            this.frozenMenuState = savedState.menuState;
            this.shownPanelView = null;
            this.decorView = null;
        }

        /* access modifiers changed from: package-private */
        public void applyFrozenState() {
            if (this.menu != null && this.frozenMenuState != null) {
                this.menu.restorePresenterStates(this.frozenMenuState);
                this.frozenMenuState = null;
            }
        }

        private static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    return SavedState.readFromParcel(in);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            int featureId;
            boolean isOpen;
            Bundle menuState;

            private SavedState() {
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.featureId);
                dest.writeInt(this.isOpen ? 1 : 0);
                if (this.isOpen) {
                    dest.writeBundle(this.menuState);
                }
            }

            /* access modifiers changed from: private */
            public static SavedState readFromParcel(Parcel source) {
                boolean z = true;
                SavedState savedState = new SavedState();
                savedState.featureId = source.readInt();
                if (source.readInt() != 1) {
                    z = false;
                }
                savedState.isOpen = z;
                if (savedState.isOpen) {
                    savedState.menuState = source.readBundle();
                }
                return savedState;
            }
        }
    }

    private class ListMenuDecorView extends FrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        public boolean dispatchKeyEvent(KeyEvent event) {
            return ActionBarActivityDelegateBase.this.dispatchKeyEvent(event);
        }

        public boolean onInterceptTouchEvent(MotionEvent event) {
            if (event.getAction() != 0 || !isOutOfBounds((int) event.getX(), (int) event.getY())) {
                return super.onInterceptTouchEvent(event);
            }
            ActionBarActivityDelegateBase.this.closePanel(0);
            return true;
        }

        public void setBackgroundResource(int resid) {
            setBackgroundDrawable(TintManager.getDrawable(getContext(), resid));
        }

        private boolean isOutOfBounds(int x, int y) {
            return x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5;
        }
    }
}
