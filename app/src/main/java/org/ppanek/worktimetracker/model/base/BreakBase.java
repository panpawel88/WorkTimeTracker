package org.ppanek.worktimetracker.model.base;

import org.ppanek.worktimetracker.model.interfaces.IBreak;
import org.ppanek.worktimetracker.model.interfaces.IWorkday;

import java.util.Date;

import static org.ppanek.worktimetracker.model.DateUtils.isAfterOneDaySinceEpoch;
import static org.ppanek.worktimetracker.model.DateUtils.isGreater;
import static org.ppanek.worktimetracker.model.DateUtils.isLess;

/**
 * Created by pawel on 18.11.2017.
 */
public abstract class BreakBase implements IBreak {

    protected abstract void setBeginImpl(Date begin);
    protected abstract Date getBeginImpl();
    protected abstract void setEndImpl(Date end);
    protected abstract Date getEndImpl();
    protected abstract long getBreakTimeImpl();
    protected abstract IWorkday getWorkdayImpl();

    public void setBegin(Date begin) {
        if (isAfterOneDaySinceEpoch(begin))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (isLess(begin, getWorkdayImpl().getBegin()))
            throw new IllegalArgumentException("Break starts before work time begins");
        if (isGreater(begin, getWorkdayImpl().getEnd()))
            throw new IllegalArgumentException("Break starts after work time ends");

        setBeginImpl(begin);
    }

    public Date getBegin() {
        return getBeginImpl();
    }

    public void setEnd(Date end) {
        if (isAfterOneDaySinceEpoch(end))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (isLess(end, getWorkdayImpl().getBegin()))
            throw new IllegalArgumentException("Break ends before work time begins");
        if (isGreater(end, getWorkdayImpl().getEnd()))
            throw new IllegalArgumentException("Break ends after work time ends");

        setEndImpl(end);
    }

    public Date getEnd() { return getEndImpl(); }

    public long getBreakTime() throws IllegalArgumentException {
        return getBreakTimeImpl();
    }
}
