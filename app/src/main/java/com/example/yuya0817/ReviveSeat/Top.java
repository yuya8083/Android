package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Top extends Activity {
    private Socket socket;
    String category_id[] = new String[10];
    String shopname[] = new String[10];
    String title[] = new String[10];
    String shareid[] = new String[10];
    private int refine,flag,i,j;
    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        refine = 0;
        flag = 0;

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
//            socket = IO.socket("http://133.25.196.30:2010");
            Log.d("1","1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        j = 19;

        //作成ボタン
        Button myButton=(Button)findViewById(R.id.sharebutton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Top.this, qrcode.class);
//                Intent intent = new Intent(Top.this, JoinQrcode.class);
//                intent.putExtra("shareid", j);
                startActivity(intent);
            }
        });
        //参加ボタン
        Button myButton2=(Button)findViewById(R.id.joinbutton);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        // Sending an object
                        JSONObject json = new JSONObject();
                        try {
                            json.put("refine", refine);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        socket.emit("sharetable_list", json);
                    }

                }).on("sharetable_list_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONArray datas = (JSONArray) args[0];
                            for (i = 0; i < 10; i++) {
                                JSONObject data = datas.getJSONObject(i);
                                category_id[i] = data.getString("category_id");
                                shopname[i] = data.getString("shopname");
                                title[i] = data.getString("title");
                                shareid[i] = data.getString("shareid");
//                                    System.out.println(title[i]);
                            }
//                                JSONArray datas = (JSONArray)args[0];
//                                JSONObject data = datas.getJSONObject(0);
//                                title = data.getString("title");
//                                System.out.println(title);

//                                for (i=0; i<10; i++){
//                                    JSONObject data = datas.getJSONObject(i);
////                                    category_id[i] = data.getString("category_id");
////                                    shopname[i] = data.getString("shopname");
//                                    title[i] = data.getString("title");
////                                    shareid[i] = data.getString("shareid");
//                                    System.out.println(title[i]);
//                                }
//                                System.out.println(title);
//                                flag = 1;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        socket.disconnect();
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        flag = 1;
                    }

                });
                socket.connect();

                while (flag == 0){
                    intent2 = new Intent(Top.this, share_table_list.class);
                }
                for (i=0; i<10; i++){
                    intent2.putExtra(i+".category_id", category_id[i]);
                    intent2.putExtra(i+".shopname", shopname[i]);
                    intent2.putExtra(i+".title", title[i]);
                    intent2.putExtra(i+".shareid", shareid[i]);
                }
                startActivity(intent2);
            }
        });
    }
}
