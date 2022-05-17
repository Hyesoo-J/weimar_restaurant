package com.se.screens;

import com.se.Question;

public class FirstScreen extends Screen {

    @Override

    protected void initQuestions() {

        questions.add(new Question(" Welcome to the Weimar Restaurant!\nDo you want to make a reservation (Y/N)?", Question.Format.YN));
        questions.add(new Question("Are you a registered User?(Y/N)", Question.Format.YN));

    }

}
