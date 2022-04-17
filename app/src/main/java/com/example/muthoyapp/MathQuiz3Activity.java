package com.example.muthoyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MathQuiz3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz3);

        final Button okBtn = findViewById(R.id.okbtnid);
        final TextView correctAnswer = findViewById(R.id.correctanswerid);
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswersid);

        final int getCorrectAnswers = getIntent().getIntExtra("correct",0);
        final int getIncorrectAnswers = getIntent().getIntExtra("incorrect",0);

        //correctAnswer.setText(String.valueOf(getCorrectAnswers));
        correctAnswer.setText("Correct answer" +":"+getCorrectAnswers);

        incorrectAnswers.setText(String.valueOf(getIncorrectAnswers));
        incorrectAnswers.setText("Wrong answer" +":"+getIncorrectAnswers);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MathQuiz3Activity.this,RewardsActivity.class));
                finish();
            }
        });

    }
}