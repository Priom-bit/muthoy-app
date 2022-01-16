package com.example.muthoyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SpinWinActivity extends AppCompatActivity {
    private static final String[] sectors = {"0","5","10","20","30","50","100","150","200","300"};
    private static final int[] sectorDegrees = new int[sectors.length];
    private static final Random random =new Random();
    private int degree = 0;
    private boolean isSpinning = false;
    private ImageView wheel;
//    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_win);
        final ImageView spinBtn =findViewById(R.id.spinBtn);
        wheel = findViewById(R.id.wheel);

        getDegreeForSectors();
        spinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isSpinning){
                    spin();
                    isSpinning = true;

                }

            }
        });

//        myDialog = new Dialog(this);
//
//    }
//    public void SpinNow(View v){
//        Button btnOk;
//        myDialog.setContentView(R.layout.activity_win);
//        btnOk = (Button) myDialog.findViewById(R.id.btnok);
//        myDialog.show();
    }
    private void spin(){
        degree = random.nextInt(sectors.length-1);
        RotateAnimation rotateAnimation = new RotateAnimation(0,(360 * sectors.length) + sectorDegrees[degree],
                RotateAnimation.RELATIVE_TO_SELF,0.5f, RotateAnimation.RELATIVE_TO_SELF,0.5f);

        rotateAnimation.setDuration(60);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(SpinWinActivity.this, "You've got"+sectors[sectors.length + (degree +1)] + "Points.", Toast.LENGTH_SHORT).show();
                isSpinning = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        wheel.startAnimation(rotateAnimation);

    }
    private void getDegreeForSectors(){
        int sectorDegree= 3600/sectors.length;
        for (int i=0; i < sectors.length; i++){
            sectorDegrees[i] = (i+1) * sectorDegree;
        }
    }
};