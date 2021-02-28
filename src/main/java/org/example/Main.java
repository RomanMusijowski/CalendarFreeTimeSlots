package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws JsonProcessingException, ParseException {
        SlotChecker checker = new SlotChecker();
        String cal1 ="{\"working_hours\":" +
                "{\"start\":\"09:00\"," +
                "\"end\":\"19:55\"}," +
                "\"planned_meeting\":" +
                "[{\"start\":\"09:00\",\"end\":\"10:30\"}," +
                "{\"start\":\"12:00\",\"end\":\"13:00\"}," +
                "{\"start\":\"16:00\",\"end\":\"18:00\"}]}";
        String cal2 ="{\"working_hours\":" +
                "{\"start\":\"10:00\"," +
                "\"end\":\"18:30\"}," +
                "\"planned_meeting\":" +
                "[{\"start\":\"10:00\",\"end\":\"11:30\"}," +
                "{\"start\":\"12:30\",\"end\":\"14:30\"}," +
                "{\"start\":\"14:30\",\"end\":\"15:00\"}," +
                "{\"start\":\"16:00\",\"end\":\"17:00\"}]}";

        String[][] slots = checker.findFreeSlots(cal1, cal2, "[00:30]");
        System.out.println(Arrays.deepToString(slots));
    }
}
