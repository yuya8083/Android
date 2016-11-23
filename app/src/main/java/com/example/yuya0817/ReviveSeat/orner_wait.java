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

public class orner_wait extends Activity {

    public Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orner_wait);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
            Log.d("1","1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on("answer_back", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                orner_wait.super.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(String.valueOf(args[0])); //うけとった文字列が何か調べてる！
                        socket.disconnect();

                        if (args[0].equals("1")) {//obj.args[0].equals("0"))
                            Intent intent = new Intent(orner_wait.this, Check_in.class);
                            startActivity(intent);
                        }
                        else if (args[0].equals("0")) {//obj.args[0].equals("0"))
                            Intent intent = new Intent(orner_wait.this, Top.class);
                            startActivity(intent);
                        }
                    }
                });
            }

        });
        socket.connect();

        Button returnButton = (Button) findViewById(R.id.cancel);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orner_wait.this.finish();
            }
        });
    }
}
