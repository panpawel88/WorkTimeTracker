package org.ppanek.worktimetracker.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by pawel on 12.11.2017.
 */

public class TimePeriod {

    private Date begin;
    private Date end;

    public void setBegin(Date begin) throws InvalidWorkdayException {
        this.begin = begin;

        if (this.end != null && this.begin.getTime() >= this.end.getTime()) {
            throw new InvalidWorkdayException("Begin is equal or after end");
        }
    }

    public Date getBegin() {
        return begin;
    }

    public void setEnd(Date end) throws InvalidWorkdayException {
        this.end = end;
        if (this.begin != null && this.end.getTime() <= this.begin.getTime()) {
            throw new InvalidWorkdayException("End is equal or before begin");
        }
    }

    public Date getEnd() {
        return end;
    }

    public boolean isValid() {
        if (begin == null || end == null)
            return false;
        if (begin.getTime() >= end.getTime())
            return false;
        return true;
    }

    public long getDuration() throws InvalidWorkdayException {
        if (!isValid())
            throw new InvalidWorkdayException("Begin or end not correct");
        return TimeUnit.MINUTES.convert(end.getTime() - begin.getTime(), TimeUnit.MILLISECONDS);
    }
}
