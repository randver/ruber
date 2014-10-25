package controllers;


import is.ru.honn.ruber.domain.DriverDTO;
import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.driver.service.DriverService;
import is.ru.honn.ruber.users.data.UserDataGateway;
import is.ru.honn.ruber.users.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import play.mvc.Controller;
import play.data.Form;
import play.mvc.Result;
import views.html.driverlist;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;

public class DriverController extends Controller
{
    protected static ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/DriverService.xml");
    public static Result getDrivers()
    {
        DriverService service = (DriverService) ctx.getBean("driverService");


        List<DriverDTO> result;
        result = service.getList();

        return ok(driverlist.render((scala.collection.immutable.List<DriverDTO>) result));
    }

    public static Result getDriverInfo(String username)
    {
        DriverService service = (DriverService) ctx.getBean("driverService");
        Object result;
        result = service.getDriverDetails(username);

        return ok(toJson(result));
    }

    public static Result getComments(int driverId)
    {
        DriverService service = (DriverService) ctx.getBean("driverService");
        Object result = new Object();
        result = service.getComments(driverId);

        return ok(toJson(result));
    }
}

