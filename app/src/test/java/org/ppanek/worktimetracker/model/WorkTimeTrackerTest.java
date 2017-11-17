package org.ppanek.worktimetracker.model;

import org.junit.Test;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;

/**
 * Created by pawel on 13.11.2017.
 */

public class WorkTimeTrackerTest {

    @Test
    public void testSetAndGetWorkday() {
        WorkTimeTracker tracker = new WorkTimeTracker();
        Workday aWorkday = new Workday();

        Calendar calendar = Calendar.getInstance();
        tracker.setWorkday(calendar.getTime(), aWorkday);

        assertEquals(aWorkday, tracker.getWorkday(calendar.getTime()));
    }

    @Test
    public void testInvalidValues() {
        WorkTimeTracker tracker = new WorkTimeTracker();
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

    @Test
    public void testSetAndGetInSameDay() {
        WorkTimeTracker tracker = new WorkTimeTracker();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);

        Workday workday = new Workday();

        tracker.setWorkday(calendar.getTime(), workday);
        assertSame(workday, tracker.getWorkday(calendar.getTime()));

        calendar.add(Calendar.HOUR_OF_DAY, 8);
        assertSame(workday, tracker.getWorkday(calendar.getTime()));
    }

}
