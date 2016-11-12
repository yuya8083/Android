package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class arrive_wait extends Activity {
    public Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrive_wait);

        findViewById(R.id.product_image_loading).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));
        Handler match = new Handler();
        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は2秒
        match.postDelayed(new arrive_wait.splashHandler(), 2000);

        Button returnButton = (Button) findViewById(R.id.cancel);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    socket = IO.socket("https://reviveseatserver.herokuapp.com/");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                Emitter on = socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        //送信
                        socket.emit("detail",0);
                        socket.disconnect();
                    }
                });
                //socket.connect();
            }
        });
    }

//    public void onBackButtonTapped(View view){
//        finish();
//    }

    class splashHandler implements Runnable {
        public void run() {
            Intent intent = new Intent(arrive_wait.this, Sharing.class);
            startActivity(intent);
            //arrive_wait.this.finish();
        }
    }
}
