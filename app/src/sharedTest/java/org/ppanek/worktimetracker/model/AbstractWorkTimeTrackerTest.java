package org.ppanek.worktimetracker.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ppanek.worktimetracker.model.interfaces.IWorkTimeTracker;
import org.ppanek.worktimetracker.model.interfaces.IWorkday;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by pawel on 13.11.2017.
 */

public abstract class AbstractWorkTimeTrackerTest {

    public abstract IWorkTimeTracker createTracker();

    @Test
    public void testSetAndGetWorkday() {
        IWorkTimeTracker tracker = createTracker();
        IWorkday aWorkday = tracker.newWorkday();

        Calendar calendar = Calendar.getInstance();
        tracker.putWorkday(calendar.getTime(), aWorkday);

        assertEquals(aWorkday, tracker.getWorkday(calendar.getTime()));
    }

    @Test
    public void testInvalidValues() {
        IWorkTimeTracker tracker = createTracker();
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

        try {
            tracker.putWorkday(Calendar.getInstance().getTime(), null);
            fail();
        } catch (IllegalArgumentException e) {
            // intentionally empty
        }
    }

    @Test
    public void testSetAndGetWorkdayInSameDay() {
        IWorkTimeTracker tracker = createTracker();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);

        IWorkday workday = tracker.newWorkday();

        tracker.putWorkday(calendar.getTime(), workday);
        assertEquals(workday, tracker.getWorkday(calendar.getTime()));

        calendar.add(Calendar.HOUR_OF_DAY, 8);
        assertEquals(workday, tracker.getWorkday(calendar.getTime()));
    }

    @Test
    public void testSetAndRemoveWorkday() {
        IWorkTimeTracker tracker = createTracker();
        Calendar calendar = Calendar.getInstance();
        IWorkday workday = tracker.newWorkday();

        tracker.putWorkday(calendar.getTime(), workday);

        assertEquals(workday, tracker.getWorkday(calendar.getTime()));

        tracker.removeWorkday(calendar.getTime());

        assertNull(tracker.getWorkday(calendar.getTime()));
    }

    @Test
    public void testGetWhen() {
        IWorkTimeTracker tracker = createTracker();
        IWorkday workday = tracker.newWorkday();

        Date whenExpected = Calendar.getInstance().getTime();
        tracker.putWorkday(whenExpected, workday);

        Date whenRetrieved = tracker.getWorkday(whenExpected).getWhen();
        compareDates(whenExpected, whenRetrieved);
    }

    @Test
    public void testGetAllWorkdays() {
        IWorkTimeTracker tracker = createTracker();
        List<? extends IWorkday> allWorkdays = tracker.getAllWorkdays();
        assertEquals(0, allWorkdays.size());

        IWorkday firstWorkday = tracker.newWorkday();
        Calendar calendar = Calendar.getInstance();
        Date firstWhen = calendar.getTime();
        tracker.putWorkday(firstWhen, firstWorkday);

        allWorkdays = tracker.getAllWorkdays();
        assertEquals(1, allWorkdays.size());
        compareDates(firstWhen, allWorkdays.get(0).getWhen());

        IWorkday secondWorkday = tracker.newWorkday();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date secondWhen = calendar.getTime();
        tracker.putWorkday(secondWhen, secondWorkday);

        allWorkdays = tracker.getAllWorkdays();
        assertEquals(2, allWorkdays.size());
        IWorkday secondRetrieved = allWorkdays.get(0).equals(firstWorkday) ? allWorkdays.get(1) : allWorkdays.get(0);
        compareDates(secondWhen, secondRetrieved.getWhen());

        tracker.removeWorkday(firstWhen);
        assertEquals(1, tracker.getAllWorkdays().size());
        tracker.removeWorkday(secondWhen);
        assertEquals(0, tracker.getAllWorkdays().size());
    }
    
    @Test
    public void testGetWorkdaysInRange() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        calendar.set(Calendar.HOUR, 4);
        
        Date beginDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date firstWorkdayDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        Date secondWorkdayDate = calendar.getTime();
        calendar.add(Calendar.HOUR, 4);
        Date endDate = calendar.getTime();

        IWorkTimeTracker tracker = createTracker();

        IWorkday firstWorkday = tracker.newWorkday();
        tracker.putWorkday(firstWorkdayDate, firstWorkday);

        IWorkday secondWorkday = tracker.newWorkday();
        tracker.putWorkday(secondWorkdayDate, secondWorkday);

        List<? extends IWorkday> workdays = tracker.getWorkdays(beginDate, endDate);
        assertEquals(2, workdays.size());
        assertTrue(workdays.contains(firstWorkday));
        assertTrue(workdays.contains(secondWorkday));

        workdays = tracker.getWorkdays(beginDate, beginDate);
        assertEquals(0, workdays.size());

        workdays = tracker.getWorkdays(endDate, endDate);
        assertEquals(1, workdays.size());
        assertTrue(workdays.contains(secondWorkday));
    }

    private void compareDates(Date dateExpected, Date dateRetrieved) {
        Calendar calendarExpected = Calendar.getInstance();
        calendarExpected.setTime(dateExpected);

        Calendar calendarRetrieved = Calendar.getInstance();
        calendarRetrieved.setTime(dateRetrieved);

        Integer[] fields = new Integer[] {Calendar.YEAR, Calendar.DAY_OF_YEAR, Calendar.MONTH};
        for (Integer field : fields)
            assertEquals(calendarExpected.get(field), calendarRetrieved.get(field));
    }
}
