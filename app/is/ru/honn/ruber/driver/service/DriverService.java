package is.ru.honn.ruber.driver.service;

import is.ru.honn.ruber.domain.DriverDTO;
import is.ru.honn.ruber.domain.informationDTO;

import java.util.List;

/**
 * Created by Randi on 24.10.2014.
 */
public interface DriverService {
    public List<informationDTO> getList();
    public List<informationDTO> getDriverDetails(String user);
    public Object getComments(int driverId);
}
