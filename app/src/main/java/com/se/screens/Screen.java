package com.se.screens;

import com.se.Question;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Screen {

    public ArrayList<Question> questions = new ArrayList<>();
    protected int currentIdx = 0;
    protected String returnData; // current active data

    static String userEmail = "";

    public Screen() {
        initQuestions();
    }

    /**
     * Fill up questions array in this method's implementation
     */
    protected abstract void initQuestions();

    /**
     * Shows all questions in sequence and returns the final answer
     *
     * @return answer of the last question. It can ignored also.
     */
    public String showQuestions() {
        setupBeforeShowingQuestions();
        Scanner sc = new Scanner(System.in);
        currentIdx = 0;
        for (; currentIdx < questions.size(); ) {
            Question question = questions.get(currentIdx);
            System.out.println(question.text);

            boolean goBack = false;

            while (true) {

                // if (question.answerFormat == Question.Format.NULL) {
                //    break;
                // }

                String ans = sc.nextLine();

                if (ans.equalsIgnoreCase("back")) {
                    goBack = true;
                    break;
                }

                returnData = ans;
                if (question.isValidAnswer(ans)) {
                    if (question.ansListener != null) {
                        question.ansListener.onAnswer(ans);
                    }
                    break;
                } else {
                    System.out.println("Invalid answer :(");
                }

            }

            if (goBack) {
                currentIdx--;
                if (currentIdx < 0) {
                    currentIdx = 0;
                }
            } else {
                currentIdx++;
            }

        }
        return returnData;
    }

    // empty function that is called after every valid user input
    // by default empty, can be overwritten
    public void processData() {

    }

    public void setupBeforeShowingQuestions() {

    }


}
