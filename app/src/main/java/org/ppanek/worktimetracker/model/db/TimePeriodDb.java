package org.ppanek.worktimetracker.model.db;

import org.ppanek.worktimetracker.model.base.TimePeriodBase;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by pawel on 29.11.2017.
 */

@Entity
public class TimePeriodDb extends TimePeriodBase {
    @Id
    private long id;

    private Date begin;

    private Date end;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    protected Date getBeginImpl() {
        return begin;
    }

    @Override
    protected void setBeginImpl(Date begin) {
        this.begin = begin;
    }

    @Override
    protected Date getEndImpl() {
        return end;
    }

    @Override
    protected void setEndImpl(Date end) {
        this.end = end;
    }

    @Override
    protected long getDurationImpl() {
        if (begin == null || end == null)
            throw new IllegalStateException("Begin or end not correct");
        return TimeUnit.MINUTES.convert(end.getTime() - begin.getTime(), TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriodDb entity = (TimePeriodDb) o;

        if (id != entity.id) return false;
        if (begin != null ? !begin.equals(entity.begin) : entity.begin != null) return false;
        return end != null ? end.equals(entity.end) : entity.end == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (begin != null ? begin.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
