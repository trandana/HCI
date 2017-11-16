package com.example.hci.calendar;

/**
 * Created by Burrows on 2017-11-11.
 */

public class ScheduleItems {
    private String title, startTime, endTime, location, date;
    boolean single;


    public ScheduleItems(String ti, String loc, String st, String end, String d, boolean si){
        title = ti;
        location = loc;
        startTime = st;
        endTime = end;
        date = d;
        single = si;

    }

    public String getTitle(){
        return title;
    }
    public String getLocation(){
        return location;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }
    public String getDate(){
        return date;
    }
    public boolean getSingle(){
        return single;
    }

}