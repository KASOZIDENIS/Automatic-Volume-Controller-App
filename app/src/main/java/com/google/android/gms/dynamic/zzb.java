package com.google.android.gms.dynamic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.zzc;

public final class zzb extends zzc.zza {
    private Fragment zzacp;

    private zzb(Fragment fragment) {
        this.zzacp = fragment;
    }

    public static zzb zza(Fragment fragment) {
        if (fragment != null) {
            return new zzb(fragment);
        }
        return null;
    }

    public Bundle getArguments() {
        return this.zzacp.getArguments();
    }

    public int getId() {
        return this.zzacp.getId();
    }

    public boolean getRetainInstance() {
        return this.zzacp.getRetainInstance();
    }

    public String getTag() {
        return this.zzacp.getTag();
    }

    public int getTargetRequestCode() {
        return this.zzacp.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.zzacp.getUserVisibleHint();
    }

    public zzd getView() {
        return zze.zzt(this.zzacp.getView());
    }

    public boolean isAdded() {
        return this.zzacp.isAdded();
    }

    public boolean isDetached() {
        return this.zzacp.isDetached();
    }

    public boolean isHidden() {
        return this.zzacp.isHidden();
    }

    public boolean isInLayout() {
        return this.zzacp.isInLayout();
    }

    public boolean isRemoving() {
        return this.zzacp.isRemoving();
    }

    public boolean isResumed() {
        return this.zzacp.isResumed();
    }

    public boolean isVisible() {
        return this.zzacp.isVisible();
    }

    public void setHasOptionsMenu(boolean hasMenu) {
        this.zzacp.setHasOptionsMenu(hasMenu);
    }

    public void setMenuVisibility(boolean menuVisible) {
        this.zzacp.setMenuVisibility(menuVisible);
    }

    public void setRetainInstance(boolean retain) {
        this.zzacp.setRetainInstance(retain);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.zzacp.setUserVisibleHint(isVisibleToUser);
    }

    public void startActivity(Intent intent) {
        this.zzacp.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        this.zzacp.startActivityForResult(intent, requestCode);
    }

    public void zze(zzd zzd) {
        this.zzacp.registerForContextMenu((View) zze.zzg(zzd));
    }

    public void zzf(zzd zzd) {
        this.zzacp.unregisterForContextMenu((View) zze.zzg(zzd));
    }

    public zzd zzov() {
        return zze.zzt(this.zzacp.getActivity());
    }

    public zzc zzow() {
        return zza(this.zzacp.getParentFragment());
    }

    public zzd zzox() {
        return zze.zzt(this.zzacp.getResources());
    }

    public zzc zzoy() {
        return zza(this.zzacp.getTargetFragment());
    }
}
