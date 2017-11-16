package com.example.hci.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hci.calendar.R;

import java.util.ArrayList;

public class listViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BeanClassForListView> beanClassArrayList;


    public listViewAdapter(Context context, ArrayList<BeanClassForListView> beanClassArrayList) {
        this.context = context;
        this.beanClassArrayList = beanClassArrayList;


    }

    @Override
    public int getCount() {
        return beanClassArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanClassArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHoder viewHoder;
        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview, parent, false);

            viewHoder = new ViewHoder();


            viewHoder.task = (TextView) convertView.findViewById(R.id.task);
            viewHoder.time = (TextView) convertView.findViewById(R.id.time);
            viewHoder.title = (TextView) convertView.findViewById(R.id.title);
            viewHoder.banner1 = (ImageView) convertView.findViewById(R.id.banar1);
            viewHoder.clock = (ImageView) convertView.findViewById(R.id.clock);
            viewHoder.lolipop = (ImageView) convertView.findViewById(R.id.lolipop);

            convertView.setTag(viewHoder);

        } else {

            viewHoder = (ViewHoder) convertView.getTag();
        }


        BeanClassForListView beanClass = (BeanClassForListView) getItem(position);




        viewHoder.clock.setImageResource(beanClass.getClock_image());
        viewHoder.lolipop.setImageResource(beanClass.getLolipop_image());
        viewHoder.banner1.setImageResource(beanClass.getProfile_image());
        viewHoder.title.setText(beanClass.getTitle());
        viewHoder.time.setText(beanClass.getTime());
        viewHoder.task.setText(beanClass.getTask());




        return convertView;

    }


    private class ViewHoder {

        ImageView clock;
        ImageView lolipop;
        ImageView banner1;
        TextView title;
        TextView time;
        TextView task;




    }
}