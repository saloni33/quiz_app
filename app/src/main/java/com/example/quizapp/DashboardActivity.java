package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.Collections;
import java.util.List;

import static com.example.quizapp.MainActivity.listOfQues;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timer_value = 20;
    LinearProgressIndicator progressIndicator;
    List<Modelclass> allQuesList;
    Modelclass modelclass;
    int index = 0;

    TextView card_ques, optiona, optionb, optionc, optiond;
    CardView cardOA, cardOB, cardOC, cardOD;

    int correct_count = 0;
    int wrong_count = 0;

    LinearLayout nextBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        allQuesList = listOfQues;
        Collections.shuffle(allQuesList);
        modelclass = listOfQues.get(index);

        cardOA.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setCardBackgroundColor(getResources().getColor(R.color.white));

        nextBtn.setClickable(false);


        setAlLData();

        progressIndicator = findViewById(R.id.quiz_time);

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer_value--;
                progressIndicator.setProgress(timer_value);
            }

            @Override
            public void onFinish() {

                Dialog dialog = new Dialog(DashboardActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);


                dialog.findViewById(R.id.btn_try_again).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        }.start();
    }

    private void setAlLData() {
        card_ques.setText(modelclass.getQuestion());
        optiona.setText(modelclass.getoA());
        optionb.setText(modelclass.getoB());
        optionc.setText(modelclass.getoC());
        optiond.setText(modelclass.getoD());
    }

    private void Hooks() {

        progressIndicator = findViewById(R.id.quiz_time);
        card_ques = findViewById(R.id.card_ques1);
        optiona = findViewById(R.id.card_option_a);
        optionb = findViewById(R.id.card_option_b);
        optionc = findViewById(R.id.card_option_c);
        optiond = findViewById(R.id.card_option_d);

        cardOA = findViewById(R.id.card1);
        cardOB = findViewById(R.id.card2);
        cardOC = findViewById(R.id.card3);
        cardOD = findViewById(R.id.card4);

        nextBtn = findViewById(R.id.next_btn);

    }

    public void Correct(CardView cardOA) {

        cardOA.setCardBackgroundColor(getResources().getColor(R.color.green));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct_count++;
                index++;
                modelclass = listOfQues.get(index);
                setAlLData();
                reset_color();
                enable_btn();
            }
        });
    }

    public void Wrong(CardView cardOA) {

        cardOA.setCardBackgroundColor(getResources().getColor(R.color.red));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong_count++;
                if(index < listOfQues.size()-1){
                    index++;
                    modelclass = listOfQues.get(index);
                    setAlLData();
                    reset_color();
                    enable_btn();
                }

                else{
                    GameFinished();
                }
            }
        });
    }

    private void GameFinished() {
        Intent intent = new Intent(DashboardActivity.this, WinActivity.class);
        intent.putExtra("correct", correct_count);
        intent.putExtra("wrong", wrong_count);
        startActivity(intent);
    }

    public void enable_btn() {
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }

    public void disable_btn() {
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }

    public void reset_color() {
        cardOA.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionA_click(View view) {
        disable_btn();
        nextBtn.setClickable(true);
        if(modelclass.getoA().equals(modelclass.getAns())){
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < listOfQues.size()-1){
                Correct(cardOA);
            }
            else {
                GameFinished();
            }
        }
        else{
            Wrong(cardOA);
        }
    }

    public void OptionB_click(View view) {
        disable_btn();
        nextBtn.setClickable(true);
        if(modelclass.getoB().equals(modelclass.getAns())){
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < listOfQues.size()-1){
                Correct(cardOB);
            }
            else {
                GameFinished();
            }
        }
        else{
            Wrong(cardOB);
        }
    }

    public void OptionC_click(View view) {
        disable_btn();
        nextBtn.setClickable(true);
        if(modelclass.getoC().equals(modelclass.getAns())){
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < listOfQues.size()-1){
                Correct(cardOC);
            }
            else {
                GameFinished();
            }
        }
        else{
            Wrong(cardOC);
        }
    }

    public void OptionD_click(View view) {
        disable_btn();
        nextBtn.setClickable(true);
        if(modelclass.getoD().equals(modelclass.getAns())){
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < listOfQues.size()-1){
                Correct(cardOD);
            }
            else {
                GameFinished();
            }
        }
        else{
            Wrong(cardOD);
        }
    }
}