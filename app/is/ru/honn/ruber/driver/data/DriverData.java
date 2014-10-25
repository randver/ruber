package is.ru.honn.ruber.driver.data;

import is.ru.honn.ruber.domain.DriverDTO;
import is.ruframework.data.RuData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Randi on 24.10.2014.
 */
public class DriverData extends RuData implements DriverDataGateWay {

    public List<DriverDTO> getDriverList()
    {
        List<DriverDTO> list = new ArrayList<>();
        List<Map<String, Object>> obj = new ArrayList<>();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        obj = jdbcTemplate.queryForList("select ru_users.username, ru_drivers.car from ru_users inner join ru_drivers on ru_users.id = ru_drivers.user_id");

        for (int i = 0; i < obj.size(); i++) {
            HashMap<String, Object> map = (HashMap) obj.get(i);
            DriverDTO dto = new DriverDTO();

            dto.setUsername((String) map.get("username"));
            dto.setCar((String) map.get("car"));
            list.add(dto);

        }
        return list;
    }

    public Object getDriver(String user)
    {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        List<Map<String, Object>> list = new ArrayList<>();
        list = jdbcTemplate.queryForList("select u.username, d.car, di.comment \n" +
                "from ru_users u \n" +
                "inner join ru_drivers d on u.id = d.user_id \n" +
                "inner join ru_driverinfo di on di.driver_id = d.id \n" +
                "where u.username = '" + user + "'");
        return list;
    }

    public Object getComments(int driverId)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.queryForList("select di.comment" +
                "from ru_driverinfo di " +
                "where di.driver_id = '" + driverId + "'");
    }
}
