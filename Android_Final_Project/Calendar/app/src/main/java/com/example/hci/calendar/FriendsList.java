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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class FriendsList extends AppCompatActivity {
    private ListView friendsList;
    private ArrayList<String> friends;
    private ArrayAdapter<String> adapter;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    private final String SHARE = "PREFS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shared = getApplicationContext().getSharedPreferences(SHARE, Context.MODE_PRIVATE);
        editor = shared.edit();

        //Set up button to add friends
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        friends = new ArrayList<String>();

        //Pull friends from the friends DB
        friends.add("Bob Smith");
        friends.add("John Scott");
        friends.add("Laura Jackson");
        friends.add("Luke Morris");
        friends.add("Kelly Johnson");
        Collections.sort(friends);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
        friendsList = (ListView) findViewById(R.id.friendsList);
        friendsList.setAdapter(adapter);

        friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Open open the Specific friends schedule. Search using their name (friends.get(i))
                Toast.makeText(getApplicationContext(), friends.get(i),Toast.LENGTH_SHORT).show();
                editor.putString("friendsName", friends.get(i));
                editor.commit();
                Intent viewFriendCal = new Intent(getApplicationContext(), Friends_Calendar.class);
                startActivity(viewFriendCal);
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
            Intent mycal = new Intent(this, MyCalendar.class);
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

}
