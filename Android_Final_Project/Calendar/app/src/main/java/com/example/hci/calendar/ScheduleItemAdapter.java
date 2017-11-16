package com.example.hci.calendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Burrows on 2017-11-11.
 */

public class ScheduleItemAdapter extends ArrayAdapter<ScheduleItems> {
    private final Context context;
    private final ArrayList<ScheduleItems> itemslist;

    public ScheduleItemAdapter(Context c, ArrayList<ScheduleItems> iteml){
        super(c, R.layout.home_page_schedule, iteml);
        context = c;
        itemslist = iteml;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View viewR = inflater.inflate(R.layout.home_page_schedule, parent, false);

        TextView title = (TextView) viewR.findViewById(R.id.title);
        TextView loc = (TextView) viewR.findViewById(R.id.location);
        TextView time = (TextView) viewR.findViewById(R.id.time);
        TextView date = (TextView) viewR.findViewById(R.id.date);

        title.setText(itemslist.get(position).getTitle());
        date.setText(itemslist.get(position).getDate());
        loc.setText("Location: " + itemslist.get(position).getLocation());
        time.setText("From: " + itemslist.get(position).getStartTime() + " To: " + itemslist.get(position).getEndTime());



        return viewR;

    }

}
