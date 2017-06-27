package com.example.jack.coursework;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class Calendar {
    public String name = "";
    public String time = "";
    public String date = "";
    public String address = "";

    Calendar(String n, String t, String d, String a){
        name = n;
        time = t;
        date = d;
        address = a;
    }

    public Calendar(MainActivity mainActivity, ArrayList<Calendar> calendars) {
    }
}
