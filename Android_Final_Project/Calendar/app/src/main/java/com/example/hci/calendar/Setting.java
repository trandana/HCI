package com.example.hci.calendar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

public class Setting extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    private RadioButton radio1;
    private RadioButton radio2;

    private Dialog myDialog;
    private Dialog dialogBloodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);

        radio1.setOnCheckedChangeListener(this);
        radio2.setOnCheckedChangeListener(this);


        Context context;
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        dialogBloodGroup = new Dialog(Setting.this);

        dialogBloodGroup.getWindow();

        if (isChecked) {

            String checked;
            if (buttonView.getId() == R.id.radio1) {

                radio2.setChecked(false);

                checked = radio1.getText().toString();
                dialogBloodGroup.dismiss();


            }

            if (buttonView.getId() == R.id.radio2)
            {
                radio1.setChecked(false);
                checked = radio2.getText().toString();
                dialogBloodGroup.dismiss();

            }
        }
    }

}