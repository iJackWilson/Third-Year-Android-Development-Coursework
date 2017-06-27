package com.example.jack.coursework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEvent extends AppCompatActivity implements View.OnClickListener {

    Button btnCancel;
    Button btnSave;
    private EditText lblName;
    private EditText lblTime;
    private EditText lblDate;
    private EditText lblAddress;
    private CalendarEventDatabaseHelper calendarEventDatabaseHelper;
    String timeRegex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]";
    /* Time regex from https://stackoverflow.com/questions/7536755/regular-expression-for-matching-hhmm-time-format */
    String dateRegex = "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))";
    /* Date regex from https://forums.asp.net/t/1410702.aspx?Regular+expression+for+validating+Date+format+dd+MM+yyyy */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        /* Initialise the database our SQLiteOpenHelper object*/
        calendarEventDatabaseHelper = new CalendarEventDatabaseHelper(this);

        /* Get Views and Buttons from the resources. */
        lblName = (EditText)findViewById(R.id.lblName);
        lblTime = (EditText)findViewById(R.id.lblTime);
        lblDate = (EditText)findViewById(R.id.lblDate);
        lblAddress = (EditText)findViewById(R.id.lblAddress);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                //Some exception handling for empty input
                if (lblName.getText().length() < 1) {
                    Toast.makeText(getApplicationContext(), "Name cannot be empty!", Toast.LENGTH_LONG).show();
                }
                else if (lblAddress.getText().length() < 1) {
                    Toast.makeText(getApplicationContext(), "Address cannot be empty!", Toast.LENGTH_LONG).show();
                }
                else if (lblTime.getText().length() < 1) {
                    Toast.makeText(getApplicationContext(), "Time cannot be empty!", Toast.LENGTH_LONG).show();
                }
                else if (lblDate.getText().length() < 1) {
                    Toast.makeText(getApplicationContext(), "Date cannot be empty!", Toast.LENGTH_LONG).show();
                }
                //Exception handling that matches regular expressions for date and time
                else if (lblTime.getText().toString().matches(timeRegex) && lblDate.getText().toString().matches(dateRegex)) {
                    addToCalendar(v);
                    this.finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid time or date (format - hh:mm, dd/mm/yyyy)", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnCancel:
                this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    // called when button is pressed
    public void addToCalendar(View v){
        /* Get entered fields data. */
        String name = lblName.getText().toString();
        String time = lblTime.getText().toString();
        String date = lblDate.getText().toString();
        String address = lblAddress.getText().toString();

        /* Construct a calendar object and pass it to the helper for database insertion */
        calendarEventDatabaseHelper.addToCalendar(new Calendar(name, time, date, address));
    }
}
