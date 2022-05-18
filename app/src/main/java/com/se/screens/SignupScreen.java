package com.se.screens;

import com.se.Language;
import com.se.Question;
import com.se.User;
import com.se.UserDataManager;

public class SignupScreen extends Screen {

    private String[] signupData; // if several lines of input data need to be accessed
    
    Language lang;

    UserDataManager userDataManager;

    public SignupScreen(UserDataManager userDataManager) {
        this.userDataManager = userDataManager;
    }

    @Override

    protected void initQuestions() {
        signupData = new String[3];// email and name

        User user = new User("","","");
        
        questions.add(new Question(lang.option.equals("1") ? "New Email Address:" : "Neue Email Adresse:", Question.Format.TXT).setAnswerListener(ans -> {
            user.email = ans;
            boolean exists = userDataManager.checkUser(user.email);
            if (exists) {
                questions.add(new Question(lang.option.equals("1") ? "Email exists. Please log in. " : lang.option+ "Email existiert. Bitte loggen Sie sich ein "  , Question.Format.NULL).setAnswerListener(ans1 -> {
                    questions.remove(questions.size() - 1);
                }));
            } else {
                questions.add(new Question(lang.option.equals("1") ?"New Customer's Name:" :"Name des neuen Kunden:", Question.Format.TXT).setAnswerListener(ans2 -> {
                    user.name = ans2;
                
                questions.add(new Question(lang.option.equals("1") ?"Contact Number:":"Kontakt Nummer:", Question.Format.TXT).setAnswerListener(ans3 -> {
                    user.number = ans3;
                    
                    
                    
                    Screen.userEmail = user.email;
                    Screen.userName = user.name;
                    Screen.userNumber = user.number;
                    userDataManager.saveUser(user);
                    super.returnData = "okay";
                    questions.remove(questions.size() - 1);
                
                
                }));
            }));
                
            }
        }));


    }

    @Override
    public void processData() {
        signupData[currentIdx]=returnData;
        //System.out.println(returnData);
    }

    public String printAllData(){
        String out = "";
        for (String i: signupData)
            out += i + "#";
        return out;
    }
    public String[] getSignupData() {
        return signupData;
    }
}
