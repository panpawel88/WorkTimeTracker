package org.ppanek.worktimetracker.model;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by pawel on 11.11.2017.
 */

public class WorkdayDefaultTest {

    @Test
    public void testBasic() {
        WorkdayDefault workday = new WorkdayDefault();

        Date begin = DateUtils.createDate(8, 0);
        workday.setBegin(begin);

        Date end = DateUtils.createDate(16, 0);
        workday.setEnd(end);

        long expectedMinutes = 8 * 60;
        assertEquals(expectedMinutes, workday.getTotalWorkTime());
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidValues() {
        WorkdayDefault workday = new WorkdayDefault();

        workday.setBegin(null);
        workday.setEnd(null);

        workday.getTotalWorkTime();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBeginAfterEnd() {
        WorkdayDefault workday = new WorkdayDefault();

        Date end = DateUtils.createDate(8, 0);
        workday.setEnd(end);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.MINUTE, 1);
        workday.setBegin(calendar.getTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBeginEqualEnd() {
        WorkdayDefault workday = new WorkdayDefault();

        Date date = DateUtils.createDate(8, 0);
        workday.setBegin(date);
        workday.setEnd(date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEndBeforeBegin() {
        WorkdayDefault workday = new WorkdayDefault();

        Date begin = DateUtils.createDate(8, 0);
        workday.setBegin(begin);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(begin);
        calendar.add(Calendar.MINUTE, -1);
        workday.setEnd(calendar.getTime());
    }

    @Test
    public void testAddBreak() {
        WorkdayDefault workday = new WorkdayDefault();

        Date begin = DateUtils.createDate(8, 0);
        workday.setBegin(begin);

        Date end = DateUtils.createDate(16, 0);
        workday.setEnd(end);

        long expectedMinutes = 8 * 60;
        assertEquals(expectedMinutes, workday.getTotalWorkTime());

        IBreak aBreak = workday.newBreak();
        aBreak.setBegin(DateUtils.createDate(13, 0));
        aBreak.setEnd((DateUtils.createDate(13, 30)));
        workday.putBreak(aBreak);

        expectedMinutes -= 30;
        assertEquals(expectedMinutes, workday.getTotalWorkTime());
    }

    @Test
    public void testGetBreaks() {
        WorkdayDefault workday = new WorkdayDefault();

        assertEquals(0, workday.getBreaks().size());

        IBreak firstBreak = workday.newBreak();
        assertEquals(0, workday.getBreaks().size());

        workday.putBreak(firstBreak);
        assertEquals(1, workday.getBreaks().size());
        assertSame(firstBreak, workday.getBreaks().get(0));

        IBreak secondBreak = workday.newBreak();
        workday.putBreak(secondBreak);
        assertEquals(2, workday.getBreaks().size());
        assertSame(secondBreak, workday.getBreaks().get(1));
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidBreak() {
        WorkdayDefault workday = new WorkdayDefault();

        workday.setBegin(DateUtils.createDate(8, 0));
        workday.setEnd(DateUtils.createDate(16,0));

        IBreak aBreak = workday.newBreak();
        workday.putBreak(aBreak);
        workday.getTotalWorkTime();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBreakBeforeBegin1() {
        WorkdayDefault workday = new WorkdayDefault();
        workday.setBegin(DateUtils.createDate(8, 0));

        IBreak aBreak = workday.newBreak();
        aBreak.setBegin(DateUtils.createDate(7, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBreakBeforeBegin2() {
        WorkdayDefault workday = new WorkdayDefault();
        workday.setBegin(DateUtils.createDate(8, 0));

        IBreak aBreak = workday.newBreak();
        aBreak.setEnd(DateUtils.createDate(7, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBreakAfterEnd1() {
        WorkdayDefault workday = new WorkdayDefault();
        workday.setEnd(DateUtils.createDate(8, 0));

        IBreak aBreak = workday.newBreak();
        aBreak.setBegin(DateUtils.createDate(9, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBreakAfterEnd2() {
        WorkdayDefault workday = new WorkdayDefault();
        workday.setEnd(DateUtils.createDate(8, 0));

        IBreak aBreak = workday.newBreak();
        aBreak.setEnd(DateUtils.createDate(9, 0));
    }

    @Test
    public void testRemoveBreak() {
        WorkdayDefault workday = new WorkdayDefault();
        IBreak aBreak = workday.newBreak();
        workday.putBreak(aBreak);
        assertEquals(1, workday.getBreaks().size());

        workday.removeBreak(aBreak);
        assertEquals(0, workday.getBreaks().size());
    }
}
