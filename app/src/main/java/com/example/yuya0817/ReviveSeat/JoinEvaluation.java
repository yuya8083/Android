package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class JoinEvaluation extends Activity {
    private Socket socket;
    private String comment;
    Button button;
    int stringToValue,recieveuserid,senduserid;
    TextView tv;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        recieveuserid = 1;
        senduserid = 2;

        tv = (TextView)findViewById(R.id.guest);

        Intent data = getIntent();
        name = data.getStringExtra("name");
        tv.setText(name);

        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar);
        // リスナー登録
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                // レートが変更された際の処理
                stringToValue = (int)(ratingBar.getRating());
            }
        });

        button=(Button)findViewById(R.id.top);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringToValue == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinEvaluation.this);
                    builder.setMessage("評価をしてください");
                    builder.show();
                }else{
                    EditText text = (EditText) findViewById(R.id.comment);
                    comment = text.getText().toString();
                    Intent intent = new Intent(JoinEvaluation.this, Top.class);
                    try {
                        socket = IO.socket("https://reviveseatserver.herokuapp.com/");
                        Log.d("1","1");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                        @Override
                        public void call(Object... args) {
                            Log.d("2","2");
                            // Sending an object
                            JSONObject obj = new JSONObject();
                            try {
                                obj.put("recieveuserid", recieveuserid);
                                obj.put("senduserid", senduserid);
                                obj.put("newhyoka", stringToValue);
                                obj.put("comment", comment);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            System.out.println(String.valueOf(obj));
                            socket.emit("sethyoka", obj);
                            socket.disconnect();
                        }

                    }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                        @Override
                        public void call(Object... args) {
                            Log.d("4","4");
                        }

                    });
                    socket.connect();
                    startActivity(intent);
                }
            }
        });
    }
}
