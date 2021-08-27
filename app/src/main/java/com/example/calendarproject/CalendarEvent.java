package com.example.calendarproject;

import java.util.ArrayList;





public class CalendarEvent {

    private String day;
    private ArrayList<String> titleArray;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<String> getTitleArray() {
        return titleArray;
    }

    public void setTitleArray(ArrayList<String> title) {
        this.titleArray = title;
    }

    public CalendarEvent(String day, ArrayList<String> titleArray) {
        this.day = day;
        this.titleArray = (ArrayList<String>) titleArray.clone();
    }
}


