package util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;


/**
 * Utility class to get 7 days per current week in dd.MM format
 */
public class DateTimeUtil {
    /**
     * @return List of dates in string format
     */
    public static ArrayList<String> getDaysForWeek() {
        ArrayList<String> result = new ArrayList<>();
        DateTime currentDay = DateTime.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM");
        for (int i = 0; i < 7; i++) {
            result.add(fmt.print(currentDay.plusDays(i)));
        }
        return result;
    }

    /**
     * @return List of dates in string format but reversed
     */
    public static ArrayList<String> getDaysForWeekReversed() {
        ArrayList<String> result = new ArrayList<>();
        DateTime currentDay = DateTime.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM");
        for (int i = 6; i >= 0; i--) {
            result.add(fmt.print(currentDay.plusDays(i)));
        }
        return result;
    }
}
