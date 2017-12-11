package org.ppanek.worktimetracker.model;

import java.util.Date;

/**
 * Created by pawel on 18.11.2017.
 */

public class BreakDefault extends BreakBase {
    private TimePeriodDefault breakTime;
    private WorkdayDefault workday;


    public BreakDefault(WorkdayDefault workday) {
        this.breakTime = new TimePeriodDefault();
        this.workday = workday;
    }

    @Override
    protected void setBeginImpl(Date begin) {
        breakTime.setBegin(begin);
    }

    @Override
    protected Date getBeginImpl() {
        return breakTime.getBegin();
    }

    @Override
    protected void setEndImpl(Date end) {
        breakTime.setEnd(end);
    }

    @Override
    protected Date getEndImpl() {
        return breakTime.getEnd();
    }

    @Override
    protected long getBreakTimeImpl() {
        return breakTime.getDuration();
    }

    @Override
    protected IWorkday getWorkdayImpl() {
        return workday;
    }
}
