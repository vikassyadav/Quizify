package com.example.quizify;

import static com.example.quizify.MainActivity.listOfQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue = 20;
    ProgressBar progressBar;
    List<Modelclass> allQuestionList;
    Modelclass modelclass;
    int index=0;
    int correctCount=0;
    int wrongCount=0;

    TextView Ques,optA,optB,optC,optD;
    CardView cardA,cardB,cardC,cardD;
    LinearLayout nextButton;
    private static final String INDEX_KEY = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Retrieve the saved index from SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        index = sharedPreferences.getInt(INDEX_KEY, 0);

        Hooks();
        setAllData();
        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));
        nextButton.setClickable(false);

        allQuestionList = listOfQ;
        //shuffle the questions
        Collections.shuffle(allQuestionList);

//        modelclass=allQuestionList.get(index);
        if (allQuestionList.size() > 0) {
            modelclass = allQuestionList.get(index); // Initialize modelclass here

            setAllData();
        } else {
            // Handle the case when listOfQ is empty
//            showDialog("No Questions Available", "There are no questions available for the quiz. Please try again later.");
        }


        countDownTimer=new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue=timerValue-1;
                progressBar.setProgress(timerValue);

            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(DashboardActivity.this);
//                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.findViewById(R.id.btnTryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        }.start();
    }

    private void setAllData() {
        if (modelclass != null) {
            Ques.setText(modelclass.getQuestion());
            optA.setText(modelclass.getoA());
            optB.setText(modelclass.getoB());
            optC.setText(modelclass.getoC());
            optD.setText(modelclass.getoD());
        } else {
            // Handle the case when modelclass is null
        }
    }

    private void Hooks() {
        progressBar = findViewById(R.id.quiz_timer);
        Ques = findViewById(R.id.Questxt);
        optA= findViewById(R.id.optionA);
        optB= findViewById(R.id.optionB);
        optC= findViewById(R.id.optionC);
        optD= findViewById(R.id.optionD);

        cardA= findViewById(R.id.card_optA);
        cardB= findViewById(R.id.card_optB);
        cardC= findViewById(R.id.card_optC);
        cardD= findViewById(R.id.card_optD);

        nextButton=findViewById(R.id.nxtbtn);

    }
    public void Correct(CardView cardView){
        cardView.setBackgroundColor(getResources().getColor(R.color.green));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                index++;
                modelclass=listOfQ.get(index);
                setAllData();
                resetColor();
                enableButton();
            }
        });
    }
    public void Wrong(CardView cardA){
        cardA.setBackgroundColor(getResources().getColor(R.color.red));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if(index<listOfQ.size()-1){
                    index++;
                    modelclass=listOfQ.get(index);
                    setAllData();
                    resetColor();
                    enableButton();
                }else {
                    GameWon();
                }
            }
        });
    }

    private void GameWon() {
        Intent intent = new Intent(DashboardActivity.this,WonActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        startActivity(intent);
    }
    public void enableButton(){
        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);

    }
    public void disableButton(){
        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);

    }
    public void resetColor(){
        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));
    }
    public void OptionAclick(View view){
        nextButton.setClickable(true);
        disableButton();
        if(modelclass.getoA().equals(modelclass.getAns())){
            cardA.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<listOfQ.size()-1){
                Correct(cardA);
            }
            else {
                GameWon();
            }
        }else {
            Wrong(cardA);
        }
    }
    public void OptionBclick(View view){
        nextButton.setClickable(true);
        disableButton();
        if(modelclass.getoB().equals(modelclass.getAns())){
            cardB.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<listOfQ.size()-1){
                Correct(cardB);
            }
            else {
                GameWon();
            }
        }else {
            Wrong(cardB);
        }
    }

    public void OptionCclick(View view){
        nextButton.setClickable(true);
        disableButton();
        if(modelclass.getoC().equals(modelclass.getAns())){
            cardC.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<listOfQ.size()-1){
                Correct(cardC);
            }
            else {
                GameWon();
            }
        }else {
            Wrong(cardC);
        }
    }

    public void OptionDclick(View view){
        nextButton.setClickable(true);
        disableButton();
        if(modelclass.getoD().equals(modelclass.getAns())){
            cardD.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<listOfQ.size()-1){
                Correct(cardD);
            }
            else {
                GameWon();
            }
        }else {
            Wrong(cardD);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Save the current index to SharedPreferences when the activity is stopped
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(INDEX_KEY, index);
        editor.apply();
    }
}