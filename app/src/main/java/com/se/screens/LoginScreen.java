package com.se.screens;

import com.se.Language;
import com.se.Question;
import com.se.User;
import com.se.UserDataManager;

public class LoginScreen extends Screen {
	
	 Language lang;

    private String[] loginData; // if several lines of input data need to be accessed

    UserDataManager userDataManager;

    public LoginScreen(UserDataManager userDataManager) {
        this.userDataManager = userDataManager;
    }

    @Override

    protected void initQuestions() {
        
    }
    
    @Override
    public void setupBeforeShowingQuestions() {
    	loginData = new String[3];

        User user = new User("", "", "");
        questions.add(new Question("Email:", Question.Format.TXT).setAnswerListener(ans -> {
            user.email = ans;
        }));
        
        questions.add(new Question("Name:", Question.Format.TXT).setAnswerListener(ans -> {
            user.name = ans;
            
            int valid = userDataManager.authenticateUser(user.email, user.name, user.number);

            switch (valid) {
                case 1:
                    questions.add(new Question(lang.option.equals("1") ?"incorrect credentials": "Falsche Anmeldeinformationen" , Question.Format.NULL).setAnswerListener(ans1 -> {
                        questions.remove(questions.size() - 1);
                    }));
                    break;
                case -1:
                    questions.add(new Question(lang.option.equals("1") ?"user not found" :"Benutzer wurde nicht gefunden", Question.Format.NULL).setAnswerListener(ans1 -> {
                        questions.remove(questions.size() - 1);
                    }));
                    break;
                case 0:
                    Screen.userEmail = user.email;
                    Screen.userName = user.name;
                    Screen.userNumber = user.number;
                    super.returnData = "okay";
                    break;

            }

        }));
    	
    	
    }
    

    @Override
    public void processData() {
        loginData[currentIdx] = returnData;
        //System.out.println(returnData);
    }

    public String printAllData() {
        String out = "";
        for (String i : loginData)
            out += i + "#";
        return out;
    }

    public String[] getLoginData() {
        return loginData;
    }
}
