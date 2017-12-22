package org.ppanek.worktimetracker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pawel on 18.11.2017.
 */

public class WorkdayDefault extends WorkdayBase {

    private ITimePeriod workTime;
    private List<IBreak> breaks;

    public WorkdayDefault() {
        workTime = new TimePeriodDefault();
        breaks = new ArrayList<>();
    }

    @Override
    protected void setBeginImpl(Date begin) {
        workTime.setBegin(begin);
    }

    @Override
    protected Date getBeginImpl() {
        return workTime.getBegin();
    }

    @Override
    protected void setEndImpl(Date end) {
        workTime.setEnd(end);
    }

    @Override
    protected Date getEndImpl() {
        return workTime.getEnd();
    }

    @Override
    protected List<? extends IBreak> getBreaksImpl() {
        return breaks;
    }

    @Override
    protected void removeBreakImpl(IBreak aBreak) {
        breaks.remove(aBreak);
    }

    @Override
    protected IBreak newBreakImpl() {
        return new BreakDefault(this);
    }

    @Override
    protected void putBreakImpl(IBreak aBreak) {
        breaks.add(aBreak);
    }

    @Override
    protected ITimePeriod getWorkTimeImpl() {
        return workTime;
    }
}
