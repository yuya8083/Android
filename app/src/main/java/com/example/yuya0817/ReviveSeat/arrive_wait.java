package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        Button chatt = (Button) findViewById(R.id.chat);
        chatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(arrive_wait.this, chat_system.class);
                startActivity(intent);
            }
        });

        Button cansell = (Button) findViewById(R.id.cansel);
        cansell.setOnClickListener(new View.OnClickListener() {
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
                        socket.emit("decide",1);
                        socket.disconnect();
                    }
                });
                Intent intent = new Intent(arrive_wait.this, Top.class);
                startActivity(intent);
            }
        });

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
                        socket.emit("decide",0);
                        socket.disconnect();
                    }
                });
                arrive_wait.this.finish();
                //socket.connect();
            }
        });

        socket.on("gcheck_back", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {//detail_back.shop_name
                arrive_wait.super.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        socket.disconnect();
                        if(args[0].equals("1"))
                        {
                            Intent intent = new Intent(arrive_wait.this, Sharing.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        socket.connect();

    }
}
