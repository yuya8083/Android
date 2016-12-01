package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AnimationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Objects;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Load extends Activity {
    /**
     * 画面のデータを保持するDto
     */
//    private ViewDto dto;

    private Socket socket;
//    public SharedPreferences dataStore;
    String userid,shareid;
    int sharecheck,flag;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        findViewById(R.id.progressBar).startAnimation(AnimationUtils.loadAnimation(this, R.anim.a1));
        Handler match = new Handler();
        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は2秒
        match.postDelayed(new Load.splashHandler(), 2000);

    }

    class splashHandler implements Runnable {
        public void run() {
            findViewById(R.id.progressBar).startAnimation(AnimationUtils.loadAnimation(Load.this, R.anim.a1));
            // ファイルに保存したデータを読み込む
            userid = String.valueOf(TempDataUtil.load(Load.this));
            System.out.println(userid);
            flag = 0;

            if (!Objects.equals(userid, "null")){
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2","2");
                        socket.emit("load", userid);
                    }

                }).on("load_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("3","3");
                        System.out.println(String.valueOf(args[0]));
                        JSONObject obj = (JSONObject)args[0];
                        try {
                            sharecheck = obj.getInt("sharecheck");
                            shareid = obj.getString("shareid");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        socket.disconnect();
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("4","4");
                        flag = 1;
                    }

                });
                socket.connect();
                while (flag == 0){
                    intent = new Intent(Load.this, Top.class);
                }
                if (sharecheck == 0){
                    intent = new Intent(Load.this, Top.class);
                }else {
                    intent = new Intent(Load.this, Sharing.class);
                }
                startActivity(intent);
            }else {
                intent = new Intent(Load.this, NewUserSet.class);
                startActivity(intent);
            }

        }
    }
}
