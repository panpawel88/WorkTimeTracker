package org.ppanek.worktimetracker.model;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.objectbox.Box;

import static org.junit.Assert.assertEquals;

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

        IBreak secondBreak = workday.newBreak();
        secondBreak.setBegin(DateUtils.createDate(12, 0));
        secondBreak.setEnd(DateUtils.createDate(13, 0));

        workday.setEnd(DateUtils.createDate(19, 0));

        assertEquals(7 * 60, workday.getTotalWorkTime());

        long id = box.put(workday);

        WorkdayDb retrieved = box.get(id);
        assertEquals(workday, retrieved);
    }
}
