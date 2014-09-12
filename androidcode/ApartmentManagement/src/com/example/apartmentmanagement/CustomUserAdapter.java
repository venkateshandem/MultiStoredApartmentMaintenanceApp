package com.example.apartmentmanagement;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

public class CustomUserAdapter extends BaseAdapter {
	ArrayList<String> namesList;
	Context context;

	public CustomUserAdapter(ArrayList<String> namesList, Context context) {
		this.namesList = namesList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return namesList.size();
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
	public View getView(int possition, View convertView, ViewGroup arg2) {
		Holder holder = new Holder();
		if (convertView == null) {
			convertView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.custom_user, null);

		}
		holder.checkedTextView = (CheckedTextView) convertView
				.findViewById(R.id.checkedText);
		holder.checkedTextView.setText(namesList.get(possition));
		return convertView;
	}

	class Holder {
		CheckedTextView checkedTextView;
	}
}
