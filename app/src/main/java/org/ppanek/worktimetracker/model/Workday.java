package org.ppanek.worktimetracker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.ppanek.worktimetracker.model.DateUtils.*;

class Workday {
    public static class Break {

        private final Workday workday;

        private TimePeriod breakTime;
        public Break(Workday workday) {
            this.breakTime = new TimePeriod();
            this.workday = workday;
        }

        public void setBegin(Date begin) throws InvalidWorkdayException {
            if (isLess(begin, workday.getBegin()))
                throw new InvalidWorkdayException("Break starts before work time begins");
            if (isGreater(begin, workday.getEnd()))
                throw new InvalidWorkdayException("Break starts after work time ends");

            breakTime.setBegin(begin);
        }

        public Date getBegin() {
            return breakTime.getBegin();
        }

        public void setEnd(Date end) throws InvalidWorkdayException {
            if (isLess(end, workday.getBegin()))
                throw new InvalidWorkdayException("Break ends before work time begins");
            if (isGreater(end, workday.getEnd()))
                throw new InvalidWorkdayException("Break ends after work time ends");

            breakTime.setEnd(end);
        }

        public Date getEnd() {
            return breakTime.getEnd();
        }

        public long getBreakTime() throws InvalidWorkdayException {
            return breakTime.getDuration();
        }

    }

    private TimePeriod workTime;
    private final List<Break> breaks;

    public Workday() {
        workTime = new TimePeriod();
        breaks = new ArrayList<Break>();
    }

    public void setBegin(Date begin) throws InvalidWorkdayException {
        if (begin != null) {
            for (Break aBreak : breaks) {
                if (isGreater(begin, aBreak.getBegin()) || isGreater(begin, aBreak.getEnd()))
                    throw new InvalidWorkdayException("Workday begins after a break");
            }
        }
        workTime.setBegin(begin);
    }

    public Date getBegin() {
        return workTime.getBegin();
    }

    public void setEnd(Date end) throws InvalidWorkdayException {
        if (end != null) {
            for (Break aBreak : breaks) {
                if (isLess(end, aBreak.getBegin()) || isLess(end, aBreak.getEnd()))
                    throw new InvalidWorkdayException("Workday ends before a break");
            }
        }
        workTime.setEnd(end);
    }

    public Date getEnd() {
        return workTime.getEnd();
    }

    public long getWorkTime() throws InvalidWorkdayException {
        long totalBreakTime = 0;
        for (Break aBreak : breaks) {
            totalBreakTime += aBreak.getBreakTime();
        }
        return workTime.getDuration() - totalBreakTime;
    }

    public Break newBreak() {
        Break aBreak = new Break(this);
        breaks.add(aBreak);
        return aBreak;
    }

    public List<Break> getBreaks() {
        return breaks;
    }

    public void removeBreak(Break aBreak) {
        if (aBreak != null) {
            breaks.remove(aBreak);
        }
    }
}
