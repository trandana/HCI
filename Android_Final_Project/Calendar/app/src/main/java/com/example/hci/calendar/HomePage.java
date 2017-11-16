package com.example.hci.calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class HomePage extends AppCompatActivity {
    private ArrayList events;
    private ListView scheduleList;
    private ArrayList dailySchedule;
    private int month, dayOfMonth, dayOfWeek, year;
    private String date;
    private DBAdapter myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");


        //Get the current date to use for comparing with db
        month = Calendar.getInstance().get(Calendar.MONTH)+1;
        dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        dayOfWeek  = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        year = Calendar.getInstance().get(Calendar.YEAR);
        date = dateFormat(dayOfMonth) + "/" + dateFormat(month) + "/"+ year;
        Toast.makeText(getApplicationContext(), date,Toast.LENGTH_SHORT).show();

        dailySchedule = new ArrayList<ScheduleItems>();
        scheduleList = (ListView) findViewById(R.id.schedulelist);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mycal = new Intent(getApplicationContext(), MyCalendar.class);
                startActivity(mycal);

            }
        });

        //Pull from User DB to populate the events list
        openDB();
        readDB();


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

        //The options for the menu
        if(id == R.id.action_home){
            return true;
        } else  if (id == R.id.action_my_calendar) {
            Intent mycal = new Intent(this, MyCalendar.class);
            startActivity(mycal);
            finish();
            return true;
        } else if(id == R.id.action_friends){
            Intent friends = new Intent(this, FriendsList.class);
            startActivity(friends);
            finish();
            return true;
        } else if(id == R.id.action_meeting){
            return true;
        } else if(id == R.id.action_settings){
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

    public void clearDB(View v){
        Cursor c = myDb.getAllRows();
        String message = "";
        if (c.moveToFirst()) {
            do {
                // Process the data:
                int id = c.getInt(DBAdapter.COL_ROWID);
                String title = c.getString(DBAdapter.COL_TITLE);
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

            } while (c.moveToNext());
        }

        myDb.deleteAll();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Database Methods
    //This method reads the DB based on the current date and shows the events
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
                    dailySchedule.add(new ScheduleItems(title,location,stime, etime,date, false));
                } else {
                    dailySchedule.add(new ScheduleItems(title,location,stime, etime, dayToString(day), true));
                }

            } while (c.moveToNext());
        }

        if(dailySchedule.isEmpty()){
            ArrayList<String> temp = new ArrayList<String>();
            temp.add("There are no Events today");
            ArrayAdapter<String> tempAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, temp);
            scheduleList.setAdapter(tempAdapter);
            scheduleList.setBackgroundColor(Color.BLACK);
        } else {
            ScheduleItemAdapter adapter = new ScheduleItemAdapter(getApplicationContext(),dailySchedule);
            scheduleList.setAdapter(adapter);
        }
    }


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

    //Converts the day that is stored in db as int to the day of the week
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
