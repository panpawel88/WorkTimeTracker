package org.ppanek.worktimetracker.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by pawel on 13.11.2017.
 */

public abstract class WorkTimeTrackerBase implements IWorkTimeTracker {

    protected Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

}
