package org.ppanek.worktimetracker.model;

import org.junit.After;
import org.junit.Before;
import org.ppanek.worktimetracker.model.MyObjectBox;

import java.io.File;
import java.io.IOException;

import io.objectbox.BoxStore;

public abstract class AbstractObjectBoxTest {
    protected File boxStoreDir;
    protected BoxStore store;

    @Before
    public void setUp() throws IOException {
        File tempFile = File.createTempFile("object-store-test", "");
        tempFile.delete();
        boxStoreDir = tempFile;
        store = MyObjectBox.builder().directory(boxStoreDir).build();
    }

    @After
    public void tearDown() throws Exception {
        if (store != null) {
            store.close();
            store.deleteAllFiles();
        }
    }

}
