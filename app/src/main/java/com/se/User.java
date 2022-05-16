package com.se;

public class User {

    public String email;
    public String name;
    public String number;

    public User(String email, String name, String number) {
        this.email = email;
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return email + ',' + name + ',' + number;
    }
}
