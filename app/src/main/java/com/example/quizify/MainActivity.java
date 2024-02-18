package com.example.quizify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   public static ArrayList<Modelclass> listOfQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listOfQ=new ArrayList<>();
//        quizz format
//        listOfQ.add(new Modelclass("question","oa","ob","oc","od","ans"));
        // Question 1
        listOfQ.add(new Modelclass("What is the capital of France?", "Berlin", "London", "Paris", "Madrid", "Paris"));
        // Question 2
        listOfQ.add(new Modelclass("What is the largest planet in our solar system?", "Mars", "Saturn", "Earth", "Jupiter", "Jupiter"));
        // Question 3
        listOfQ.add(new Modelclass("What is the capital of India?", "Mumbai", "Mars", "Kolkata", "Chennai", "Delhi"));
        // Question 4
        listOfQ.add(new Modelclass("What is the chemical symbol for water?", "O2", "CO2", "H2O", "NaCl", "H2O"));
        // Question 5
        listOfQ.add(new Modelclass("Who wrote 'To Kill a Mockingbird'?", "Harper Lee", "Ernest Hemingway", "Jane Austen", "F. Scott Fitzgerald", "Harper Lee"));
        // Question 6
        listOfQ.add(new Modelclass("Which planet is known as the Red Planet?", "Mars", "Venus", "Jupiter", "Saturn", "Mars"));
        // Question 7
        listOfQ.add(new Modelclass("Which country is famous for the Taj Mahal?", "Italy", "Egypt", "India", "China", "India"));
        // Question 8
        listOfQ.add(new Modelclass("Who painted the Mona Lisa?", "Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo", "Leonardo da Vinci"));
        // Question 9
        listOfQ.add(new Modelclass("What is the chemical symbol for gold?", "Fe", "Ag", "Hg", "Au", "Au"));
        // Question 10
        listOfQ.add(new Modelclass("Which bird is often associated with wisdom?", "Eagle", "Owl", "Hawk", "Crow", "Owl"));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}