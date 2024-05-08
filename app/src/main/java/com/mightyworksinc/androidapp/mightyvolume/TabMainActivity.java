package com.mightyworksinc.androidapp.mightyvolume;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mightyworksinc.androidapp.mightyvolume.MightyworksAnalyticsApplication;
import java.util.HashMap;
import timber.log.Timber;

public class TabMainActivity extends ActionBarActivity {
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.tab_main);
        Tracker mTracker = ((MightyworksAnalyticsApplication) getApplication()).getTracker(MightyworksAnalyticsApplication.TrackerName.APP_TRACKER);
        mTracker.setScreenName("Application open");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        getSupportActionBar().setDisplayOptions(10);
        this.mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        this.mViewPager = (ViewPager) findViewById(R.id.pager);
        this.mViewPager.setAdapter(this.mSectionsPagerAdapter);
        this.mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                Timber.d("position = " + position, new Object[0]);
                if (position == 0) {
                    Tracker mTracker = ((MightyworksAnalyticsApplication) TabMainActivity.this.getApplication()).getTracker(MightyworksAnalyticsApplication.TrackerName.APP_TRACKER);
                    mTracker.setScreenName("AutoVolumeControl");
                    mTracker.send(new HitBuilders.ScreenViewBuilder().build());
                    return;
                }
                Tracker mTracker2 = ((MightyworksAnalyticsApplication) TabMainActivity.this.getApplication()).getTracker(MightyworksAnalyticsApplication.TrackerName.APP_TRACKER);
                mTracker2.setScreenName("ManualVolumeControl");
                mTracker2.send(new HitBuilders.ScreenViewBuilder().build());
            }
        });
    }

    public SectionsPagerAdapter getSectionPageAdapter() {
        return this.mSectionsPagerAdapter;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        HashMap<Integer, Fragment> mCache = new HashMap<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            if (this.mCache.containsKey(Integer.valueOf(position))) {
                return this.mCache.get(Integer.valueOf(position));
            }
            Fragment frag = null;
            switch (position) {
                case 0:
                    frag = new AutoMaticFragment();
                    break;
                case 1:
                    frag = new ManualFragment();
                    break;
            }
            this.mCache.put(Integer.valueOf(position), frag);
            return frag;
        }

        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_faq) {
            startActivity(new Intent(this, FAQ.class));
            overridePendingTransition(0, 0);
            return true;
        } else if (id == R.id.action_rate) {
            String appPackageName = getPackageName();
            Timber.d(appPackageName, new Object[0]);
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPackageName)));
                return true;
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                return true;
            }
        } else if (id == R.id.action_about) {
            startActivity(new Intent(this, About.class));
            overridePendingTransition(0, 0);
            return true;
        } else if (id != R.id.action_version) {
            return super.onOptionsItemSelected(item);
        } else {
            startActivity(new Intent(this, Version.class));
            overridePendingTransition(0, 0);
            return true;
        }
    }
}
