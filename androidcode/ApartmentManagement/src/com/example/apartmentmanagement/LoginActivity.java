package com.example.apartmentmanagement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apartment.to.UserTO;
import com.example.apartmentmanagement.ws.Constants;
import com.example.apartmentmanagement.ws.WebCalls;

public class LoginActivity extends Activity {
	Button loginButton;
	EditText emailId, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginButton = (Button) findViewById(R.id.loginButton);
		emailId = (EditText) findViewById(R.id.login);
		password = (EditText) findViewById(R.id.password);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String email = emailId.getText().toString();
				String passString = password.getText().toString();
				if (email != null && passString != null
						&& email.trim().length() > 0
						&& passString.trim().length() > 0) {
					login();
				} else {
					Toast.makeText(getApplicationContext(),
							"Email Id or Password should not be empty",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	protected void login() {
		String _emailId = emailId.getText().toString().trim();
		String _password = password.getText().toString().trim();
		final UserTO userTO = new UserTO();
		userTO.setEmailId(_emailId);
		userTO.setPassword(_password);
		final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle("Please wait.");
		progressDialog.setMessage("Loading...");
		AsyncTask task = new AsyncTask() {

			@Override
			protected void onPostExecute(Object result) {

				super.onPostExecute(result);
				if (progressDialog != null && progressDialog.isShowing()) {
					progressDialog.cancel();
				}
				System.out.println(result);
				UserTO userTO = (UserTO) result;
				if (userTO != null && userTO.getUser_id() != 0
						&& userTO.getUserType().equals("admin")) {
					Intent intent = new Intent(getApplicationContext(),
							AdminHomeActivity.class);
					saveSharedPreferences(userTO, getApplicationContext());
					startActivity(intent);
				} else if (userTO != null && userTO.getUser_id() != 0
						&& userTO.getUserType().equals("user")) {
					saveSharedPreferences(userTO, getApplicationContext());
					Intent intent = new Intent(getApplicationContext(),
							AdminHomeActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(getApplicationContext(),
							"UserName Password didnt match", Toast.LENGTH_LONG)
							.show();
				}
			}

			@Override
			protected Object doInBackground(Object... params) {
				return WebCalls.loginUser(userTO);
			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				progressDialog.show();
			}

		};
		task.execute("");
	}

	public static void saveSharedPreferences(UserTO userTO, Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constants.SHARED_PREF_PATH, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt("user_id", userTO.getUser_id());
		editor.putString("userName;", userTO.getUserName());
		editor.putString("contactNumber", userTO.getContactNumber());
		editor.putString("apartmentName", userTO.getAppartmentName());
		editor.putString("aparrmentBlock", userTO.getAparrmentBlock());
		editor.putString("address", userTO.getAddress());
		editor.putString("userType", userTO.getUserType());
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
