package is.ru.honn.ruber.driver.data;

import is.ru.honn.ruber.domain.informationDTO;
import is.ruframework.data.RuData;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class which pulls information from the database to pass on
 */
public class DriverData extends RuData implements DriverDataGateWay {

    /**
     * A function which pulls from the database the drivers and their cars to pass to the view
     * Hashmap was used instead of Rowmapper
     * @return
     */
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

    /**
     * A function which takes in a username of a single driver and pulls from database detailed information about
     * a driver and puts it in a DTO class to pass to view
     * @param user
     * @return
     */
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

    /**
     * A function which pulls from a database all comments for a specific driver
     * @param driverId
     * @return
     */
    public Object getComments(int driverId)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.queryForList("select di.comment, u.username " +
                "from ru_driverinfo di " +
                "inner join ru_users u on u.id = di.user_id " +
                "where di.driver_id = " + driverId);
    }

    /**
     * A function which pulls the rating for a specific driver from database
     * @param driverId
     * @return
     */
    public Object getRating(int driverId)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.queryForList("select di.rating from ru_driverinfo di where di.driver_id = " + driverId);
    }

    /**
     * A function which receives a takes in a comment for a specific driver and adds to database
     * @param driverId
     * @param comment
     */
    public void addComment(int driverId, String comment)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        jdbcTemplate.update("insert into ru_driverinfo(driver_id, comment) values(driverId, comment)");
    }
}
