package org.ppanek.worktimetracker.model;

import java.util.Date;

/**
 * Created by pawel on 12.11.2017.
 */

public class TimePeriod implements ITimePeriod {
    private final ITimePeriod decorated;

    public TimePeriod() {
        this.decorated = new DummyTimePeriod();
    }

    public TimePeriod(ITimePeriod decorated) {
        this.decorated = decorated;
    }

    @Override
    public void setBegin(Date begin) {
        if (decorated.getEnd() != null && begin.getTime() >= decorated.getEnd().getTime()) {
            throw new IllegalArgumentException("Begin is equal or after end");
        }
        decorated.setBegin(begin);
    }

    @Override
    public Date getBegin() {
        return decorated.getBegin();
    }

    @Override
    public void setEnd(Date end) {
        if (decorated.getBegin() != null && end.getTime() <= decorated.getBegin().getTime()) {
            throw new IllegalArgumentException("End is equal or before begin");
        }
        decorated.setEnd(end);
    }

    @Override
    public Date getEnd() {
        return decorated.getEnd();
    }

    @Override
    public long getDuration() {
        return decorated.getDuration();
    }

}
