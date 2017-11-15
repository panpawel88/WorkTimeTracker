package org.ppanek.worktimetracker.model;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by pawel on 11.11.2017.
 */

public class WorkdayTest {

    @Test
    public void testBasic() throws InvalidWorkdayException {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 10, 11, 8, 0);
        workday.setBegin(calendar.getTime());

        calendar.set(2017, 10, 11, 16, 0);
        workday.setEnd(calendar.getTime());

        long expectedMinutes = 8 * 60;
        assertEquals(expectedMinutes, workday.getWorkTime());
    }

    @Test(expected = InvalidWorkdayException.class)
    public void testInvalidValues() throws InvalidWorkdayException {
        Workday workday = new Workday();

        workday.setBegin(null);
        workday.setEnd(null);

        workday.getWorkTime();
    }

    @Test(expected = InvalidWorkdayException.class)
    public void testBeginAfterEnd() throws InvalidWorkdayException {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 10, 12, 8, 0);
        workday.setEnd(calendar.getTime());

        calendar.add(Calendar.MINUTE, 1);
        workday.setBegin(calendar.getTime());
    }

    @Test(expected = InvalidWorkdayException.class)
    public void testBeginEqualEnd() throws InvalidWorkdayException {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 10, 12, 8, 0);
        workday.setBegin(calendar.getTime());
        workday.setEnd(calendar.getTime());
    }

    @Test(expected = InvalidWorkdayException.class)
    public void testEndBeforeBegin() throws InvalidWorkdayException {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 10, 12, 8, 0);
        workday.setBegin(calendar.getTime());

        calendar.add(Calendar.MINUTE, -1);
        workday.setEnd(calendar.getTime());
    }

    @Test
    public void testAddBreak() {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 10, 11, 8, 0);
        try {
            workday.setBegin(calendar.getTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }

        calendar.set(2017, 10, 11, 16, 0);
        try {
            workday.setEnd(calendar.getTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }

        long expectedMinutes = 8 * 60;
        try {
            assertEquals(expectedMinutes, workday.getWorkTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }

        try {
            Workday.Break aBreak = workday.newBreak();
            calendar.set(2017, 10, 11, 13, 0);
            aBreak.setBegin(calendar.getTime());
            calendar.set(2017, 10, 11, 13, 30);
            aBreak.setEnd(calendar.getTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }

        expectedMinutes -= 30;
        try {
            assertEquals(expectedMinutes, workday.getWorkTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetBreaks() {
        Workday workday = new Workday();

        assertEquals(0, workday.getBreaks().size());

        Workday.Break firstBreak = workday.newBreak();
        assertEquals(1, workday.getBreaks().size());
        assertSame(firstBreak, workday.getBreaks().get(0));

        Workday.Break secondBreak = workday.newBreak();
        assertEquals(2, workday.getBreaks().size());
        assertSame(secondBreak, workday.getBreaks().get(1));

    }

    @Test(expected = InvalidWorkdayException.class)
    public void testInvalidBreak() throws InvalidWorkdayException {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(2017, 10, 11, 8, 0);
            workday.setBegin(calendar.getTime());
            calendar.add(Calendar.HOUR_OF_DAY, 8);
            workday.setEnd(calendar.getTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }

        workday.newBreak();
        workday.getWorkTime();
    }


    @Test(expected = InvalidWorkdayException.class)
    public void testBreakBeforeBegin1() throws InvalidWorkdayException {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(2017, 10, 11, 8, 0);
            workday.setBegin(calendar.getTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }

        Workday.Break aBreak = workday.newBreak();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        aBreak.setBegin(calendar.getTime());
    }

    @Test(expected = InvalidWorkdayException.class)
    public void testBreakBeforeBegin2() throws InvalidWorkdayException {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(2017, 10, 11, 8, 0);
            workday.setBegin(calendar.getTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }

        Workday.Break aBreak = workday.newBreak();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        aBreak.setEnd(calendar.getTime());
    }

    @Test(expected = InvalidWorkdayException.class)
    public void testBreakAfterEnd1() throws InvalidWorkdayException {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(2017, 10, 11, 8, 0);
            workday.setEnd(calendar.getTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }

        Workday.Break aBreak = workday.newBreak();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        aBreak.setBegin(calendar.getTime());
    }

    @Test(expected = InvalidWorkdayException.class)
    public void testBreakAfterEnd2() throws InvalidWorkdayException {
        Workday workday = new Workday();

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(2017, 10, 11, 8, 0);
            workday.setEnd(calendar.getTime());
        } catch (InvalidWorkdayException e) {
            assertTrue(false);
        }

        Workday.Break aBreak = workday.newBreak();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        aBreak.setEnd(calendar.getTime());
    }

    @Test
    public void testRemoveBreak() {
        Workday workday = new Workday();
        Workday.Break aBreak = workday.newBreak();
        assertEquals(1, workday.getBreaks().size());

        workday.removeBreak(aBreak);
        assertEquals(0, workday.getBreaks().size());
    }
}
