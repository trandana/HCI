package com.example.hci.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

public class StickyListviewActivity extends AppCompatActivity {

    private RecyclerView list1;
    private RecyclerView list2;

    private ImageView banar1;
    public String[] title = {"Meeting with Janet","Catch up with Brian","Lunch with Diane","Dinner with Marie","Meeting with James","Lunch with Smith","Lunch with Diane","Dinner with Marie"};
    public int[] banner1 = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.b, R.drawable.c};
    public int[] clock = {R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1};
    public int[] lolipop = {R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point};
    public String[] time = {"8 -10 am", "10 - 12 pm","12 - 2 pm", "2 - 4 pm", "4 - 6 pm", "6 - 8 pm", "6 - 8 pm", "6 - 8 pm"};
    public String[] task = {"Starbucks", "Mobile Project","Restaurant", "Bar & Grill", "Marketing Website", "Fashion Show","Restaurant","Restaurant"};




    private ImageView banar2;
    public String[] title1 = {"Meeting with Janet","Catch up with Brian","Lunch with Diane","Dinner with Marie","Meeting with James","Lunch with Smith","Lunch with Diane","Dinner with Marie"};
    public int[] banner12 = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.b, R.drawable.c};
    public int[] clock1 = {R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1};
    public int[] lolipop1 = {R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point};
    public String[] time1 = {"8 -10 am", "10 - 12 pm","12 - 2 pm", "2 - 4 pm", "4 - 6 pm", "6 - 8 pm", "6 - 8 pm", "6 - 8 pm"};
    public String[] task1 = {"Starbucks", "Mobile Project","Restaurant", "Bar & Grill", "Marketing Website", "Fashion Show","Restaurant","Restaurant"};




    private ArrayList<BeanClassForListView> beanClassArrayList;
    private NavigationAdapter navigationAdapter;
    private NavigationAdapter navigationAdapter2;

    MyRecycleAdapter mAdapter1;
    MyRecycleAdapter mAdapter2;



    private ArrayList<BeanClassForListView> beanClassArrayList2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sticky_listview_activity);

        beanClassArrayList= new ArrayList<>();


        for (int i = 0; i < title.length; i++) {
            BeanClassForListView beanClassForListView = new BeanClassForListView( clock[i],lolipop[i], banner1[i], task[i],time[i],title[i]);

            beanClassArrayList.add(beanClassForListView);
        }



        beanClassArrayList2 = new ArrayList<>();

        for (int i = 0; i < title.length; i++) {
            BeanClassForListView beanClassForListView = new BeanClassForListView( clock[i],lolipop[i], banner1[i], task[i],time[i],title[i]);

            beanClassArrayList2.add(beanClassForListView);
        }

        list1 = (RecyclerView) findViewById(R.id.list1);
        list2 = (RecyclerView)findViewById(R.id.list2);



        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(StickyListviewActivity.this, LinearLayoutManager.VERTICAL, false);
        list1.setLayoutManager(mLayoutManager1);




        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(StickyListviewActivity.this, LinearLayoutManager.VERTICAL, false);

        list1.setLayoutManager(mLayoutManager);
        list1.setNestedScrollingEnabled(false);

    //    list1.setHasFixedSize(false);


        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(StickyListviewActivity.this, LinearLayoutManager.VERTICAL, false);

        list2.setLayoutManager(mLayoutManager2);

        mAdapter1 = new MyRecycleAdapter(beanClassArrayList);
        list1.setAdapter(mAdapter1);

        mAdapter2 = new MyRecycleAdapter(beanClassArrayList2);
        list2.setAdapter(mAdapter2);

        list2.setNestedScrollingEnabled(false);






//                        recyclerViews[j].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                                LinearLayout.LayoutParams.WRAP_CONTENT));

    //    list2.setNestedScrollingEnabled(false);

//        list2.setHasFixedSize(false);




    }

}
