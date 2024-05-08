package com.mightyworksinc.androidapp.mightyvolume;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import timber.log.Timber;

public class BlueToothReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Timber.d("BlueToothReceiver", new Object[0]);
        if (BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1) != 2) {
            Timber.d("not BluetoothProfile.STATE_CONNECTED", new Object[0]);
            AutoVolumeControl.setISBlueToothHeadSetCheck(false);
        }
        try {
            BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, new BluetoothProfile.ServiceListener() {
                public void onServiceDisconnected(int profile) {
                }

                public void onServiceConnected(int profile, BluetoothProfile proxy) {
                    for (BluetoothDevice next : proxy.getConnectedDevices()) {
                        AutoVolumeControl.setISBlueToothHeadSetCheck(true);
                    }
                    BluetoothAdapter.getDefaultAdapter().closeProfileProxy(profile, proxy);
                }
            }, 1);
        } catch (Exception e) {
            Timber.i("Exception", new Object[0]);
        }
    }
}
