package com.mightyworksinc.androidapp.mightyvolume;

import android.app.Application;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import java.util.HashMap;

public class MightyworksAnalyticsApplication extends Application {
    private static final String PROPERTY_ID = "UA-61681747-4";
    HashMap<TrackerName, Tracker> mTrackers = new HashMap<>();

    public enum TrackerName {
        APP_TRACKER
    }

    public void onCreate() {
        super.onCreate();
    }

    /* access modifiers changed from: package-private */
    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!this.mTrackers.containsKey(trackerId)) {
            Tracker t = GoogleAnalytics.getInstance(this).newTracker(PROPERTY_ID);
            t.enableAdvertisingIdCollection(true);
            t.enableExceptionReporting(true);
            this.mTrackers.put(trackerId, t);
        }
        return this.mTrackers.get(trackerId);
    }
}
