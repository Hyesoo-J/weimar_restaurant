package com.se.screens;

import com.se.Question;
import com.se.User;
import com.se.UserDataManager;

public class LoginScreen extends Screen {

    private String[] loginData; // if several lines of input data need to be accessed

    UserDataManager userDataManager;

    public LoginScreen(UserDataManager userDataManager) {
        this.userDataManager = userDataManager;
    }

    @Override

    protected void initQuestions() {
        loginData = new String[2];// username # password

        User user = new User("", "");
        questions.add(new Question("Email:", Question.Format.TXT).setAnswerListener(ans -> {
            user.email = ans;
        }));
        questions.add(new Question("Password:", Question.Format.TXT).setAnswerListener(ans -> {

            user.password = ans;
            int valid = userDataManager.authenticateUser(user.email, user.password);

            switch (valid) {
                case 1:
                    questions.add(new Question("incorrect credentials", Question.Format.NULL).setAnswerListener(ans1 -> {
                        questions.remove(questions.size() - 1);
                    }));
                    break;
                case -1:
                    questions.add(new Question("user not found", Question.Format.NULL).setAnswerListener(ans1 -> {
                        questions.remove(questions.size() - 1);
                    }));
                    break;
                case 0:
                    Screen.userEmail = user.email;
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
