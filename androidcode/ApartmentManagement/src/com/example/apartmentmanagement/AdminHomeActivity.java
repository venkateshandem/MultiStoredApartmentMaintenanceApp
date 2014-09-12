package com.example.apartmentmanagement;

import java.util.ArrayList;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.apartment.to.Message;
import com.example.apartmentmanagement.ws.Constants;
import com.example.apartmentmanagement.ws.WebCalls;

public class AdminHomeActivity extends ActionBarActivity {
	ActionBar actionBar;
	EditText messageText;
	Button sendMsgButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_admin);
		actionBar = getSupportActionBar();
		// actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		messageText = (EditText) findViewById(R.id.message);
		sendMsgButton = (Button) findViewById(R.id.sendButton);
		sendMsgButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (messageText.getText().toString() != null
						&& messageText.getText().toString().length() > 0)
					sendMessage(messageText.getText().toString());
			}
		});
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(getApplicationContext(),
				ScanNotificationService.class);
		PendingIntent pendingIntent = PendingIntent.getService(
				getApplicationContext(), 0, intent, 0);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis(), 1000 * 60 * 1, pendingIntent);
		ArrayList<Message> messages = getMessages();
	}

	private ArrayList<Message> getMessages() {
		AsyncTask task = new AsyncTask() {

			@Override
			protected Object doInBackground(Object... params) {
				return WebCalls.getMessagesList();
			}

			@Override
			protected void onPostExecute(Object result) {
				super.onPostExecute(result);
				ArrayList<Message> messageList = (ArrayList<Message>) result;
				if (messageList != null && messageList.size() > 0) {
					displayMessages(messageList);
				}
			}

		};
		task.execute("");
		return null;
	}

	CustomAdapter customAdapter;

	protected void displayMessages(ArrayList<Message> messageList) {
		customAdapter = new CustomAdapter(messageList, this);
		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(customAdapter);
		// listView.scrollTo(0, listView.getHeight());
		int messageid = messageList.get(messageList.size() - 1).getMessageId();
		Editor editor = getSharedPreferences(Constants.SHARED_PREF_PATH,
				Context.MODE_PRIVATE).edit();
		editor.putInt("messageId", messageid);
		editor.commit();
	}

	Message message;
	String userType;

	protected void sendMessage(final String string) {
		AsyncTask task = new AsyncTask() {

			@Override
			protected Object doInBackground(Object... params) {
				message = new Message();
				SharedPreferences preferences = getSharedPreferences(
						Constants.SHARED_PREF_PATH, Context.MODE_PRIVATE);
				int userId = preferences.getInt("user_id", -1);
				System.out.println("userid=" + userId);

				return WebCalls.sendMessage(string, userId);
			}

			@Override
			protected void onPostExecute(Object result) {
				System.out.println(result);
				if (result != null && result.toString().contains("true")) {
					messageText.setText("");
					getMessages();
				} else {
					Toast.makeText(getApplicationContext(),
							"Sorry! Unable to send message", Toast.LENGTH_LONG)
							.show();
				}
				super.onPostExecute(result);
			}

		};
		task.execute("");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		userType = getSharedPreferences(Constants.SHARED_PREF_PATH,
				Context.MODE_PRIVATE).getString("userType", "user");
		getMenuInflater().inflate(R.menu.main, menu);
		if (userType != null && !userType.equals("admin")) {
			menu.removeItem(R.id.addUserMenu);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		System.out.println(id);
		switch (id) {
		case R.id.addUserMenu:
			Intent intent = new Intent(getApplicationContext(),
					AddUserActivity.class);
			startActivity(intent);
			break;
		case R.id.usersList:
			Intent intent2 = new Intent(getApplicationContext(),
					UserListActivity.class);
			startActivity(intent2);
			break;
		case android.R.id.home:
			finish();
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
