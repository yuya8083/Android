package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

//import com.github.nkzawa.socketio.client.IO;
//import com.github.nkzawa.socketio.client.Socket;

public class share_table_list extends Activity {

//    private Handler handler = new Handler();
//    //private SocketIO socket;
//    private Object a[];
//    private TextView textView1;
//    private String servermessage,test="test";
//    private IOAcknowledge ack;
//
//    /*private void connectSocketIO() throws MalformedURLException {
//        socket = new SocketIO("https://reviveseatserver.herokuapp.com/");
//        socket.connect(iocallback);
//    }*/
//    private Socket mSocket;

    public Socket socket;
    private TextView titletext,category_id,shopname;
    public int i,refine;

//    {
//        try {
//            IO.Options opts = new IO.Options();
//            // IO.socket("サーバーから提示提供されたURL");
//            mSocket = IO.socket("https://reviveseatserver.herokuapp.com/");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }

    /*public void sendEvent(View view){
        try {
            // イベント送信
            JSONObject json = new JSONObject();

            json.put("test", "1");

            socket.emit("test");
            //socket.emit("sharetable_back",a);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_table_list);

        category_id = (TextView)findViewById(R.id.textView);
        titletext = (TextView)findViewById(R.id.textView1);
        shopname = (TextView)findViewById(R.id.textView2);

        refine = 0;

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
                // Sending an object
                JSONObject json = new JSONObject();
                try {
                    json.put("refine",refine);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("sharetable_list",json);
            }

        }).on("sharetable_list_back", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println(String.valueOf(args[0]));
//                title.setText(String.valueOf(args[0]));


//                titletext.setText("こんにちは");
//                try {
//                    JSONObject jsonObject = new JSONObject(String.valueOf(args[0]));
//                    title.setText((Integer) jsonObject.get("[0].title"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                //title.setText(String.valueOf(args[0]).getChars("[0].title"));
//                socket.disconnect();
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("4","4");
            }

        });
        socket.connect();



//        textView1=(TextView)findViewById(R.id.textView1) ;


        /*mSocket.connect();
        mSocket.emit("test","a");
        mSocket.on("test_back", new Emitter.Listener() {
            @Override
            public void call(final Object... arg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data=(JSONObject)arg[0];
                        textView1.setText(arg[0].toString());
                    }
                });
            }
        });2

        mSocket.connect();*/




        //textView1.setText(onNewMessage.toString());

        //iocallback.on("test_back",ack,a);




        Button list1=(Button)findViewById(R.id.list1);
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent1);
            }
        });

        Button list2=(Button)findViewById(R.id.list2);
        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent2);
            }
        });

        Button list3=(Button)findViewById(R.id.list3);
        list3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent3);
            }
        });

        Button list4=(Button)findViewById(R.id.list4);
        list4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent4);
            }
        });

        Button list5=(Button)findViewById(R.id.list5);
        list5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent5);
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


    /*Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            share_table_list.super.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data=(JSONObject)args[0];
                    Log.d("受信データ", String.valueOf(args[0]));
                    textView1=(TextView)findViewById(R.id.textView1) ;
                    try {
                        textView1.setText(data.getString(""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };*/
    /*private IOCallback iocallback = new IOCallback() {

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
                                servermessage=message.toString();
                                servermessage="ks";
                                message.put("share_id", message);

                                textView1.setText(servermessage);
                                /*if(message.getString("share_id") != null) {
                                    // メッセージが空でなければ追加
                                    servermessage=message.toString();
                                    servermessage="ks";
                                    message.put("share_id", message);

                                    textView1.setText(servermessage);
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
*/

    public void onBackButtonTapped(View view){
        finish();
    }
}
