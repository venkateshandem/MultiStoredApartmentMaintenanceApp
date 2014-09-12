package com.example.apartmentmanagement;

import com.example.apartmentmanagement.ws.Constants;
import com.example.apartmentmanagement.ws.WebCalls;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class ScanNotificationService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		checkMessages();
		return super.onStartCommand(intent, flags, startId);
	}

	private void checkMessages() {
		System.out.println("in check message");
		AsyncTask task = new AsyncTask() {

			@Override
			protected Object doInBackground(Object... params) {
				int messageId = getSharedPreferences(
						Constants.SHARED_PREF_PATH, Context.MODE_PRIVATE)
						.getInt("messageId", 0);
				return WebCalls.checkMessages(messageId);
			}

			@Override
			protected void onPostExecute(Object result) {
				System.out.println(result.toString());
				if (result != null && result.toString().contains("true"))
					sendNotification();
				super.onPostExecute(result);
			}

		};
		task.execute("");
	}

	protected void sendNotification() {
		Notification notification = new Notification();
		notification.icon = R.drawable.ic_launcher;
		// System.out.println(remainder);
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, intent, 0);
		notification
				.setLatestEventInfo(this, "Notification", "", pendingIntent);
		// notification.defaults |= Notification.DEFAULT_ALL;
		notification.defaults |= Notification.DEFAULT_LIGHTS;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(getApplicationContext(),
				ScanNotificationService.class);
		PendingIntent pendingIntent = PendingIntent.getService(
				getApplicationContext(), 0, intent, 0);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis(), 1000 * 60 * 1, pendingIntent);
	}

}
