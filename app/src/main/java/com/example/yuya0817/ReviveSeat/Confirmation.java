package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;


import java.net.MalformedURLException;


import java.net.URISyntaxException;


import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Confirmation extends Activity {


    private io.socket.client.Socket socket;
    public String title,item,hour,minute,text,seat1,seat2,seat3,seat4,seat;
    public int categoryid,shopid,tableid,userid,seatinfo,share_id;
    private ToggleButton toggleButton1, toggleButton2, toggleButton3, toggleButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView titletext = (TextView) findViewById(R.id.titletext);
        TextView categorytext = (TextView) findViewById(R.id.categorytext);
        TextView timetext = (TextView) findViewById(R.id.timetext);
        TextView hosokutext = (TextView) findViewById(R.id.hosokutext);

        // インテントを取得
        Intent data = getIntent();
        // インテントに保存されたデータを取得
        title = data.getStringExtra("title");
        item = data.getStringExtra("item");
        hour = data.getStringExtra("hour");
        minute = data.getStringExtra("minute");
        text = data.getStringExtra("text");

        String sheet1 = data.getStringExtra("sheet1");
        String sheet2 = data.getStringExtra("sheet2");
        String sheet3 = data.getStringExtra("sheet3");
        String sheet4 = data.getStringExtra("sheet4");

        seat1 = data.getStringExtra("seat1");
        seat2 = data.getStringExtra("seat2");
        seat3 = data.getStringExtra("seat3");
        seat4 = data.getStringExtra("seat4");
        Log.d("seat1",seat1);
        Log.d("seat2",seat2);
        Log.d("seat3",seat3);
        Log.d("seat4",seat4);

        seat = seat1+seat2+seat3+seat4;
        Log.d("seat",seat);

        toggleButton1 = (ToggleButton) findViewById(R.id.button1);
        toggleButton1.setTextOn(sheet1);
        toggleButton1.setTextOff(sheet1);
        toggleButton1.setChecked(false);


        toggleButton2 = (ToggleButton) findViewById(R.id.button2);
        toggleButton2.setTextOn(sheet2);
        toggleButton2.setTextOff(sheet2);
        toggleButton2.setChecked(false);

        toggleButton3 = (ToggleButton) findViewById(R.id.button3);
        toggleButton3.setTextOn(sheet3);
        toggleButton3.setTextOff(sheet3);
        toggleButton3.setChecked(false);

        toggleButton4 = (ToggleButton) findViewById(R.id.button4);
        toggleButton4.setTextOn(sheet4);
        toggleButton4.setTextOff(sheet4);
        toggleButton4.setChecked(false);


        titletext.setText(title);
        categorytext.setText(item);
        timetext.setText(hour + "時" + minute + "分");
        hosokutext.setText(text);

        categoryid = Integer.valueOf(item);
        shopid = 1;
        tableid = 1;
        userid = 1;
        seatinfo = Integer.valueOf(seat);

        Button myButton = (Button) findViewById(R.id.next);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("0","0");

                Intent intent = new Intent(Confirmation.this, wait.class);

                //mSocket.on("test_back", onNewMessage);
                intent = new Intent(Confirmation.this, wait.class);
                Log.d("7","7");

                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2","2-1");
                        // Sending an object
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("title", title);
                            obj.put("category_id", categoryid);
                            obj.put("endtime", hour + ":" + minute);
                            obj.put("explain", text);
                            obj.put("shopid", shopid);
                            obj.put("tableid", tableid);
                            obj.put("userid", userid);
                            obj.put("seatinfo", seatinfo);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        socket.emit("sharetable_start", obj);
                        //socket.disconnect();
                    }

                }).on("sharetable_start_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("3","3");

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
        });

        Button returnButton = (Button) findViewById(R.id.back);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

        @Override
        protected void onResume() {
            super.onResume();
        }

        @Override
        protected void onPause() {
            super.onPause();
        }

        @Override
        public void onStop() {
            super.onStop();
        }
    }




