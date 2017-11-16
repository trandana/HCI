package com.example.hci.calendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Friends_Calendar extends AppCompatActivity {
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    private final String SHARE = "PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends__calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        shared = getApplicationContext().getSharedPreferences(SHARE, Context.MODE_PRIVATE);
        editor = shared.edit();
        String pageTitle = shared.getString("friendsName","");
        if(pageTitle.equals(""))
            pageTitle = "Error";

        //Use getActionBar().setTitle("Hello world App"); to set the title to friends name (retrieved from shared prefs)
        getSupportActionBar().setTitle(pageTitle);

        //Set up button 
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
            return true;
        } else if (id == R.id.action_my_calendar){
            Intent mycal = new Intent(this, com.example.hci.calendar.MyCalendar.class);
            startActivity(mycal);
            finish();
            return true;
        } else if(id == R.id.action_friends){
            return true;
        } else if(id == R.id.action_meeting){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        editor.remove("friendsName");
        editor.commit();
        super.onBackPressed();
    }
}
