package com.example.calendarproject;

import java.time.LocalDate;

public class Event {

    private String title, location, explain, hour, min;
    private LocalDate targetDateTime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public LocalDate getTargetDateTime() {
        return targetDateTime;
    }

    public void setTargetDateTime(LocalDate targetDateTime) {
        this.targetDateTime = targetDateTime;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public Event(String title, String location, String explain, LocalDate targetDateTime, String hour, String min) {
        this.title = title;
        this.location = location;
        this.explain = explain;
        this.targetDateTime = targetDateTime;
        this.hour = hour;
        this.min = min;
    }
}
