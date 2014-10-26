package is.ru.honn.ruber.domain;

/**
 * Created by Randi on 26.10.2014.
 */
public class TripDTO {

    double distance;
    int duration, id;
    String username, driver, car, date;


    public TripDTO() {

    }

    public TripDTO(double distance, int duration, String username, String driver, String car, String date) {

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(Long startTime, Long endTime) {
        this.duration = 0;
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

    public void setDate(String date) {
        this.date = date;
    }
}
