package org.ppanek.worktimetracker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pawel on 18.11.2017.
 */

public class DummyWorkday implements IWorkday {

    private TimePeriod workTime;
    private List<IBreak> breaks;

    public DummyWorkday() {
        workTime = new TimePeriod();
        breaks = new ArrayList<>();
    }

    @Override
    public void setBegin(Date date) {
        workTime.setBegin(date);
    }

    @Override
    public Date getBegin() {
        return workTime.getBegin();
    }

    @Override
    public void setEnd(Date end) {
        workTime.setEnd(end);
    }

    @Override
    public Date getEnd() {
        return workTime.getEnd();
    }

    @Override
    public long getTotalWorkTime() {
        return 0;
    }

    @Override
    public IBreak newBreak() {
        Break aBreak = new Break(this);
        breaks.add(aBreak);
        return aBreak;
    }

    @Override
    public List<IBreak> getBreaks() {
        return breaks;
    }

    @Override
    public void removeBreak(IBreak aBreak) {
        breaks.remove(aBreak);
    }

    @Override
    public ITimePeriod getWorkTime() {
        return workTime;
    }
}
