package com.se;

public class Booking {
    public String email;
    public String time;
    public String date;
    public int noOfPeople;

    @Override
    public String toString() {
        return "[" +
                "Email='" + email + '\'' +
                "| Date='" + date + '\'' +
                "| Time='" + time + '\'' +
                "| People=" + noOfPeople +
                ']';
    }
}
