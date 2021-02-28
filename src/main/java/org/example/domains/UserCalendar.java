package org.example.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class UserCalendar {
    @JsonProperty("working_hours")
    private Slot workingHours;
    @JsonProperty("planned_meeting")
    private List<Slot> meetings = new ArrayList<>();

    public UserCalendar() {
    }

    public UserCalendar(Slot workingHours, List<Slot> meetings) {
        this.workingHours = workingHours;
        this.meetings = meetings;
    }

    public Slot getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Slot workingHours) {
        this.workingHours = workingHours;
    }

    public List<Slot> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Slot> meetings) {
        this.meetings = meetings;
    }

    @Override
    public String toString() {
        return "org.example.Calendar{" +
                "workingHours=" + workingHours +
                ", workingHoursList=" + meetings.toString() +
                '}';
    }
}
