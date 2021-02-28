package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class UserCalendar {
    @JsonProperty("working_hours")
    private Slot slot;
    @JsonProperty("planned_meeting")
    private List<Slot> meetings = new ArrayList<>();

    public UserCalendar() {
    }

    public UserCalendar(Slot slot, List<Slot> meetings) {
        this.slot = slot;
        this.meetings = meetings;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
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
                "workingHours=" + slot +
                ", workingHoursList=" + meetings.toString() +
                '}';
    }
}
