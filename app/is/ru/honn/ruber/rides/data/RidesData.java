package is.ru.honn.ruber.rides.data;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.rides.service.TripException;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

/**
 * A class which puts trips into a database which are read from an url with TripReader
 */
public class RidesData extends RuData implements RidesDataGateway {

    /**
     * Takes in a Trip for a certain user and adds the information to the database
     * Returns a key which is the auto incremented id so it can be added to the trip
     * @param trip
     * @param userId
     * @return
     * @throws is.ru.honn.ruber.rides.service.TripException
     */
    @Override
    public int addTrip(Trip trip, int userId) throws TripException {
        SimpleJdbcInsert insert =
                new SimpleJdbcInsert(getDataSource())
                        .withTableName("ru_trips")
                        .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<String, Object>(7);
        parameters.put("distance", trip.getDistance());
        parameters.put("end_time", trip.getEndTime());
        parameters.put("product_id", trip.getProductId());
        parameters.put("request_time", trip.getRequestTime());
        parameters.put("start_time", trip.getStartTime());
        parameters.put("uuid", userId);
        parameters.put("status", trip.getStatus().toString());


        int returnKey;

        try
        {
            returnKey = insert.executeAndReturnKey(parameters).intValue();
        }
        catch(DataIntegrityViolationException divex)
        {
            throw new TripException("Inserting Trip Failed" , divex);
        }

        trip.setId(returnKey);
        return returnKey;
    }

}
