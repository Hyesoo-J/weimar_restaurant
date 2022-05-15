package com.se;

public class User {

    public String email;
    public String name;

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return email + ',' + name;
    }
}
