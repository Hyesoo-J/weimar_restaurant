package com.se.screens;

import com.se.Booking;
import com.se.Question;
import com.se.RestarauntManager;

public class ViewBookingsScreen extends Screen {

    RestarauntManager manager;

    public ViewBookingsScreen(RestarauntManager manager) {
        this.manager = manager;
    }

    @Override
    protected void initQuestions() {

    }

    @Override
    public void setupBeforeShowingQuestions() {

        StringBuilder builder = new StringBuilder();

        for (Booking booking : manager.bookingList) {
            if (booking.email.equals(Screen.userEmail)) {
                builder.append(booking).append("\n");
            }
        }
        questions.add(new Question(builder.toString(), Question.Format.NULL).setAnswerListener(ans -> {
            questions.clear();
        }));
    }
}
