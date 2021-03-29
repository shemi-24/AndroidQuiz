package com.js.quizandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private final String SCORE_KEY="SCORE";
    private final String INDEX_KEY="INDEX";
    private TextView Question,QuestionStats;
    private ProgressBar mProgressBar;
    private int mQuestionIndex;
    private Button ButtonTrue,ButtonFalse;
    private int mUserScore;
    private QuizModel[] quizModels=new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,false),
            new QuizModel(R.string.q4,true),
            new QuizModel(R.string.q5,false),
            new QuizModel(R.string.q6,true),
            new QuizModel(R.string.q7,false),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,true),
        };
    final int user_progress=(int)Math.ceil(100.0/quizModels.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
            mUserScore=savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex=savedInstanceState.getInt(INDEX_KEY);

        } else{
            mUserScore=0;
            mQuestionIndex=0;
        }



        Question=findViewById(R.id.txtQuestion);
        QuizModel q1=quizModels[mQuestionIndex];
        Question.setText(q1.getmQuestion());
        QuestionStats=findViewById(R.id.txtQuizStats);
        QuestionStats.setText(mUserScore+"");
        mProgressBar=findViewById(R.id.quizPB);
        ButtonTrue=findViewById(R.id.btntrue);
        ButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateUsersAnswer(true);
            changeQustionOnButtonClick();
            }
        });
        ButtonFalse=findViewById(R.id.btnfalse);
        ButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateUsersAnswer(false);
            changeQustionOnButtonClick();
            }
        });


    }
    private void changeQustionOnButtonClick(){
        mQuestionIndex=(mQuestionIndex+1)%10;
        if(mQuestionIndex==0){
            AlertDialog.Builder quizAlert=new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The quiz is finished..");
            quizAlert.setMessage("Your Score is "+mUserScore);
            quizAlert.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizAlert.show();
        }
        Question.setText(quizModels[mQuestionIndex].getmQuestion());
        mProgressBar.incrementProgressBy(user_progress);
        QuestionStats.setText(mUserScore+"");
    }

    private void evaluateUsersAnswer(boolean userGuess){
        boolean currentQuestionAnswer=quizModels[mQuestionIndex].ismAnswer();
        if(currentQuestionAnswer==userGuess){
            //QuestionStats.setText();
            Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
            mUserScore++;
        }
        else{
            Toast.makeText(this, R.string.incorrect_toast_message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(SCORE_KEY,mUserScore);
        outState.putInt(INDEX_KEY,mQuestionIndex);
    }
}