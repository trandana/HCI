package com.example.hci.calendar;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CalendarActivity extends ActionBarActivity {

    TextView month;

    private ImageView banar1;
    public String[] title = {"Meeting with Janet","HCI Meeting","Lunch with Diane","Dinner with Marie","Meeting with James","Lunch with Smith"};
    public int[] banner1 = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f};
    public int[] clock = {R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1};
    public int[] lolipop = {R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point};
    public String[] time = {"8 -10 am", "10 - 12 pm","12 - 2 pm", "2 - 4 pm", "4 - 6 pm", "6 - 8 pm"};
    public String[] task = {"Starbucks", "HCI Project","Restaurant", "Bar & Grill", "Marketing Website", "Fashion Show"};

    private static final String TAG = "CalendarActivity";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private boolean shouldShow = false;

    private android.support.v7.widget.Toolbar toolbar;

    private ArrayList<BeanClassForListView> BeanClassForListview;
    private listViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        ListView listview = (ListView) findViewById(R.id.listview);

        BeanClassForListview = new ArrayList<>();

        for (int i = 0; i < title.length; i++)
        {
            BeanClassForListView beanClassForListView = new BeanClassForListView( clock[i],lolipop[i], banner1[i], task[i],time[i],title[i]);
            listview.setFocusable(false);
            BeanClassForListview.add(beanClassForListView);
        }

        listViewAdapter = new listViewAdapter(CalendarActivity.this, BeanClassForListview);
        listview.setAdapter(listViewAdapter);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        month = (TextView) toolbar.findViewById(R.id.month);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            setSupportActionBar(toolbar);
           // actionBar.setDisplayHomeAsUpEnabled(true);
      //  actionBar.setTitle("");




        //  final ActionBar actionBar = getSupportActionBar();
        //Array contains date info
        final List<String> mutableBookings = new ArrayList<>();

//        final ListView bookingsListView = (ListView) findViewById(R.id.bookings_listview);

//        final Button showPreviousMonthBut = (Button) findViewById(R.id.prev_button);
//        final Button showNextMonthBut = (Button) findViewById(R.id.next_button);
//        final Button slideCalendarBut = (Button) findViewById(R.id.slide_calendar);
//        final Button showCalendarWithAnimationBut = (Button) findViewById(R.id.show_with_animation_calendar);

        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mutableBookings);
//        bookingsListView.setAdapter(adapter);
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        // below allows you to configure color for the current day in the month
        compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.light_sky));
        // below allows you to configure colors for the current day the user has selected
        compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.dark_sky));
        
//        addEvents(compactCalendarView, -1);
//        addEvents(compactCalendarView, Calendar.DECEMBER);
//        addEvents(compactCalendarView, Calendar.AUGUST);
        compactCalendarView.invalidate();

        // below line will display Sunday as the first day of the week
        // compactCalendarView.setShouldShowMondayAsFirstDay(false);

        //set initial title
        month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<com.example.hci.calendar.Event> bookingsFromMap = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "inside onclick " + dateClicked);
                if (bookingsFromMap != null) {
                    Log.d(TAG, bookingsFromMap.toString());
                    mutableBookings.clear();
                    for (Event booking : bookingsFromMap) {
                        mutableBookings.add((String) booking.getData());
                    }
                    adapter.notifyDataSetChanged();
                }
            }



        @Override
        public void onMonthScroll(Date firstDayOfNewMonth)
        {
                month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

//        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               compactCalendarView.showPreviousMonth();
//            }
//        });
//
//        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 compactCalendarView.showNextMonth();
//            }
//        });

//        slideCalendarBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (shouldShow) {
//                    compactCalendarView.showCalendar();
//                } else {
//                    compactCalendarView.hideCalendar();
//                }
//                shouldShow = !shouldShow;
//            }
//        });

//        showCalendarWithAnimationBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (shouldShow) {
//                    compactCalendarView.showCalendarWithAnimation();
//                } else {
//                    compactCalendarView.hideCalendarWithAnimation();
//                }
//                shouldShow = !shouldShow;
//            }
//        });

    }

    //open add event window
    public void addEvent(View view)
    {
        Intent newEvent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(newEvent);
    }
    //open navigation menu
    public void openNavigationMenu(View v)
    {
        Intent openNav = new Intent (getApplicationContext(), NavigationActivity.class);
       Log.i("Open nav", ":");
        startActivity(openNav);
    }

      private void addEvents(CompactCalendarView compactCalendarView, int month) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();
        for (int i = 0; i < 6; i++) {
            currentCalender.setTime(firstDayOfMonth);
            if (month > -1) {
                currentCalender.set(Calendar.MONTH, month);
            }
           currentCalender.add(Calendar.DATE, i);
            setToMidnight(currentCalender);
            long timeInMillis = currentCalender.getTimeInMillis();

            List<Event> events = getEvents(timeInMillis, i);
            compactCalendarView.addEvents(events);
        }
    }

    private List<Event> getEvents(long timeInMillis, int day) {
        if (day < 2) {
            return Arrays.asList(new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)));
        } else if ( day > 2 && day <= 4) {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)));
        } else {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis) ),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 70, 68, 65), timeInMillis, "Event 3 at " + new Date(timeInMillis)));
        }
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
