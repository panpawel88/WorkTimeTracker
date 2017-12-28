package org.ppanek.worktimetracker.model.interfaces;

import java.util.Date;
import java.util.List;

/**
 * Created by pawel on 16.12.2017.
 */

public interface IWorkTimeTracker {
    IWorkday newWorkday();
    void putWorkday(Date date, IWorkday workday);
    IWorkday getWorkday(Date date);
    List<? extends IWorkday> getAllWorkdays();
    void removeWorkday(Date date);
}
