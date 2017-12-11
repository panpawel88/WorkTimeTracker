package org.ppanek.worktimetracker.model;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.objectbox.Box;

import static org.junit.Assert.assertEquals;

/**
 * Created by pawel on 29.11.2017.
 */

@RunWith(AndroidJUnit4.class)
public class BreakDbTest extends AbstractObjectBoxTest {

    @Test
    public void testCreateAndRead() {
        Box<BreakDb> box = store.boxFor(BreakDb.class);
        BreakDb entity = new BreakDb();

        entity.setBegin(DateUtils.createDate(10, 0));
        entity.setEnd(DateUtils.createDate(12, 0));

        long id = box.put(entity);

        BreakDb retrieved = box.get(id);
        assertEquals(entity, retrieved);
    }
}
