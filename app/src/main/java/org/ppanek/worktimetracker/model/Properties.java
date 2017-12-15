package org.ppanek.worktimetracker.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

/**
 * Created by pawel on 15.12.2017.
 */

public class Properties extends java.util.Properties {
    public static final String BACKEND = "org.ppanek.worktimetracker.property.backend";
    public static final String DEFAULT_BACKEND = "org.ppanek.worktimetracker.model.WorkTimeTrackerDb";

    private static final String PROPERTIES_FILE = "worktimetracker.properties";
    private final File rootDir;

    public Properties(File rootDir) {
        this.rootDir = rootDir;
        try {
            FileReader reader = new FileReader(resolvePropsFile());
            load(reader);
        } catch (IOException e) {
            setDefaultValues();
        }
    }

    private void setDefaultValues() {
        setProperty(BACKEND, DEFAULT_BACKEND);
    }

    private File resolvePropsFile() {
        URI propsURI = rootDir.toURI().resolve(PROPERTIES_FILE);
        return new File(propsURI);
    }

    public void store(String comments) throws IOException {
        store(new FileWriter(resolvePropsFile()), comments);
    }

    public Class getBackend() throws ClassNotFoundException {
        String backendName = getProperty(BACKEND);
        return Class.forName(backendName);
    }

    public void setBackend(Class backendName) {
        setProperty(BACKEND, backendName.getName());
    }
}
