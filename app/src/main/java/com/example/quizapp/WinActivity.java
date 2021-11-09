package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WinActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView result;
    int correct, wrong;
    LinearLayout share_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);


        circularProgressBar = findViewById(R.id.circularProgressBar);
        result = findViewById(R.id.result);
        share_btn = findViewById(R.id.share_btn);

        circularProgressBar.setProgress(correct);
        result.setText(correct + "/" + (wrong+correct));

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nI scored " + correct + " out of 8 points !!\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });


    }




}