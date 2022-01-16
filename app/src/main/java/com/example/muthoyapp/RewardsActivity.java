package com.example.muthoyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RewardsActivity extends AppCompatActivity {

    private String selectedTopicName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        TextView btn = findViewById(R.id.rewardhistoryid);
        Button button = findViewById(R.id.redeemid);
        final LinearLayout mathquiz = findViewById(R.id.mathquizlayout);
        final LinearLayout spinandwin = findViewById(R.id.spinandwinlayout);
        final LinearLayout scratchandwin = findViewById(R.id.scratchandwinlayout);

        scratchandwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RewardsActivity.this, ScratchWinActivity.class);
                startActivity(intent);
            }
        });

        spinandwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RewardsActivity.this, SpinWinActivity.class);
                startActivity(intent);
            }
        });

        mathquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "mathquiz";
                Intent intent=new Intent(RewardsActivity.this, MathQuizActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RewardsActivity.this,RedeemActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RewardsActivity.this, RewardsHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}