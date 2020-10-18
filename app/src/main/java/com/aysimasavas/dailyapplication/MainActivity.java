package com.aysimasavas.dailyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.aysimasavas.dailyapplication.uı.firstFragment;
import com.aysimasavas.dailyapplication.uı.secondFragment;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    private firstFragment firstFragment;
    private secondFragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstFragment=new firstFragment();
        secondFragment=new secondFragment();

        firstFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout,firstFragment).commit();




    }
}