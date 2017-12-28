package org.ppanek.worktimetracker.model.interfaces;

import java.util.Date;

/**
 * Created by pawel on 18.11.2017.
 */

public interface ITimePeriod {
    void setBegin(Date date);
    Date getBegin();
    void setEnd(Date date);
    Date getEnd();
    long getDuration();

}
