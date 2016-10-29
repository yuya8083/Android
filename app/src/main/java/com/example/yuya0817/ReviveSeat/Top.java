package com.example.yuya0817.ReviveSeat;

import android.content.Intent;
import android.os.Handler;
import android.app.Activity;
import android.os.Bundle;

public class Top extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        Handler match = new Handler();
        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は5秒
        match.postDelayed(new Top.splashHandler(), 5000);
    }

    class splashHandler implements Runnable {
        public void run() {
            Intent intent = new Intent(Top.this, MainActivity.class);
            startActivity(intent);
            //arrive_wait.this.finish();
        }
    }
}
