package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Modelclass> listOfQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfQues = new ArrayList<>();
        listOfQues.add(new Modelclass("What is the smallest planet in the solar system by mass?", "Mercury", "Earth", "Venus", "Mars", "Mercury"));
        listOfQues.add(new Modelclass("When did the Space Age begin?", "1968", "1957", "1999", "1941", "1957"));
        listOfQues.add(new Modelclass("What is the name of the first space tourist?", "Mark Shuttleworth", "Richard Garriott", "Dennis Tito", "Gregory Olsen", "Dennis Tito"));
        listOfQues.add(new Modelclass("When was Pluto removed from the list of planets?", "2006", "2008", "2000", "2002", "2006"));
        listOfQues.add(new Modelclass("In which galaxy was a black hole discovered in the year 2017?", "NGC 1300", "Comet", "M87", "IC 1101", "M87"));
        listOfQues.add(new Modelclass("The number of terrestrial planets in the solar system is:", "Two", "Eight", "Six", "Four", "Four"));
        listOfQues.add(new Modelclass("Which type of star is the Sun classified?", "G2 V Star", "F-type Star", "K-Type Star", "A-Type Star", "G2 V Star"));
        listOfQues.add(new Modelclass("Which year is Halley's Comet expected to return to the solar system?", "2110", "2045", "2086", "2061", "2061"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        },1500);
    }
}