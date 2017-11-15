package org.ppanek.worktimetracker.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 13.11.2017.
 */

public class WorkTimeTracker {
    private static WorkTimeTracker instance;

    private Map<Date, Workday> workdays;

    protected WorkTimeTracker() {
        workdays = new HashMap<>();
    }

    private Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static synchronized WorkTimeTracker getInstance() {
        if (instance == null)
            instance = new WorkTimeTracker();
        return instance;
    }

    public void setWorkday(Date date, Workday workday) {
        if (date == null)
            throw new IllegalArgumentException();
        workdays.put(truncateTime(date), workday);
    }

    public Workday getWorkday(Date date) {
        if (date == null)
            throw new IllegalArgumentException();
        return workdays.get(truncateTime(date));
    }
}
