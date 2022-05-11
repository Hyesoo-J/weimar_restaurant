package com.se;

public class Question {

    public String text;
    public Format answerFormat;

    public AnswerListener ansListener;

    public Question(String text, Format answerFormat) {
        this.text = text;
        this.answerFormat = answerFormat;
    }

    public Question setAnswerListener(AnswerListener ansListener) {
        this.ansListener = ansListener;
        return this;
    }

    public boolean isValidAnswer(String ans) {

        if (ans == null) {
            return false;
        }

        if (ans.isEmpty()) {
            return answerFormat == Format.NULL;
        }

        switch (answerFormat) {
            case NUM -> {
                try {
                    Integer.parseInt(ans);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            case YN -> {
                return ans.equalsIgnoreCase("Y") ||
                        ans.equalsIgnoreCase("N") ||
                        ans.equalsIgnoreCase("yes") ||
                        ans.equalsIgnoreCase("no");
            }
            case DATE -> {
                return true; // TODO check for date pattern
            }
            default -> { // if format is txt
                return true;
            }
        }

    }

    public enum Format {
        NULL, YN, NUM, TXT, DATE
    }

    public interface AnswerListener {
        void onAnswer(String ans);
    }

}
