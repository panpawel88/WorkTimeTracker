package org.ppanek.worktimetracker.model.def;

import org.ppanek.worktimetracker.model.base.WorkdayBase;
import org.ppanek.worktimetracker.model.interfaces.IBreak;
import org.ppanek.worktimetracker.model.interfaces.ITimePeriod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pawel on 18.11.2017.
 */

public class WorkdayDefault extends WorkdayBase {

    private Date when;
    private ITimePeriod workTime;
    private List<IBreak> breaks;

    public WorkdayDefault() {
        workTime = new TimePeriodDefault();
        breaks = new ArrayList<>();
    }

    @Override
    protected void setBeginImpl(Date begin) {
        workTime.setBegin(begin);
    }

    @Override
    protected Date getBeginImpl() {
        return workTime.getBegin();
    }

    @Override
    protected void setEndImpl(Date end) {
        workTime.setEnd(end);
    }

    @Override
    protected Date getEndImpl() {
        return workTime.getEnd();
    }

    @Override
    protected List<? extends IBreak> getBreaksImpl() {
        return breaks;
    }

    @Override
    protected void removeBreakImpl(IBreak aBreak) {
        breaks.remove(aBreak);
    }

    @Override
    protected IBreak newBreakImpl() {
        return new BreakDefault(this);
    }

    @Override
    protected void putBreakImpl(IBreak aBreak) {
        breaks.add(aBreak);
    }

    @Override
    protected ITimePeriod getWorkTimeImpl() {
        return workTime;
    }

    @Override
    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkdayDefault that = (WorkdayDefault) o;

        if (when != null ? !when.equals(that.when) : that.when != null) return false;
        if (workTime != null ? !workTime.equals(that.workTime) : that.workTime != null)
            return false;
        return breaks != null ? breaks.equals(that.breaks) : that.breaks == null;
    }

    @Override
    public int hashCode() {
        int result = when != null ? when.hashCode() : 0;
        result = 31 * result + (workTime != null ? workTime.hashCode() : 0);
        result = 31 * result + (breaks != null ? breaks.hashCode() : 0);
        return result;
    }
}
