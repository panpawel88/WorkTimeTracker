package org.ppanek.worktimetracker.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel on 16.12.2017.
 */

public class WorkTimeTrackerDefault extends WorkTimeTrackerBase {
    private Map<Date, IWorkday> workdays;

    public WorkTimeTrackerDefault() {
        workdays = new HashMap<>();
    }

    @Override
    public IWorkday newWorkday() {
        return new WorkdayDefault();
    }

    @Override
    public void putWorkday(Date date, IWorkday workday) {
        if (date == null || workday == null)
            throw new IllegalArgumentException();
        workdays.put(truncateTime(date), workday);
    }

    @Override
    public IWorkday getWorkday(Date date) {
        if (date == null)
            throw new IllegalArgumentException();
        return workdays.get(truncateTime(date));
    }

    @Override
    public Collection<IWorkday> getAllWorkdays() {
        return workdays.values();
    }

    @Override
    public void removeWorkday(Date date) {
        if (date == null)
            throw new IllegalArgumentException();
        workdays.remove(truncateTime(date));
    }
}
