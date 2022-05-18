package com.se;

public class Booking {
	public String id;
    public String email;
    public String time;
    public String date;
    public String noOfPeople;

    
    public Booking (String id, String email, String time , String date, String noOfPeople) {
    	this.id = id;
        this.email = email;
        this.time = time;
        this.date = date;
        this.noOfPeople = noOfPeople;
    }
    @Override
    public String toString() {
        return id+','+ email + ',' + time + ',' + date + ',' + noOfPeople;
        		
   
    }
	
}
