package is.ru.honn.ruber.rides.data;

import is.ru.honn.ruber.domain.Trip;
import is.ruframework.data.RuDataAccess;

/**
 * Created by Lenovo on 23.10.2014.
 */
public interface RidesDataGateway extends RuDataAccess{

    public int addTrip(Trip trip, int userId);

}
