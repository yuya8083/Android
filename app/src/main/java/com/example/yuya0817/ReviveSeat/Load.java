package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AnimationUtils;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Load extends Activity {
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        findViewById(R.id.progressBar).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));
        Handler match = new Handler();
        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は2秒
        match.postDelayed(new Load.splashHandler(), 2000);
    }

    class splashHandler implements Runnable {
        public void run() {
            try {
                socket = IO.socket("https://reviveseatserver.herokuapp.com/");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    Log.d("2","2");
                    // Sending an object
//                    JSONObject obj = new JSONObject();
//                    try {
//                        obj.put("username", username);
//                        obj.put("password", password);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    socket.emit("load", "hello");
                }

            }).on("load_back", new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    Log.d("3","3");
                    System.out.println(String.valueOf(args[0]));
//                        JSONObject obj2 = (JSONObject)args[0];
//                        try {
//                            share_id = obj2.getInt("share_id");
//                        } catch (JSONException e) {
//                            return;
//                        }
                    //Log.d("recieve", String.valueOf(share_id));
                    //socket.on("",);
//                    socket.disconnect();
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    Log.d("4","4");
                }

            });
            socket.connect();
        }
    }
}
