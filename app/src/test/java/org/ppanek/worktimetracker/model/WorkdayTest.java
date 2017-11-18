package org.ppanek.worktimetracker.model;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by pawel on 11.11.2017.
 */

public class WorkdayTest {

    @Test
    public void testBasic() {
        Workday workday = new Workday();

        Date begin = DateUtils.createDate(8, 0);
        workday.setBegin(begin);

        Date end = DateUtils.createDate(16, 0);
        workday.setEnd(end);

        long expectedMinutes = 8 * 60;
        assertEquals(expectedMinutes, workday.getTotalWorkTime());
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidValues() {
        Workday workday = new Workday();

        workday.setBegin(null);
        workday.setEnd(null);

        workday.getTotalWorkTime();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBeginAfterEnd() {
        Workday workday = new Workday();

        Date end = DateUtils.createDate(8, 0);
        workday.setEnd(end);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.MINUTE, 1);
        workday.setBegin(calendar.getTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBeginEqualEnd() {
        Workday workday = new Workday();

        Date date = DateUtils.createDate(8, 0);
        workday.setBegin(date);
        workday.setEnd(date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEndBeforeBegin() {
        Workday workday = new Workday();

        Date begin = DateUtils.createDate(8, 0);
        workday.setBegin(begin);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(begin);
        calendar.add(Calendar.MINUTE, -1);
        workday.setEnd(calendar.getTime());
    }

    @Test
    public void testAddBreak() {
        Workday workday = new Workday();

        Date begin = DateUtils.createDate(8, 0);
        workday.setBegin(begin);

        Date end = DateUtils.createDate(16, 0);
        workday.setEnd(end);

        long expectedMinutes = 8 * 60;
        assertEquals(expectedMinutes, workday.getTotalWorkTime());

        IBreak aBreak = workday.newBreak();
        aBreak.setBegin(DateUtils.createDate(13, 0));
        aBreak.setEnd((DateUtils.createDate(13, 30)));

        expectedMinutes -= 30;
        assertEquals(expectedMinutes, workday.getTotalWorkTime());
    }

    @Test
    public void testGetBreaks() {
        Workday workday = new Workday();

        assertEquals(0, workday.getBreaks().size());

        IBreak firstBreak = workday.newBreak();
        assertEquals(1, workday.getBreaks().size());
        assertSame(firstBreak, workday.getBreaks().get(0));

        IBreak secondBreak = workday.newBreak();
        assertEquals(2, workday.getBreaks().size());
        assertSame(secondBreak, workday.getBreaks().get(1));
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidBreak() {
        Workday workday = new Workday();

        workday.setBegin(DateUtils.createDate(8, 0));
        workday.setEnd(DateUtils.createDate(16,0));

        workday.newBreak();
        workday.getTotalWorkTime();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBreakBeforeBegin1() {
        Workday workday = new Workday();
        workday.setBegin(DateUtils.createDate(8, 0));

        IBreak aBreak = workday.newBreak();
        aBreak.setBegin(DateUtils.createDate(7, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBreakBeforeBegin2() {
        Workday workday = new Workday();
        workday.setBegin(DateUtils.createDate(8, 0));

        IBreak aBreak = workday.newBreak();
        aBreak.setEnd(DateUtils.createDate(7, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBreakAfterEnd1() {
        Workday workday = new Workday();
        workday.setEnd(DateUtils.createDate(8, 0));

        IBreak aBreak = workday.newBreak();
        aBreak.setBegin(DateUtils.createDate(9, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBreakAfterEnd2() {
        Workday workday = new Workday();
        workday.setEnd(DateUtils.createDate(8, 0));

        IBreak aBreak = workday.newBreak();
        aBreak.setEnd(DateUtils.createDate(9, 0));
    }

    @Test
    public void testRemoveBreak() {
        Workday workday = new Workday();
        IBreak aBreak = workday.newBreak();
        assertEquals(1, workday.getBreaks().size());

        workday.removeBreak(aBreak);
        assertEquals(0, workday.getBreaks().size());
    }
}
