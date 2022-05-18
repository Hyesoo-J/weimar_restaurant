package com.se.screens;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.se.Booking;
import com.se.Language;
import com.se.Question;
import com.se.RestarauntManager;

public class ChangeBookingScreen extends Screen {

    RestarauntManager manager;
    Booking newBooking = new Booking("","","","","");
    public List<Booking> optionsUpdate = new ArrayList<Booking>();
    Language lang;
    
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
        
        builder.append(lang.option.equals("1") ? "Please enter the 'Number' you want to change\n" :"Bitte geben Sie die 'Nummer' ein, die Sie ändern möchten\n");
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
				
				questions.add(new Question(lang.option.equals("1") ? "What Date?(yyyy-mm-dd)":  "Welches Datum?(jjjj-mm-tt)", Question.Format.DATE).setAnswerListener(ans0 -> {
		        	
		        	try {
		        	    new SimpleDateFormat("yyyy-mm-dd").parse(ans0);
		        	    newBooking.date = ans0;
		        	    questions.add(new Question(lang.option.equals("1") ? "What Time?(hh:mm)": "Wie viel Uhr?(hh:mm)", Question.Format.TXT).setAnswerListener(ans2-> {
		        	    	
		    	    	try {
		            	    new SimpleDateFormat("hh:mm").parse(ans2);
		                    newBooking.time = ans2;
		                    questions.add(new Question(lang.option.equals("1") ? "How many people?(Max30)": "\r\n"+ "Wie viele Leute?(Max30)", Question.Format.NUM).setAnswerListener(ans3 -> {
		                    	newBooking.noOfPeople = ans3;

		                    	 boolean available = manager.checkAvailibility(newBooking.noOfPeople);
		                         boolean dateAvaliable =  manager.checkTimeAvailibility(newBooking.date, newBooking.time );
		                         
		                         if (available) {
		                         	if(dateAvaliable) {
		                            newBooking.email = Screen.userEmail;
		                            newBooking.id = keyModified;
		                            manager.updateBooking(newBooking);
		                            
		                            warningMssg(lang.option.equals("1") ? "Confirmed Update!\nBooking Details:\n" + "[" +
		                                    "Email='" + newBooking.email + '\'' +
		                                    "| Date='" + newBooking.date + '\'' +
		                                    "| Time='" + newBooking.time + '\'' +
		                                    "| People='" + newBooking.noOfPeople + '\'' +
		                                    ']':
		                                	"Bestätigte Aktualisierung!\nBuchungsdetails:\n" + "[" +
		                                    "Email='" + newBooking.email + '\'' +
		                                    "| Datum='" + newBooking.date + '\'' +
		                                    "| Zeit='" + newBooking.time + '\'' +
		                                    "| Leute='" + newBooking.noOfPeople + '\'' +
		                                    ']'	);
		                            
		                         	}else {
		                         		warningMssg(lang.option.equals("1") ? "Not Confirmed \n Not avaliable date :(" : "Nicht bestätigt \nNicht verfügbares Datum :(");
		                            }
		                        } else {
		                        	warningMssg(lang.option.equals("1") ? "Not Confirmed\nNot enough seats :(": "Nicht bestätigt \nNicht genug Sitzplätze :(");
		                        }

		                    }));
		                }catch (ParseException e) {
		            		warningMssg(lang.option.equals("1") ? "Invalid time format": "Ungültiges Zeitformat");
		            	}
		            	
		                }));


		        	} catch (ParseException e) {
		        		warningMssg(lang.option.equals("1") ? "Invalid date format": "Ungültiges Datumformat");
		        	}
		        	
		        	 
		        }));
				
				
				
			}else {
				 warningMssg(lang.option.equals("1") ? "Enter a valid option :(" :"Geben Sie eine gültige Option ein");
			}
	    	
        }else {
        	warningMssg(lang.option.equals("1") ? "No reservations avaliable" :" Keine Reservierungen möglich");
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
