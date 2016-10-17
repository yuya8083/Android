package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;

public class arrive_wait extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrive_wait);

        findViewById(R.id.cycle).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));
        Handler match = new Handler();
        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は2秒
        match.postDelayed(new arrive_wait.splashHandler(), 5000);
    }

    public void onBackButtonTapped(View view){
        finish();
    }

    class splashHandler implements Runnable {
        public void run() {
            Intent intent = new Intent(arrive_wait.this, Sharing.class);
            startActivity(intent);
            //arrive_wait.this.finish();
        }
    }
}
