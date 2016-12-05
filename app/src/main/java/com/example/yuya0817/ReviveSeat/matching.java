package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class matching extends Activity {
    private Socket socket;
    int userid,hyoka;
    String name;
    int flag;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Intent data = getIntent();
        userid = data.getIntExtra("userid", 0);
        name = data.getStringExtra("name");
        hyoka = data.getIntExtra("hyoka", 0);

        TextView guest = (TextView)findViewById(R.id.guest);
        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar);

        guest.setText(name);
        bar.setRating(hyoka);

        Button cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("answer", 0);
                        socket.disconnect();
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        flag = 1;
                    }

                });
                socket.connect();
                while (flag == 0){
                    intent = new Intent(matching.this, wait.class);
                }
//                startActivity(intent);
                finish();
            }
        });
        Button share=(Button)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("answer", 1);
                        socket.disconnect();
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        flag = 1;
                    }

                });
                socket.connect();
                while (flag == 0){
                    intent = new Intent(matching.this, arrive_wait.class);
                }
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

    }

}
