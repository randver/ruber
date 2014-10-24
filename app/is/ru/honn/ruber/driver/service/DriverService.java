package is.ru.honn.ruber.driver.service;

/**
 * Created by Randi on 24.10.2014.
 */
public interface DriverService {
    public Object getList();
    public Object getDriverDetails(String user);
    public Object getComments(int driverId);
}
