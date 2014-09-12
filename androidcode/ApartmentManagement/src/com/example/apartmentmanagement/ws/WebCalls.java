package com.example.apartmentmanagement.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.apartment.to.Message;
import com.apartment.to.UserTO;
import com.google.gson.Gson;

public class WebCalls {

	public static UserTO loginUser(UserTO userTO) {
		HttpParams httpparams = new BasicHttpParams();
		ConnManagerParams.setTimeout(httpparams, 10000);
		HttpConnectionParams.setConnectionTimeout(httpparams, 50000);
		HttpConnectionParams.setSoTimeout(httpparams, 50000);
		HttpClient httpclient = new DefaultHttpClient(httpparams);
		String responseString = null;
		try {
			HttpPost post = new HttpPost(Constants.LOGIN);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");

			System.out.println(new Gson().toJson(userTO));
			StringEntity entity = new StringEntity(new Gson().toJson(userTO));
			post.setEntity(entity);
			HttpResponse response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				Log.d("statuline", "ok");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				userTO = new Gson().fromJson(responseString, UserTO.class);
			} else {
				Log.d("statuline", "not ok");
				ByteArrayOutputStream out1 = new ByteArrayOutputStream();
				try {
					response.getEntity().writeTo(out1);
					out1.close();
					String error_message = out1.toString();
				} catch (IOException e) {
					Log.d("catch", "inside");
				}
			}
		} catch (UnsupportedEncodingException e) {
			Log.d("exception background", "UnsupportedEncodingException");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			Log.d("exception background", "ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("exception background", "IOException");
			e.printStackTrace();
		}
		if (responseString != null)
			Log.d("response", responseString);
		return userTO;
	}

	public static String registerUser(UserTO to) {
		HttpParams httpparams = new BasicHttpParams();
		ConnManagerParams.setTimeout(httpparams, 10000);
		HttpConnectionParams.setConnectionTimeout(httpparams, 50000);
		HttpConnectionParams.setSoTimeout(httpparams, 50000);
		HttpClient httpclient = new DefaultHttpClient(httpparams);
		String responseString = null;
		try {
			HttpPost post = new HttpPost(Constants.REGISTER);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			to.setUserType("user");
			StringEntity entity = new StringEntity(new Gson().toJson(to));
			post.setEntity(entity);
			HttpResponse response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			System.out.println("statusLine" + statusLine);
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				Log.d("statuline", "ok");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				// to = new Gson().fromJson(responseString, UserTO.class);
				System.out.println("testing" + responseString);
			} else {
				Log.d("statuline", "not ok");
				ByteArrayOutputStream out1 = new ByteArrayOutputStream();
				try {
					response.getEntity().writeTo(out1);
					out1.close();
					String error_message = out1.toString();
				} catch (IOException e) {
					Log.d("catch", "inside");
				}
			}
		} catch (UnsupportedEncodingException e) {
			Log.d("exception background", "UnsupportedEncodingException");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			Log.d("exception background", "ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("exception background", "IOException");
			e.printStackTrace();
		}
		if (responseString != null)
			Log.d("response", responseString);
		return responseString;
	}

	public static Object sendMessage(String string, int userId) {
		HttpParams httpparams = new BasicHttpParams();
		ConnManagerParams.setTimeout(httpparams, 10000);
		HttpConnectionParams.setConnectionTimeout(httpparams, 50000);
		HttpConnectionParams.setSoTimeout(httpparams, 50000);
		HttpClient httpclient = new DefaultHttpClient(httpparams);
		String responseString = null;
		try {
			HttpPost post = new HttpPost(Constants.SENDMSG);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			Message to = new Message();
			to.setMessageDescription(string);
			to.setUserId(userId);
			StringEntity entity = new StringEntity(new Gson().toJson(to));
			post.setEntity(entity);
			HttpResponse response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			System.out.println("statusLine" + statusLine);
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				Log.d("statuline", "ok");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				// to = new Gson().fromJson(responseString, UserTO.class);
				System.out.println("testing" + responseString);
			} else {
				Log.d("statuline", "not ok");
				ByteArrayOutputStream out1 = new ByteArrayOutputStream();
				try {
					response.getEntity().writeTo(out1);
					out1.close();
					String error_message = out1.toString();
				} catch (IOException e) {
					Log.d("catch", "inside");
				}
			}
		} catch (UnsupportedEncodingException e) {
			Log.d("exception background", "UnsupportedEncodingException");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			Log.d("exception background", "ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("exception background", "IOException");
			e.printStackTrace();
		}
		if (responseString != null)
			Log.d("response", responseString);
		return responseString;
	}

