package org.ppanek.worktimetracker.model;

import org.ppanek.worktimetracker.model.interfaces.IWorkTimeTracker;

public class WorkTimeTrackerInstance {

    private static IWorkTimeTracker instance;

    public static IWorkTimeTracker Get() {
        return instance;
    }

    public static void Set(IWorkTimeTracker tracker) {
        instance = tracker;
    }
}
