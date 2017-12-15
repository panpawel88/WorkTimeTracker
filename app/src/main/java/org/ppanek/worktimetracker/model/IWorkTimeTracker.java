package org.ppanek.worktimetracker.model;

import java.util.Date;

/**
 * Created by pawel on 16.12.2017.
 */

public interface IWorkTimeTracker {
    void putWorkday(Date date, IWorkday workday);
    IWorkday getWorkday(Date date);
    void removeWorkday(Date date);
}
