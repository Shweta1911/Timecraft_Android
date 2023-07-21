package com.example.timetable1;
public class TimetableModal {

    // variables for our coursename,
    // description, tracks and duration, id.
   private String Subject,Time,Day,Type,Faculty;

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    // constructor
    public TimetableModal(String Subject_,
                          String Time_,
                          String Day_,
                          String Type_,
                          String Faculty_)
    {
        this.Subject = Subject_;
        this.Time = Time_;
        this.Day=Day_;
        this.Type=Type_;
        this.Faculty=Faculty_;
    }
}