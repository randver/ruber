package is.ru.honn.ruber.users.data;

import is.ru.honn.ruber.domain.User;
import is.ruframework.data.RuDataAccess;

public interface UserDataGateway extends RuDataAccess
{
    public int addUser(User user);
    public User getUserByUsername(String username);
    public Object getDriverList();
    public Object getDriver(String user);
    public Object getComments(int driverId);
}