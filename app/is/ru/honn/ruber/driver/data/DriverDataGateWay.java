package is.ru.honn.ruber.driver.data;

import is.ru.honn.ruber.domain.informationDTO;
import is.ruframework.data.RuDataAccess;

import java.util.List;

/**
 * This interface is implemented by the Class DriverData and used by DriverService
 */
public interface DriverDataGateWay extends RuDataAccess {
    public List<informationDTO> getDriverList();
    public List<informationDTO> getDriverDetails(String user);
    public Object getComments(int driverId);
    public void addComment(int driverId, String comment);
    public Object getRating(int driverId);
}
