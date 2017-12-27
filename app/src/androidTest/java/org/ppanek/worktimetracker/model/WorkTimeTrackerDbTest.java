package org.ppanek.worktimetracker.model;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * Created by pawel on 27.12.2017.
 */

public class WorkTimeTrackerDbTest extends AbstractWorkTimeTrackerTest {

    private AbstractObjectBoxTest objectBoxTest;

    public WorkTimeTrackerDbTest() {
        objectBoxTest = new AbstractObjectBoxTest() {};
    }

    @Before
    public void setUp() throws IOException {
        objectBoxTest.setUp();
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        objectBoxTest.tearDown();
    }

    @Override
    public IWorkTimeTracker createTracker() {
        return new WorkTimeTrackerDb(objectBoxTest.store.boxFor(WorkdayDb.class));
    }
}
