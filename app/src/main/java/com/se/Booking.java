package com.se;

public class Booking {
    public String email;
    public String name;
    public String number;
    public String time;
    public String date;
    public int noOfPeople;

    @Override
    public String toString() {
        return "[" +
                "Email='" + email + '\'' +
                "| Name='" + name + '\'' +
                "| Phone='" + number + '\'' +
                "| Date='" + date + '\'' +
                "| Time='" + time + '\'' +
                "| People='" + noOfPeople + '\'' +
                ']';
    }
}
