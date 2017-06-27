package com.example.jack.coursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailedActivity extends AppCompatActivity {
    TextView lblName;
    TextView lblTime;
    TextView lblDate;
    TextView lblAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Bundle bundle=getIntent().getExtras();
        String VALUE_REC1=bundle.getString("KEY1");
        String VALUE_REC2=bundle.getString("KEY2");
        String VALUE_REC3=bundle.getString("KEY3");
        String VALUE_REC4=bundle.getString("KEY4");

        lblName = (TextView) findViewById(R.id.lblName);
        lblTime = (TextView) findViewById(R.id.lblTime);
        lblDate = (TextView) findViewById(R.id.lblDate);
        lblAddress = (TextView) findViewById(R.id.lblAddress);

        lblName.setText(VALUE_REC1);
        lblTime.setText(VALUE_REC2);
        lblDate.setText(VALUE_REC3);
        lblAddress.setText(VALUE_REC4);
    }
}
