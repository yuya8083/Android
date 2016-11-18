package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.IOAcknowledge;
import io.socket.client.IO;
import io.socket.emitter.Emitter;

public class share_table_list extends Activity {

    private Handler handler = new Handler();
    //private SocketIO socket;
    private Object a[];
    private TextView textView1;
    private String servermessage,test="test";
    private IOAcknowledge ack;

    /*private void connectSocketIO() throws MalformedURLException {
        socket = new SocketIO("https://reviveseatserver.herokuapp.com/");
        socket.connect(iocallback);
    }*/
    private io.socket.client.Socket socket;

    /*{
        try {
            IO.Options opts = new IO.Options();
            // IO.socket("サーバーから提示提供されたURL");
            mSocket = IO.socket("https://reviveseatserver.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }*/

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



        textView1=(TextView)findViewById(R.id.textView1) ;



        /*mSocket.emit("test","a");
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
        });

        mSocket.connect();*/









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

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
            Log.d("1","1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("-1","-1");
        }
        socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("2","2");
                //socket.emit("sharetable_list", "hi");
                // Sending an object
                JSONObject obj = new JSONObject();
                try {
                    obj.put("hello", "server");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //socket.emit("sharetable_list", obj);
                socket.emit("sharetable_test_back",obj);
                textView1.setText((CharSequence) obj);
                socket.disconnect();
            }

        }).on("sharetable_list_back", new Emitter.Listener() {


            @Override
            public void call(Object... args) {
                Log.d("3","3");
                JSONObject obj = (JSONObject)args[0];
                textView1.setText((CharSequence) obj);
            }

        }).on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("4","4");
            }

        });
        socket.connect();

    }




    public void onBackButtonTapped(View view){
        finish();
    }
}
