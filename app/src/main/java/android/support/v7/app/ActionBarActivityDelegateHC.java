package android.support.v7.app;

import android.annotation.TargetApi;
import android.support.v7.internal.view.SupportActionModeWrapper;
import android.support.v7.internal.widget.NativeActionModeAwareLayout;
import android.view.ActionMode;
import android.view.View;

@TargetApi(11)
class ActionBarActivityDelegateHC extends ActionBarActivityDelegateBase implements NativeActionModeAwareLayout.OnActionModeForChildListener {
    private NativeActionModeAwareLayout mNativeActionModeAwareLayout;

    ActionBarActivityDelegateHC(ActionBarActivity activity) {
        super(activity);
    }

    /* access modifiers changed from: package-private */
    public void onSubDecorInstalled() {
        this.mNativeActionModeAwareLayout = (NativeActionModeAwareLayout) this.mActivity.findViewById(16908290);
        if (this.mNativeActionModeAwareLayout != null) {
            this.mNativeActionModeAwareLayout.setActionModeForChildListener(this);
        }
    }

    public ActionMode startActionModeForChild(View originalView, ActionMode.Callback callback) {
        android.support.v7.view.ActionMode supportActionMode = startSupportActionMode(new SupportActionModeWrapper.CallbackWrapper(originalView.getContext(), callback));
        if (supportActionMode != null) {
            return new SupportActionModeWrapper(this.mActivity, supportActionMode);
        }
        return null;
    }
}
