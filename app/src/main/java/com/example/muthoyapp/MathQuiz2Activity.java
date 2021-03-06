package com.example.muthoyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MathQuiz2Activity extends AppCompatActivity {

    private TextView questions;
    private TextView question;

    private Button option1, option2, option3, option4;
    private Button submitid;

    private Timer quizTimer;

    private int totalTimeInMins = 5;
    private int seconds = 0;
    private  List<QuestionsList> questionsLists;

    private int currentQuestionPosition = 0;

    private String selectedOptionByUser = "";
    Dialog myDialog;

    //volley variables
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().setTitle("Math Quiz");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_math_quiz2);
        questionsLists = new ArrayList<>();
        myDialog = new Dialog(this);
        questionsLists = QuestionsBank.mathquizQuestions();

        final TextView timer = findViewById(R.id.timer);

        questions =findViewById(R.id.questions);
        question = findViewById(R.id.question);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        submitid = findViewById(R.id.submitid);



        startTimer(timer);

        questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
        question.setText(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());

        option1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.round_btn_red);
                    option1.setTextColor(Color.parseColor("#FFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.round_btn_red);
                    option2.setTextColor(Color.parseColor("#FFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.round_btn_red);
                    option3.setTextColor(Color.parseColor("#FFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option4.getText().toString();

                    option4.setBackgroundResource(R.drawable.round_btn_red);
                    option4.setTextColor(Color.parseColor("#FFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        submitid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(MathQuiz2Activity.this, "please select an option", Toast.LENGTH_SHORT).show();
                }
                else{
                    changeNextQuestion();

                }

            }
        });
//
//

//
    }
//    public void Submit(View v) {
//        Button okbtn;
//        myDialog.setContentView(R.layout.activity_math_quiz3);
//        okbtn = (Button) myDialog.findViewById(R.id.okbtnid);
//        myDialog.show();
//    }

    private void changeNextQuestion(){
        currentQuestionPosition++;
        if((currentQuestionPosition+1) == questionsLists.size()){
            submitid.setText("Submit Quiz");
        }
        if(currentQuestionPosition < questionsLists.size()){
            selectedOptionByUser = "";

            option1.setBackgroundResource(R.drawable.border_color);
            option1.setTextColor(Color.parseColor("#222222"));
//            option1.setTextColor(android.R.color.holo_green_dark);

            option2.setBackgroundResource(R.drawable.border_color);
            option2.setTextColor(Color.parseColor("#222222"));

            option3.setBackgroundResource(R.drawable.border_color);
            option3.setTextColor(Color.parseColor("#222222"));

            option4.setBackgroundResource(R.drawable.border_color);
            option4.setTextColor(Color.parseColor("#222222"));

            questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
            question.setText(questionsLists.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsLists.get(currentQuestionPosition).getOption1());
            option2.setText(questionsLists.get(currentQuestionPosition).getOption2());
            option3.setText(questionsLists.get(currentQuestionPosition).getOption3());
            option4.setText(questionsLists.get(currentQuestionPosition).getOption4());

        }
        else {

            myDialog.setContentView(R.layout.activity_math_quiz3);
            TextView button1 = myDialog.findViewById(R.id.correctanswerid);
            TextView button2 = myDialog.findViewById(R.id.incorrectAnswersid);

            Button btn = myDialog.findViewById(R.id.okbtnid);
            button1.setText("Correct Answer : "+getCorrectAnswers());
            button2.setText("Wrong Answer : "+getInCorrectAnswers());

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MathQuiz2Activity.this,RewardsActivity.class);
                    startActivity(intent);
                }
            });
            myDialog.show();

//            Intent intent = new Intent(MathQuiz2Activity.this, MathQuiz3Activity.class);
//            intent.putExtra("correct", getCorrectAnswers());
//            intent.putExtra("incorrect", getInCorrectAnswers());
//            startActivity(intent);

//            finish();
        }
    }
    private void startTimer(TextView timerTextview){

        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (seconds ==0){
                    totalTimeInMins--;
                    seconds = 59;
                }
                else if (seconds == 0 && totalTimeInMins == 0){
                    quizTimer.purge();
                    quizTimer.cancel();
                    Toast.makeText(MathQuiz2Activity.this, "Time Over", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MathQuiz2Activity.this, MathQuiz3Activity.class);
                    intent.putExtra("correct",getCorrectAnswers() );
                    intent.putExtra("incorrect", getInCorrectAnswers());
                    startActivity(intent);
                    finish();
                }
                else{
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length() ==5){
                            finalMinutes = "0"+finalMinutes;
                        }
                        if(finalSeconds.length() == 1){
                            finalSeconds = "0"+finalSeconds;
                        }

                        timerTextview.setText(finalMinutes +":"+finalSeconds);
                    }
                });

            }
        },1000, 1000);

    }
    private int getCorrectAnswers(){

        int correctAnswers = 0;

        for (int i=0;i<questionsLists.size();i++){
            final String getuserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(getuserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }

        }
        return correctAnswers;


    }
    private int getInCorrectAnswers(){

        int correctAnswers = 0;

        for (int i=0;i<questionsLists.size();i++){
            final String getuserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(!getuserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }

        }
        return correctAnswers;


    }

    @Override
    public void onBackPressed() {
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(MathQuiz2Activity.this, RewardsActivity.class));
        finish();
    }
    @SuppressLint("ResourceAsColor")
    private void revealAnswer(){
        final String getAnswer = questionsLists.get(currentQuestionPosition).getAnswer();
        if(option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.round_btn_green);
//            option1.setTextColor(android.R.color.holo_green_dark);
            option1.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_btn_green);
            option2.setTextColor(Color.parseColor("#FFFFFF"));

        }
        else if(option3.getText().toString().equals(getAnswer)) {
            option3.setBackgroundResource(R.drawable.round_btn_green);
            option3.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else if(option4.getText().toString().equals(getAnswer)) {
            option4.setBackgroundResource(R.drawable.round_btn_green);
            option4.setTextColor(Color.parseColor("#FFFFFF"));
        }


    }
    private void result(String reslt) {
        mRequestQueue = Volley.newRequestQueue(MathQuiz2Activity.this);
        String result = reslt;

        mStringRequest = new StringRequest(Request.Method.POST,
                getBaseUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");
                    String message = jsonObject.getString("message");

//                    if (success.equals("0")) {
//                        //Toast.makeText(SpinWinActivity.this,message,Toast.LENGTH_SHORT).show();
//
//
//                    }
//                    if (success.equals("1")) {
//
////                                mProgress.setVisibility(View.GONE);
//                        Toast.makeText(SpinWinActivity.this,message,Toast.LENGTH_SHORT).show();
//
//                    }


                } catch (JSONException e) {

//                            mProgress.setVisibility(View.GONE);
                    // Toast.makeText(SpinWinActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                        mProgress.setVisibility(View.GONE);
                // Toast.makeText(SpinWinActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("result", result);


                return params;
            }
        };

        mStringRequest.setShouldCache(false);
        mRequestQueue.add(mStringRequest);
    }
        private String getBaseUrl (){
            return "http://muthoyapp.riyacash.xyz/api/scratchwin.php";
        }
}