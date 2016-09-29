package com.example.yuya0817.ReviveSeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

public class arrive_wait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrive_wait);

        findViewById(R.id.cycle).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));
    }
}
