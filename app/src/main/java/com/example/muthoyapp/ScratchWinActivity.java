package com.example.muthoyapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.anupkumarpanwar.scratchview.ScratchView;

public class ScratchWinActivity extends AppCompatActivity {
    private ScratchView scratchView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_win);
        scratchView= findViewById(R.id.scratchView);

       scratchView.setRevealListener(new ScratchView.IRevealListener() {
           @Override
           public void onRevealed(ScratchView scratchView) {

           }

           @Override
           public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {

           }
       });
    }
}