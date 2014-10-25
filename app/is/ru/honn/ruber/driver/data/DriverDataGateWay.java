package is.ru.honn.ruber.driver.data;

import is.ru.honn.ruber.domain.DriverDTO;
import is.ruframework.data.RuDataAccess;

import java.util.List;

/**
 * Created by Randi on 24.10.2014.
 */
public interface DriverDataGateWay extends RuDataAccess {
    public List<DriverDTO> getDriverList();
    public Object getDriver(String user);
    public Object getComments(int driverId);
}
