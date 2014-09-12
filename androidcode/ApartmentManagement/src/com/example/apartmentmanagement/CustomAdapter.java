package com.example.apartmentmanagement;

import java.util.ArrayList;

import com.apartment.to.Message;
import com.example.apartmentmanagement.ws.Constants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomAdapter extends android.widget.BaseAdapter {
	ArrayList<Message> list = new ArrayList<Message>();
	Context context;
	int myUserId;

	public CustomAdapter(ArrayList<Message> messageList, Context context) {
		this.list = messageList;
		this.context = context;
		myUserId = context.getSharedPreferences(Constants.SHARED_PREF_PATH,
				Context.MODE_PRIVATE).getInt("user_id", -1);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			convertView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.custom_list, null);

		}
		holder = new Holder();
		holder.messageDesc = (TextView) convertView
				.findViewById(R.id.messageDesc);
		holder.messageBy = (TextView) convertView.findViewById(R.id.userName);
		holder.messageDate = (TextView) convertView.findViewById(R.id.sentDate);
		holder.phoneNumber = (TextView) convertView
				.findViewById(R.id.contactNumber);
		Message message = list.get(position);
		holder.messageDesc.setText(message.getMessageDescription() + "");
		holder.messageDate.setText(message.getMessageDate());
		holder.phoneNumber.setText("");
		if (myUserId != -1 && myUserId == message.getUserId()) {
			holder.messageBy.setText("Me");
		} else {
			holder.messageBy.setText(message.getUserName());
		}
		return convertView;
	}

	class Holder {
		TextView messageDesc, messageDate, messageBy, phoneNumber;
	}
}
