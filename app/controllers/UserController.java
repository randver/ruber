package controllers;


import is.ru.honn.ruber.domain.TripDTO;
import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.users.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.gethistory;
import views.html.userlist;

import java.util.List;

public class UserController extends Controller
{
  protected static ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/UserService.xml");

  public static Result getHistory(int uuid)
  {
      UserService service = (UserService) ctx.getBean("userService");
      List<TripDTO> triplist;
      triplist = service.getHistory(uuid);

      return ok(gethistory.render(triplist));
  }

    public static Result getUsers()
    {
        UserService service = (UserService) ctx.getBean("userService");
        List<User> userList;
        userList = service.getUsers();

        return ok(userlist.render(userList));
    }
}
