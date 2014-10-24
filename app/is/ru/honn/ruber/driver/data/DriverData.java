package is.ru.honn.ruber.driver.data;

import is.ruframework.data.RuData;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Randi on 24.10.2014.
 */
public class DriverData extends RuData implements DriverDataGateWay {

    public Object getDriverList()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.queryForList("select ru_users.username, ru_drivers.car from ru_users inner join ru_drivers on ru_users.id = ru_drivers.user_id");
    }

    public Object getDriver(String user)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.queryForList("select u.username, d.car, di.comment \n" +
                "from ru_users u\n" +
                "inner join ru_drivers d on u.id = d.user_id\n" +
                "right join ru_driverinfo di on di.driver_id = d.id\n" +
                "where u.username = '" + user + "'");
    }

    public Object getComments(int driverId)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.queryForList("select di.comment" +
                "from ru_driverinfo di " +
                "where di.driver_id = '" + driverId + "'");
    }
}
