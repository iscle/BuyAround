package cat.buyaround.app.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String password;
    private Direction[] directions;
    private String profilePicture;
    private long birthday;
    private String email;

    public User(String name, String password, Direction[] directions, String profilePicture, long birthday, String email) {
        this.name = name;
        this.password = password;
        this.directions = directions;
        this.profilePicture = profilePicture;
        this.birthday = birthday;
        this.email = email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.password = password;
        this.directions = null;
        this.profilePicture = null;
        this.birthday = -1;
        this.email = email;
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

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
