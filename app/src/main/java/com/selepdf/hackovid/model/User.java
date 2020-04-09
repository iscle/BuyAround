package com.selepdf.hackovid.model;

public class User {
    private String name;
    private String password;
    private Direction direction;
    private String profilePicture;
    private long birthday;
    private String email;

    public User(String name, String password, Direction direction, String profilePicture, long birthday, String email) {
        this.name = name;
        this.password = password;
        this.direction = direction;
        this.profilePicture = profilePicture;
        this.birthday = birthday;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
