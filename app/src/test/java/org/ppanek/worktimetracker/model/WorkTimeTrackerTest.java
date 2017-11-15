package org.ppanek.worktimetracker.model;

import org.junit.Test;

/**
 * Created by pawel on 13.11.2017.
 */

public class WorkTimeTrackerTest {

    @Test
    public void testAddWorkdays() {
        // TODO: API should look like tracker.addWorkday(Calendar.getInstance().getTime(), aWorkday)
        WorkTimeTracker tracker = WorkTimeTracker.getInstance();
        Workday aWorkday = new Workday();
        aWorkday.setBegin(null);
    }
}
