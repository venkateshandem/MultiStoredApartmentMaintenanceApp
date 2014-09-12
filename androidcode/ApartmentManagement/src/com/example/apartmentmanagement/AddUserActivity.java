package com.example.apartmentmanagement;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apartment.to.UserTO;
import com.example.apartmentmanagement.ws.WebCalls;

public class AddUserActivity extends ActionBarActivity {
	ActionBar actionBar;
	EditText userName, contactNumber, emailId, apartmentName, apartmentBlock,
			password, flatnumber;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_user);
		actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		userName = (EditText) findViewById(R.id.userName);
		contactNumber = (EditText) findViewById(R.id.contactNumber);
		emailId = (EditText) findViewById(R.id.emailId);
		apartmentName = (EditText) findViewById(R.id.apartmentName);
		apartmentBlock = (EditText) findViewById(R.id.apartmentBlock);
		password = (EditText) findViewById(R.id.password);
		flatnumber = (EditText) findViewById(R.id.flatNumber);
		button = (Button) findViewById(R.id.register);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				registerUser();

			}
		});
	}

	ProgressDialog dialog;

	protected void registerUser() {
		final UserTO to = new UserTO();
		String userName = this.userName.getText().toString();
		String password = this.password.getText().toString();
		String contactNumber = this.contactNumber.getText().toString();
		String emailId = this.emailId.getText().toString();
		String apartmentName = this.apartmentName.getText().toString();
		String apartmentBlock = this.apartmentBlock.getText().toString();
		String flatNumber = this.flatnumber.getText().toString();
		boolean status = false;
		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		boolean status4 = false;
		if (userName != null && userName.trim().length() > 0) {
			to.setUserName(userName);
			status = true;
		} else {
			status = false;
			Toast.makeText(getApplicationContext(),
					"User Name Should Not be empty.", Toast.LENGTH_LONG).show();
		}
		if (contactNumber != null && contactNumber.trim().length() > 0) {
			to.setContactNumber(contactNumber);
			status1 = true;
		} else {
			status1 = false;
			Toast.makeText(getApplicationContext(),
					"User Contact Number Should Not be empty.",
					Toast.LENGTH_LONG).show();
		}

		if (password != null && password.trim().length() > 0) {
			to.setPassword(password);
			status2 = true;
		} else {
			status2 = false;
			Toast.makeText(getApplicationContext(),
					"User Password Should Not be empty.", Toast.LENGTH_LONG)
					.show();
		}
		if (emailId != null && emailId.trim().length() > 0) {
			to.setEmailId(emailId);
			status3 = true;
		} else {
			status3 = false;
			Toast.makeText(getApplicationContext(),
					"User Email ID Should Not be empty.", Toast.LENGTH_LONG)
					.show();
		}
		if (flatNumber != null && flatNumber.trim().length() > 0) {
			to.setFlatnumber(flatNumber);
			status4 = true;
		} else {
			status4 = false;
			Toast.makeText(getApplicationContext(),
					"Flat number Should Not be empty.", Toast.LENGTH_LONG)
					.show();
		}
		to.setApartmentName(apartmentName);
		to.setAparrmentBlock(apartmentBlock);
		// sto.setFlatnumber(flatnumber);
		if (status && status1 && status2 && status3 && status4) {
			System.out.println(to);
			AsyncTask task = new AsyncTask() {

				@Override
				protected Object doInBackground(Object... params) {
					return WebCalls.registerUser(to);
				}

				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					dialog = new ProgressDialog(AddUserActivity.this);
					dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					dialog.setTitle("Please Wait.");
					dialog.setMessage("Loading...");
					dialog.show();
				}

				@Override
				protected void onPostExecute(Object result) {
					super.onPostExecute(result);
					if (dialog != null && dialog.isShowing()) {
						dialog.dismiss();
					}
					if (result.toString().contains("true")) {

						Toast.makeText(getApplicationContext(),
								"Sucessully register", Toast.LENGTH_LONG)
								.show();
						finish();
					} else {
						Toast.makeText(getApplicationContext(),
								"Sorry! Unable to register. Please try again.",
								Toast.LENGTH_LONG).show();
					}
				}
			};
			task.execute("");

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_user, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			finish();

		}
		return super.onOptionsItemSelected(item);
	}
}
