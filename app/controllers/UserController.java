package controllers;


import is.ru.honn.ruber.users.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.mvc.Controller;
import play.mvc.Result;


import static play.libs.Json.toJson;

public class UserController extends Controller
{
  protected static ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/UserService.xml");

  public static Result getHistory()
  {
      UserService service = (UserService) ctx.getBean("userService");


      Object result = new Object();
      result = service.getHistory(1);

      return ok(toJson(result));
  }
}
