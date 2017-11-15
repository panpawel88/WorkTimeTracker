package org.ppanek.worktimetracker.model;

/**
 * Created by pawel on 13.11.2017.
 */

public class WorkTimeTracker {
    private static WorkTimeTracker instance;

    protected WorkTimeTracker() { }

    public static synchronized WorkTimeTracker getInstance() {
        if (instance == null)
            instance = new WorkTimeTracker();
        return instance;
    }
}
