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

        questions.add(new Question("What Date?(yyyy-mm-dd)", Question.Format.DATE).setAnswerListener(ans -> {
      
        	 newBooking.date = ans;
        }));
        questions.add(new Question("What Time?(hh:mm)", Question.Format.TXT).setAnswerListener(ans -> {
            newBooking.time = ans;
        }));
        questions.add(new Question("How many people?(Max30)", Question.Format.NUM).setAnswerListener(ans -> {
        	newBooking.noOfPeople = ans;

        	 boolean available = manager.checkAvailibility(newBooking.noOfPeople);
             boolean dateAvaliable =  manager.checkTimeAvailibility(newBooking.date, newBooking.time );
             
             if (available) {
             	if(dateAvaliable) {
                newBooking.email = Screen.userEmail;
                newBooking.id = Integer.toString(manager.loadAllBooked().size());
                manager.saveBooking(newBooking);
                
                questions.add(new Question("Confirmed!\nBooking Details:\n" + "[" +
                        "Email='" + newBooking.email + '\'' +
                        "| Date='" + newBooking.date + '\'' +
                        "| Time='" + newBooking.time + '\'' +
                        "| People='" + newBooking.noOfPeople + '\'' +
                        ']', Question.Format.NULL).setAnswerListener(ans1 -> {
                    questions.remove(questions.size() - 1);
                }));
                
             	}else {
                    questions.add(new Question("Not Confirmed \n Not avaliable date :(", Question.Format.NULL).setAnswerListener(ans1 -> {
                        questions.remove(questions.size() - 1);
                }));
                }
            } else {
                questions.add(new Question("Not Confirmed\nNot enough seats :(", Question.Format.NULL).setAnswerListener(ans1 -> {
                    questions.remove(questions.size() - 1);
                }));
            }

        }));

    }

    @Override
    public void setupBeforeShowingQuestions() {
        newBooking = new Booking("","","","","");
    }
}
