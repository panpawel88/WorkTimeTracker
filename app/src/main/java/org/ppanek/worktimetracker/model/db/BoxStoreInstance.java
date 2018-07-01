package org.ppanek.worktimetracker.model.db;

import io.objectbox.BoxStore;

public class BoxStoreInstance {

    private static BoxStore instance;

    public static BoxStore Get() {
        return instance;
    }
    
    public static void Set(BoxStore store) {
        instance = store;
    }
}
