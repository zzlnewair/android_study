package com.zzl.broadcastreceiver.compoundbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class Receiver1 extends BroadcastReceiver {
	private final String TAG = "Receiver1";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "INTENT RECEIVED");

		Toast.makeText(context, "INTENT RECEIVED by Receiver1", Toast.LENGTH_LONG).show();
	}

}
