package org.ppanek.worktimetracker.model;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by pawel on 13.11.2017.
 */

public class DateUtils {

    static Date oneDaySinceEpoch;
    static {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        oneDaySinceEpoch = calendar.getTime();
    }

    public static boolean areEqual(Date begin, Date end) {
        if (begin == null || end == null)
            return false;
        return begin.getTime() == end.getTime();
    }

    public static boolean isLess(Date begin, Date end) {
        if (begin == null || end == null)
            return false;
        return begin.getTime() < end.getTime();
    }

    public static boolean isLessOrEqual(Date begin, Date end) {
        if (begin == null || end == null)
            return false;
        return begin.getTime() <= end.getTime();
    }

    public static boolean isGreater(Date begin, Date end) {
        if (begin == null || end == null)
            return false;
        return begin.getTime() > end.getTime();
    }

    public static boolean isGreaterOrEqual(Date begin, Date end) {
        if (begin == null || end == null)
            return false;
        return begin.getTime() >= end.getTime();
    }

    public static boolean isLessThanOneDay(Date date) {
        return date != null ? date.before(oneDaySinceEpoch) : false;
    }

    public static Date createDate(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
}
