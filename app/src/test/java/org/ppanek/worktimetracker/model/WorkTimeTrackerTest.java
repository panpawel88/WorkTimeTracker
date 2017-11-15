package org.ppanek.worktimetracker.model;

import org.junit.Test;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by pawel on 13.11.2017.
 */

public class WorkTimeTrackerTest {

    @Test
    public void testSetAndGetWorkday() {
        WorkTimeTracker tracker = WorkTimeTracker.getInstance();
        Workday aWorkday = new Workday();

        Calendar calendar = Calendar.getInstance();
        tracker.setWorkday(calendar.getTime(), aWorkday);

        assertEquals(aWorkday, tracker.getWorkday(calendar.getTime()));
    }

    @Test
    public void testInvalidValues() {
        WorkTimeTracker tracker = WorkTimeTracker.getInstance();
        try {
            tracker.setWorkday(null, null);
            fail();
        } catch (IllegalArgumentException e) {
            // intentionally empty
        }

        try {
            tracker.getWorkday(null);
            fail();
        } catch (IllegalArgumentException e) {
            // intentionally empty
        }

        // It's legal to set null workday
        try {
            tracker.setWorkday(Calendar.getInstance().getTime(), null);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

}
