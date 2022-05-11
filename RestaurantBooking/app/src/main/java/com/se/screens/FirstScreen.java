package com.se.screens;

import com.se.Question;

public class FirstScreen extends Screen {

    @Override

    protected void initQuestions() {

        questions.add(new Question("Hello! Do you want to make a reservation (Y/N)?", Question.Format.YN));
        questions.add(new Question("Are you a registered User?(Y/N)", Question.Format.YN));

    }

}
