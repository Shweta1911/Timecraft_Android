package com.example.timetable1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Intent.getIntent;
import static android.support.v4.content.ContextCompat.startActivity;

public class DbConnect extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TimeTable.db";

    public DbConnect(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Timetable " + "(Subject text,Time text,Day text,Type text,F_Name text,PRIMARY KEY(Day,Time))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Timetable");
        onCreate(db);
    }

    public void insertContact (String Subj, String Tim, String Da,String typ,String f_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Subject", Subj);
        contentValues.put("Time", Tim);
        contentValues.put("Day", Da);
        contentValues.put("Type", typ);
        contentValues.put("F_Name", f_name);

        db.insert("Timetable", null, contentValues);
        db.close();
    }

    public void deleteCourse(String courseName) {
        spinSelect obj=new spinSelect();
        String day=obj.Day;
        Log.d("Subject",courseName);
        Log.d("Day",day);
        SQLiteDatabase db1 = this.getWritableDatabase();


        db1.delete("Timetable", "Subject=? AND Day=?", new String[]{courseName, day});
        db1.close();

    }

    public ArrayList<TimetableModal> readCourses(String str) {

        SQLiteDatabase db = this.getReadableDatabase();

        // creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("select * from Timetable where Day= '" + str + "' ORDER BY Time", null);


        ArrayList<TimetableModal> courseModalArrayList = new ArrayList<>();


        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new TimetableModal(cursorCourses.getString(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),cursorCourses.getString(4)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }
}
