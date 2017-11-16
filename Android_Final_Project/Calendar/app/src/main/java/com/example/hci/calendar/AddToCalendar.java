package com.example.hci.calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddToCalendar extends AppCompatActivity {
    private CheckBox onetimecb, sun, mon, tues, wed, thur, fri, sat;
    private LinearLayout dayChoices;
    private TextView datelabel;
    private EditText date, to, from, title, location;
    private ListView eventlist;
    private DBAdapter myDb;
    private ArrayList eventAdded, days;
    private ScheduleItemAdapter adapter;
    private Button add, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Array to hold the items added
        eventAdded = new ArrayList<ScheduleItems>();
        days = new ArrayList<String>();
        eventlist = (ListView) findViewById(R.id.eventslist);


        add = (Button) findViewById(R.id.addevent);
        clear = (Button) findViewById(R.id.clear);

        onetimecb = (CheckBox) findViewById(R.id.onetimecd);

        mon = (CheckBox) findViewById(R.id.mon);
        mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(!days.contains(compoundButton.getTag().toString())){
                        days.add(compoundButton.getTag().toString());
                    }
                } else {
                    if(days.contains(compoundButton.getTag().toString())){
                        days.remove(compoundButton.getTag().toString());
                    }
                }
            }
        });
        tues = (CheckBox) findViewById(R.id.tue);
        tues.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(!days.contains(compoundButton.getTag().toString())){
                        days.add(compoundButton.getTag().toString());
                    }
                } else {
                    if(days.contains(compoundButton.getTag().toString())){
                        days.remove(compoundButton.getTag().toString());
                    }
                }
            }
        });
        wed = (CheckBox) findViewById(R.id.wed);
        wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(!days.contains(compoundButton.getTag().toString())){
                        days.add(compoundButton.getTag().toString());
                    }
                } else {
                    if(days.contains(compoundButton.getTag().toString())){
                        days.remove(compoundButton.getTag().toString());
                    }
                }
            }
        });
        thur = (CheckBox) findViewById(R.id.thur);
        thur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(!days.contains(compoundButton.getTag().toString())){
                        days.add(compoundButton.getTag().toString());
                    }
                } else {
                    if(days.contains(compoundButton.getTag().toString())){
                        days.remove(compoundButton.getTag().toString());
                    }
                }
            }
        });

        fri = (CheckBox) findViewById(R.id.fri);
        fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(!days.contains(compoundButton.getTag().toString())){
                        days.add(compoundButton.getTag().toString());
                    }
                } else {
                    if(days.contains(compoundButton.getTag().toString())){
                        days.remove(compoundButton.getTag().toString());
                    }
                }
            }
        });
        sat = (CheckBox) findViewById(R.id.sat);
        sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(!days.contains(compoundButton.getTag().toString())){
                        days.add(compoundButton.getTag().toString());
                    }
                } else {
                    if(days.contains(compoundButton.getTag().toString())){
                        days.remove(compoundButton.getTag().toString());
                    }
                }
            }
        });
        sun = (CheckBox) findViewById(R.id.sun);
        sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(!days.contains(compoundButton.getTag().toString())){
                        days.add(compoundButton.getTag().toString());
                    }
                } else {
                    if(days.contains(compoundButton.getTag().toString())){
                        days.remove(compoundButton.getTag().toString());
                    }
                }
            }
        });

        date = (EditText) findViewById(R.id.date);
        to = (EditText) findViewById(R.id.to);
        from = (EditText) findViewById(R.id.from);
        title = (EditText) findViewById(R.id.event);
        location = (EditText)findViewById(R.id.loc);

        datelabel = (TextView) findViewById(R.id.datelabel);

        dayChoices = (LinearLayout) findViewById(R.id.dayselection);
        date.setVisibility(View.GONE);
        datelabel.setVisibility(View.GONE);


        //Controls what the users options are
        onetimecb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    dayChoices.setVisibility(View.GONE);
                    date.setVisibility(View.VISIBLE);
                    datelabel.setVisibility(View.VISIBLE);
                } else {
                    dayChoices.setVisibility(View.VISIBLE);
                    date.setVisibility(View.GONE);
                    datelabel.setVisibility(View.GONE);
                }

            }
        });
        //Open the database. All db functions are at the bottom
        openDB();
        //Save to the db
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!eventAdded.isEmpty()){
                    for(int i = 0; i < eventAdded.size();i++){
                        ScheduleItems event = (ScheduleItems) eventAdded.get(i);
                        if(event.getSingle()){
                            myDb.insertRow(event.getTitle(), event.getLocation(), event.getStartTime(),event.getEndTime(), event.getDate(),event.getSingle());
                        } else {
                            myDb.insertRow(event.getTitle(), event.getLocation(), event.getStartTime(), event.getEndTime(), event.getSingle(), dayToInt(event.getDate()));
                        }
                    }
                    Snackbar.make(view, "Events Added to Calendar", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Please create events first", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    //Add event to the list to be displayed to the user. (not saved to the db)
    public void addEvent(View v){
        if(title.getText().toString().isEmpty() || to.getText().toString().isEmpty() || from.getText().toString().isEmpty() || location.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        } else if(onetimecb.isChecked() && date.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a date.", Toast.LENGTH_SHORT).show();
        } else if(!onetimecb.isChecked() && (!mon.isChecked() && !tues.isChecked() && !wed.isChecked() &&
                !thur.isChecked() && !fri.isChecked() && !sat.isChecked() && !sun.isChecked())){
            Toast.makeText(getApplicationContext(), !onetimecb.isChecked() + "Please select a day of the week.", Toast.LENGTH_SHORT).show();
        } else {
            if(onetimecb.isChecked()){
                Toast.makeText(getApplicationContext(), date.getText().toString(),Toast.LENGTH_SHORT).show();
                eventAdded.add(new ScheduleItems(title.getText().toString(), location.getText().toString(), to.getText().toString(), from.getText().toString(), date.getText().toString(), true));
            } else {
                for(int i = 0; i < days.size();i++) {
                    eventAdded.add(new ScheduleItems(title.getText().toString(), location.getText().toString(), to.getText().toString(), from.getText().toString(), days.get(i).toString(), false));
                }
            }
            adapter = new ScheduleItemAdapter(getApplicationContext(),eventAdded);
            eventlist.setAdapter(adapter);

        }
    }

//Clears everything that the user entered
    public void clear(View v){
        mon.setChecked(false);
        tues.setChecked(false);
        wed.setChecked(false);
        thur.setChecked(false);
        fri.setChecked(false);
        sat.setChecked(false);
        sun.setChecked(false);
        title.setText("");
        location.setText("");
        to.setText("");
        from.setText("");
        onetimecb.setChecked(false);
        days.clear();
        eventAdded.clear();
        adapter = new ScheduleItemAdapter(getApplicationContext(),eventAdded);
        eventlist.setAdapter(adapter);
    }

    public int dayToInt(String s){
        int day = 0;
        switch(s){
            case "Sunday":
                day = 1;
                break;
            case "Monday":
                day = 2;
                break;
            case "Tuesday":
                day = 3;
                break;
            case "Wednesday":
                day = 4;
                break;
            case "Thursday":
                day = 5;
                break;
            case "Friday":
                day = 6;
                break;
            case "Saturday":
                day = 7;
                break;
        }
        return day;
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    //Methods for dialog boxes to control user input of date and time
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int min = Calendar.getInstance().get(Calendar.MINUTE);
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        //from time setter
        if(id == 111){
            return new TimePickerDialog(this,myFromTimeListener,hour,min, false);
        }
        //to time setter
        if(id == 222) {
            return new TimePickerDialog(this,myToTimeListener,hour,min, false);
        }

        return null;
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            date.setText(dateFormat(day) + "/" +dateFormat(month+1) + "/" + year);
        }
    };

    private TimePickerDialog.OnTimeSetListener myFromTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            String amOrPm = "am";
            if(i > 12){
                i -= 12;
                amOrPm = "pm";
            }
            if(i == 0) {
                i = 12;
                amOrPm = "am";
            }
            if(i == 12)
                amOrPm = "pm";
            from.setText(i + ":" + i1+ " " + amOrPm);
        }
    };

    private TimePickerDialog.OnTimeSetListener myToTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            String amOrPm = "am";
            if(i > 12){
                i -= 12;
                amOrPm = "pm";
            }
            if(i == 0) {
                i = 12;
                amOrPm = "am";
            }
            to.setText(i + ":" + i1 + " " + amOrPm);
        }
    };


    //Onclick method for the date edit text
    public void dateClicked(View v) {
        showDialog(999);
    }

    //Onclick method for the from time edit text
    public void fromTimeClicked(View v) {
        showDialog(111);
    }

    //Onclick method for the to time edit text
    public void toTimeClicked(View v) {
        showDialog(222);
    }

    ///////////////////////////////////////////////////////////////////////////////////////


    //Methods for the navigation bar
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
        }

        return super.onOptionsItemSelected(item);
    }

    //Helper function to make date format dd/mm/yyyy
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






}
