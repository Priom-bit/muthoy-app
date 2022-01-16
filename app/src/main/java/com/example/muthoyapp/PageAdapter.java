package com.example.muthoyapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.sql.Ref;

public class PageAdapter extends FragmentPagerAdapter {
    int numCount;

    public PageAdapter(@NonNull FragmentManager fm, int numCount) {
        super(fm);
        this.numCount = numCount;
    }

    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                All all=new All();
                return all;
            case 1:
                DailyCheckin dailyCheckin=new DailyCheckin();
                return dailyCheckin;
            case 2:
                SpinandWin spinandWin=new SpinandWin();
                return spinandWin;
            case 3:
                ScratchandWin scratchandWin=new ScratchandWin();
                return scratchandWin;
            case 4:
                MathQuiz mathQuiz=new MathQuiz();
                return mathQuiz;
            case 5:
                Game game=new Game();
                return game;
            case 6:
                Survey survey=new Survey();
                return survey;
            case 7:
                ReferandWin referandWin=new ReferandWin();
                return referandWin;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numCount;
    }
}
