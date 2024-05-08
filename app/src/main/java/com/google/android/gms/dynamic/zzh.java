package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.google.android.gms.dynamic.zzc;

public final class zzh extends zzc.zza {
    private Fragment zzTb;

    private zzh(Fragment fragment) {
        this.zzTb = fragment;
    }

    public static zzh zza(Fragment fragment) {
        if (fragment != null) {
            return new zzh(fragment);
        }
        return null;
    }

    public Bundle getArguments() {
        return this.zzTb.getArguments();
    }

    public int getId() {
        return this.zzTb.getId();
    }

    public boolean getRetainInstance() {
        return this.zzTb.getRetainInstance();
    }

    public String getTag() {
        return this.zzTb.getTag();
    }

    public int getTargetRequestCode() {
        return this.zzTb.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.zzTb.getUserVisibleHint();
    }

    public zzd getView() {
        return zze.zzt(this.zzTb.getView());
    }

    public boolean isAdded() {
        return this.zzTb.isAdded();
    }

    public boolean isDetached() {
        return this.zzTb.isDetached();
    }

    public boolean isHidden() {
        return this.zzTb.isHidden();
    }

    public boolean isInLayout() {
        return this.zzTb.isInLayout();
    }

    public boolean isRemoving() {
        return this.zzTb.isRemoving();
    }

    public boolean isResumed() {
        return this.zzTb.isResumed();
    }

    public boolean isVisible() {
        return this.zzTb.isVisible();
    }

    public void setHasOptionsMenu(boolean hasMenu) {
        this.zzTb.setHasOptionsMenu(hasMenu);
    }

    public void setMenuVisibility(boolean menuVisible) {
        this.zzTb.setMenuVisibility(menuVisible);
    }

    public void setRetainInstance(boolean retain) {
        this.zzTb.setRetainInstance(retain);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.zzTb.setUserVisibleHint(isVisibleToUser);
    }

    public void startActivity(Intent intent) {
        this.zzTb.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        this.zzTb.startActivityForResult(intent, requestCode);
    }

    public void zze(zzd zzd) {
        this.zzTb.registerForContextMenu((View) zze.zzg(zzd));
    }

    public void zzf(zzd zzd) {
        this.zzTb.unregisterForContextMenu((View) zze.zzg(zzd));
    }

    public zzd zzov() {
        return zze.zzt(this.zzTb.getActivity());
    }

    public zzc zzow() {
        return zza(this.zzTb.getParentFragment());
    }

    public zzd zzox() {
        return zze.zzt(this.zzTb.getResources());
    }

    public zzc zzoy() {
        return zza(this.zzTb.getTargetFragment());
    }
}
