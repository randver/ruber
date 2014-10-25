package is.ru.honn.ruber.domain;

/**
 * Created by Randi on 24.10.2014.
 */
public class DriverDTO {

    String username, car;

    public DriverDTO() {
    }

    public DriverDTO(String username, String car) {
        this.username = username;
        this.car = car;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
