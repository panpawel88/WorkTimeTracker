package org.ppanek.worktimetracker.model;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import io.objectbox.Box;

import static org.junit.Assert.assertEquals;

/**
 * Created by pawel on 06.12.2017.
 */

@RunWith(AndroidJUnit4.class)
public class WorkdayEntityTest extends AbstractObjectBoxTest {

    @Test
    public void testCreateAndRead() {
        WorkdayEntity workday = new WorkdayEntity();
        Box<WorkdayEntity> box = store.boxFor(WorkdayEntity.class);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        workday.setBegin(calendar.getTime());

        calendar.add(Calendar.HOUR_OF_DAY, 2);
        workday.setEnd(calendar.getTime());

        long id = box.put(workday);

        WorkdayEntity retrieved = box.get(id);
        assertEquals(workday, retrieved);
    }
}