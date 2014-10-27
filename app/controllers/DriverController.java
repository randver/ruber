package controllers;


import is.ru.honn.ruber.domain.informationDTO;
import is.ru.honn.ruber.driver.service.DriverService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.driverdetails;
import views.html.driverlist;

import java.util.List;

import static play.libs.Json.toJson;

public class DriverController extends Controller
{
    protected static ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/DriverService.xml");

    public static Result getDrivers()
    {
        DriverService service = (DriverService) ctx.getBean("driverService");


        List<informationDTO> drivers;
        drivers = service.getList();

        return ok(driverlist.render(drivers));
    }

    public static Result getDriverDetails(String username)
    {
        DriverService service = (DriverService) ctx.getBean("driverService");
        List<informationDTO> details;
        details = service.getDriverDetails(username);

        return ok(driverdetails.render(details));
    }

    public static Result getComments(int driverId)
    {
        DriverService service = (DriverService) ctx.getBean("driverService");
        Object result = new Object();
        result = service.getComments(driverId);

        return ok(toJson(result));
    }

    public static void addComment(int driverId, String comment)
    {
        DriverService service = (DriverService) ctx.getBean("driverService");
        service.addComment(driverId, comment);
    }
}

