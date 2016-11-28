package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class wait extends Activity {
    public Socket socket;
    int userid,hyoka,flag;
    String name;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");//http://133.25.196.30:2010
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }

        findViewById(R.id.progressBar).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));
//        Handler match = new Handler();
//        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は2秒
//        match.postDelayed(new wait.splashHandler(), 2000);

//        flag = 0;
        socket.on("decide_back", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println(String.valueOf(args[0]));
                JSONObject json = (JSONObject)args[0];
                try {
                    userid = json.getInt("userid");
                    name = json.getString("name");
                    hyoka = json.getInt("hyoka");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(userid);
                System.out.println(name);
                System.out.println(hyoka);

                socket.disconnect();
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("4","4");
                intent = new Intent(wait.this, matching.class);
                intent.putExtra("userid", userid);
                intent.putExtra("name", name);
                intent.putExtra("hyoka", hyoka);
                startActivity(intent);
            }
        });
        socket.connect();

        Button returnButton = (Button) findViewById(R.id.cancel);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//            @Override
//            public void call(final Object... args) {//detail_back.shop_name
//                wait.super.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for(int i=0; i<3; i++)
//                        {
//                            System.out.println(String.valueOf(args[i])); //うけとった文字列が何か調べてる！
//                        }
//                        socket.disconnect();
//                        if(args[0].equals(null))
//                        {
//                        }
//                        else{
//                            Intent intent = new Intent(wait.this, matching.class);
//                            startActivity(intent);
//                        }
//                    }
//                });
//            }

//    socket.on("decide_back", new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {//detail_back.shop_name
//            wait.super.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    for(int i=0; i<3; i++)
//                    {
//                        System.out.println(String.valueOf(args[i])); //うけとった文字列が何か調べてる！
//                    }
//                    socket.disconnect();
//                    if(args[0].equals(null))
//                    {
//                    }
//                    else{
//                        Intent intent = new Intent(wait.this, matching.class);
//                        startActivity(intent);
//                    }
//                }
//            });
//        }
//    });
//    socket.connect();
    }

//    class splashHandler implements Runnable {
//        public void run() {
//            flag = 0;
//            findViewById(R.id.progressBar).startAnimation(AnimationUtils.loadAnimation(wait.this, R.anim.a1));
//            socket.on("decide_back", new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//                    System.out.println(String.valueOf(args[0]));
//                    JSONObject json = (JSONObject)args[0];
//                    try {
//                        userid = json.getInt("userid");
//                        name = json.getString("name");
//                        hyoka = json.getInt("hyoka");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(userid);
//                    System.out.println(name);
//                    System.out.println(hyoka);
//
//                    socket.disconnect();
//                }
//            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
//
//                @Override
//                public void call(Object... args) {
//                    Log.d("4","4");
//                    flag = 1;
//                }
//            });
//            socket.connect();
//            while (flag == 0){
//                intent = new Intent(wait.this, matching.class);
//            }
//            intent.putExtra("userid", userid);
//            intent.putExtra("name", name);
//            intent.putExtra("hyoka", hyoka);
//            startActivity(intent);
//        }
//    }
}
