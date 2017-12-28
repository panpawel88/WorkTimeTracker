package org.ppanek.worktimetracker.model;

import org.ppanek.worktimetracker.model.def.WorkTimeTrackerDefault;
import org.ppanek.worktimetracker.model.interfaces.IWorkTimeTracker;

/**
 * Created by pawel on 27.12.2017.
 */

public class WorkTimeTrackerDefaultTest extends AbstractWorkTimeTrackerTest {
    @Override
    public IWorkTimeTracker createTracker() {
        return new WorkTimeTrackerDefault();
    }
}
