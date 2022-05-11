package com.se.screens;

import com.se.Booking;
import com.se.Question;
import com.se.RestarauntManager;

public class CancelBookingScreen extends Screen {

    RestarauntManager manager;

    public CancelBookingScreen(RestarauntManager manager) {
        this.manager = manager;
    }

    @Override
    protected void initQuestions() {


    }

    @Override
    public void setupBeforeShowingQuestions() {

        StringBuilder builder = new StringBuilder();

        int idx = 0;
        builder.append(idx++).append(". ").append("exit\n");
        for (Booking booking : manager.bookingList) {
            if (booking.email.equals(Screen.userEmail)) {
                builder.append(idx++).append(". ").append(booking).append("\n");
            }
        }

        questions.add(new Question(builder.toString(), Question.Format.NUM).setAnswerListener(ans -> {
            int idxToDelete = Integer.parseInt(ans);

            if (idxToDelete == 0) {
                questions.clear();
            } else {

                manager.bookingList.remove(idxToDelete - 1);
                questions.add(new Question("Deleted!", Question.Format.NULL).setAnswerListener(ans2 -> {
                    questions.clear();
                }));
            }


        }));
    }

}
