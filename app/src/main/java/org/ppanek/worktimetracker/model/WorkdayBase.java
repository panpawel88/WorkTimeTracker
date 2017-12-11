package org.ppanek.worktimetracker.model;

import java.util.Date;
import java.util.List;

import static org.ppanek.worktimetracker.model.DateUtils.isAfterOneDaySinceEpoch;
import static org.ppanek.worktimetracker.model.DateUtils.isGreater;
import static org.ppanek.worktimetracker.model.DateUtils.isLess;

public abstract class WorkdayBase implements IWorkday {

    protected abstract void setBeginImpl(Date begin);
    protected abstract Date getBeginImpl();
    protected abstract void setEndImpl(Date end);
    protected abstract Date getEndImpl();
    protected abstract List<? extends IBreak> getBreaksImpl();
    protected abstract void removeBreakImpl(IBreak aBreak);
    protected abstract IBreak newBreakImpl();
    protected abstract ITimePeriod getWorkTimeImpl();

    @Override
    public void setBegin(Date begin) {
        if (isAfterOneDaySinceEpoch(begin))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (begin != null) {
            for (IBreak aBreak : getBreaksImpl()) {
                if (isGreater(begin, aBreak.getBegin()) || isGreater(begin, aBreak.getEnd()))
                    throw new IllegalArgumentException("WorkdayBase begins after a break");
            }
        }
        setBeginImpl(begin);
    }

    @Override
    public Date getBegin() {
        return getBeginImpl();
    }

    @Override
    public void setEnd(Date end) {
        if (isAfterOneDaySinceEpoch(end))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (end != null) {
            for (IBreak aBreak : getBreaksImpl()) {
                if (isLess(end, aBreak.getBegin()) || isLess(end, aBreak.getEnd()))
                    throw new IllegalArgumentException("WorkdayBase ends before a break");
            }
        }
        setEndImpl(end);
    }

    @Override
    public Date getEnd() {
        return getEndImpl();
    }

    @Override
    public long getTotalWorkTime() {
        long totalBreakTime = 0;
        for (IBreak aBreak : getBreaksImpl()) {
            totalBreakTime += aBreak.getBreakTime();
        }
        return getWorkTimeImpl().getDuration() - totalBreakTime;
    }

    @Override
    public IBreak newBreak() {
        return newBreakImpl();
    }

    @Override
    public List<? extends IBreak> getBreaks() {
        return getBreaksImpl();
    }

    @Override
    public void removeBreak(IBreak aBreak) {
        if (aBreak != null) {
            removeBreakImpl(aBreak);
        }
    }
}
