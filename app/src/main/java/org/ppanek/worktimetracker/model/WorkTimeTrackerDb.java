package org.ppanek.worktimetracker.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.objectbox.Box;

/**
 * Created by pawel on 15.12.2017.
 */

public class WorkTimeTrackerDb extends WorkTimeTrackerBase {

    private final Box<WorkdayDb> box;

    public WorkTimeTrackerDb() {
        this(MyObjectBox.builder().build().boxFor(WorkdayDb.class));
    }

    public WorkTimeTrackerDb(Box<WorkdayDb> box) {
        this.box = box;
    }

    @Override
    public IWorkday newWorkday() {
        return new WorkdayDb();
    }

    @Override
    public void putWorkday(Date date, IWorkday workday) {
        if (date == null || workday == null)
            throw new IllegalArgumentException();
        if (!(workday instanceof WorkdayDb))
            throw new IllegalArgumentException();

        Date truncated = truncateTime(date);
        WorkdayDb updated = (WorkdayDb) workday;
        WorkdayDb original  = getWorkdayByDate(truncated);
        if (original != null) {
            if (original.getId() != updated.getId())
                throw new IllegalStateException("Couldn't put more than one Workday per day");
        }

        updated.setDate(truncated);
        box.put(updated);
    }

    @Override
    public IWorkday getWorkday(Date date) {
        if (date == null)
            throw new IllegalArgumentException();
        return getWorkdayByDate(truncateTime(date));
    }

    @Override
    public Collection<? extends IWorkday> getAllWorkdays() {
        return box.getAll();
    }

    @Override
    public void removeWorkday(Date date) {
        if (date == null)
            throw new IllegalArgumentException();
        WorkdayDb workday = getWorkdayByDate(truncateTime(date));
        if (workday != null)
            box.remove(workday);
    }

    private WorkdayDb getWorkdayByDate(Date truncated) {
        List<WorkdayDb> workdayDbs = box.query().equal(WorkdayDb_.date, truncated).build().find();
        if (workdayDbs.size() == 1)
            return workdayDbs.get(0);
        return null;
    }
}
