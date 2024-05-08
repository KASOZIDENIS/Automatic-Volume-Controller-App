package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.internal.zzhw;

public final class zzf {
    public static final String zzg(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_title);
            case 2:
            case 18:
                return resources.getString(R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
                return null;
            case 5:
                Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
                return resources.getString(R.string.common_google_play_services_invalid_account_title);
            case 7:
                Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                return resources.getString(R.string.common_google_play_services_network_error_title);
            case 8:
                Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                return resources.getString(R.string.common_google_play_services_unsupported_title);
            case 10:
                Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
                return null;
            case 16:
                Log.e("GooglePlayServicesUtil", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GooglePlayServicesUtil", "The specified account could not be signed in.");
                return resources.getString(R.string.common_google_play_services_sign_in_failed_title);
            case 42:
                return resources.getString(R.string.common_android_wear_update_title);
            default:
                Log.e("GooglePlayServicesUtil", "Unexpected error code " + i);
                return null;
        }
    }

    public static String zzh(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return zzhw.zzb(context.getResources()) ? resources.getString(R.string.common_google_play_services_install_text_tablet) : resources.getString(R.string.common_google_play_services_install_text_phone);
            case 2:
            case 18:
                return resources.getString(R.string.common_google_play_services_update_text);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_text);
            case 5:
                return resources.getString(R.string.common_google_play_services_invalid_account_text);
            case 7:
                return resources.getString(R.string.common_google_play_services_network_error_text);
            case 9:
                return resources.getString(R.string.common_google_play_services_unsupported_text);
            case 16:
                return resources.getString(R.string.commono_google_play_services_api_unavailable_text);
            case 17:
                return resources.getString(R.string.common_google_play_services_sign_in_failed_text);
            case 42:
                return resources.getString(R.string.common_android_wear_update_text);
            default:
                return resources.getString(R.string.common_google_play_services_unknown_issue);
        }
    }

    public static String zzi(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_button);
            case 2:
            case 18:
            case 42:
                return resources.getString(R.string.common_google_play_services_update_button);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }

    public static String zzj(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_notification_needs_installation_title);
            case 2:
            case 18:
                return resources.getString(R.string.common_google_play_services_notification_needs_update_title);
            case 3:
                return resources.getString(R.string.common_google_play_services_needs_enabling_title);
            case 5:
                return resources.getString(R.string.common_google_play_services_invalid_account_text);
            case 7:
                return resources.getString(R.string.common_google_play_services_network_error_text);
            case 9:
                return resources.getString(R.string.common_google_play_services_unsupported_text);
            case 16:
                return resources.getString(R.string.commono_google_play_services_api_unavailable_text);
            case 17:
                return resources.getString(R.string.common_google_play_services_sign_in_failed_text);
            case 42:
                return resources.getString(R.string.common_android_wear_notification_needs_update_text);
            default:
                return resources.getString(R.string.common_google_play_services_unknown_issue);
        }
    }
}
