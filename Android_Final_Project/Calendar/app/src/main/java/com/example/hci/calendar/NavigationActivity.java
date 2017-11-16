package com.example.hci.calendar;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class NavigationActivity extends ActionBarActivity  {

    private Toolbar toolbar;
    private RelativeLayout relativeClose;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationAdapter adapter;
    private ListView list;
    private ArrayList<NavigationItems> items;


    private String[] NAME = {"Calendar", "Today", "Friends",  "Profile/Settings", "Logout"};

    Fragment newFragment;
    android.support.v4.app.FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setToolbar();

        relativeClose = (RelativeLayout)findViewById(R.id.relativeClose);

        transaction = getSupportFragmentManager().beginTransaction();
        newFragment = new NavigationKnock();
        transaction.replace(R.id.frame_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();


            list = (ListView) findViewById(R.id.list);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

            items = new ArrayList<NavigationItems>();

            for (int i = 0; i < NAME.length; i++) {

                NavigationItems nav = new NavigationItems(NAME[i] );
                items.add(nav);
            }

            adapter = new NavigationAdapter(NavigationActivity.this, items);
            list.setAdapter(adapter);

        list.setSelection(0);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    selectItem(position);
                    drawerLayout.closeDrawer(relativeClose);
                }


            });

            initDrawer();
    }
    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,toolbar , R.string.drawer_open, R.string.drawer_close
        ) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);



            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }



    private void selectItem(int position) {

        transaction = getSupportFragmentManager().beginTransaction();

        switch (position)
        {
            //                newFragment = new NavigationKnock();
//                transaction.replace(R.id.frame_container, newFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                break;
            case 0:
                Intent openCalendar = new Intent(this, CalendarActivity.class);
                startActivity(openCalendar);
                break;
            case 1:
                Intent openToday = new Intent(this, NavigationKnock.class);
                startActivity(openToday);
                break;
            case 2:
                Intent openFriends = new Intent(this, FriendsList.class);
                startActivity(openFriends);
                break;
            case 3:
                Intent openSettings = new Intent(this, Setting.class);
                startActivity(openSettings);
                break;
            case 4:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                break;


        }
    }

    private void ReferFriend(){

        Intent shareIntent =
                new Intent(Intent.ACTION_SEND);

        //set the type
        shareIntent.setType("text/plain");

        //add a subject
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                "");

        //build the body of the message to be shared
        String shareMessage = "";

        //add the message
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                shareMessage);

        //start the chooser for sharing
        startActivity(Intent.createChooser(shareIntent,
                ""));
    }


    private void setToolbar(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("");

    }
}
