package com.example.hci.calendar;
/**
 * Created by Burrows on 2017-11-12.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBAdapter {

    /////////////////////////////////////////////////////////////////////

    // For logging:
    private static final String TAG = "DBAdapter";

    // DB Fields
    public static final String KEY_ROWID = "_id";
    public static final int COL_ROWID = 0;


    //	Constants & Data
    public static final String KEY_TITLE = "title";
    public static final String KEY_LOC = "location";
    public static final String KEY_STIME = "stime";
    public static final String KEY_ETIME = "etime";
    public static final String KEY_DATE = "date";
    public static final String KEY_REPEAT = "repeat";
    public static final String KEY_DAY = "day";


    // Update the field numbers here (0 = KEY_ROWID, 1=...)
    public static final int COL_TITLE = 1;
    public static final int COL_LOC = 2;
    public static final int COL_STIME = 3;
    public static final int COL_ETIME = 4;
    public static final int COL_DATE = 5;
    public static final int COL_REPEAT = 6;
    public static final int COL_DAY = 7;

    // [TO_DO_A4]
    // Update the ALL-KEYS string array
    public static final String[] ALL_KEYS = new String[] {KEY_ROWID,  KEY_TITLE, KEY_LOC, KEY_STIME, KEY_ETIME, KEY_DATE, KEY_REPEAT, KEY_DAY};

    // [TO_DO_A5]
    // DB info: db name and table name.
    public static final String DATABASE_NAME = "StudentDb";
    public static final String DATABASE_TABLE = "User_Sched";

    // [TO_DO_A6]
    // Track DB version
    public static final int DATABASE_VERSION = 1;


    // [TO_DO_A7]
    // DATABASE_CREATE SQL command
    private static final String DATABASE_CREATE_SQL =
            "create table " + DATABASE_TABLE
                    + " (" + KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_TITLE         + " text not null, "
                    + KEY_LOC         + " text not null, "
                    + KEY_STIME   + " text not null, "
                    + KEY_ETIME    + " text not null, "
                    + KEY_DATE   + " text, "
                    + KEY_REPEAT   + " boolean not null, "
                    + KEY_DAY   + " integer "
                    + ");";

    // Context of application who uses us.
    private final Context context;

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    // ==================
    //	Public methods:
    // ==================

    public DBAdapter(Context ctx) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // Open the database connection.
    public DBAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }

    // Add a new set of values to the database.
    public long insertRow(String title, String loc, String stime, String etime, String date, boolean repeat)
    {
        // [TO_DO_A8]
        // Update data in the row with new fields.
        // Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_LOC, loc);
        initialValues.put(KEY_STIME, stime);
        initialValues.put(KEY_ETIME, etime);
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_REPEAT, repeat);

        // Insert it into the database.
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    //
    public long insertRow(String title, String loc, String stime, String etime, boolean repeat, int day) {
        // [TO_DO_A8]
        // Update data in the row with new fields.
        // Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_LOC, loc);
        initialValues.put(KEY_STIME, stime);
        initialValues.put(KEY_ETIME, etime);
        initialValues.put(KEY_REPEAT, repeat);
        initialValues.put(KEY_DAY, day);

        // Insert it into the database.
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(DATABASE_TABLE, where, null) != 0;
    }

    // Delete all records
    public void deleteAll() {
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
        if (c.moveToFirst()) {
            do {
                deleteRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all rows in the database.
    public Cursor getAllRows() {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    //Get row by date
    public Cursor getRow(String date, int day) {
        String where = KEY_DATE + "=?  or " + KEY_DAY + "=?";
        String whereArgs[] = {date, day+""};
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, whereArgs, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateRow(long rowId, String title, String loc, String stime, String etime, String date, boolean repeat, int day) {
        String where = KEY_ROWID + "=" + rowId;

        // [TO_DO_A8]
        // Update data in the row with new fields.
        // Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_TITLE, title);
        newValues.put(KEY_LOC, loc);
        newValues.put(KEY_STIME, stime);
        newValues.put(KEY_ETIME, etime);
        newValues.put(KEY_DATE, date);
        newValues.put(KEY_REPEAT, repeat);
        newValues.put(KEY_DAY, day);


        // Insert it into the database.
        return db.update(DATABASE_TABLE, newValues, where, null) != 0;
    }



    // ==================
    //	Private Helper Classes:
    // ==================

    /**
     * Private class which handles database creation and upgrading.
     * Used to handle low-level database access.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            // Recreate new database:
            onCreate(_db);
        }
    }


}
