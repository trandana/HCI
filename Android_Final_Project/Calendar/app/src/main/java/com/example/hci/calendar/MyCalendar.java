package com.example.hci.calendar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MyCalendar extends AppCompatActivity {
    private CalendarView calendar;
    private ListView upcomingEventsList;
    private ScheduleItemAdapter upcomingEventsAdapter;
    private ArrayList upcomingEvents;
    private TextView upComing;
    private DBAdapter myDb;
    private int month, dayOfMonth, dayOfWeek, year;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the current date to use for comparing with db
        month = Calendar.getInstance().get(Calendar.MONTH)+1;
        dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        dayOfWeek  = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        year = Calendar.getInstance().get(Calendar.YEAR);
        date = dateFormat(dayOfMonth) + "/" + dateFormat(month) + "/"+ year;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEvent = new Intent(getApplicationContext(), AddToCalendar.class);
                startActivity(addEvent);
                finish();
            }
        });


        upcomingEventsList = (ListView) findViewById(R.id.upcomingInfo);
        upcomingEvents = new ArrayList<ScheduleItems>();

        openDB();
        readDB();

        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                //launch view for that day using year(i), month(i1) and day(i2)
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_home){
            Intent home = new Intent(this, HomePage.class);
            startActivity(home);
            finish();
        } else if (id == R.id.action_my_calendar){
            return true;
        } else if(id == R.id.action_friends){
            Intent friends = new Intent(this, FriendsList.class);
            startActivity(friends);
            finish();
            return true;
        } else if(id == R.id.action_meeting){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private String dateFormat(int i){
        String output = i+"";
        if(i < 10){
            output = "0" + i;
        }
        return output;
    }

    //Database Methods
    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }
    private void closeDB() {
        myDb.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }

    //If there are no events then a default message is shown
    private void readDB(){
        Cursor c = myDb.getRow(date, dayOfWeek);
        String message="";

        if (c.moveToFirst()) {
            do {
                // Process the data:
                int id = c.getInt(DBAdapter.COL_ROWID);
                String title = c.getString(DBAdapter.COL_TITLE);
                String location = c.getString(DBAdapter.COL_LOC);
                String stime = c.getString(DBAdapter.COL_STIME);
                String etime = c.getString(DBAdapter.COL_ETIME);

                String date = c.getString(DBAdapter.COL_DATE);

                int day = c.getInt(DBAdapter.COL_DAY);


                // Append data to the message:
                message += "id=" + id
                        + ", title =" + title
                        + ", Date =" + date
                        + ", From =" + stime
                        + ", To=" + etime
                        + "\n";
                if (date != null) {
                    upcomingEvents.add(new ScheduleItems(title,location,stime, etime,date, false));
                } else {
                    upcomingEvents.add(new ScheduleItems(title,location,stime, etime, dayToString(day), true));
                }

            } while (c.moveToNext());
        }
        if(upcomingEvents.isEmpty()){
            ArrayList<String> temp = new ArrayList<String>();
            temp.add("There are no Events today");
            ArrayAdapter<String> tempAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, temp);
            upcomingEventsList.setAdapter(tempAdapter);
            upcomingEventsList.setBackgroundColor(Color.BLACK);
        } else {
            ScheduleItemAdapter adapter = new ScheduleItemAdapter(getApplicationContext(),upcomingEvents);
            upcomingEventsList.setAdapter(adapter);
        }
    }



    public String dayToString(int i){
        String day = "";
        switch(i){
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

}
