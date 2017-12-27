package org.ppanek.worktimetracker.model;

/**
 * Created by pawel on 27.12.2017.
 */

public class WorkTimeTrackerDefaultTest extends AbstractWorkTimeTrackerTest {
    @Override
    public IWorkTimeTracker createTracker() {
        return new WorkTimeTrackerDefault();
    }
}
