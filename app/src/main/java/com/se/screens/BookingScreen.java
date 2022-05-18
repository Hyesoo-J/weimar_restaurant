package com.se.screens;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        	
        	try {
        	    new SimpleDateFormat("yyyy-mm-dd").parse(ans);
        	    newBooking.date = ans;
        	    questions.add(new Question("What Time?(hh:mm)", Question.Format.TXT).setAnswerListener(ans2-> {
        	    	
    	    	try {
            	    new SimpleDateFormat("hh:mm").parse(ans2);
                    newBooking.time = ans2;
                    questions.add(new Question("How many people?(Max30)", Question.Format.NUM).setAnswerListener(ans3 -> {
                    	newBooking.noOfPeople = ans3;

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
                }catch (ParseException e) {
            		warningMssg("Invalid time format");
            	}
            	
                }));


        	} catch (ParseException e) {
        		warningMssg("Invalid date format");
        	}
        	
        	 
        }));
       
    }

    
    public void warningMssg(String mssg) {
    	 questions.add(new Question(mssg, Question.Format.NULL).setAnswerListener(ans1 -> {
             questions.remove(questions.size() - 1); 
        }));
    }
    
    @Override
    public void setupBeforeShowingQuestions() {
        newBooking = new Booking("","","","","");
    }
}
