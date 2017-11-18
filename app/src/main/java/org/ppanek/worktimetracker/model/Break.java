package org.ppanek.worktimetracker.model;

import java.util.Date;

import static org.ppanek.worktimetracker.model.DateUtils.isAfterOneDaySinceEpoch;
import static org.ppanek.worktimetracker.model.DateUtils.isGreater;
import static org.ppanek.worktimetracker.model.DateUtils.isLess;

/**
 * Created by pawel on 18.11.2017.
 */
public class Break implements IBreak {

    private final IWorkday workday;
    private IBreak decorated;

    public Break(IWorkday workday) {
        this.decorated = new DummyBreak();
        this.workday = workday;
    }

    public void setBegin(Date begin) {
        if (isAfterOneDaySinceEpoch(begin))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (isLess(begin, workday.getBegin()))
            throw new IllegalArgumentException("Break starts before work time begins");
        if (isGreater(begin, workday.getEnd()))
            throw new IllegalArgumentException("Break starts after work time ends");

        decorated.setBegin(begin);
    }

    public Date getBegin() {
        return decorated.getBegin();
    }

    public void setEnd(Date end) {
        if (isAfterOneDaySinceEpoch(end))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (isLess(end, workday.getBegin()))
            throw new IllegalArgumentException("Break ends before work time begins");
        if (isGreater(end, workday.getEnd()))
            throw new IllegalArgumentException("Break ends after work time ends");

        decorated.setEnd(end);
    }

    public Date getEnd() {
        return decorated.getEnd();
    }

    public long getBreakTime() throws IllegalArgumentException {
        return decorated.getBreakTime();
    }
}
