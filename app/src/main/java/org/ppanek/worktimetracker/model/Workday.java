package org.ppanek.worktimetracker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.ppanek.worktimetracker.model.DateUtils.isAfterOneDaySinceEpoch;
import static org.ppanek.worktimetracker.model.DateUtils.isGreater;
import static org.ppanek.worktimetracker.model.DateUtils.isLess;

public class Workday implements IWorkday {

    private TimePeriod workTime;
    private List<IBreak> breaks;

    public Workday() {
        workTime = new TimePeriod();
        breaks = new ArrayList<>();
    }

    public void setBegin(Date begin) {
        if (isAfterOneDaySinceEpoch(begin))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (begin != null) {
            for (IBreak aBreak : breaks) {
                if (isGreater(begin, aBreak.getBegin()) || isGreater(begin, aBreak.getEnd()))
                    throw new IllegalArgumentException("Workday begins after a break");
            }
        }
        workTime.setBegin(begin);
    }

    public Date getBegin() {
        return workTime.getBegin();
    }

    public void setEnd(Date end) {
        if (isAfterOneDaySinceEpoch(end))
            throw new IllegalArgumentException("Date argument should be less than 1 day since Epoch");
        if (end != null) {
            for (IBreak aBreak : breaks) {
                if (isLess(end, aBreak.getBegin()) || isLess(end, aBreak.getEnd()))
                    throw new IllegalArgumentException("Workday ends before a break");
            }
        }
        workTime.setEnd(end);
    }

    public Date getEnd() {
        return workTime.getEnd();
    }

    public long getWorkTime() {
        long totalBreakTime = 0;
        for (IBreak aBreak : breaks) {
            totalBreakTime += aBreak.getBreakTime();
        }
        return workTime.getDuration() - totalBreakTime;
    }

    public IBreak newBreak() {
        Break aBreak = new Break(this);
        breaks.add(aBreak);
        return aBreak;
    }

    public List<IBreak> getBreaks() {
        return breaks;
    }

    public void removeBreak(IBreak aBreak) {
        if (aBreak != null) {
            breaks.remove(aBreak);
        }
    }
}
