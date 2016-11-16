package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;



public class MainActivity extends Activity {
    //public SocketIO socketIO;
    public Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //作成ボタン
        Button myButton=(Button)findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, qrcode.class);
                startActivity(intent);
            }
        });
        //参加ボタン
        Button myButton2=(Button)findViewById(R.id.button2);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent2 = new Intent(MainActivity.this, Select_find_method.class);
                Intent intent2 = new Intent(MainActivity.this, share_table_list.class);
                startActivity(intent2);
            }
        });

        Button myButton3=(Button)findViewById(R.id.button3);
        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, TableSet.class);
                startActivity(intent3);
            }
        });

        Button myButton4=(Button)findViewById(R.id.button4);
        myButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this, MapsActivity.class);
                //Intent intent4 = new Intent(MainActivity.this, JoinConfirmation.class);
//                try {
//                    socketIO = new SocketIO("https://reviveseatserver.herokuapp.com/socketio-test.html");
//                    Log.d("1","1");
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//                socketIO.connect(new IOCallback() {
//                    @Override
//                    public void onMessage(JSONObject json, IOAcknowledge ack) {
//                        try {
//                            System.out.println("Server said:" + json.toString(2));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onMessage(String data, IOAcknowledge ack) {
//                        System.out.println("Server said: " + data);
//                    }
//
//                    @Override
//                    public void onError(SocketIOException socketIOException) {
//                        System.out.println("an Error occured");
//                        socketIOException.printStackTrace();
//                    }
//
//                    @Override
//                    public void onDisconnect() {
//                        System.out.println("Connection terminated.");
//                    }
//
//                    @Override
//                    public void onConnect() {
//                        System.out.println("Connection established");
//                        socket.emit("test", "Hello Server!");
//                    }
//
//                    @Override
//                    public void on(String event, IOAcknowledge ack, Object... args) {
//                        System.out.println("Server triggered event '" + event + "'");
//                    }
//                });

                try {
                    socket = IO.socket("https://reviveseatserver.herokuapp.com/");
                    Log.d("1","1");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    Log.e("-1","-1");
                }
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2","2");
                        //socket.emit("test", "hi");
                        // Sending an object
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("hello", "server");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        socket.emit("test", obj);
                        //socket.disconnect();
                    }

                }).on("test_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("3","3");
                        JSONObject obj2 = (JSONObject)args[0];
                        Log.d("recieve", obj2.toString());
                        System.out.println(obj2);
                        socket.disconnect();
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("4","4");
                    }

                });
                socket.connect();

                // This line is cached until the connection is establisched.
                startActivity(intent4);
            }
        });

    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
}
