package com.example.hci.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pl.rspective.pagerdatepicker.PagerDatePickerDateFormat;
import pl.rspective.pagerdatepicker.model.DateItem;
import pl.rspective.pagerdatepicker.view.DateRecyclerView;

public class NavigationKnock extends Fragment {


    private DateRecyclerView dateList;


    private ImageView banar1;
    public String[] title = {"Meeting with Janet","Catch up with Brian","Lunch with Diane","Dinner with Marie","Meeting with James","Lunch with Smith"};
    public int[] banner1 = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f};
    public int[] clock = {R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1};
    public int[] lolipop = {R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point};
    public String[] time = {"8 -10 am", "10 - 12 pm","12 - 2 pm", "2 - 4 pm", "4 - 6 pm", "6 - 8 pm"};
    public String[] task = {"Starbucks", "Mobile Project","Restaurant", "Bar & Grill", "Marketing Website", "Fashion Show"};

    private ArrayList<BeanClassForListView> BeanClassForListview;
    private listViewAdapter listViewAdapter;

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.navigation_knock, container, false);


        Calendar calendar = Calendar.getInstance();

        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH)+1;
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);


        //  Toast.makeText(NavigationKnock.this,""+thisDay+"-"+thisMonth+"-"+thisYear,Toast.LENGTH_LONG).show();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            calendar.setTime(sdf.parse(""+thisDay+"-"+thisMonth+"-"+thisYear));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, 30);


        int endYear = calendar.get(Calendar.YEAR);
        int endMonth = calendar.get(Calendar.MONTH)+1;
        int endDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Toast.makeText(NavigationKnock.this,""+endDay+"-"+endMonth+"-"+endYear,Toast.LENGTH_LONG).show();

        dateList = (DateRecyclerView) view.findViewById(R.id.date_list);

        // dateList.addItemDecoration(new RecyclerViewInsetDecoration(NavigationKnock.this, R.dimen.date_card_insets));

        Date start = null;
        Date end = null;
        Date defaultDate = null;

        try {
            start = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse(""+thisDay+"-"+thisMonth+"-"+thisYear);
            end = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse(""+endDay+"-"+endMonth+"-"+endYear);

            defaultDate = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse(""+thisDay+"-"+thisMonth+"-"+thisYear);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        CustomDateAdapter dateAdapter = new CustomDateAdapter(start, end, defaultDate);
        dateList.setAdapter(dateAdapter);



        dateList.setDatePickerListener(new DateRecyclerView.DatePickerListener() {
            @Override
            public void onDatePickerItemClick(DateItem dateItem, int position) {
                Toast.makeText(getActivity(), "Clicked: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDatePickerPageSelected(int position) {
            }

            @Override
            public void onDatePickerPageStateChanged(int state) {
            }

            @Override
            public void onDatePickerPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
        });

        // Set animation for current selected view
        dateAdapter.setCurrentViewAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.blinking_animation));

        ListView listview = (ListView) view.findViewById(R.id.listview);

        BeanClassForListview = new ArrayList<>();


        for (int i = 0; i < title.length; i++) {
            BeanClassForListView beanClassForListView = new BeanClassForListView( clock[i],lolipop[i], banner1[i], task[i],time[i],title[i]);
            listview.setFocusable(false);
            BeanClassForListview.add(beanClassForListView);
        }
        listViewAdapter = new listViewAdapter(getActivity(), BeanClassForListview);
        listview.setAdapter(listViewAdapter);

        return view;


    }
}
