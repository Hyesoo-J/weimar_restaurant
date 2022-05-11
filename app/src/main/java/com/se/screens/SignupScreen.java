package com.se.screens;

import com.se.Question;
import com.se.User;
import com.se.UserDataManager;

public class SignupScreen extends Screen {

    private String[] signupData; // if several lines of input data need to be accessed

    UserDataManager userDataManager;

    public SignupScreen(UserDataManager userDataManager) {
        this.userDataManager = userDataManager;
    }

    @Override

    protected void initQuestions() {
        signupData = new String[2];// username # password

        User user = new User("", "");

        questions.add(new Question("New email:", Question.Format.TXT).setAnswerListener(ans -> {
            user.email = ans;
            boolean exists = userDataManager.checkUser(user.email);
            if (exists) {
                questions.add(new Question("Email exists. Please log in. ", Question.Format.NULL).setAnswerListener(ans1 -> {
                    questions.remove(questions.size() - 1);
                }));
            } else {
                questions.add(new Question("New password:", Question.Format.TXT).setAnswerListener(ans2 -> {
                    user.password = ans2;
                    Screen.userEmail = user.email;
                    userDataManager.saveUser(user);
                    super.returnData = "okay";
                    questions.remove(questions.size() - 1);
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
