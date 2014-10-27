package is.ru.honn.ruber.users.data;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.domain.TripDTO;
import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.users.service.UserNotFoundException;
import is.ru.honn.ruber.users.service.UsernameExistsException;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.*;

public class UserData extends RuData implements UserDataGateway
{
  public int addUser(User user)
  {
    SimpleJdbcInsert insert =
        new SimpleJdbcInsert(getDataSource())
            .withTableName("ru_users")
            .usingGeneratedKeyColumns("id");

    Map<String, Object> parameters = new HashMap<String, Object>(6);
    parameters.put("username", user.getUsername());
    parameters.put("firstname", user.getFirstName());
    parameters.put("lastname", user.getLastName());
    parameters.put("password", user.getPassword());
    parameters.put("email", user.getEmail());
    parameters.put("registered", new Date());

    int returnKey;

    try
    {
      returnKey = insert.executeAndReturnKey(parameters).intValue();
    }
    catch(DataIntegrityViolationException divex)
    {
      throw new UsernameExistsException("User " + user.getUsername() + " already exits", divex);
    }

    user.setId(returnKey);
    return returnKey;
  }

  public User getUserByUsername(String username)
  {
    Collection<String> users;
    JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

    User user;
    try
    {
      user = (User)jdbcTemplate.queryForObject(
          "select * from ru_users where username = '" + username + "'", new UserRowMapper());
    }
    catch (EmptyResultDataAccessException erdaex)
    {
      throw new UserNotFoundException("No user found with username: " + username);
    }
    return user;
  }

    public List<TripDTO> getHistory(int uuid)
    {

        List<TripDTO> list = new ArrayList<>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        List<Map<String, Object>> obj = new ArrayList<>();
        User user;
        try
        {
            user = (User)jdbcTemplate.queryForObject(
                    "select * from ru_users where id = '" + uuid + "'", new UserRowMapper());

            obj = jdbcTemplate.queryForList(
                    "select u.username, t.distance, t.start_time, t.end_time, p.display_name \n" +
                            "from ru_trips t \n" +
                            "inner join ru_products p on p.id = t.product_id \n" +
                            "inner join ru_drivers d on d.id = p.driver_id \n" +
                            "inner join ru_users u on u.id = d.user_id \n" +
                            "where uuid = '" + uuid + "'");

            for (int i = 0; i < obj.size(); i++) {
                HashMap<String, Object> map = (HashMap) obj.get(i);
                TripDTO tdto = new TripDTO();

                tdto.setUsername(user.getUsername());
                tdto.setDistance((Double) map.get("distance"));
                tdto.setCar((String) map.get("display_name"));
                tdto.setDuration((Long)map.get("start_time"), (Long)map.get("end_time"));
                tdto.setDate((Long) map.get("start_time"));
                tdto.setDriver((String)map.get("username"));
                list.add(tdto);
            }
        }
        catch (EmptyResultDataAccessException erdaex)
        {
            throw new UserNotFoundException("No trips found with uuid: " + uuid);
        }


        return list;

    }


}
