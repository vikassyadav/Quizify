package com.example.quizify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import  android.content.Intent;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonActivity extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView resultText;
    LinearLayout btnShare, restart;
    int correct ,wrong;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("wrong",0);

        circularProgressBar= findViewById(R.id.circularProgressBar);
        resultText=findViewById(R.id.resulttxt);
        btnShare=findViewById(R.id.BtnShare);
        restart=findViewById(R.id.Restart);

        circularProgressBar.setProgress(correct);
        resultText.setText(correct+"/10");
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Quizify");
                    String shareMessage= "\n I Got "+correct +"Out Of 10 You Can also Try" ;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    e.toString();
                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WonActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });

    }
}