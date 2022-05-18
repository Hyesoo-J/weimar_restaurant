package com.se.screens;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.se.Booking;
import com.se.Language;
import com.se.Question;
import com.se.RestarauntManager;

public class BookingScreen extends Screen {

    RestarauntManager manager;
    Language lang;
    Booking newBooking = new Booking("","","","","");;
    public BookingScreen(RestarauntManager manager) {
        this.manager = manager;
    }

    @Override
	public void setupBeforeShowingQuestions() {

        questions.add(new Question(lang.option.equals("1") ? "What Date?(yyyy-mm-dd)":  "Welches Datum?(jjjj-mm-tt)", Question.Format.DATE).setAnswerListener(ans -> {
        	
        	try {
        	    new SimpleDateFormat("yyyy-mm-dd").parse(ans);
        	    newBooking.date = ans;
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
                            newBooking.id = Integer.toString(manager.loadAllBooked().size());
                            manager.saveBooking(newBooking);
                            
                            warningMssg(lang.option.equals("1") ? "Confirmed!\nBooking Details:\n" + "[" +
                                    "Email='" + newBooking.email + '\'' +
                                    "| Date='" + newBooking.date + '\'' +
                                    "| Time='" + newBooking.time + '\'' +
                                    "| People='" + newBooking.noOfPeople + '\'' +
                                    ']':
                                	"Bestätigt!\nBuchungsdetails:\n" + "[" +
                                    "Email='" + newBooking.email + '\'' +
                                    "| Datum='" + newBooking.date + '\'' +
                                    "| Zeit='" + newBooking.time + '\'' +
                                    "| Leute='" + newBooking.noOfPeople + '\'' +
                                    ']'	);
                            
                         	}else {
                         		 warningMssg(lang.option.equals("1") ? "Not Confirmed \n Not avaliable date :(" : "Nicht bestätigt \nNicht verfügbares Datum :(");
                           
                            }
                        } else {
                        	warningMssg(lang.option.equals("1") ? "Not Confirmed\nNot enough seats :(": "Nicht bestätigt \nNicht genug Sitzplätze :(" );
                        }

                    }));
                }catch (ParseException e) {
            		warningMssg(lang.option.equals("1") ? "Invalid time format": "Ungültiges Zeitformat" );
            	}
            	
                }));


        	} catch (ParseException e) {
        		warningMssg(lang.option.equals("1") ? "Invalid date format": "Ungültiges Datumformat");
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
    
    @Override
    protected void initQuestions() {


    }

}
