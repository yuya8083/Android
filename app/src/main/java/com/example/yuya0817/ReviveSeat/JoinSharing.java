package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class JoinSharing extends Activity {
    private Socket socket;
    int flag,shareid;
    String hyokauser[] = new String[2];
    Intent intent,data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_sharing);

        data = getIntent();
        shareid = data.getIntExtra("shareid", 0);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("2", "2");
                socket.emit("hyokauser", shareid); //shareid送る
            }

        }).on("hyokauser_back", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println(String.valueOf(args[0]));
                hyokauser = (String[]) args[0];
                System.out.println(hyokauser); //[0]:ホストユーザ名, [1]:ゲストユーザ名
                socket.disconnect();
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                flag = 1;
            }

        });
        socket.connect();


        Button button=(Button)findViewById(R.id.finishbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do {
                    intent = new Intent(JoinSharing.this, JoinEvaluation.class);
                }while (flag == 0);
                intent.putExtra("ホスト", hyokauser[0]);
                startActivity(intent);
            }
        });
    }
}
