package is.ru.honn.ruber.rides;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.users.service.UsernameExistsException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Randi on 23.10.2014.
 */
public class RidesData implements RidesDataGateway {

    @Override
    public int addTrip(Trip trip)
    {
        SimpleJdbcInsert insert =
                new SimpleJdbcInsert(getDataSource())
                        .withTableName("ru_trips")
                        .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<String, Object>(6);
        parameters.put("distance", trip.getDistance());
        parameters.put("end_time", trip.getEndTime());
        parameters.put("product_id", trip.getProductId());
        parameters.put("request_time", trip.getRequestTime());
        parameters.put("start_time", trip.getStartTime());
        parameters.put("status", trip.getStatus().toString());


        int returnKey;

        try
        {
            returnKey = insert.executeAndReturnKey(parameters).intValue();
        }
        catch(DataIntegrityViolationException divex)
        {
            throw new UsernameExistsException("Inserting Trip Failed" , divex);
        }

        trip.setId(returnKey);
        return returnKey;
    }

    @Override
    public void setDataSource(DataSource dataSource) {

    }

    @Override
    public DataSource getDataSource() {
        return null;
    }

}