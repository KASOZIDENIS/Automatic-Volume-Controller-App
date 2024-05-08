package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzv;

public class ErrorDialogFragment extends DialogFragment {
    private Dialog mDialog = null;
    private DialogInterface.OnCancelListener zzOK = null;

    public static ErrorDialogFragment newInstance(Dialog dialog) {
        return newInstance(dialog, (DialogInterface.OnCancelListener) null);
    }

    public static ErrorDialogFragment newInstance(Dialog dialog, DialogInterface.OnCancelListener cancelListener) {
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        Dialog dialog2 = (Dialog) zzv.zzb(dialog, (Object) "Cannot display null dialog");
        dialog2.setOnCancelListener((DialogInterface.OnCancelListener) null);
        dialog2.setOnDismissListener((DialogInterface.OnDismissListener) null);
        errorDialogFragment.mDialog = dialog2;
        if (cancelListener != null) {
            errorDialogFragment.zzOK = cancelListener;
        }
        return errorDialogFragment;
    }

    public void onCancel(DialogInterface dialog) {
        if (this.zzOK != null) {
            this.zzOK.onCancel(dialog);
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (this.mDialog == null) {
            setShowsDialog(false);
        }
        return this.mDialog;
    }

    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }
}
