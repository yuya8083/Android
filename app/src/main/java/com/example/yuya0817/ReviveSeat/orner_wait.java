package com.example.yuya0817.ReviveSeat;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

public class orner_wait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orner_wait);

        findViewById(R.id.cycle).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));
        Handler match = new Handler();
        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は2秒
        match.postDelayed(new orner_wait.splashHandler(), 5000);
    }

    public void onBackButtonTapped(View view){
        finish();
    }

    class splashHandler implements Runnable {
        public void run() {
            Intent intent = new Intent(orner_wait.this, check_in.class);
            startActivity(intent);
            orner_wait.this.finish();
        }
    }
}
