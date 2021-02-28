package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domains.Slot;
import org.example.domains.UserCalendar;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.DateTimeUtil.*;

public class SlotChecker {

    private final ObjectMapper mapper = new ObjectMapper();
    private UserCalendar user1;
    private UserCalendar user2;

    public String[][] findFreeSlots(String cal1, String cal2, String duration) throws ParseException, JsonProcessingException {

        user1 = mapper.readValue(cal1, UserCalendar.class);
        user2 = mapper.readValue(cal2, UserCalendar.class);

        List<Slot> merged = mergeMeetings(user1.getMeetings(), user2.getMeetings());
        Map<String, String> result = new LinkedHashMap<>();
        Integer targetMinutes = stringToMinutes(duration);

        Date startTime = getStartTime(user1.getWorkingHours().getStart(), user2.getWorkingHours().getStart());
        Date endTime = getEndTime(user1.getWorkingHours().getEnd(), user2.getWorkingHours().getEnd());

        for (int i = 0; i <= merged.size(); i++) {
            if (i == 0) {
                if (freeMinutes(startTime, merged.get(0).getStart()) >= targetMinutes)
                    result.put(dateToString(startTime), dateToString(merged.get(0).getStart()));

            } else if (i == merged.size()) {
                if (freeMinutes(merged.get(i-1).getEnd(), endTime) >= targetMinutes)
                    result.put(dateToString(merged.get(i-1).getEnd()), dateToString(endTime));

            }else {
                if (freeMinutes(merged.get(i-1).getEnd(), merged.get(i).getStart()) >= targetMinutes)
                    result.put(dateToString(merged.get(i - 1).getEnd()), dateToString(merged.get(i).getStart()));
            }
        }

        return result.entrySet().stream()
                .map(entry -> new String[]{entry.getKey(), entry.getValue()})
                .toArray(size -> new String[size][1]);
    }

    private static List<Slot> mergeMeetings(List<Slot> meetings1, List<Slot> meetings2) {
        List<Slot> meetings = new ArrayList<>();
        if (meetings1 == null || meetings2 == null) return meetings;

        meetings = Stream
                .concat(meetings1.stream(), meetings2.stream())
                .collect(Collectors.toList());
        meetings.sort(Comparator.comparing(Slot::getStart)
                .thenComparing(Slot::getEnd));
        return meetings;
    }
}
