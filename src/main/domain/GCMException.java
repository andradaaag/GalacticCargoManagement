package main.domain;

/**
 * Represents the Galactic Cargo Management custom Exception class being thrown throughout the logic level of the application and caught in the console
 */
public class GCMException extends RuntimeException {
    public GCMException(String message) {
        super(message);
    }
}
