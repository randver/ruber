package is.ru.honn.ruber.driver.service;

import is.ru.honn.ruber.domain.DriverDTO;
import is.ru.honn.ruber.driver.data.DriverDataGateWay;

import java.util.List;

/**
 * Created by Randi on 24.10.2014.
 */
public class DriverServiceData implements DriverService {

    private DriverDataGateWay driverDataGateWay;
    public DriverServiceData(DriverDataGateWay driverDataGateway)
    {
        this.driverDataGateWay = driverDataGateway;
    }



    public List<DriverDTO> getList()
    {
        return driverDataGateWay.getDriverList();
    }

    public Object getDriverDetails(String user)
    {
        return driverDataGateWay.getDriver(user);
    }

    public Object getComments(int driverId){ return driverDataGateWay.getComments(driverId);}

}

