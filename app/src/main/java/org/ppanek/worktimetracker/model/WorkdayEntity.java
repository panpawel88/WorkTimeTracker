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
public class WorkdayEntity implements IWorkday {
    @Id
    private long id;
    private ToOne<TimePeriodEntity> workTimeEntity;
    @Backlink
    private ToMany<BreakEntity> breaks;

    public WorkdayEntity() {
        workTimeEntity.setTarget(new TimePeriodEntity());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ToOne<TimePeriodEntity> getWorkTimeEntity() {
        return workTimeEntity;
    }

    public void setWorkTimeEntity(ToOne<TimePeriodEntity> workTimeEntity) {
        this.workTimeEntity = workTimeEntity;
    }

    public void setBreaks(ToMany<BreakEntity> breaks) {
        this.breaks = breaks;
    }

    @Override
    public void setBegin(Date date) {
        workTimeEntity.getTarget().setBegin(date);
    }

    @Override
    public Date getBegin() {
        return workTimeEntity.getTarget().getBegin();
    }

    @Override
    public void setEnd(Date end) {
        workTimeEntity.getTarget().setEnd(end);
    }

    @Override
    public Date getEnd() {
        return workTimeEntity.getTarget().getEnd();
    }

    @Override
    public long getTotalWorkTime() {
        long totalBreakTime = 0;
        for (IBreak aBreak : getBreaks()) {
            totalBreakTime += aBreak.getBreakTime();
        }
        return getWorkTime().getDuration() - totalBreakTime;
    }

    @Override
    public IBreak newBreak() {
        BreakEntity aBreak = new BreakEntity();
        breaks.add(aBreak);
        return aBreak;
    }

    @Override
    public List<BreakEntity> getBreaks() {
        return breaks.getListFactory().createList();
    }

    @Override
    public void removeBreak(IBreak aBreak) {
        if (!(aBreak instanceof BreakEntity))
            return;
        BreakEntity breakEntity = (BreakEntity) aBreak;
        breaks.remove(breakEntity);
    }

    @Override
    public ITimePeriod getWorkTime() {
        return workTimeEntity.getTarget();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkdayEntity that = (WorkdayEntity) o;

        if (id != that.id) return false;
        if (workTimeEntity != null ? !workTimeEntity.equals(that.workTimeEntity) : that.workTimeEntity != null)
            return false;
        return breaks.toArray().length > 0 ? breaks.toArray().equals(that.breaks.toArray()) : that.breaks.toArray().length == 0;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (workTimeEntity != null ? workTimeEntity.hashCode() : 0);
        result = 31 * result + (breaks != null ? breaks.hashCode() : 0);
        return result;
    }
}
