package is.ru.honn.ruber.driver.data;

import is.ru.honn.ruber.domain.DriverDTO;
import is.ru.honn.ruber.domain.informationDTO;
import is.ruframework.data.RuDataAccess;

import java.util.List;

/**
 * Created by Randi on 24.10.2014.
 */
public interface DriverDataGateWay extends RuDataAccess {
    public List<informationDTO> getDriverList();
    public List<informationDTO> getDriverDetails(String user);
    public Object getComments(int driverId);
    public void addComment(int driverId, String comment);
}
