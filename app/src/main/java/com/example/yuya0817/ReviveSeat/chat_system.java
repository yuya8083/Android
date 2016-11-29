package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class chat_system extends Activity {

    public io.socket.client.Socket socket;
    public TextView textView[]=new TextView[10];
    public EditText editText;
    public String text;
    public int i=0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_system);


        Button send=(Button)findViewById(R.id.button1);
        editText=(EditText)findViewById(R.id.editText1);
        textView[0]=(TextView)findViewById(R.id.textView1);
        textView[1]=(TextView)findViewById(R.id.textView2);
        textView[2]=(TextView)findViewById(R.id.textView3);
        textView[3]=(TextView)findViewById(R.id.textView4);
        textView[4]=(TextView)findViewById(R.id.textView5);
        textView[5]=(TextView)findViewById(R.id.textView6);
        textView[6]=(TextView)findViewById(R.id.textView7);
        textView[7]=(TextView)findViewById(R.id.textView8);
        textView[8]=(TextView)findViewById(R.id.textView9);
        textView[9]=(TextView)findViewById(R.id.textView10);






        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");//http://133.25.196.30:2010
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }


        /*socket.on(socket.EVENT_CONNECT,new Emitter.Listener(){
            @Override
            public void call(Object... args){
                if (args[0]!=null){
                    textView[i].setText(String.valueOf(args[0]));
                    ++i;
                }
            }
        }).on("chat_reception", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        chat_system.super.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (i = 0; i < 10; ++i) {
                                    if (args[i] != null) {
                                        textView[i].setText(String.valueOf(args[0]));

                                    }
                                }
                            }
                        });

                    }
                });
        socket.connect();*/

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                text=editText.getText().toString();
                editText.setText("");



                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        //送信
                        JSONObject obj = new JSONObject();
                        if (text!=null) {
                            socket.emit("chat_send", text);
                        }
                    }
                }).on("chat_reception", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        chat_system.super.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                i=0;
                                textView[i].setText(String.valueOf(args[i]));

                                //textView[1].setText(String.valueOf(args[1]));
                                //for (i=0;i<10;++i){
                                /*if (args[i] != "") {
                                    textView[i].setText(String.valueOf(args[i]));
                                }*/
                                //textView[i-1].setGravity(Gravity.RIGHT);
                                //}
                                socket.disconnect();
                            }
                        });
                    }




                });
                socket.connect();

            }
        });






    }

}
