package com.example.sid.accontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Scheduler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        this.setTitle("Scheduler");
        // Spinner click listener


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("12 PM");
        categories.add("8 PM");
        categories.add("5 PM");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
        spinner3.setAdapter(dataAdapter);

    }
}
