package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.emitter.Emitter;


public class wait extends Activity {
    public io.socket.client.Socket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        Button returnButton = (Button) findViewById(R.id.cancel);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    socket = IO.socket("https://reviveseatserver.herokuapp.com/");//http://133.25.196.30:2010
                }
                catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        //送信
                        socket.emit("decide",0);
                        socket.disconnect();
                    }
                });
                socket.connect();
                wait.this.finish();
            }
        });

    socket.on("decide_back", new Emitter.Listener() {
        @Override
        public void call(final Object... args) {//detail_back.shop_name
            wait.super.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0; i<3; i++)
                    {
                        System.out.println(String.valueOf(args[i])); //うけとった文字列が何か調べてる！
                    }
                    socket.disconnect();
                    if(args[0].equals(null))
                    {
                    }
                    else{
                        Intent intent = new Intent(wait.this, matching.class);
                        startActivity(intent);
                    }
                }
            });
        }
    });
    socket.connect();
    }

//    public void onBackButtonTapped(View view){
//        finish();
//    }

}
