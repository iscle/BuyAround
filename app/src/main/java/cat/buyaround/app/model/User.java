package cat.buyaround.app.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String name;
    private String surnames;
    private String password;
    private Direction[] directions;
    private String profilePicture;
    private Date birthday;
    private String email;
    private String phone;

    public User(String name, String surnames, String password, Direction[] directions, String profilePicture, Date birthday, String email, String phone) {
        this.name = name;
        this.surnames = surnames;
        this.password = password;
        this.directions = directions;
        this.profilePicture = profilePicture;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String surnames, Date birthday, String email, String phone, String password) {
        this.name = name;
        this.surnames = surnames;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Direction[] getDirections() {
        return directions;
    }

    public void setDirections(Direction[] directions) {
        this.directions = directions;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
