package org.ppanek.worktimetracker.model;

import java.util.Collection;
import java.util.Date;

/**
 * Created by pawel on 16.12.2017.
 */

public interface IWorkTimeTracker {
    IWorkday newWorkday();
    void putWorkday(Date date, IWorkday workday);
    IWorkday getWorkday(Date date);
    Collection<IWorkday> getAllWorkdays();
    void removeWorkday(Date date);
}
