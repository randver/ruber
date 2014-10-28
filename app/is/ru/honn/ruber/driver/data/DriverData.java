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
        obj = jdbcTemplate.queryForList("select u.username, p.display_name \n" +
                "from ru_users u \n" +
                "inner join ru_drivers d \n" +
                "on u.id = d.user_id \n" +
                "inner join ru_products p on p.id = d.product_id");

        for (int i = 0; i < obj.size(); i++) {
            HashMap<String, Object> map = (HashMap) obj.get(i);
            informationDTO dto = new informationDTO();

            dto.setUsername((String) map.get("username"));
            dto.setCar((String) map.get("display_name"));
            list.add(dto);

        }
        return list;
    }

    public List<informationDTO> getDriverDetails(String user)
    {
        List<informationDTO> list = new ArrayList<>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        List<Map<String, Object>> obj = new ArrayList<>();
        int ratingtemp;
        obj = jdbcTemplate.queryForList("select u.id, u.username, u.firstname, u.lastname, u.email, p.display_name \n" +
                "from ru_users u \n" +
                "inner join ru_drivers d on u.id = d.user_id \n" +
                "inner join ru_products p on d.id = p.id\n" +
                "where u.username = '" + user + "'");

        for (int i = 0; i < obj.size(); i++) {
            HashMap<String, Object> map = (HashMap) obj.get(i);
            informationDTO idto = new informationDTO();
            ratingtemp = 0;
            List<Map<String, Object>> ratings = (List<Map<String, Object>>) getRating((Integer) map.get("id"));
            for(int j = 0;j<ratings.size(); j++) {
                HashMap<String, Object> map2 = (HashMap) ratings.get(j);
                ratingtemp += (Integer) map2.get("rating");
            }
            if(ratings.size() == 0) {
                idto.setRating(0);
            }
            else {
                ratingtemp = ratingtemp/ratings.size();
                idto.setRating(ratingtemp);
            }

            idto.setUsername((String) map.get("username"));
            idto.setCar((String) map.get("display_name"));
            idto.setFirstName((String) map.get("firstname"));
            idto.setLastName((String) map.get("lastname"));
            idto.setEmail((String) map.get("email"));
            idto.setId((Integer) map.get("id"));
            list.add(idto);
        }
        return list;
    }

    public Object getComments(int driverId)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.queryForList("select di.comment, u.username " +
                "from ru_driverinfo di " +
                "inner join ru_users u on u.id = di.user_id " +
                "where di.driver_id = " + driverId);
    }

    public Object getRating(int driverId)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.queryForList("select di.rating from ru_driverinfo di where di.driver_id = " + driverId);
    }

    public void addComment(int driverId, String comment)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        jdbcTemplate.update("insert into ru_driverinfo(driver_id, comment) values(driverId, comment)");
    }
}
