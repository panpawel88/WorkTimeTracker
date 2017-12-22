package org.ppanek.worktimetracker.model;

import java.util.Date;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

/**
 * Created by pawel on 06.12.2017.
 */

@Entity
public class WorkdayDb extends WorkdayBase {
    @Id
    private long id;
    private ToOne<TimePeriodDb> workTime;

    @Backlink
    private ToMany<BreakDb> breaksDb;

    public WorkdayDb() {
        workTime.setTarget(new TimePeriodDb());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ToOne<TimePeriodDb> getWorkTime() {
        return workTime;
    }

    public void setWorkTime(ToOne<TimePeriodDb> workTime) {
        this.workTime = workTime;
    }

    public ToMany<BreakDb> getBreaksDb() {
        return breaksDb;
    }

    public void setBreaksDb(ToMany<BreakDb> breaksDb) {
        this.breaksDb = breaksDb;
    }

    @Override
    protected void setBeginImpl(Date begin) {
        workTime.getTarget().setBegin(begin);
    }

    @Override
    protected Date getBeginImpl() {
        return workTime.getTarget().getBegin();
    }

    @Override
    protected void setEndImpl(Date end) {
        workTime.getTarget().setEnd(end);
    }

    @Override
    protected Date getEndImpl() {
        return  workTime.getTarget().getEnd();
    }

    @Override
    protected List<? extends IBreak> getBreaksImpl() {
        return breaksDb;
    }

    @Override
    protected void removeBreakImpl(IBreak aBreak) {
        if (!(aBreak instanceof BreakDb))
            return;
        BreakDb breakDb = (BreakDb) aBreak;
        breaksDb.remove(breakDb);
    }

    @Override
    protected IBreak newBreakImpl() {
        return new BreakDb(this);
    }

    @Override
    protected void putBreakImpl(IBreak aBreak) {
        if (aBreak instanceof BreakDb) {
            breaksDb.add((BreakDb) aBreak);
        }
    }

    @Override
    protected ITimePeriod getWorkTimeImpl() {
        return workTime.getTarget();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkdayDb that = (WorkdayDb) o;

        if (id != that.id) return false;
        if (workTime.getTarget() != null ? !workTime.getTarget().equals(that.workTime.getTarget()) : that.workTime.getTarget() != null)
            return false;

        if (breaksDb != null && that.breaksDb == null)
            return false;
        if (breaksDb == null && that.breaksDb != null)
            return false;
        if (breaksDb != null && breaksDb.size() != that.breaksDb.size())
            return false;
        return breaksDb != null ? breaksDb.containsAll(that.breaksDb) : that.breaksDb == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (workTime != null ? workTime.hashCode() : 0);
        result = 31 * result + (breaksDb != null ? breaksDb.hashCode() : 0);
        return result;
    }
}
