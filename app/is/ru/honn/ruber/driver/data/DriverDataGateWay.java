package is.ru.honn.ruber.driver.data;

import is.ruframework.data.RuDataAccess;

/**
 * Created by Randi on 24.10.2014.
 */
public interface DriverDataGateWay extends RuDataAccess {
    public Object getDriverList();
    public Object getDriver(String user);
    public Object getComments(int driverId);
}
