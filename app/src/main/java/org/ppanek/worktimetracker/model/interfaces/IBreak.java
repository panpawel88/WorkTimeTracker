package org.ppanek.worktimetracker.model.interfaces;

import java.util.Date;

/**
 * Created by pawel on 18.11.2017.
 */

public interface IBreak {
    void setBegin(Date begin);
    Date getBegin();
    void setEnd(Date end);
    Date getEnd();
    long getBreakTime();
}
