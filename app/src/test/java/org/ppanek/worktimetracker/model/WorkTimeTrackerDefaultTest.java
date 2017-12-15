package org.ppanek.worktimetracker.model;

import org.junit.Test;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;

/**
 * Created by pawel on 13.11.2017.
 */

public class WorkTimeTrackerDefaultTest {

    @Test
    public void testSetAndGetWorkday() {
        WorkTimeTrackerDefault tracker = new WorkTimeTrackerDefault();
        WorkdayDefault aWorkday = new WorkdayDefault();

        Calendar calendar = Calendar.getInstance();
        tracker.putWorkday(calendar.getTime(), aWorkday);

        assertEquals(aWorkday, tracker.getWorkday(calendar.getTime()));
    }

    @Test
    public void testInvalidValues() {
        WorkTimeTrackerDefault tracker = new WorkTimeTrackerDefault();
        try {
            tracker.putWorkday(null, null);
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

        try {
            tracker.removeWorkday(null);
            fail();
        } catch (IllegalArgumentException e) {
            // intentionally empty
        }

        // It's legal to set null workday
        try {
            tracker.putWorkday(Calendar.getInstance().getTime(), null);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testSetAndGetWorkdayInSameDay() {
        WorkTimeTrackerDefault tracker = new WorkTimeTrackerDefault();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);

        WorkdayDefault workday = new WorkdayDefault();

        tracker.putWorkday(calendar.getTime(), workday);
        assertSame(workday, tracker.getWorkday(calendar.getTime()));

        calendar.add(Calendar.HOUR_OF_DAY, 8);
        assertSame(workday, tracker.getWorkday(calendar.getTime()));
    }

    @Test
    public void testSetAndRemoveWorkday() {
        WorkTimeTrackerDefault tracker = new WorkTimeTrackerDefault();
        Calendar calendar = Calendar.getInstance();
        WorkdayDefault workday = new WorkdayDefault();

        tracker.putWorkday(calendar.getTime(), workday);

        assertSame(workday, tracker.getWorkday(calendar.getTime()));

        tracker.removeWorkday(calendar.getTime());

        assertNull(tracker.getWorkday(calendar.getTime()));
    }

}
