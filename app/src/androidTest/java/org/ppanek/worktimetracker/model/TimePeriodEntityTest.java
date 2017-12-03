package org.ppanek.worktimetracker.model;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import io.objectbox.Box;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by pawel on 29.11.2017.
 */

@RunWith(AndroidJUnit4.class)
public class TimePeriodEntityTest extends AbstractObjectBoxTest {

    @Test
    public void testCreateAndRead() {
        Box<TimePeriodEntity> box = store.boxFor(TimePeriodEntity.class);
        TimePeriodEntity entity = new TimePeriodEntity();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        entity.setBegin(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        entity.setEnd(calendar.getTime());

        long id = box.put(entity);
        assertNotEquals(0, id);

        TimePeriodEntity retrieved = box.get(id);
        assertEquals(entity, retrieved);
    }
}
