package com.example.jack.coursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Declaring variables
    Button btnAdd;
    private CalendarEventDatabaseHelper calendarEventDatabaseHelper;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        //Listener for click (to open DetailedActivity)
        list = (ListView) findViewById(R.id.list_events);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar c = (Calendar)parent.getItemAtPosition(position);

                //Open activity_detailed.xml and DetailedActivity.java
                Intent j = new Intent(MainActivity.this, DetailedActivity.class);
                j.putExtra("KEY1",c.name);
                j.putExtra("KEY2",c.time);
                j.putExtra("KEY3",c.date);
                j.putExtra("KEY4",c.address);

                startActivity(j);
            }
        });

        //Listener for long click (to open MapsActivity)
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar c = (Calendar)parent.getItemAtPosition(position);

                //Open Maps Activity
                Intent k = new Intent(MainActivity.this, MapsActivity.class);
                k.putExtra("KEY1",c.name);
                k.putExtra("KEY4",c.address);
                startActivity(k);
                return true;
            }
        });

        calendarEventDatabaseHelper = new CalendarEventDatabaseHelper(this);

        //Call populateList method
        populateListView();

        //Display some usage instructions
        Toast onAppStart = Toast.makeText(MainActivity.this,"Press activity to view details, long press to view on map",Toast.LENGTH_LONG);
        onAppStart.show();
    }

    private void populateListView(){

        //Get contacts list from helper
        ArrayList<Calendar> calendars = calendarEventDatabaseHelper.getEventsList();

        //Initialise adapter
        CalendarAdapter adapter = new CalendarAdapter (this, calendars);

        list.setAdapter(adapter);
    }

    //Refreshes ListView when coming back to activity (e.g. after adding new event)
    public void onResume() {
        super.onResume();
        populateListView();
    }

    //onClick listener for button to open AddEvent activity
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                //Launch activity called AddEvent when btnAdd is pressed
                Intent i = new Intent(MainActivity.this, AddEvent.class);
                startActivity(i);
                break;
        }
    }
}
