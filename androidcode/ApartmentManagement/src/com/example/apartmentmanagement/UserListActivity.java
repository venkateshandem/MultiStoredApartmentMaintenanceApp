package com.example.apartmentmanagement;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.apartment.to.UserTO;
import com.example.apartmentmanagement.ws.WebCalls;

public class UserListActivity extends ActionBarActivity {
	ArrayList<UserTO> userTOs;
	ArrayList<Integer> positionsList;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userlist);
		actionBar = getSupportActionBar();
		positionsList = new ArrayList<Integer>();
		actionBar.setDisplayHomeAsUpEnabled(true);
		// actionBar.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.sendSMS:
			// System.out.println(positionsList);
			sendSMS();
			break;
		case android.R.id.home:
			finish();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void sendSMS() {
		if (positionsList != null && positionsList.size() > 0) {
			String smsto = "";
			for (Integer number : positionsList) {
				String contactNumber = userList.get(number).getContactNumber();
				;
				if (contactNumber != null && contactNumber.trim().length() > 0)
					smsto += contactNumber + ';';
			}
			if (smsto.trim().length() > 1) {
				Intent smsIntent = new Intent(Intent.ACTION_SENDTO,
						Uri.parse("smsto:" + smsto));
				// smsIntent.putExtra("sms_body", "sms message goes here");
				startActivity(smsIntent);
			}
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		getUsersList();
	}

	ArrayList<UserTO> userList;

	private void getUsersList() {
		AsyncTask task = new AsyncTask() {

			@Override
			protected Object doInBackground(Object... params) {
				return WebCalls.getUsersList();
			}

			@Override
			protected void onPostExecute(Object result) {
				userList = (ArrayList<UserTO>) result;
				System.out.println(userList);
				displayUsers();
				super.onPostExecute(result);
			}
		};
		task.execute("");

	}

	protected void displayUsers() {
		ArrayList<String> namesList = new ArrayList<String>();
		for (UserTO userto : userList) {
			namesList.add(userto.getUserName() + "\n"
					+ userto.getContactNumber());

		}
		CustomUserAdapter customUserAdapter = new CustomUserAdapter(namesList,
				getApplicationContext());
		ListView listView = (ListView) findViewById(R.id.usersList);
		listView.setAdapter(customUserAdapter);
		//listView.scrollTo(0, listView.getHeight());
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {

				CheckedTextView textView = (CheckedTextView) view
						.findViewById(R.id.checkedText);
				if (textView.isChecked()) {
					textView.setChecked(false);
					positionsList.remove((Integer) arg2);
				} else {
					textView.setChecked(true);
					positionsList.add(arg2);
				}
			}
		});
	}

}
