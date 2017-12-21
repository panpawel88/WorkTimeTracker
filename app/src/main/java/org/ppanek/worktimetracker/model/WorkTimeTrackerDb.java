package org.ppanek.worktimetracker.model;

import java.util.Collection;
import java.util.Date;

/**
 * Created by pawel on 15.12.2017.
 */

public class WorkTimeTrackerDb extends WorkTimeTrackerBase {

    public WorkTimeTrackerDb() {}

    @Override
    public IWorkday newWorkday() {
        return new WorkdayDb();
    }

    @Override
    public void putWorkday(Date date, IWorkday workday) {
    }

    @Override
    public IWorkday getWorkday(Date date) {
        return null;
    }

    @Override
    public Collection<IWorkday> getAllWorkdays() {
        return null;
    }

    @Override
    public void removeWorkday(Date date) {
    }
}
