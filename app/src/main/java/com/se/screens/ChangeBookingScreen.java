package com.se.screens;

import com.se.Booking;
import com.se.Question;
import com.se.RestarauntManager;

public class ChangeBookingScreen extends Screen {

    RestarauntManager manager;
    Booking newBooking;
	
    
    public ChangeBookingScreen(RestarauntManager manager) {
        this.manager = manager;
    }

    @Override
    protected void initQuestions() {


    }

    @Override
    public void setupBeforeShowingQuestions() {

        StringBuilder builder = new StringBuilder();

        int idx = 1; //0
        builder.append("Please enter the 'Number' you want to change\n");
        for (Booking booking : manager.bookingList) {
            if (booking.email.equals(Screen.userEmail)) {
                builder.append(idx++).append(". ").append(booking).append("\n");
            }
        }

        questions.add(new Question(builder.toString(), Question.Format.NUM).setAnswerListener(ans -> {
            int idxToDelete = Integer.parseInt(ans);

            if (idxToDelete == 0) {
                questions.clear();
            } 
            
            else {

                manager.bookingList.remove(idxToDelete - 1); {
                questions.clear();
                }
               
                    questions.add(new Question("What New Date?(yyyy-mm-dd)", Question.Format.DATE).setAnswerListener(ans6 -> {
                        newBooking.date = ans6;
                    }));
                    questions.add(new Question("What New Time?(hh:mm)", Question.Format.TXT).setAnswerListener(ans7 -> {
                        newBooking.time = ans7;
                    }));
                    questions.add(new Question("How many people?(Max30)", Question.Format.NUM).setAnswerListener(ans8 -> {
                        newBooking.noOfPeople = ans8;

                        boolean available = manager.checkAvailibility(newBooking.noOfPeople);
                        boolean dateAvaliable =  manager.checkTimeAvailibility(newBooking.date, newBooking.time);
                        
                        if (available) {
                        	
                        	if(dateAvaliable) {
                        		newBooking.email = Screen.userEmail;
                                questions.add(new Question("Confirmed!\nBooking Details:\n" + newBooking, Question.Format.NULL).setAnswerListener(ans1 -> {
                                    questions.remove(questions.size() - 1);
                                }));
                        		
                        	}else {
                                questions.add(new Question("Not avaliable date :(", Question.Format.NULL).setAnswerListener(ans1 -> {
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
            


        }));
    }

}
