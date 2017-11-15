package org.ppanek.worktimetracker.model;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by pawel on 13.11.2017.
 */

public class DateUtilsTest {

    @Test
    public void testNulls() {
        Date begin = null;
        Date end = null;
        assertFalse(DateUtils.areEqual(begin, end));
        assertFalse(DateUtils.isLess(begin, end));
        assertFalse(DateUtils.isLessOrEqual(begin, end));
        assertFalse(DateUtils.isGreater(begin, end));
        assertFalse(DateUtils.isGreaterOrEqual(begin, end));

        begin = new Date(0);
        assertFalse(DateUtils.areEqual(begin, end));
        assertFalse(DateUtils.isLess(begin, end));
        assertFalse(DateUtils.isLessOrEqual(begin, end));
        assertFalse(DateUtils.isGreater(begin, end));
        assertFalse(DateUtils.isGreaterOrEqual(begin, end));

        end = begin;
        begin = null;
        assertFalse(DateUtils.areEqual(begin, end));
        assertFalse(DateUtils.isLess(begin, end));
        assertFalse(DateUtils.isLessOrEqual(begin, end));
        assertFalse(DateUtils.isGreater(begin, end));
        assertFalse(DateUtils.isGreaterOrEqual(begin, end));
    }

    @Test
    public void testValidValues() {
        Date begin = new Date(0);
        Date end = new Date(1);

        assertFalse(DateUtils.areEqual(begin, end));
        assertTrue(DateUtils.isLess(begin, end));
        assertTrue(DateUtils.isLessOrEqual(begin, end));
        assertFalse(DateUtils.isGreater(begin, end));
        assertFalse(DateUtils.isGreaterOrEqual(begin, end));
    }

    @Test
    public void testIsAfterOneDaySinceEpoch() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date valid = calendar.getTime();
        assertTrue(DateUtils.isAfterOneDaySinceEpoch(valid));

        calendar.setTime(new Date(0));
        Date invalid = calendar.getTime();
        assertFalse(DateUtils.isAfterOneDaySinceEpoch(invalid));

        calendar.setTime(new Date(0));
        calendar.add(Calendar.HOUR_OF_DAY, 23);
        calendar.add(Calendar.MINUTE, 59);
        invalid = calendar.getTime();
        assertFalse(DateUtils.isAfterOneDaySinceEpoch(invalid));
    }

    @Test
    public void testCreateDateFromHoursAndMinutes() {
        Integer expected[][] = new Integer[][] {{0, 0}, {0, 59}, {1, 0}, {12, 0}, {23, 59}};

        for (Integer[] pair : expected) {
            int hour = pair[0];
            int minute = pair[1];

            Date date = DateUtils.createDate(hour, minute);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            assertEquals(hour, calendar.get(Calendar.HOUR_OF_DAY));
            assertEquals(minute, calendar.get(Calendar.MINUTE));
        }

        Integer invalid[][] = new Integer[][] {{-1, 0}, {0, -1}, {0, 60}, {25, 0}};
        for (Integer[] pair : invalid) {
            try {
                int hour = pair[0];
                int minute = pair[1];
                Date date = DateUtils.createDate(hour, minute);
                fail("Exception not thrown");
            } catch (IllegalArgumentException exception) {
                // intentionally empty
            }
        }
    }

}
