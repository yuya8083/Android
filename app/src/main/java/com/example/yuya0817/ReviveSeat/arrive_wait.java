package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class arrive_wait extends Activity {
    public Socket socket;
    Intent intent,data;
    int i;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrive_wait);

        data = getIntent();
        name = data.getStringExtra("name");

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }

        findViewById(R.id.progressBar).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));

        socket.on("gcheck_back", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println(String.valueOf(args[0]));
                i = (int) args[0];

                socket.disconnect();
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                intent = new Intent(arrive_wait.this, Sharing.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        socket.connect();

        Button chatt = (Button) findViewById(R.id.chat);
        chatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(arrive_wait.this, chat_system.class);
                startActivity(intent);
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(arrive_wait.this, Top.class);
                startActivity(intent);
            }
        });
    }
}
