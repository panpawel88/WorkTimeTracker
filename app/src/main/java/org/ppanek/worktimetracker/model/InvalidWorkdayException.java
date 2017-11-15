package org.ppanek.worktimetracker.model;

/**
 * Created by pawel on 12.11.2017.
 */

class InvalidWorkdayException extends Exception {

    public InvalidWorkdayException() {
    }

    public InvalidWorkdayException(String message) {
        super(message);
    }

    public InvalidWorkdayException(String message, Throwable error) {
        super(message, error);
    }
}
