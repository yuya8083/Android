package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class orner_wait extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orner_wait);

        findViewById(R.id.cycle).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));
        Handler match = new Handler();
        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は2秒
        match.postDelayed(new orner_wait.splashHandler(), 5000);

        Button returnButton = (Button) findViewById(R.id.cancel);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    public void onBackButtonTapped(View view){
//        finish();
//    }

    class splashHandler implements Runnable {
        public void run() {
            Intent intent = new Intent(orner_wait.this, Check_in.class);
            //Log.d("1","1");
            startActivity(intent);
            //Log.d("2","2");
            orner_wait.this.finish();
        }
    }
}
