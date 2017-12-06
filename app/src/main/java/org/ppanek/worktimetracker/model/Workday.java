package org.ppanek.worktimetracker.model;

import java.util.Date;
import java.util.List;

import static org.ppanek.worktimetracker.model.DateUtils.isAfterOneDaySinceEpoch;
import static org.ppanek.worktimetracker.model.DateUtils.isGreater;
import static org.ppanek.worktimetracker.model.DateUtils.isLess;

public class Workday implements IWorkday {

    private IWorkday decorated;

    public Workday() {
        decorated = new DummyWorkday();
    }

    public Workday(IWorkday decorated) {
        this.decorated = decorated;
    }

    @Override
    public void setBegin(Date begin) {
        if (isAfterOneDaySinceEpoch(begin))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (begin != null) {
            for (IBreak aBreak : decorated.getBreaks()) {
                if (isGreater(begin, aBreak.getBegin()) || isGreater(begin, aBreak.getEnd()))
                    throw new IllegalArgumentException("Workday begins after a break");
            }
        }
        decorated.setBegin(begin);
    }

    @Override
    public Date getBegin() {
        return decorated.getBegin();
    }

    @Override
    public void setEnd(Date end) {
        if (isAfterOneDaySinceEpoch(end))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (end != null) {
            for (IBreak aBreak : decorated.getBreaks()) {
                if (isLess(end, aBreak.getBegin()) || isLess(end, aBreak.getEnd()))
                    throw new IllegalArgumentException("Workday ends before a break");
            }
        }
        decorated.setEnd(end);
    }

    @Override
    public Date getEnd() {
        return decorated.getEnd();
    }

    @Override
    public long getTotalWorkTime() {
        long totalBreakTime = 0;
        for (IBreak aBreak : decorated.getBreaks()) {
            totalBreakTime += aBreak.getBreakTime();
        }
        return decorated.getWorkTime().getDuration() - totalBreakTime;
    }

    @Override
    public IBreak newBreak() {
        return decorated.newBreak();
    }

    @Override
    public List<? extends IBreak> getBreaks() {
        return decorated.getBreaks();
    }

    @Override
    public void removeBreak(IBreak aBreak) {
        if (aBreak != null) {
            decorated.removeBreak(aBreak);
        }
    }

    @Override
    public ITimePeriod getWorkTime() {
        return decorated.getWorkTime();
    }
}
