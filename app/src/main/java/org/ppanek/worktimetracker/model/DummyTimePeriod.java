package org.ppanek.worktimetracker.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by pawel on 19.11.2017.
 */

public class DummyTimePeriod implements ITimePeriod {
    private Date begin;
    private Date end;

    @Override
    public void setBegin(Date date) {
        this.begin = date;
    }

    @Override
    public Date getBegin() {
        return begin;
    }

    @Override
    public void setEnd(Date date) {
        this.end = date;
    }

    @Override
    public Date getEnd() {
        return end;
    }

    @Override
    public long getDuration() {
        if (begin == null || end == null)
            throw new IllegalStateException("Begin or end not correct");
        return TimeUnit.MINUTES.convert(end.getTime() - begin.getTime(), TimeUnit.MILLISECONDS);
    }

}
