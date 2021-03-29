package com.js.quizandroid;

public class QuizModel {
    private int mQuestion;
    private boolean mAnswer;

    public QuizModel(int Question,boolean Answer){
        this.mQuestion=Question;
        this.mAnswer=Answer;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean ismAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
