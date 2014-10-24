package is.ru.honn.ruber.users.data;

import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.users.service.UserNotFoundException;
import is.ru.honn.ruber.users.service.UsernameExistsException;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
