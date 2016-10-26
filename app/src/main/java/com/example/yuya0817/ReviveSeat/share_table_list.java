package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

public class share_table_list extends Activity {

    private Handler handler = new Handler();
    private SocketIO socket;
    private Object a[];
    private TextView textView1;
    private String servermessage,test="test";
    private IOAcknowledge ack;

    private void connectSocketIO() throws MalformedURLException {
        socket = new SocketIO("https://reviveseatserver.herokuapp.com/");
        socket.connect(iocallback);
    }
    public void sendEvent(View view){
        try {
            // イベント送信
            JSONObject json = new JSONObject();

            json.put("test", "1");

            socket.emit("test");
            //socket.emit("sharetable_back",a);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_table_list);





        textView1=(TextView)findViewById(R.id.textView1) ;

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
                                }*/

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


    public void onBackButtonTapped(View view){
        finish();
    }
}
