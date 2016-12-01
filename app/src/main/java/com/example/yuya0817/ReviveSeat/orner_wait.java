package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class orner_wait extends Activity {

    public Socket socket;
    int i,shareid;
    Intent intent,data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orner_wait);

        data = getIntent();
        shareid = data.getIntExtra("shareid", 0);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        findViewById(R.id.progressBar).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));

        socket.on("answer_back", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println(String.valueOf(args[0]));
                i = (int) args[0];
                socket.disconnect();
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("4","4");
                if (i == 0){
                    intent = new Intent(orner_wait.this, Top.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(orner_wait.this, Check_in.class);
                    intent.putExtra("shareid", shareid);
                    startActivity(intent);
                }
            }

        });
//            @Override
//            public void call(final Object... args) {
//                orner_wait.super.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println(String.valueOf(args[0])); //うけとった文字列が何か調べてる！
//                        socket.disconnect();
//
//                        if (args[0].equals("1")) {//obj.args[0].equals("0"))
//                            Intent intent = new Intent(orner_wait.this, Check_in.class);
//                            startActivity(intent);
//                        }
//                        else if (args[0].equals("0")) {//obj.args[0].equals("0"))
//                            Intent intent = new Intent(orner_wait.this, Top.class);
//                            startActivity(intent);
//                        }
//                    }
//                });
//            }
//        });
        socket.connect();

        Button returnButton = (Button) findViewById(R.id.cancel);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(orner_wait.this, Top.class);
                startActivity(intent);
            }
        });
    }
}
