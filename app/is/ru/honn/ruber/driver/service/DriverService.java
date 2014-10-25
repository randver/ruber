package is.ru.honn.ruber.driver.service;

import is.ru.honn.ruber.domain.DriverDTO;

import java.util.List;

/**
 * Created by Randi on 24.10.2014.
 */
public interface DriverService {
    public List<DriverDTO> getList();
    public Object getDriverDetails(String user);
    public Object getComments(int driverId);
}
