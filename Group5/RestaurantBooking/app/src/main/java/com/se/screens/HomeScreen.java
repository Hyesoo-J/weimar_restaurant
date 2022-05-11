package com.se.screens;

import com.se.Question;

public class HomeScreen extends Screen {

	@Override
	protected void initQuestions() {
		questions.add(new Question("""
				Please select the operation you would like to perform
				1. New Booking
				2. View Bookings
				3. Cancel Booking
				4. Logout
				5. Exit""", Question.Format.NUM));

	}

}
