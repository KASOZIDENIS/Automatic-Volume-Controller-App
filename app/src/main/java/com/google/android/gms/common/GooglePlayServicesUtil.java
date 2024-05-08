package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompatExtras;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.common.zzb;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzic;

public final class GooglePlayServicesUtil {
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 7327000;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    public static boolean zzOV = false;
    public static boolean zzOW = false;
    private static int zzOX = -1;
    private static String zzOY = null;
    private static Integer zzOZ = null;
    private static final Object zznu = new Object();

    private static class zza extends Handler {
        private final Context zzoh;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        zza(Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.zzoh = context.getApplicationContext();
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzoh);
                    if (GooglePlayServicesUtil.isUserRecoverableError(isGooglePlayServicesAvailable)) {
                        GooglePlayServicesUtil.zza(isGooglePlayServicesAvailable, this.zzoh);
                        return;
                    }
                    return;
                default:
                    Log.w("GooglePlayServicesUtil", "Don't know how to handle this message: " + msg.what);
                    return;
            }
        }
    }

    private GooglePlayServicesUtil() {
    }

    @Deprecated
    public static Dialog getErrorDialog(int errorCode, Activity activity, int requestCode) {
        return getErrorDialog(errorCode, activity, requestCode, (DialogInterface.OnCancelListener) null);
    }

    @Deprecated
    public static Dialog getErrorDialog(int errorCode, Activity activity, int requestCode, DialogInterface.OnCancelListener cancelListener) {
        return zza(errorCode, activity, (Fragment) null, requestCode, cancelListener);
    }

    protected static int getErrorNotificationId(int errorCode) {
        switch (errorCode) {
            case 1:
            case 3:
            case 18:
                return 10436;
            default:
                return 39789;
        }
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int errorCode, Context context, int requestCode) {
        Intent zzar = zzar(errorCode);
        if (zzar == null) {
            return null;
        }
        return PendingIntent.getActivity(context, requestCode, zzar, 268435456);
    }

    @Deprecated
    public static String getErrorString(int errorCode) {
        switch (errorCode) {
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            case 16:
                return "API_UNAVAILABLE";
            case 18:
                return "SERVICE_UPDATING";
            default:
                return "UNKNOWN_ERROR_CODE";
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getOpenSourceSoftwareLicenseInfo(android.content.Context r4) {
        /*
            r1 = 0
            android.net.Uri$Builder r0 = new android.net.Uri$Builder
            r0.<init>()
            java.lang.String r2 = "android.resource"
            android.net.Uri$Builder r0 = r0.scheme(r2)
            java.lang.String r2 = "com.google.android.gms"
            android.net.Uri$Builder r0 = r0.authority(r2)
            java.lang.String r2 = "raw"
            android.net.Uri$Builder r0 = r0.appendPath(r2)
            java.lang.String r2 = "oss_notice"
            android.net.Uri$Builder r0 = r0.appendPath(r2)
            android.net.Uri r0 = r0.build()
            android.content.ContentResolver r2 = r4.getContentResolver()     // Catch:{ Exception -> 0x004e }
            java.io.InputStream r2 = r2.openInputStream(r0)     // Catch:{ Exception -> 0x004e }
            java.util.Scanner r0 = new java.util.Scanner     // Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
            r0.<init>(r2)     // Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
            java.lang.String r3 = "\\A"
            java.util.Scanner r0 = r0.useDelimiter(r3)     // Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
            java.lang.String r0 = r0.next()     // Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
            if (r2 == 0) goto L_0x003e
            r2.close()     // Catch:{ Exception -> 0x004e }
        L_0x003e:
            return r0
        L_0x003f:
            r0 = move-exception
            if (r2 == 0) goto L_0x0045
            r2.close()     // Catch:{ Exception -> 0x004e }
        L_0x0045:
            r0 = r1
            goto L_0x003e
        L_0x0047:
            r0 = move-exception
            if (r2 == 0) goto L_0x004d
            r2.close()     // Catch:{ Exception -> 0x004e }
        L_0x004d:
            throw r0     // Catch:{ Exception -> 0x004e }
        L_0x004e:
            r0 = move-exception
            r0 = r1
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(android.content.Context):java.lang.String");
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (!zzd.zzSV) {
            try {
                context.getResources().getString(R.string.common_google_play_services_unknown_issue);
            } catch (Throwable th) {
                Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
            }
        }
        if (!zzd.zzSV && !"com.google.android.gms".equals(context.getPackageName())) {
            zzO(context);
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
            zzc zzkA = zzc.zzkA();
            if (!zzhs.zzbj(packageInfo.versionCode) && !zzhs.zzV(context)) {
                try {
                    zzb.zza zza2 = zzkA.zza(packageManager.getPackageInfo(GOOGLE_PLAY_STORE_PACKAGE, 64), zzb.zzbd.zzOU);
                    if (zza2 == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                        return 9;
                    }
                    if (zzkA.zza(packageInfo, zza2) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                        return 9;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    if (zzh(context, GOOGLE_PLAY_STORE_PACKAGE)) {
                        Log.w("GooglePlayServicesUtil", "Google Play Store is updating.");
                        if (zzkA.zza(packageInfo, zzb.zzbd.zzOU) == null) {
                            Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                            return 9;
                        }
                    } else {
                        Log.w("GooglePlayServicesUtil", "Google Play Store is neither installed nor updating.");
                        return 9;
                    }
                }
            } else if (zzkA.zza(packageInfo, zzb.zzbd.zzOU) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (zzhs.zzbh(packageInfo.versionCode) < zzhs.zzbh(7327000)) {
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires 7327000 but found " + packageInfo.versionCode);
                return 2;
            }
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo == null) {
                try {
                    applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.");
                    e2.printStackTrace();
                    return 1;
                }
            }
            return !applicationInfo.enabled ? 3 : 0;
        } catch (PackageManager.NameNotFoundException e3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    @Deprecated
    public static boolean isUserRecoverableError(int errorCode) {
        switch (errorCode) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int errorCode, Activity activity, int requestCode) {
        return showErrorDialogFragment(errorCode, activity, requestCode, (DialogInterface.OnCancelListener) null);
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int errorCode, Activity activity, int requestCode, DialogInterface.OnCancelListener cancelListener) {
        return showErrorDialogFragment(errorCode, activity, (Fragment) null, requestCode, cancelListener);
    }

    public static boolean showErrorDialogFragment(int errorCode, Activity activity, Fragment fragment, int requestCode, DialogInterface.OnCancelListener cancelListener) {
        boolean z = false;
        Dialog zza2 = zza(errorCode, activity, fragment, requestCode, cancelListener);
        if (zza2 == null) {
            return false;
        }
        try {
            z = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
        }
        if (z) {
            SupportErrorDialogFragment.newInstance(zza2, cancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), GMS_ERROR_DIALOG);
        } else if (zzic.zzne()) {
            ErrorDialogFragment.newInstance(zza2, cancelListener).show(activity.getFragmentManager(), GMS_ERROR_DIALOG);
        } else {
            throw new RuntimeException("This Activity does not support Fragments.");
        }
        return true;
    }

    @Deprecated
    public static void showErrorNotification(int errorCode, Context context) {
        if (zzhs.zzV(context) && errorCode == 2) {
            errorCode = 42;
        }
        if (zze(context, errorCode) || zzf(context, errorCode)) {
            zzP(context);
        } else {
            zza(errorCode, context);
        }
    }

    @Deprecated
    public static void zzM(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable != 0) {
            Intent zzar = zzar(isGooglePlayServicesAvailable);
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + isGooglePlayServicesAvailable);
            if (zzar == null) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", zzar);
        }
    }

    private static void zzO(Context context) {
        Integer num;
        synchronized (zznu) {
            if (zzOY == null) {
                zzOY = context.getPackageName();
                try {
                    Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                    if (bundle != null) {
                        zzOZ = Integer.valueOf(bundle.getInt("com.google.android.gms.version"));
                    } else {
                        zzOZ = null;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.wtf("GooglePlayServicesUtil", "This should never happen.", e);
                }
            } else if (!zzOY.equals(context.getPackageName())) {
                throw new IllegalArgumentException("isGooglePlayServicesAvailable should only be called with Context from your application's package. A previous call used package '" + zzOY + "' and this call used package '" + context.getPackageName() + "'.");
            }
            num = zzOZ;
        }
        if (num == null) {
            throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        } else if (num.intValue() != 7327000) {
            throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 7327000 but found " + num + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
        }
    }

    private static void zzP(Context context) {
        zza zza2 = new zza(context);
        zza2.sendMessageDelayed(zza2.obtainMessage(1), 120000);
    }

    @Deprecated
    public static void zzQ(Context context) {
        try {
            ((NotificationManager) context.getSystemService("notification")).cancel(10436);
        } catch (SecurityException e) {
        }
    }

    private static String zzR(Context context) {
        ApplicationInfo applicationInfo;
        String str = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo).toString() : packageName;
    }

    private static Dialog zza(int i, Activity activity, Fragment fragment, int i2, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder builder = null;
        if (i == 0) {
            return null;
        }
        if (zzhs.zzV(activity) && i == 2) {
            i = 42;
        }
        if (zzic.zznh()) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, typedValue, true);
            if ("Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                builder = new AlertDialog.Builder(activity, 5);
            }
        }
        if (builder == null) {
            builder = new AlertDialog.Builder(activity);
        }
        builder.setMessage(zzf.zzh(activity, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        Intent zzar = zzar(i);
        zzg zzg = fragment == null ? new zzg(activity, zzar, i2) : new zzg(fragment, zzar, i2);
        String zzi = zzf.zzi(activity, i);
        if (zzi != null) {
            builder.setPositiveButton(zzi, zzg);
        }
        String zzg2 = zzf.zzg(activity, i);
        if (zzg2 != null) {
            builder.setTitle(zzg2);
        }
        return builder.create();
    }

    /* access modifiers changed from: private */
    public static void zza(int i, Context context) {
        zza(i, context, (String) null);
    }

    private static void zza(int i, Context context, String str) {
        Notification notification;
        Resources resources = context.getResources();
        String zzj = zzf.zzj(context, i);
        String string = resources.getString(R.string.common_google_play_services_error_notification_requested_by_msg, new Object[]{zzR(context)});
        PendingIntent errorPendingIntent = getErrorPendingIntent(i, context, 0);
        if (zzhs.zzV(context)) {
            zzv.zzP(zzic.zzni());
            notification = new Notification.Builder(context).setSmallIcon(R.drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle(new Notification.BigTextStyle().bigText(zzj + " " + string)).addAction(R.drawable.common_full_open_on_phone, resources.getString(R.string.common_open_on_phone), errorPendingIntent).build();
        } else {
            Notification notification2 = new Notification(17301642, resources.getString(R.string.common_google_play_services_notification_ticker), System.currentTimeMillis());
            notification2.flags |= 16;
            if (Build.VERSION.SDK_INT >= 21) {
                notification2.flags |= 256;
            } else if (Build.VERSION.SDK_INT >= 19) {
                notification2.extras.putBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY, true);
            }
            notification2.setLatestEventInfo(context, zzj, string, errorPendingIntent);
            notification = notification2;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (str != null) {
            notificationManager.notify(str, getErrorNotificationId(i), notification);
        } else {
            notificationManager.notify(getErrorNotificationId(i), notification);
        }
    }

    public static boolean zza(Context context, int i, String str) {
        if (zzic.zznk()) {
            try {
                ((AppOpsManager) context.getSystemService("appops")).checkPackage(i, str);
                return true;
            } catch (SecurityException e) {
                return false;
            }
        } else {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
            if (str == null || packagesForUid == null) {
                return false;
            }
            for (String equals : packagesForUid) {
                if (str.equals(equals)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Deprecated
    public static Intent zzar(int i) {
        switch (i) {
            case 1:
            case 2:
            case 18:
                return zzm.zzbQ("com.google.android.gms");
            case 3:
                return zzm.zzbO("com.google.android.gms");
            case 42:
                return zzm.zzmk();
            default:
                return null;
        }
    }

    public static boolean zzb(PackageManager packageManager) {
        synchronized (zznu) {
            if (zzOX == -1) {
                try {
                    if (zzc.zzkA().zza(packageManager.getPackageInfo("com.google.android.gms", 64), zzb.zzON[1]) != null) {
                        zzOX = 1;
                    } else {
                        zzOX = 0;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    zzOX = 0;
                }
            }
        }
        return zzOX != 0;
    }

    @Deprecated
    public static boolean zzb(PackageManager packageManager, String str) {
        return zzc.zzkA().zzb(packageManager, str);
    }

    public static boolean zzc(PackageManager packageManager) {
        return zzb(packageManager) || !zzkz();
    }

    public static boolean zzd(Context context, int i) {
        return zza(context, i, "com.google.android.gms") && zzb(context.getPackageManager(), "com.google.android.gms");
    }

    @Deprecated
    public static boolean zze(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return zzh(context, "com.google.android.gms");
        }
        return false;
    }

    @Deprecated
    public static boolean zzf(Context context, int i) {
        if (i == 9) {
            return zzh(context, GOOGLE_PLAY_STORE_PACKAGE);
        }
        return false;
    }

    public static boolean zzh(Context context, String str) {
        if (zzic.zznm()) {
            for (PackageInstaller.SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                if (str.equals(appPackageName.getAppPackageName())) {
                    return true;
                }
            }
        } else {
            try {
                if (context.getPackageManager().getApplicationInfo(str, 8192).enabled) {
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return false;
    }

    public static boolean zzkz() {
        return zzOV ? zzOW : "user".equals(Build.TYPE);
    }
}
