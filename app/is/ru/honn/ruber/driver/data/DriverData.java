package is.ru.honn.ruber.driver.data;

import is.ru.honn.ruber.domain.DriverDTO;
import is.ru.honn.ruber.domain.informationDTO;
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

    public List<informationDTO> getDriverList()
    {
        List<informationDTO> list = new ArrayList<>();
        List<Map<String, Object>> obj;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        obj = jdbcTemplate.queryForList("select ru_users.username, ru_drivers.car from ru_users inner join ru_drivers on ru_users.id = ru_drivers.user_id");

        for (int i = 0; i < obj.size(); i++) {
            HashMap<String, Object> map = (HashMap) obj.get(i);
            informationDTO dto = new informationDTO();

            dto.setUsername((String) map.get("username"));
            dto.setCar((String) map.get("car"));
            list.add(dto);

        }
        return list;
    }

    public List<informationDTO> getDriverDetails(String user)
    {
        List<informationDTO> list = new ArrayList<>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        List<Map<String, Object>> obj = new ArrayList<>();
        obj = jdbcTemplate.queryForList("select u.id, u.username, u.firstname, u.lastname, u.email, d.car \n" +
                "from ru_users u \n" +
                "inner join ru_drivers d on u.id = d.user_id \n" +
                "where u.username = '" + user + "'");

        for (int i = 0; i < obj.size(); i++) {
            HashMap<String, Object> map = (HashMap) obj.get(i);
            informationDTO idto = new informationDTO();

            List<Map<String, Object>> comments = (List<Map<String, Object>>) getComments((Integer) map.get("id"));
            for(int j = 0;j<comments.size(); j++) {
                HashMap<String, Object> map2 = (HashMap) comments.get(j);
                idto.addComment((String) map2.get("comment"));
            }
            idto.setUsername((String) map.get("username"));
            idto.setCar((String) map.get("car"));
            idto.setFirstName((String) map.get("firstname"));
            idto.setLastName((String) map.get("lastname"));
            idto.setEmail((String) map.get("email"));
            list.add(idto);
        }
        return list;
    }

    public Object getComments(int driverId)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.queryForList("select di.comment from ru_driverinfo di where di.driver_id = " + driverId);
    }
}
