package com.example.timetable1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity2 extends AppCompatActivity {

EditText Sub,Fac_name;
Spinner spinner,spinner_hr,spinner_min,spinner_typ;
Button b,submit;
String Time,Hour,Min,Lec_type;
String[] Days = {"Select a Day","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
String[] Hours={"Hour","1","2","3","4","5","6","7","8","9","10","11","12"};
String[] Minutes={"Min","00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35"
        ,"36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
String [] Type_s={"Theory","Practical"};
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       // Time=findViewById(R.id.editTextTime);
        Sub=findViewById(R.id.EnterSub);
       // Day=findViewById(R.id.editTextDay);
        b=findViewById(R.id.add);
        spinner=findViewById(R.id.spinner);
        spinner_hr=findViewById(R.id.spin_hour);
        spinner_min=findViewById(R.id.spin_min);
        spinner_typ=findViewById(R.id.spin_type);
        submit=findViewById(R.id.submit);
        Fac_name=findViewById(R.id.FacultyName);

        ArrayAdapter ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Days);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);

        ArrayAdapter ad_type = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Type_s);
        ad_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typ.setAdapter(ad_type);

        ArrayAdapter ad_hr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Hours);
        ad_hr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad_hr.notifyDataSetChanged();
        spinner_hr.setAdapter(ad_hr);
        spinner_hr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              //  ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(20);
                Object selectedItem = adapterView.getItemAtPosition(i);
                if (selectedItem != null) {
                    Hour = selectedItem.toString();
                    Time=Hour;
                    Log.d("Selected item",Hour);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter ad_min = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Minutes);
        ad_min.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad_min.notifyDataSetChanged();
        spinner_min.setAdapter(ad_min);
        spinner_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(20);
                Object selectedItem = adapterView.getItemAtPosition(i);
                if (selectedItem != null) {
                    Min = selectedItem.toString();
                    Time=Time+":"+Min;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              //  ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(20);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_typ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(20);
                Object selectedItem = adapterView.getItemAtPosition(i);
                if (selectedItem != null) {
                    Lec_type = selectedItem.toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DbConnect dbHandler = new DbConnect(MainActivity2.this);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Subject=Sub.getText().toString();
                String day1 = spinner.getSelectedItem().toString();
                String F_name=Fac_name.getText().toString();
                dbHandler.insertContact(Subject,Time,day1,Lec_type,F_name);

                Toast.makeText(MainActivity2.this, "Course has been added.", Toast.LENGTH_SHORT).show();

                Time="";
                Sub.setText("");


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}