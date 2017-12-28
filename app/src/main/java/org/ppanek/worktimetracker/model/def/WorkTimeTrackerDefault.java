package org.ppanek.worktimetracker.model.def;

import org.ppanek.worktimetracker.model.base.WorkTimeTrackerBase;
import org.ppanek.worktimetracker.model.interfaces.IWorkday;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        if (!(workday instanceof WorkdayDefault))
            throw new IllegalArgumentException();
        Date truncated = truncateTime(date);
        WorkdayDefault workdayDefault = (WorkdayDefault) workday;
        workdayDefault.setWhen(truncated);
        workdays.put(truncated, workday);
    }

    @Override
    public IWorkday getWorkday(Date date) {
        if (date == null)
            throw new IllegalArgumentException();
        return workdays.get(truncateTime(date));
    }

    @Override
    public List<? extends IWorkday> getAllWorkdays() {
        ArrayList<IWorkday> result = new ArrayList<>();
        result.addAll(workdays.values());
        return result;
    }

    @Override
    public void removeWorkday(Date date) {
        if (date == null)
            throw new IllegalArgumentException();
        workdays.remove(truncateTime(date));
    }
}
