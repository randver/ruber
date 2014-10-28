package is.ru.honn.ruber.rides.service;

/**
 Exception class for the Ride service
 */
public class TripException extends Throwable {

    public TripException() {
    }

    public TripException(String message) {
        super(message);
    }

    public TripException(String message, Throwable cause) {
        super(message, cause);
    }
}
