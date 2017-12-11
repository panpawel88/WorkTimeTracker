package org.ppanek.worktimetracker.model;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by pawel on 29.11.2017.
 */

@Entity
public class BreakEntity implements IBreak {
    @Id
    private long id;
    private ToOne<TimePeriodEntity> timePeriod;
    private ToOne<WorkdayEntity> workday;

    public BreakEntity() {
        timePeriod.setTarget(new TimePeriodEntity());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ToOne<TimePeriodEntity> getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(ToOne<TimePeriodEntity> timePeriod) {
        this.timePeriod = timePeriod;
    }

    public ToOne<WorkdayEntity> getWorkday() {
        return workday;
    }

    public void setWorkday(ToOne<WorkdayEntity> workday) {
        this.workday = workday;
    }

    @Override
    public void setBegin(Date begin) {
        timePeriod.getTarget().setBegin(begin);
    }

    @Override
    public Date getBegin() {
        return timePeriod.getTarget().getBegin();
    }

    @Override
    public void setEnd(Date end) {
        timePeriod.getTarget().setEnd(end);
    }

    @Override
    public Date getEnd() {
        return timePeriod.getTarget().getEnd();
    }

    @Override
    public long getBreakTime() {
        return timePeriod.getTarget().getDuration();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreakEntity that = (BreakEntity) o;

        if (id != that.id) return false;
        return timePeriod != null ? timePeriod.equals(that.timePeriod) : that.timePeriod == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (timePeriod != null ? timePeriod.hashCode() : 0);
        return result;
    }
}
