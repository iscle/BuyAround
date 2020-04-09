package com.selepdf.hackovid.model;

public class User {
    private String name;
    private String password;
    private String direction;
    private String profilePicture;
    private long birthday;
    private String email;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
