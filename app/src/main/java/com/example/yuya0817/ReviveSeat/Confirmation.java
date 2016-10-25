package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

public class Confirmation extends Activity {

    private Handler handler = new Handler();
    private SocketIO socket;
    private String title,item,hour,minute,text;
    private int categoryid,shopid,tableid,userid,seatinfo;

    private ToggleButton toggleButton1,toggleButton2,toggleButton3,toggleButton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView titletext = (TextView)findViewById(R.id.titletext);
        TextView categorytext = (TextView)findViewById(R.id.categorytext);
        TextView timetext = (TextView)findViewById(R.id.timetext);
        TextView hosokutext = (TextView)findViewById(R.id.hosokutext);

        // インテントを取得
        Intent data = getIntent();
        // インテントに保存されたデータを取得
        title = data.getStringExtra("title");
        item = data.getStringExtra("item");
        hour = data.getStringExtra("hour");
        minute = data.getStringExtra("minute");
        text = data.getStringExtra("text");
//        final boolean sheet1 = data.getBooleanExtra("sheet1",true);
//        final boolean sheet2 = data.getBooleanExtra("sheet2",true);
//        final boolean sheet3 = data.getBooleanExtra("sheet3",true);
//        final boolean sheet4 = data.getBooleanExtra("sheet4",true);
//        final String title = data.getStringExtra("title");
//        final String item = data.getStringExtra("item");
//        final String hour = data.getStringExtra("hour");
//        final String minute = data.getStringExtra("minute");
//        final String text = data.getStringExtra("text");
        String sheet1 = data.getStringExtra("sheet1");
        String sheet2 = data.getStringExtra("sheet2");
        String sheet3 = data.getStringExtra("sheet3");
        String sheet4 = data.getStringExtra("sheet4");

        toggleButton1=(ToggleButton)findViewById(R.id.button1);
            toggleButton1.setTextOn(sheet1);
            toggleButton1.setTextOff(sheet1);
            toggleButton1.setChecked(true);



        toggleButton2=(ToggleButton)findViewById(R.id.button2);
        toggleButton2.setTextOn(sheet2);
        toggleButton2.setTextOff(sheet2);
        toggleButton2.setChecked(true);

        toggleButton3=(ToggleButton)findViewById(R.id.button3);
        toggleButton3.setTextOn(sheet3);
        toggleButton3.setTextOff(sheet3);
        toggleButton3.setChecked(true);

        toggleButton4=(ToggleButton)findViewById(R.id.button4);
        toggleButton4.setTextOn(sheet4);
        toggleButton4.setTextOff(sheet4);
        toggleButton4.setChecked(true);


        titletext.setText(title);
        categorytext.setText(item);
        timetext.setText(hour + "時" + minute + "分");
        hosokutext.setText(text);

        categoryid= Integer.parseInt(item);
        shopid=1;
        tableid=1;
        userid=1;
        seatinfo=1002;

        Button myButton=(Button)findViewById(R.id.next);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Confirmation.this, wait.class);
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

    private void connect() throws MalformedURLException {
        SocketIO socket = new SocketIO("https://reviveseatserver.herokuapp.com/");
        socket.connect(iocallback);
    }

    private IOCallback iocallback = new IOCallback() {

        @Override
        public void onConnect() {
            System.out.println("onConnect");
        }

        @Override
        public void onDisconnect() {
            System.out.println("onDisconnect");
        }

        @Override
        public void onMessage(JSONObject json, IOAcknowledge ack) {
            System.out.println("onMessage");
        }

        @Override
        public void onMessage(String data, IOAcknowledge ack) {
            System.out.println("onMessage");
        }

        @Override
        public void on(String event, IOAcknowledge ack, Object... args) {
            final JSONObject message = (JSONObject)args[0];

            new Thread(new Runnable() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                if(message.getString("share_id") != null) {
                                    // メッセージが空でなければ追加
                                    message.put("share_id", message);
                                    //adapter.insert(message.getString("message"), 0);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }).start();
        }

        @Override
        public void onError(SocketIOException socketIOException) {
            System.out.println("onError");
            socketIOException.printStackTrace();
        }
    };

    public void sendEvent(View view){
        try {
            // イベント送信
            JSONObject json = new JSONObject();
            json.put("title", title);
            json.put("category_id", categoryid);
            json.put("endtime", hour + ":" + minute);
            json.put("explain", text);
            json.put("shopid", shopid);
            json.put("tableid", tableid);
            json.put("userid", userid);
            json.put("seatinfo", seatinfo);

            socket.emit("sharetable_start", json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
