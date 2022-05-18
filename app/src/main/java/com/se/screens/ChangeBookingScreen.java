package com.se.screens;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.se.Booking;
import com.se.Question;
import com.se.RestarauntManager;

public class ChangeBookingScreen extends Screen {

    RestarauntManager manager;
    Booking newBooking = new Booking("","","","","");
    public List<Booking> optionsUpdate = new ArrayList<Booking>();
    
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
        String indexSelected = "0";
        
        builder.append("Please enter the 'Number' you want to change\n");
        for (Booking booking : manager.bookingList) {
            if (booking.email.equals(Screen.userEmail)) {
                builder.append(idx++).append(". ").append(booking).append("\n");
                optionsUpdate.add(booking);
            }
        }

        questions.add(new Question(builder.toString(), Question.Format.NUM).setAnswerListener(ans -> {
        	
        if(optionsUpdate.size() > 0) {
        	
	    	int idxToDelete = Integer.parseInt(ans) - 1;			    
			if ( idxToDelete >= 0 && idxToDelete <= manager.bookingList.size()) {
				
				String keyModified = optionsUpdate.get(idxToDelete).id;
				
				questions.add(new Question("What Date?(yyyy-mm-dd)", Question.Format.DATE).setAnswerListener(ans0 -> {
		        	
		        	try {
		        	    new SimpleDateFormat("yyyy-mm-dd").parse(ans0);
		        	    newBooking.date = ans0;
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
		                            newBooking.id = keyModified;
		                            manager.updateBooking(newBooking);
		                            
		                            warningMssg("Confirmed Update!\nBooking Details:\n" + "[" +
		                                    "Email='" + newBooking.email + '\'' +
		                                    "| Date='" + newBooking.date + '\'' +
		                                    "| Time='" + newBooking.time + '\'' +
		                                    "| People='" + newBooking.noOfPeople + '\'' +
		                                    ']');
		                            
		                         	}else {
		                         		warningMssg("Not Confirmed \\n Not avaliable date :");
		                            }
		                        } else {
		                        	warningMssg("Not Confirmed\nNot enough seats :(");
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
				
				
				
			}else {
				 warningMssg("Enter a valid option :(");
			}
	    	
        }else {
        	warningMssg("No reservations avaliable");
        }
		
		
	    }));
       
	}
    
    
        

        public void warningMssg(String mssg) {
        	 questions.add(new Question(mssg, Question.Format.NULL).setAnswerListener(ans1 -> {
                 questions.remove(questions.size() - 1); 
                 questions.clear();
                 manager.loadAllBooked();
                 
            }));
        	
        }
}
