package com.se.screens;

import com.se.Booking;
import com.se.Question;
import com.se.RestarauntManager;

public class BookingScreen extends Screen {

    RestarauntManager manager;
    Booking newBooking;

    public BookingScreen(RestarauntManager manager) {
        this.manager = manager;
    }

    @Override
    protected void initQuestions() {

        questions.add(new Question("What Date?", Question.Format.DATE).setAnswerListener(ans -> {
            newBooking.date = ans;
        }));
        questions.add(new Question("What Time?", Question.Format.TXT).setAnswerListener(ans -> {
            newBooking.time = ans;
        }));
        questions.add(new Question("How many people?", Question.Format.NUM).setAnswerListener(ans -> {
            newBooking.noOfPeople = Integer.parseInt(ans);

            boolean available = manager.checkAvailibility(newBooking.noOfPeople);
            if (available) {
                newBooking.email = Screen.userEmail;
                manager.bookingList.add(newBooking);
                questions.add(new Question("Confirmed!\nBooking Details:\n" + newBooking, Question.Format.NULL).setAnswerListener(ans1 -> {
                    questions.remove(questions.size() - 1);
                }));
            } else {
                questions.add(new Question("Not Confirmed\nNot enough seats :(", Question.Format.NULL).setAnswerListener(ans1 -> {
                    questions.remove(questions.size() - 1);
                }));
            }

        }));

    }

    @Override
    public void setupBeforeShowingQuestions() {
        newBooking = new Booking();
    }
}
