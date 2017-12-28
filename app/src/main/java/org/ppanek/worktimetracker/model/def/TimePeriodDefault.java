package org.ppanek.worktimetracker.model.def;

import org.ppanek.worktimetracker.model.base.TimePeriodBase;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by pawel on 19.11.2017.
 */

public class TimePeriodDefault extends TimePeriodBase {
    private Date begin;
    private Date end;

    @Override
    protected void setBeginImpl(Date date) {
        this.begin = date;
    }

    @Override
    protected Date getBeginImpl() {
        return begin;
    }

    @Override
    protected void setEndImpl(Date date) {
        this.end = date;
    }

    @Override
    protected Date getEndImpl() {
        return end;
    }

    @Override
    protected long getDurationImpl() {
        if (begin == null || end == null)
            throw new IllegalStateException("Begin or end not correct");
        return TimeUnit.MINUTES.convert(end.getTime() - begin.getTime(), TimeUnit.MILLISECONDS);
    }

}
