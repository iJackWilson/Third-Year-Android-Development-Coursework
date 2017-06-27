package com.example.jack.coursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class CalendarEventDatabaseHelper extends SQLiteOpenHelper {

    //Initialising variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB";
    private static final String CALENDAR_TABLE_NAME = "events";
    private static final String[] COLUMN_NAMES = {"Name", "Time", "Date", "Address"};
    //String to create table + columns
    private static final String CONTACTS_TABLE_CREATE =
            "CREATE TABLE " + CALENDAR_TABLE_NAME + " (" +
                    COLUMN_NAMES[0] + " TEXT, " +
                    COLUMN_NAMES[1] + " TEXT, " +
                    COLUMN_NAMES[2] + " TEXT, " +
                    COLUMN_NAMES[3] + " TEXT);";

    CalendarEventDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates database
        // Execute above SQL query
        db.execSQL(CONTACTS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Empty
    }

    public void addToCalendar(Calendar c){
        //Put details into variables for adding to database
        ContentValues row = new ContentValues();
        row.put(this.COLUMN_NAMES[0], c.name);
        row.put(this.COLUMN_NAMES[1], c.time);
        row.put(this.COLUMN_NAMES[2], c.date);
        row.put(this.COLUMN_NAMES[3], c.address);

        // Get writable database and add a new row to the database
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(CALENDAR_TABLE_NAME, null, row);
        db.close();
    }

    public ArrayList<Calendar> getEventsList(){
        //Get the readable database
        SQLiteDatabase db = this.getReadableDatabase();

        //Select everything from database
        Cursor result = db.query(CALENDAR_TABLE_NAME, COLUMN_NAMES, null, null, null, null, null, null);

        //Convert above to an arraylist
        ArrayList<Calendar> cEvents = new ArrayList<Calendar>();

        for(int i = 0; i < result.getCount(); i++){
            result.moveToPosition(i);
            //Create an event object with using data from name, time, date and address columns. Add it to list. */
            cEvents.add(new Calendar(result.getString(0), result.getString(1), result.getString(2), result.getString(3)));
        }

        return cEvents;
    }
}
