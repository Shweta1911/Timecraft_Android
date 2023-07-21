package com.example.timetable1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spin;
    spinSelect obj=new spinSelect();
    RecyclerView Rview;
    String text;

    String[] Days = {"Select a Day","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    ArrayList<TimetableModal> courseModalArrayList = null;

    DbConnect dbHandler = new DbConnect(this);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv;
        tv=findViewById(R.id.DayDisplay);
        tv.setText(obj.Day);
        spin = findViewById(R.id.spinner3);
        ArrayAdapter ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Days);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);
        spin.setOnItemSelectedListener(this);

        courseModalArrayList=dbHandler.readCourses(obj.Day);
        System.out.println(obj.Day);
        Adaptor adaptor = new Adaptor(courseModalArrayList, MainActivity.this);
        Rview = findViewById(R.id.Rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        Rview.setLayoutManager(linearLayoutManager);
        Rview.setAdapter(adaptor);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.plus:
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
                return true;
            case R.id.subrem:
                Intent intent1 = new Intent(this, MainActivity3.class);
                startActivityForResult(intent1, 8);
                return true;
            case R.id.refresh:
                finish();
                startActivity(getIntent());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       // ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) adapterView.getChildAt(0)).setTextSize(20);

        switch(i){
            case 1:
                obj.selectDay("Monday");
                break;
            case 2:
                obj.selectDay("Tuesday");
                break;
            case 3:
                obj.selectDay("Wednesday");
                break;
            case 4:
                obj.selectDay("Thursday");
                break;
            case 5:
                obj.selectDay("Friday");
                break;
            case 6:
                obj.selectDay("Saturday");
                break;
            case 7:
                obj.selectDay("Sunday");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}