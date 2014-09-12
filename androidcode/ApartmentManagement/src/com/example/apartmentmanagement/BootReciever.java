package com.example.apartmentmanagement;

import com.example.apartmentmanagement.ws.Constants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int userId = context.getSharedPreferences(Constants.SHARED_PREF_PATH,
				Context.MODE_PRIVATE).getInt("user_id", -1);
		if (userId != -1) {
			Intent intent1 = new Intent(context, ScanNotificationService.class);
			context.startService(intent1);
		}
	}

}
