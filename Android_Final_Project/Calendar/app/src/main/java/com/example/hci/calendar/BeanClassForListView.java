package com.example.hci.calendar;


public class BeanClassForListView {


    private String title;
    private int clock_image;
    private String time;
    private int lolipop_image;
    private String task;
    private int profile_image;


    public BeanClassForListView(int clock_image, int lolipop_image, int profile_image, String task, String time, String title) {
        this.clock_image = clock_image;
        this.lolipop_image = lolipop_image;
        this.profile_image = profile_image;
        this.task = task;
        this.time = time;
        this.title = title;
    }

    public int getClock_image() {
        return clock_image;
    }

    public void setClock_image(int clock_image) {
        this.clock_image = clock_image;
    }

    public int getLolipop_image() {
        return lolipop_image;
    }

    public void setLolipop_image(int lolipop_image) {
        this.lolipop_image = lolipop_image;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}



