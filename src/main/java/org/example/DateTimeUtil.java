package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtil {

    public static Date getEndTime(Date time1, Date time2) {
        return time1.before(time2) ? time1 : time2;
    }

    public static Date getStartTime(Date time1, Date time2){
        return time1.after(time2) ? time1 : time2;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static Long freeMinutes(Date from, Date to) {

        Long milliseconds = to.getTime() - from.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(milliseconds);
    }

    public static Integer stringToMinutes(String str) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        str = str.replaceAll("\\[", "")
                 .replaceAll("\\]","");

        calendar.setTime(format.parse(str));
        Integer minutesCount = (calendar.get(Calendar.HOUR) * 60) + calendar.get(Calendar.MINUTE);

        if (minutesCount == 0) throw new DateTimeException("Amount of minutes can't be 0.");
        return minutesCount;
    }
}
