package org.ppanek.worktimetracker.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by pawel on 15.12.2017.
 */

public class WorkTimeTrackerFactoryTest {

    @Rule
    public TemporaryFolder tempDir = new TemporaryFolder();

    @Test
    public void testCreate() {
        Properties props = new Properties(tempDir.getRoot());
        WorkTimeTracker tracker = WorkTimeTrackerFactory.create(props);
        try {
            assertEquals(props.getBackend(), tracker.getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }

        props.setBackend(WorkTimeTracker.class);
        tracker = WorkTimeTrackerFactory.create(props);
        try {
            assertEquals(props.getBackend(), tracker.getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }
}
