package org.ppanek.worktimetracker.model.db;

import org.ppanek.worktimetracker.model.base.BreakBase;
import org.ppanek.worktimetracker.model.interfaces.IWorkday;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by pawel on 29.11.2017.
 */

@Entity
public class BreakDb extends BreakBase {
    @Id
    private long id;
    private ToOne<TimePeriodDb> timePeriod;
    private ToOne<WorkdayDb> workday;

    public BreakDb() {
        timePeriod.setTarget(new TimePeriodDb());
        workday.setTarget(new WorkdayDb());
    }

    public BreakDb(WorkdayDb workdayDb) {
        timePeriod.setTarget(new TimePeriodDb());
        workday.setTarget(workdayDb);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ToOne<TimePeriodDb> getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(ToOne<TimePeriodDb> timePeriod) {
        this.timePeriod = timePeriod;
    }

    public ToOne<WorkdayDb> getWorkday() {
        return workday;
    }

    public void setWorkday(ToOne<WorkdayDb> workday) {
        this.workday = workday;
    }

    @Override
    protected void setBeginImpl(Date begin) {
        timePeriod.getTarget().setBegin(begin);
    }

    @Override
    protected Date getBeginImpl() {
        return timePeriod.getTarget().getBegin();
    }

    @Override
    protected void setEndImpl(Date end) {
        timePeriod.getTarget().setEnd(end);
    }

    @Override
    protected Date getEndImpl() {
        return timePeriod.getTarget().getEnd();
    }

    @Override
    protected long getBreakTimeImpl() {
        return timePeriod.getTarget().getDuration();
    }

    @Override
    protected IWorkday getWorkdayImpl() {
        return workday.getTarget();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreakDb that = (BreakDb) o;

        if (id != that.id) return false;
        return timePeriod.getTarget() != null ? timePeriod.getTarget().equals(that.timePeriod.getTarget()) : that.timePeriod.getTarget() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (timePeriod.getTarget() != null ? timePeriod.getTarget().hashCode() : 0);
        return result;
    }
}
