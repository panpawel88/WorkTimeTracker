package org.ppanek.worktimetracker.model.base;

import org.ppanek.worktimetracker.model.interfaces.ITimePeriod;

import java.util.Date;

/**
 * Created by pawel on 12.11.2017.
 */

public abstract class TimePeriodBase implements ITimePeriod {

    protected abstract void setBeginImpl(Date begin);
    protected abstract Date getBeginImpl();
    protected abstract void setEndImpl(Date end);
    protected abstract Date getEndImpl();
    protected abstract long getDurationImpl();

    @Override
    public void setBegin(Date begin) {
        if (getEndImpl() != null && begin.getTime() >= getEndImpl().getTime()) {
            throw new IllegalArgumentException("Begin is equal or after end");
        }
        setBeginImpl(begin);
    }

    @Override
    public Date getBegin() {
        return getBeginImpl();
    }

    @Override
    public void setEnd(Date end) {
        if (getBeginImpl() != null && end.getTime() <= getBeginImpl().getTime()) {
            throw new IllegalArgumentException("End is equal or before begin");
        }
        setEndImpl(end);
    }

    @Override
    public Date getEnd() {
        return getEndImpl();
    }

    @Override
    public long getDuration() {
        return getDurationImpl();
    }

}
