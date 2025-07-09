package org.surena.usermanagement.exception;

public class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException() {
        super();
    }

    public InvalidUsernameException(String message) {
        super(message);
    }

    public InvalidUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
