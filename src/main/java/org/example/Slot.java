package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Slot {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone="Europe/Budapest")
    private Date start;
    @JsonFormat(pattern = "HH:mm", timezone="Europe/Budapest")
    private Date end;

    public Slot() {
    }

    public Slot(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "WorkingHours{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
