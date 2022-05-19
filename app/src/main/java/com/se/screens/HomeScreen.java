package com.se.screens;

import com.se.Language;
import com.se.Question;

public class HomeScreen extends Screen {
	
	Language lang ;

	@Override
	protected void initQuestions() {
		
	}
	
	 @Override
    public void setupBeforeShowingQuestions() {
		 questions.add(new Question(lang.option.equals("1") ? """
					Please select the operation you would like to perform
					1. New Booking
					2. View Booking
					3. Cancel Booking
					4. Change Booking
					5. Logout
					6. Exit""":
					"""
					Bitte wählen Sie den Vorgang aus, den Sie ausführen möchten
					1. Neue Buchung
					2. Buchung ansehen
					3. Buchung stornieren
					4. Buchung ändern
					5. Ausloggen
					6. Ausgang"""
						, Question.Format.NUM));

	}

}
