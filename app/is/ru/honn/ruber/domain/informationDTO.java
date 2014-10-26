package is.ru.honn.ruber.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Randi on 25.10.2014.
 */
public class informationDTO {

    List<String> comments;
    int rating;
    String firstName, lastName, username, car, email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public informationDTO() {
        this.comments = new ArrayList<>();
    }

    public informationDTO(List<String> comments, int rating, String firstName, String lastName, String username, String car, String email) {

        this.comments = comments;
        this.rating = rating;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.car = car;
        this.email = email;
    }

    public List<String> getComments() {

        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
    public void addComment(String comment) {
        comments.add(comment);
    }
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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