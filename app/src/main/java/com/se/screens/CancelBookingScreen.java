package com.se.screens;

import com.se.Booking;
import com.se.Language;
import com.se.Question;
import com.se.RestarauntManager;

public class CancelBookingScreen extends Screen {

    RestarauntManager manager;
    Language lang;

    public CancelBookingScreen(RestarauntManager manager) {
        this.manager = manager;
    }

    @Override
    protected void initQuestions() {


    }

    @Override
    public void setupBeforeShowingQuestions() {

        StringBuilder builder = new StringBuilder();

        int idx = 1; //0
        builder.append(lang.option.equals("1") ? "Please enter the reservation number you wish to cancel\n" :
        	"Bitte geben Sie die Reservierungsnummer ein, die Sie stornieren möchten\n" );
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
                questions.add(new Question(lang.option.equals("1") ? "Deleted!": "Gelöscht!", Question.Format.NULL).setAnswerListener(ans2 -> {
                    questions.clear();
                }));
            }


        }));
    }

}
