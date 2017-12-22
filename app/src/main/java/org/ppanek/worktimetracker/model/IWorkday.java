package org.ppanek.worktimetracker.model;

import java.util.Date;
import java.util.List;

/**
 * Created by pawel on 18.11.2017.
 */

public interface IWorkday {
    void setBegin(Date date);
    Date getBegin();
    void setEnd(Date end);
    Date getEnd();
    long getTotalWorkTime();
    IBreak newBreak();
    List<? extends IBreak> getBreaks();
    void putBreak(IBreak aBreak);
    void removeBreak(IBreak aBreak);
}
