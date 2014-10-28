package is.ru.honn.ruber.driver.service;

import is.ru.honn.ruber.domain.DriverDTO;
import is.ru.honn.ruber.domain.informationDTO;
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



    public List<informationDTO> getList()
    {
        return driverDataGateWay.getDriverList();
    }

    public List<informationDTO> getDriverDetails(String user)
    {
        return driverDataGateWay.getDriverDetails(user);
    }

    public Object getComments(int driverId){ return driverDataGateWay.getComments(driverId);}

    public void addComment(int driverId, String comment) { driverDataGateWay.addComment(driverId, comment);}

    public Object getRating(int driverId) { return driverDataGateWay.getRating(driverId);}
}

