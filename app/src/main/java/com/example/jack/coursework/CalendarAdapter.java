package com.example.jack.coursework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CalendarAdapter extends ArrayAdapter<Calendar> {
    public CalendarAdapter(Context context, ArrayList<Calendar> calendars) {
        super(context, 0, calendars);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Calendar c = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_detailed, parent, false);
        }
        TextView lblName = (TextView) convertView.findViewById(R.id.lblName);
        TextView lblTime = (TextView) convertView.findViewById(R.id.lblTime);
        TextView lblDate = (TextView) convertView.findViewById(R.id.lblDate);
        TextView lblAddress = (TextView) convertView.findViewById(R.id.lblAddress);

        //Add the data to the detailed event screen
        lblName.setText(c.name);
        lblTime.setText(c.time);
        lblDate.setText(c.date);
        lblAddress.setText(c.address);

        //Return the completed view
        return convertView;
    }
}
