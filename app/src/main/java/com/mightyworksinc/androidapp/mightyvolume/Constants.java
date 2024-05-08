package com.mightyworksinc.androidapp.mightyvolume;

public class Constants {

    public interface ACTION {
        public static final String FOREGRUOND = "IMPORTANCE_FOREGROUND";
        public static final String IntentMaxAction = "Max";
        public static final String IntentNotificationUserAction = "Click";
        public static final String MAIN_ACTION = "com.truiton.foregroundservice.action.main";
        public static final String Phone_STATE = "android.intent.action.PHONE_STATE";
        public static final String STARTFOREGROUND_ACTION = "com.truiton.foregroundservice.action.startforeground";
        public static final String STOPFOREGROUND_ACTION = "com.truiton.foregroundservice.action.stopforeground";
        public static final String VOLUME_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    }

    public interface NOTIFICATION_ID {
        public static final int FOREGROUND_SERVICE = 101;
    }
}
