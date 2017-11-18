package org.ppanek.worktimetracker.model;

import java.util.Date;

/**
 * Created by pawel on 18.11.2017.
 */

public class DummyBreak implements IBreak {
    TimePeriod breakTime;

    public DummyBreak() {
        breakTime = new TimePeriod();
    }

    @Override
    public void setBegin(Date begin) {
        breakTime.setBegin(begin);
    }

    @Override
    public Date getBegin() {
        return breakTime.getBegin();
    }

    @Override
    public void setEnd(Date end) {
        breakTime.setEnd(end);
    }

    @Override
    public Date getEnd() {
        return breakTime.getEnd();
    }

    @Override
    public long getBreakTime() {
        return breakTime.getDuration();
    }
}
