package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.ParseException;
import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SlotCheckerTest {

    private final SlotChecker checker = new SlotChecker();

    private String cal1;
    private String cal2;
    private String cal3;

    private String target1;
    private String target2;
    private String target3;

    @BeforeAll
    void init(){

        target1 = "[00:00]";
        target2 = "[00:30]";
        target3 = "[02:00]";

        cal1 ="{\"working_hours\":" +
                "{\"start\":\"09:00\"," +
                "\"end\":\"19:55\"}," +
                "\"planned_meeting\":" +
                "[{\"start\":\"09:00\",\"end\":\"10:30\"}," +
                "{\"start\":\"12:00\",\"end\":\"13:00\"}," +
                "{\"start\":\"16:00\",\"end\":\"18:00\"}]}";
        cal2 ="{\"working_hours\":" +
                "{\"start\":\"10:00\"," +
                "\"end\":\"18:30\"}," +
                "\"planned_meeting\":" +
                "[{\"start\":\"10:00\",\"end\":\"11:30\"}," +
                "{\"start\":\"12:30\",\"end\":\"14:30\"}," +
                "{\"start\":\"14:30\",\"end\":\"15:00\"}," +
                "{\"start\":\"16:00\",\"end\":\"17:00\"}]}";
        cal3 ="{\"working_hours\":" +
                "{\"start\":\"09:00\"," +
                "\"end\":\"19:55\"}," +
                "\"planned_meeting\":[]}";
    }

    @Test
    void whenFindFreeSlots_thenThrowException() {
        assertThrows(DateTimeException.class, ()-> checker.findFreeSlots(cal1, cal2, target1));
    }

    @Test
    void whenFindFreeSlots_thenReturnArrayOfThree() throws ParseException, JsonProcessingException {
        String[][] freeSlots = checker.findFreeSlots(cal1, cal2, target2);
        assertEquals(3, freeSlots.length);
    }

    @Test
    void whenFindFreeSlotsForOneFreeCalendar_thenReturnArrayOfThree() throws ParseException, JsonProcessingException {
        String[][] freeSlots = checker.findFreeSlots(cal1, cal3, target2);
        assertEquals(3, freeSlots.length);
    }

    @Test
    void whenFindFreeSlots_thenReturnArrayOfOne() throws ParseException, JsonProcessingException {
        String[][] freeSlots = checker.findFreeSlots(cal1, cal3, target3);
        assertEquals(1, freeSlots.length);
    }
}