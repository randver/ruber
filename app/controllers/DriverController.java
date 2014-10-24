package controllers;


import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.users.data.UserDataGateway;
import is.ru.honn.ruber.users.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import play.mvc.Controller;
import play.data.Form;
import play.mvc.Result;

import java.util.ArrayList;

import static play.libs.Json.toJson;

public class DriverController extends Controller
{
    protected static ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/DriverService.xml");
    public static Result getDrivers()
    {
        UserService service = (UserService) ctx.getBean("userService");


        Object result = new Object();
        result = service.getList();

        return ok(toJson(result));
    }

    public static Result getDriverInfo(String username)
    {
        UserService service = (UserService) ctx.getBean("userService");
        Object result = new Object();
        result = service.getDriverDetails(username);

        return ok(toJson(result));
    }

    public static Result getComments(int driverId)
    {
        UserService service = (UserService) ctx.getBean("userService");
        Object result = new Object();
        result = service.getComments(driverId);

        return ok(toJson(result));
    }
}

