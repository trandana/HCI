package com.example.hci.calendar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText title, st, et, d, m, y;
    ListView display;
    Spinner days;
    CheckBox oneTime;
    ArrayList<String> info;
    String daysOfWeek[];
    DBAdapter myDb;
    ArrayAdapter<String> adapter, dow;

    LinearLayout oneTimeOptions, repeatOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        oneTimeOptions = (LinearLayout) findViewById(R.id.onetimeset);
        oneTimeOptions.setVisibility(View.INVISIBLE);

        repeatOptions = (LinearLayout) findViewById(R.id.repeatset);


        oneTime = (CheckBox) findViewById(R.id.onetime);
        days = (Spinner) findViewById(R.id.dow);
        title = (EditText) findViewById(R.id.title);
        st = (EditText) findViewById(R.id.start);
        et = (EditText) findViewById(R.id.end);
        d = (EditText) findViewById(R.id.day);
        m = (EditText) findViewById(R.id.month);
        y = (EditText) findViewById(R.id.year);

        display = (ListView) findViewById(R.id.display);
        info = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);


        daysOfWeek = getResources().getStringArray(R.array.days);
        dow = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, daysOfWeek);

        openDB();

        oneTimeOptions.setVisibility(View.GONE);
        repeatOptions.setVisibility(View.VISIBLE);

        days.setAdapter(dow);

        oneTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    oneTimeOptions.setVisibility(View.VISIBLE);
                    repeatOptions.setVisibility(View.GONE);
                } else {
                    oneTimeOptions.setVisibility(View.GONE);
                    repeatOptions.setVisibility(View.VISIBLE);

                }
            }
        });

        Cursor cursor = myDb.getAllRows();
        displayRecordSet(cursor);

    }

    public void addEntry(View v) {
        long newId = -1;
        if (oneTime.isChecked()) {
            if (title.getText().toString().isEmpty() || st.getText().toString().isEmpty() || et.getText().toString().isEmpty()
                    || d.getText().toString().isEmpty() || m.getText().toString().isEmpty() || y.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                String date = d.getText().toString() + "/" + m.getText().toString() + "/" + y.getText().toString();
                //newId = myDb.insertRow(title.getText().toString(), st.getText().toString(), et.getText().toString(), date,
                //       oneTime.isChecked());

                if (newId != -1) {
                    Toast.makeText(getBaseContext(), "Entry Added!", Toast.LENGTH_SHORT).show();
                    Cursor cursor = myDb.getAllRows();
                    displayRecordSet(cursor);
                } else {
                    Toast.makeText(this, "Error! Entry not added", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            if (title.getText().toString().isEmpty() || st.getText().toString().isEmpty() || et.getText().toString().isEmpty() || days.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
              //  newId = myDb.insertRow(title.getText().toString(), st.getText().toString(), et.getText().toString(),
                 //      oneTime.isChecked(),days.getSelectedItemPosition());

                if (newId != -1) {
                    Toast.makeText(getBaseContext(), "Entry Added!", Toast.LENGTH_SHORT).show();
                    Cursor cursor = myDb.getAllRows();
                    displayRecordSet(cursor);
                } else {
                    Toast.makeText(this, "Error! Entry not added", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


    public void queryEmail(View v){
            Cursor cursor = myDb.getAllRows();
            displayRecordSet(cursor);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }


    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }
    private void closeDB() {
        myDb.close();
    }

    public void onClick_ClearAll(View v) {
        myDb.deleteAll();
        myDb.deleteAll();
        info.clear();
        title.setText("");
        st.setText("");
        et.setText("");
        d.setText("");
        m.setText("");
        y.setText("");
        display.setAdapter(adapter);
    }

    // Display an entire recordset to the screen.
    private void displayRecordSet(Cursor cursor) {
        String message = "";

        info.clear();
        // Reset cursor to start, checking to see if there's data:
        if (cursor.moveToFirst()) {
            do {
                // Process the data:
                int id = cursor.getInt(DBAdapter.COL_ROWID);
                String title = cursor.getString(DBAdapter.COL_TITLE);
                String stime = cursor.getString(DBAdapter.COL_STIME);
                String etime = cursor.getString(DBAdapter.COL_ETIME);

                String date = cursor.getString(DBAdapter.COL_DATE);

                int day = cursor.getInt(DBAdapter.COL_DAY);

                // Append data to the message:
                message += "id=" + id
                        + ", title =" + title
                        + ", Date =" + date
                        + ", From =" + stime
                        + ", To=" + etime
                        + "\n";
                if(date != null) {
                    info.add(date + "  \n " + title + " \nFrom " + stime + " To " + etime);
                } else {
                    String d = daysOfWeek[day];
                    info.add(d + "'s \n" + title + " \nFrom " + stime + " To " + etime);
                }

            } while (cursor.moveToNext());
        }
        if(info.isEmpty()){
            info.add("No data found.");
        }
        display.setAdapter(adapter);

        // Close the cursor to avoid a resource leak.
        cursor.close();

    }

}
