package org.ppanek.worktimetracker.model;

import java.util.Date;

import static org.ppanek.worktimetracker.model.DateUtils.isAfterOneDaySinceEpoch;
import static org.ppanek.worktimetracker.model.DateUtils.isGreater;
import static org.ppanek.worktimetracker.model.DateUtils.isLess;

/**
 * Created by pawel on 18.11.2017.
 */
public class Break implements IBreak {

    private final Workday workday;

    private TimePeriod breakTime;

    public Break(Workday workday) {
        this.breakTime = new TimePeriod();
        this.workday = workday;
    }

    public void setBegin(Date begin) {
        if (isAfterOneDaySinceEpoch(begin))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (isLess(begin, workday.getBegin()))
            throw new IllegalArgumentException("Break starts before work time begins");
        if (isGreater(begin, workday.getEnd()))
            throw new IllegalArgumentException("Break starts after work time ends");

        breakTime.setBegin(begin);
    }

    public Date getBegin() {
        return breakTime.getBegin();
    }

    public void setEnd(Date end) {
        if (isAfterOneDaySinceEpoch(end))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (isLess(end, workday.getBegin()))
            throw new IllegalArgumentException("Break ends before work time begins");
        if (isGreater(end, workday.getEnd()))
            throw new IllegalArgumentException("Break ends after work time ends");

        breakTime.setEnd(end);
    }

    public Date getEnd() {
        return breakTime.getEnd();
    }

    public long getBreakTime() throws IllegalArgumentException {
        return breakTime.getDuration();
    }
}
