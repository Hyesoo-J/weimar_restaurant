package com.se;

import java.util.ArrayList;
import java.util.List;

public class RestarauntManager {

	int seats = 30;
	public List<Booking> bookingList = new ArrayList<Booking>();

	public boolean checkAvailibility(int noOfPeople) {

		int total = noOfPeople;

		for (Booking b : bookingList) {

			total += b.noOfPeople;

		}
		int seatsavailable = seats - total;

		return seatsavailable>=0;


	}

}
