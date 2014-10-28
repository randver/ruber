package is.ru.honn.ruber.rides.service;

/**
 * Created by Lenovo on 28.10.2014.
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
