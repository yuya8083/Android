package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import io.socket.SocketIO;


//import com.github.nkzawa.socketio.client.Socket;

public class Confirmation extends Activity {

    private Handler handler = new Handler();
    public static SocketIO socket;
    public static String title, item, hour, minute, text;
    public static int categoryid, shopid, tableid, userid, seatinfo;

    //public static SocketIO getsocket(){return socket;}

    private ToggleButton toggleButton1, toggleButton2, toggleButton3, toggleButton4;

    private Socket mSocket;

    {
        try {
            IO.Options opts = new IO.Options();
            // IO.socket("サーバーから提示提供されたURL");
            mSocket = IO.socket("https://reviveseatserver.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
//        mSocket.connect();

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
////        final boolean sheet1 = data.getBooleanExtra("sheet1",true);
////        final boolean sheet2 = data.getBooleanExtra("sheet2",true);
////        final boolean sheet3 = data.getBooleanExtra("sheet3",true);
////        final boolean sheet4 = data.getBooleanExtra("sheet4",true);
////        final String title = data.getStringExtra("title");
////        final String item = data.getStringExtra("item");
////        final String hour = data.getStringExtra("hour");
////        final String minute = data.getStringExtra("minute");
////        final String text = data.getStringExtra("text");
        String sheet1 = data.getStringExtra("sheet1");
        String sheet2 = data.getStringExtra("sheet2");
        String sheet3 = data.getStringExtra("sheet3");
        String sheet4 = data.getStringExtra("sheet4");

        toggleButton1 = (ToggleButton) findViewById(R.id.button1);
        toggleButton1.setTextOn(sheet1);
        toggleButton1.setTextOff(sheet1);
        toggleButton1.setChecked(true);


        toggleButton2 = (ToggleButton) findViewById(R.id.button2);
        toggleButton2.setTextOn(sheet2);
        toggleButton2.setTextOff(sheet2);
        toggleButton2.setChecked(true);

        toggleButton3 = (ToggleButton) findViewById(R.id.button3);
        toggleButton3.setTextOn(sheet3);
        toggleButton3.setTextOff(sheet3);
        toggleButton3.setChecked(true);

        toggleButton4 = (ToggleButton) findViewById(R.id.button4);
        toggleButton4.setTextOn(sheet4);
        toggleButton4.setTextOff(sheet4);
        toggleButton4.setChecked(true);


        titletext.setText(title);
        categorytext.setText(item);
        timetext.setText(hour + "時" + minute + "分");
        hosokutext.setText(text);

        categoryid = new Integer(item).intValue();
        shopid = 1;
        tableid = 1;
        userid = 1;
        seatinfo = 1002;

        Button myButton = (Button) findViewById(R.id.next);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Log.d("3","3");
                mSocket.on("test_back", onNewMessage);
                Log.d("4","4");
                Intent intent = new Intent(Confirmation.this, wait.class);
                Log.d("7","7");

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



        private Emitter.Listener onNewMessage = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.d("5", "5");
                Confirmation.super.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("6", "6");
                        Log.d("受信データ", String.valueOf(args[0]));
                    }
                });
            }
        };

        private void connect() throws MalformedURLException {
            mSocket.connect();
            //String sendData = "3000,1," + linkData();
            //Log.d("SENDDATA", sendData);
            // イベント送信 mSocket.emit("サーバー班が決めたkey", sendData);
            Log.d("1", "1");
            mSocket.emit("test", "1");
            Log.d("2", "2");

//        SocketIO socket = new SocketIO("https://reviveseatserver.herokuapp.com");
//        Intent intent = new Intent(this, MyIntentService.class);
//        intent.putExtra("title", title);
//        intent.putExtra("category_id", categoryid);
//        intent.putExtra("endtime", hour + ":" + minute);
//        intent.putExtra("explain", text);
//        intent.putExtra("shopid", shopid);
//        intent.putExtra("tableid", tableid);
//        intent.putExtra("userid", userid);
//        intent.putExtra("seatinfo", seatinfo);
//
//        Log.e("title",title);
//        Log.e("category_id", item);
//        Log.e("explain", text);
//        Log.e("shopid", Integer.toString(shopid));
//        Log.e("tableid", Integer.toString(tableid));
//        Log.e("userid", Integer.toString(userid));
//        Log.e("seatinfo", Integer.toString(seatinfo));
//
//        Log.e("",intent.toString());
//
//        this.startService(intent);
//
//        Log.e("ok", "intent");


//        socket.connect(iocallback);
        }

//    private IOCallback iocallback = new IOCallback() {
//
//        @Override
//        public void onConnect() {
//            System.out.println("onConnect");
//        }
//
//        @Override
//        public void onDisconnect() {
//            System.out.println("onDisconnect");
//        }
//
//        @Override
//        public void onMessage(JSONObject json, IOAcknowledge ack) {
//            System.out.println("onMessage");
//        }
//
//        @Override
//        public void onMessage(String data, IOAcknowledge ack) {
//            System.out.println("onMessage");
//        }
//
//        @Override
//        public void on(String event, IOAcknowledge ack, Object... args) {
//            final JSONObject message = (JSONObject)args[0];
//
//            new Thread(new Runnable() {
//                public void run() {
//                    handler.post(new Runnable() {
//                        public void run() {
//                            try {
//                                if(message.getString("share_id") != null) {
//                                    // メッセージが空でなければ追加
//                                    message.put("share_id", message);
//                                    //adapter.insert(message.getString("message"), 0);
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                }
//            }).start();
//        }
//
//        @Override
//        public void onError(SocketIOException socketIOException) {
//            System.out.println("onError");
//            socketIOException.printStackTrace();
//        }
//    };
//
//    public void sendEvent(View view){
//        try {
//            // イベント送信
//            JSONObject json = new JSONObject();
//            json.put("title", title);
//            json.put("category_id", categoryid);
//            json.put("endtime", hour + ":" + minute);
//            json.put("explain", text);
//            json.put("shopid", shopid);
//            json.put("tableid", tableid);
//            json.put("userid", userid);
//            json.put("seatinfo", seatinfo);
//
//////            socket.emit("sharetable_start", json);
//            socket.emit("test", json);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

//    private void post(){
//        //MyIntentServiceを起動する
//        Intent intent = new Intent(this, MyIntentService.class);
//        intent.putExtra("title", title);
//        intent.putExtra("category_id", categoryid);
//        intent.putExtra("endtime", hour + ":" + minute);
//        intent.putExtra("explain", text);
//        intent.putExtra("shopid", shopid);
//        intent.putExtra("tableid", tableid);
//        intent.putExtra("userid", userid);
//        intent.putExtra("seatinfo", seatinfo);
//        this.startService(intent);
//    }

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

