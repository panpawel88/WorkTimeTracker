package org.ppanek.worktimetracker.model;

/**
 * Created by pawel on 15.12.2017.
 */

public class WorkTimeTrackerFactory {

    public static WorkTimeTracker create(Properties props) {
        try {
            Class backendClass = props.getBackend();
            return (WorkTimeTracker) backendClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}