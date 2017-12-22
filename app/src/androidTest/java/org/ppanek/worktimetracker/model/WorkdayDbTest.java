package org.ppanek.worktimetracker.model;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import io.objectbox.Box;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by pawel on 06.12.2017.
 */

@RunWith(AndroidJUnit4.class)
public class WorkdayDbTest extends AbstractObjectBoxTest {

    @Test
    public void testCreateAndRead() {
        WorkdayDb workday = new WorkdayDb();
        Box<WorkdayDb> box = store.boxFor(WorkdayDb.class);

        workday.setBegin(DateUtils.createDate(10, 0));
        workday.setEnd(DateUtils.createDate(12, 0));

        long id = box.put(workday);

        WorkdayDb retrieved = box.get(id);
        assertEquals(workday, retrieved);
    }

    @Test
    public void testAddBreaks() {
        WorkdayDb workday = new WorkdayDb();
        Box<WorkdayDb> box = store.boxFor(WorkdayDb.class);

        workday.setBegin(DateUtils.createDate(10, 0));

        IBreak firstBreak = workday.newBreak();
        firstBreak.setBegin(DateUtils.createDate(11, 0));
        firstBreak.setEnd(DateUtils.createDate(12, 0));
        workday.putBreak(firstBreak);

        IBreak secondBreak = workday.newBreak();
        secondBreak.setBegin(DateUtils.createDate(12, 0));
        secondBreak.setEnd(DateUtils.createDate(13, 0));
        workday.putBreak(secondBreak);

        workday.setEnd(DateUtils.createDate(19, 0));

        assertEquals(7 * 60, workday.getTotalWorkTime());

        long id = box.put(workday);

        WorkdayDb retrieved = box.get(id);
        assertEquals(workday, retrieved);
    }

    @Test
    public void testEqualsWithDifferentBreaksCount() {
        WorkdayDb workday1 = new WorkdayDb();
        WorkdayDb workday2 = new WorkdayDb();

        Date begin = DateUtils.createDate(10, 0);
        Date end = DateUtils.createDate(18, 0);

        workday1.setBegin(begin);
        workday1.setEnd(end);
        workday2.setBegin(begin);
        workday2.setEnd(end);

        Date break1Begin = DateUtils.createDate(12, 0);
        Date break1End = DateUtils.createDate(13, 0);

        Date break2Begin = DateUtils.createDate(16, 0);
        Date break2End = DateUtils.createDate(17, 0);

        {
            IBreak aBreak = workday1.newBreak();
            aBreak.setBegin(break1Begin);
            aBreak.setEnd(break1End);
            workday1.putBreak(aBreak);
        }

        {
            IBreak aBreak = workday1.newBreak();
            aBreak.setBegin(break2Begin);
            aBreak.setEnd(break2End);
            workday1.putBreak(aBreak);
        }

        {
            IBreak aBreak = workday2.newBreak();
            aBreak.setBegin(break1Begin);
            aBreak.setEnd(break1End);
            workday2.putBreak(aBreak);
        }

        assertNotEquals(workday1, workday2);
        assertNotEquals(workday2, workday1);
    }
}
