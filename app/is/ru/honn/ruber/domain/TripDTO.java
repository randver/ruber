package is.ru.honn.ruber.domain;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * A class whici holds detailed information about a trip
 */
public class TripDTO {

    double distance;
    int id;
    String username, driver, car, date, duration;


    public TripDTO() {

    }

    public TripDTO(double distance, String duration, String username, String driver, String car, String date) {

        this.distance = distance;
        this.duration = duration;
        this.username = username;
        this.driver = driver;
        this.car = car;
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(Long startTime, Long endTime) {
        DateTime startDate = new DateTime(startTime*1000);
        DateTime endDate = new DateTime(endTime*1000);
        Period p = new Period(startDate, endDate);
        long hours, minutes, days;
        hours = p.getHours();
        minutes = p.getMinutes();
        days = p.getDays();
        if(days > 0)
        {
            this.duration = days + " Days, " + hours + " Hours," + minutes + " Minutes.";
        }
        else if(hours > 0)
        {
            this.duration = hours + " Hours," + minutes + " Minutes.";
        }
        else
        {
            this.duration = minutes + " Minutes.";
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Long time) {
        Date date = new Date(time*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String result = sdf.format(date);
        this.date = result;
    }
}
