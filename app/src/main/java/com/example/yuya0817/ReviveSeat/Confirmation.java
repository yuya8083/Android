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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;


//import com.github.nkzawa.socketio.client.Socket;

public class Confirmation extends Activity {

    private Handler handler = new Handler();
    public static SocketIO socket;
    public static String title,item,hour,minute,text;
    public static int categoryid,shopid,tableid,userid,seatinfo;

//    public static SocketIO getsocket(){return socket;}

    private ToggleButton toggleButton1, toggleButton2, toggleButton3, toggleButton4;

//    private com.github.nkzawa.socketio.client.Socket mSocket;
//    {
//        try {
//            IO.Options opts = new IO.Options();
//            // IO.socket("サーバーから提示提供されたURL");
//            mSocket = IO.socket("https://reviveseatserver.herokuapp.com/socketio-test.html");
//            //mSocket = IO.socket("https://revuveserver.herokuapp.com/");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }

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
////        final boolean sheet1 = data.getBooleanExtra("sheet1",true);
////        final boolean sheet2 = data.getBooleanExtra("sheet2",true);
////        final boolean sheet3 = data.getBooleanExtra("sheet3",true);
////        final boolean sheet4 = data.getBooleanExtra("sheet4",true)
        String sheet1 = data.getStringExtra("sheet1");
        String sheet2 = data.getStringExtra("sheet2");
        String sheet3 = data.getStringExtra("sheet3");
        String sheet4 = data.getStringExtra("sheet4");

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
                    connectSocketIO();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //mSocket.on("test_back", onNewMessage);
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

//    private void connecting() throws MalformedURLException {
////        mSocket.connect();
////        //String sendData = "3000,1," + linkData();
////        //Log.d("SENDDATA", sendData);
////        // イベント送信 mSocket.emit("サーバー班が決めたkey", sendData);
////        Log.d("1","1");
////        mSocket.emit("test", "test");
////        Log.d("2","2");
//
////        socket = new SocketIO("https://reviveseatserver.herokuapp.com");
//        socket = new SocketIO("https://reviveseatserver.herokuapp.com/socketio-test.html");
////        SocketIO socket = new SocketIO("http://172.20.10.2:2010");
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
//        Log.e("title", title);
//        Log.e("category_id", item);
//        Log.e("explain", text);
//        Log.e("shopid", Integer.toString(shopid));
//        Log.e("tableid", Integer.toString(tableid));
//        Log.e("userid", Integer.toString(userid));
//        Log.e("seatinfo", Integer.toString(seatinfo));
//    }


//    private Emitter.Listener onNewMessage = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            Log.d("5", "5");
//            Confirmation.super.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Log.d("6", "6");
//                    Log.d("受信データ", String.valueOf(args[0]));
//                }
//            });
//        }
//    };

    private void connectSocketIO() throws MalformedURLException {
        // ローカルホストの3000のポートに接続開始
        socket = new SocketIO("https://reviveseatserver.herokuapp.com/socketio-test.html");
//        //socket = new SocketIO("http://172.20.10.2:2010");
        socket.connect(socketIOCallback);

//        socket = new SocketIO();
//        socket.connect("https://reviveseatserver.herokuapp.com/socketio-test.html", socketIOCallback);
//        SocketIO.setDefaultSSLSocketFactory(SSLContext.getDefault());

//        try {
//            // イベント送信
//            JSONObject json = new JSONObject();
//            json.put("title", title);
//            Log.d("test", json.toString());
//            socket.emit("test", json);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        //mSocket.connect();
//        //String sendData = "3000,1," + linkData();
//        //Log.d("SENDDATA", sendData);
//        // イベント送信 mSocket.emit("サーバー班が決めたkey", sendData);
//        Log.d("1", "1");
//        mSocket.emit("test", "1");
//        Log.d("2", "2");
////        SocketIO socket = new SocketIO("https://reviveseatserver.herokuapp.com");
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
//
////        Log.e("",intent.toString());
////
////        this.startService(intent);
////
////        Log.e("ok", "intent");
//
//
//        socket.connect(iocallback);
    }

    private IOCallback socketIOCallback = new IOCallback() {

        @Override
        public void onConnect() {
            System.out.println("onConnect");
//            try {
//                // イベント送信
//                JSONObject json = new JSONObject();
//                json.put("title", title);
//                Log.d("test", json.toString());
//                socket.emit("test", json);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            socket.emit("test", "hello");
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
            Log.d("5","5");

            final JSONObject message = (JSONObject)args[0];

            new Thread(new Runnable() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                // メッセージが空でなければ追加
                                Log.d("message",message.toString());
                                message.put("share_id", message);
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

