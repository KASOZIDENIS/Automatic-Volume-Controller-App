package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.common.zza;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzr;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public final class GoogleAuthUtil {
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_ANDROID_PACKAGE_NAME = (Build.VERSION.SDK_INT >= 14 ? "androidPackageName" : "androidPackageName");
    public static final String KEY_CALLER_UID = (Build.VERSION.SDK_INT >= 11 ? "callerUid" : "callerUid");
    public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
    @Deprecated
    public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    private static final ComponentName zzKA = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
    private static final ComponentName zzKB = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");

    private GoogleAuthUtil() {
    }

    public static void clearToken(Context context, String token) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
        Context applicationContext = context.getApplicationContext();
        zzv.zzbJ("Calling this from your main thread can lead to deadlock");
        zzM(applicationContext);
        Bundle bundle = new Bundle();
        String str = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", str);
        if (!bundle.containsKey(KEY_ANDROID_PACKAGE_NAME)) {
            bundle.putString(KEY_ANDROID_PACKAGE_NAME, str);
        }
        zza zza = new zza();
        zzk zzU = zzk.zzU(applicationContext);
        if (zzU.zza(zzKA, (ServiceConnection) zza, "GoogleAuthUtil")) {
            try {
                Bundle zza2 = zzr.zza.zza(zza.zzku()).zza(token, bundle);
                String string = zza2.getString(zzha.zzLz);
                if (!zza2.getBoolean("booleanResult")) {
                    throw new GoogleAuthException(string);
                }
                zzU.zzb(zzKA, (ServiceConnection) zza, "GoogleAuthUtil");
            } catch (RemoteException e) {
                Log.i("GoogleAuthUtil", "GMS remote exception ", e);
                throw new IOException("remote exception");
            } catch (InterruptedException e2) {
                throw new GoogleAuthException("Interrupted");
            } catch (Throwable th) {
                zzU.zzb(zzKA, (ServiceConnection) zza, "GoogleAuthUtil");
                throw th;
            }
        } else {
            throw new IOException("Could not bind to service with the given context.");
        }
    }

    public static List<AccountChangeEvent> getAccountChangeEvents(Context ctx, int eventIndex, String accountName) throws GoogleAuthException, IOException {
        zzv.zzh(accountName, "accountName must be provided");
        zzv.zzbJ("Calling this from your main thread can lead to deadlock");
        Context applicationContext = ctx.getApplicationContext();
        zzM(applicationContext);
        zza zza = new zza();
        zzk zzU = zzk.zzU(applicationContext);
        if (zzU.zza(zzKA, (ServiceConnection) zza, "GoogleAuthUtil")) {
            try {
                List<AccountChangeEvent> events = zzr.zza.zza(zza.zzku()).zza(new AccountChangeEventsRequest().setAccountName(accountName).setEventIndex(eventIndex)).getEvents();
                zzU.zzb(zzKA, (ServiceConnection) zza, "GoogleAuthUtil");
                return events;
            } catch (RemoteException e) {
                Log.i("GoogleAuthUtil", "GMS remote exception ", e);
                throw new IOException("remote exception");
            } catch (InterruptedException e2) {
                throw new GoogleAuthException("Interrupted");
            } catch (Throwable th) {
                zzU.zzb(zzKA, (ServiceConnection) zza, "GoogleAuthUtil");
                throw th;
            }
        } else {
            throw new IOException("Could not bind to service with the given context.");
        }
    }

    public static String getAccountId(Context ctx, String accountName) throws GoogleAuthException, IOException {
        zzv.zzh(accountName, "accountName must be provided");
        zzv.zzbJ("Calling this from your main thread can lead to deadlock");
        zzM(ctx.getApplicationContext());
        return getToken(ctx, accountName, "^^_account_id_^^", new Bundle());
    }

    public static String getToken(Context context, Account account, String scope) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, account, scope, new Bundle());
    }

    public static String getToken(Context context, Account account, String scope, Bundle extras) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zza(context, account, scope, extras, (Boolean) null);
    }

    @Deprecated
    public static String getToken(Context context, String accountName, String scope) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(accountName, GOOGLE_ACCOUNT_TYPE), scope);
    }

    @Deprecated
    public static String getToken(Context context, String accountName, String scope, Bundle extras) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(accountName, GOOGLE_ACCOUNT_TYPE), scope, extras);
    }

    public static String getTokenWithNotification(Context context, Account account, String scope, Bundle extras) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putBoolean("handle_notification", true);
        return zza(context, account, scope, extras);
    }

    public static String getTokenWithNotification(Context context, Account account, String scope, Bundle extras, Intent callback) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        zzh(callback);
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putParcelable("callback_intent", callback);
        extras.putBoolean("handle_notification", true);
        return zza(context, account, scope, extras);
    }

    public static String getTokenWithNotification(Context context, Account account, String scope, Bundle extras, String authority, Bundle syncBundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        if (TextUtils.isEmpty(authority)) {
            throw new IllegalArgumentException("Authority cannot be empty or null.");
        }
        if (extras == null) {
            extras = new Bundle();
        }
        if (syncBundle == null) {
            syncBundle = new Bundle();
        }
        ContentResolver.validateSyncExtrasBundle(syncBundle);
        extras.putString("authority", authority);
        extras.putBundle("sync_extras", syncBundle);
        extras.putBoolean("handle_notification", true);
        return zza(context, account, scope, extras);
    }

    @Deprecated
    public static String getTokenWithNotification(Context context, String accountName, String scope, Bundle extras) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(accountName, GOOGLE_ACCOUNT_TYPE), scope, extras);
    }

    @Deprecated
    public static String getTokenWithNotification(Context context, String accountName, String scope, Bundle extras, Intent callback) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(accountName, GOOGLE_ACCOUNT_TYPE), scope, extras, callback);
    }

    @Deprecated
    public static String getTokenWithNotification(Context context, String accountName, String scope, Bundle extras, String authority, Bundle syncBundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(accountName, GOOGLE_ACCOUNT_TYPE), scope, extras, authority, syncBundle);
    }

    @Deprecated
    public static void invalidateToken(Context context, String token) {
        AccountManager.get(context).invalidateAuthToken(GOOGLE_ACCOUNT_TYPE, token);
    }

    private static void zzM(Context context) throws GoogleAuthException {
        try {
            GooglePlayServicesUtil.zzM(context);
        } catch (GooglePlayServicesRepairableException e) {
            throw new GooglePlayServicesAvailabilityException(e.getConnectionStatusCode(), e.getMessage(), e.getIntent());
        } catch (GooglePlayServicesNotAvailableException e2) {
            throw new GoogleAuthException(e2.getMessage());
        }
    }

    private static String zza(Context context, Account account, String str, Bundle bundle) throws IOException, GoogleAuthException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            return getToken(context, account, str, bundle);
        } catch (GooglePlayServicesAvailabilityException e) {
            GooglePlayServicesUtil.showErrorNotification(e.getConnectionStatusCode(), context);
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        } catch (UserRecoverableAuthException e2) {
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
    }

    public static String zza(Context context, Account account, String str, Bundle bundle, Boolean bool) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzb(context, account, str, bundle, bool).getString("authtoken");
    }

    public static Bundle zzb(Context context, Account account, String str, Bundle bundle, Boolean bool) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        Context applicationContext = context.getApplicationContext();
        zzv.zzbJ("Calling this from your main thread can lead to deadlock");
        zzM(applicationContext);
        Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        String str2 = context.getApplicationInfo().packageName;
        bundle2.putString("clientPackageName", str2);
        if (TextUtils.isEmpty(bundle2.getString(KEY_ANDROID_PACKAGE_NAME))) {
            bundle2.putString(KEY_ANDROID_PACKAGE_NAME, str2);
        }
        if (bool != null) {
            bundle2.putBoolean("UseCache", bool.booleanValue());
        }
        zza zza = new zza();
        zzk zzU = zzk.zzU(applicationContext);
        if (zzU.zza(zzKA, (ServiceConnection) zza, "GoogleAuthUtil")) {
            try {
                Bundle zza2 = zzr.zza.zza(zza.zzku()).zza(account, str, bundle2);
                if (zza2 == null) {
                    Log.w("GoogleAuthUtil", "Binder call returned null.");
                    throw new GoogleAuthException("ServiceUnavailable");
                } else if (!TextUtils.isEmpty(zza2.getString("authtoken"))) {
                    zzU.zzb(zzKA, (ServiceConnection) zza, "GoogleAuthUtil");
                    return zza2;
                } else {
                    String string = zza2.getString("Error");
                    Intent intent = (Intent) zza2.getParcelable("userRecoveryIntent");
                    if (zzbh(string)) {
                        throw new UserRecoverableAuthException(string, intent);
                    } else if (zzbg(string)) {
                        throw new IOException(string);
                    } else {
                        throw new GoogleAuthException(string);
                    }
                }
            } catch (RemoteException e) {
                Log.i("GoogleAuthUtil", "GMS remote exception ", e);
                throw new IOException("remote exception");
            } catch (InterruptedException e2) {
                throw new GoogleAuthException("Interrupted");
            } catch (Throwable th) {
                zzU.zzb(zzKA, (ServiceConnection) zza, "GoogleAuthUtil");
                throw th;
            }
        } else {
            throw new IOException("Could not bind to service with the given context.");
        }
    }

    private static boolean zzbg(String str) {
        return "NetworkError".equals(str) || "ServiceUnavailable".equals(str) || "Timeout".equals(str);
    }

    private static boolean zzbh(String str) {
        return "BadAuthentication".equals(str) || "CaptchaRequired".equals(str) || "DeviceManagementRequiredOrSyncDisabled".equals(str) || "NeedPermission".equals(str) || "NeedsBrowser".equals(str) || "UserCancel".equals(str) || "AppDownloadRequired".equals(str) || zzha.DM_SYNC_DISABLED.zzjQ().equals(str) || zzha.DM_ADMIN_BLOCKED.zzjQ().equals(str) || zzha.DM_ADMIN_PENDING_APPROVAL.zzjQ().equals(str) || zzha.DM_STALE_SYNC_REQUIRED.zzjQ().equals(str) || zzha.DM_DEACTIVATED.zzjQ().equals(str) || zzha.DM_REQUIRED.zzjQ().equals(str) || zzha.THIRD_PARTY_DEVICE_MANAGEMENT_REQUIRED.zzjQ().equals(str);
    }

    private static void zzh(Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("Callback cannot be null.");
        }
        try {
            Intent.parseUri(intent.toUri(1), 1);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }
}
