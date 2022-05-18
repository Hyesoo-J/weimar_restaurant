package com.se.screens;

import java.text.SimpleDateFormat;

import com.se.Language;
import com.se.Question;
import com.se.RestarauntManager;

public class FirstScreen extends Screen {
	
	Language lang  = new Language("");
    @Override

    protected void initQuestions() {
    	
    	questions.add(new Question("""
				Select a language \nWähle eine Sprache?
				1. EN
				2. DE
				""", Question.Format.NUM).setAnswerListener(ans -> {
		             	if( ans.equals("1") || ans.equals("2")) {
		             		lang.option = ans;
		             		questions.add(new Question( lang.option.equals("1") ? "Welcome to the Weimar Restaurant!\nDo you want to make a reservation (Y/N)?" : " Willkommen im Restaurant Weimar !\n\r\n"
		             				+ "Möchten Sie eine Reservierung vornehmen? (J/N)?" 
		             				, Question.Format.YN));
		                    questions.add(new Question(lang.option.equals("1") ? "Are you a registered User?(Y/N)" : "Sind Sie ein registrierter Benutzer?(J/N)", Question.Format.YN));
		             		
		             	}else {
		             		warningMssg("Select valid language / Gültige Sprache auswählen ");
		             	}
		        }));
    
        
    }
    
    public void warningMssg(String mssg) {
   	 questions.add(new Question(mssg, Question.Format.NULL).setAnswerListener(ans1 -> {
   		 	initQuestions();
            
       }));
   	
   }

}
