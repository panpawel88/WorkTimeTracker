package org.ppanek.worktimetracker.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;

/**
 * Created by pawel on 15.12.2017.
 */

public class PropertiesTest {

    @Rule
    public TemporaryFolder tempDir = new TemporaryFolder();

    @Test
    public void testLoadAndStore() {
        Properties props = new Properties(tempDir.getRoot());
        props.setBackend(BackendMock.class);
        try {
            props.store("");
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        props = new Properties(tempDir.getRoot());
        try {
            assertEquals(BackendMock.class, props.getBackend());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCustomProperties() {
        final String customName = "customName";
        final String customValue = "customValue";

        Properties props = new Properties(tempDir.getRoot());
        props.setProperty(customName, customValue);

        try {
            assertEquals(Properties.DEFAULT_BACKEND, props.getBackend().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }

        try {
            props.store("");
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        props = new Properties(tempDir.getRoot());
        assertEquals(customValue, props.getProperty(customName));

        try {
            props = new Properties(tempDir.newFolder());
            assertNull(props.getProperty(customName));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

}
