package com.example.muthoyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScratchWin1Activity extends AppCompatActivity {

    TextView popid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_win1);

        popid= findViewById(R.id.popID);

         String Points = getIntent().getStringExtra("Points");



         popid.setText(Points+" Points");


    }
}