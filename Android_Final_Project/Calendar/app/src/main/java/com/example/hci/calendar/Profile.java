package com.example.hci.calendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {



    private ImageView banar1;
    public String[] title = {"Meeting with Janet","Catch up with Brian","Lunch with Diane","Dinner with Marie","Meeting with James","Lunch with Smith"};
    public int[] banner1 = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f};
    public int[] clock = {R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1, R.drawable.ic_clock_1};
    public int[] lolipop = {R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point, R.drawable.ic_point};
    public String[] time = {"8 -10 am", "10 - 12 pm","12 - 2 pm", "2 - 4 pm", "4 - 6 pm", "6 - 8 pm"};
    public String[] task = {"Starbucks", "Mobile Project","Restaurant", "Bar & Grill", "Marketing ", "Fashion Show"};






    private ArrayList<BeanClassForListView> BeanClassForListview;
    private listViewAdapter listViewAdapter;

    private ArrayList<BeanClassForListView> BeanClassForListview1;
    private listViewAdapter listViewAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ListView listview = (ListView) findViewById(R.id.listview);



        BeanClassForListview = new ArrayList<>();


        for (int i = 0; i < title.length; i++) {
            BeanClassForListView beanClassForListView = new BeanClassForListView( clock[i],lolipop[i], banner1[i], task[i],time[i],title[i]);
            listview.setFocusable(false);
            BeanClassForListview.add(beanClassForListView);
        }
        listViewAdapter = new listViewAdapter(Profile.this, BeanClassForListview);
        listview.setAdapter(listViewAdapter);



        Context context;
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.b);

        ImageView imgv = (ImageView) findViewById(R.id.banar1);

        //  Bitmap bitmap = StringToBitMap(imgv);
        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        paint.setAntiAlias(true);
        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);

        imgv.setImageBitmap(circleBitmap);
    }
}
