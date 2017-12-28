package org.ppanek.worktimetracker.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.ppanek.worktimetracker.model.def.WorkTimeTrackerDefault;
import org.ppanek.worktimetracker.model.interfaces.IWorkTimeTracker;

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
        IWorkTimeTracker tracker = WorkTimeTrackerFactory.create(props);
        try {
            assertEquals(props.getBackend(), tracker.getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }

        props.setBackend(WorkTimeTrackerDefault.class);
        tracker = WorkTimeTrackerFactory.create(props);
        try {
            assertEquals(props.getBackend(), tracker.getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }
}