	public static ArrayList<Message> getMessagesList() {
		ArrayList<Message> arrayList = new ArrayList<Message>();
		HttpParams httpparams = new BasicHttpParams();
		ConnManagerParams.setTimeout(httpparams, 10000);
		HttpConnectionParams.setConnectionTimeout(httpparams, 50000);
		HttpConnectionParams.setSoTimeout(httpparams, 50000);
		HttpClient httpclient = new DefaultHttpClient(httpparams);
		String responseString = null;
		try {
			HttpPost post = new HttpPost(Constants.GETMESSAGES);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			HttpResponse response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			System.out.println("statusLine" + statusLine);
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				Log.d("statuline", "ok");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				// to = new Gson().fromJson(responseString, UserTO.class);
				System.out.println("testing" + responseString);
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("data", responseString);
					JSONArray jsonArray = new JSONArray(responseString);
					System.out.println("json Array" + jsonArray);
					if (jsonArray != null && jsonArray.length() > 0) {
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject object = jsonArray.getJSONObject(i);
							Message message = new Gson().fromJson(
									object.toString(), Message.class);
							arrayList.add(message);
							System.out.println(message);
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				Log.d("statuline", "not ok");
				ByteArrayOutputStream out1 = new ByteArrayOutputStream();
				try {
					response.getEntity().writeTo(out1);
					out1.close();
					String error_message = out1.toString();
				} catch (IOException e) {
					Log.d("catch", "inside");
				}
			}
		} catch (UnsupportedEncodingException e) {
			Log.d("exception background", "UnsupportedEncodingException");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			Log.d("exception background", "ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("exception background", "IOException");
			e.printStackTrace();
		}
		if (responseString != null)
			Log.d("response", responseString);
		return arrayList;
	}

	public static boolean checkMessages(int messageId) {
		HttpParams httpparams = new BasicHttpParams();
		ConnManagerParams.setTimeout(httpparams, 10000);
		HttpConnectionParams.setConnectionTimeout(httpparams, 50000);
		HttpConnectionParams.setSoTimeout(httpparams, 50000);
		HttpClient httpclient = new DefaultHttpClient(httpparams);
		String responseString = null;
		try {
			HttpPost post = new HttpPost(Constants.CHECKMSG);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			System.out.println("message_id" + messageId);
			Message to = new Message();
			to.setMessageId(messageId);
			StringEntity entity = new StringEntity(new Gson().toJson(to));
			post.setEntity(entity);
			HttpResponse response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			System.out.println("statusLine" + statusLine);
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				Log.d("statuline", "ok");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				// to = new Gson().fromJson(responseString, UserTO.class);
				System.out.println("testing" + responseString);
				return responseString.contains("true");
			} else {
				Log.d("statuline", "not ok");
				ByteArrayOutputStream out1 = new ByteArrayOutputStream();
				try {
					response.getEntity().writeTo(out1);
					out1.close();
					String error_message = out1.toString();
				} catch (IOException e) {
					Log.d("catch", "inside");
				}
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			Log.d("exception background", "UnsupportedEncodingException");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			Log.d("exception background", "ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("exception background", "IOException");
			e.printStackTrace();
		}
		if (responseString != null)
			Log.d("response", responseString);
		return false;
	}

	public static ArrayList<UserTO> getUsersList() {
		ArrayList<UserTO> userList = new ArrayList<UserTO>();
		HttpParams httpparams = new BasicHttpParams();
		ConnManagerParams.setTimeout(httpparams, 10000);
		HttpConnectionParams.setConnectionTimeout(httpparams, 50000);
		HttpConnectionParams.setSoTimeout(httpparams, 50000);
		HttpClient httpclient = new DefaultHttpClient(httpparams);
		String responseString = null;
		try {
			HttpPost post = new HttpPost(Constants.GET_USERSLIST);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			HttpResponse response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			System.out.println("statusLine" + statusLine);
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				Log.d("statuline", "ok");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				// to = new Gson().fromJson(responseString, UserTO.class);
				System.out.println("testing" + responseString);
				// return responseString.contains("true");
				if (responseString != null
						&& responseString.trim().length() > 0) {
					try {
						JSONArray jsonArray = new JSONArray(responseString);
						for (int index = 0; index < jsonArray.length(); index++) {
							JSONObject jsonObject = jsonArray
									.getJSONObject(index);
							UserTO usrto = new Gson().fromJson(
									jsonObject.toString(), UserTO.class);
							userList.add(usrto);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else {
				Log.d("statuline", "not ok");
				ByteArrayOutputStream out1 = new ByteArrayOutputStream();
				try {
					response.getEntity().writeTo(out1);
					out1.close();
					String error_message = out1.toString();
				} catch (IOException e) {
					Log.d("catch", "inside");
				}
				// return false;
			}
		} catch (UnsupportedEncodingException e) {
			Log.d("exception background", "UnsupportedEncodingException");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			Log.d("exception background", "ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("exception background", "IOException");
			e.printStackTrace();
		}
		if (responseString != null)
			Log.d("response", responseString);
		return userList;
	}
}
