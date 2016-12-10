package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Check_in extends Activity {
    int shareid,price,id;
    private int i;
    String name,shop_name,shop_address,menu,image,price1;
    private double shop_x,shop_y;
    Socket socket;
    TextView textView;
    Intent intent4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);


        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
            Log.d("1","1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        Intent data = getIntent();
        shareid = data.getIntExtra("shareid", 0);
        name = data.getStringExtra("name");
        shop_name = data.getStringExtra("shop_name");
        shop_address = data.getStringExtra("shop_address");
        shop_x = data.getDoubleExtra("shop_x", 0);
        shop_y = data.getDoubleExtra("shop_y", 0);
        textView=(TextView)findViewById(R.id.textView6);


        Button mapp = (Button) findViewById(R.id.map);
        mapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check_in.this, MapsActivity.class);
                intent.putExtra("shareid", shareid);
                intent.putExtra("name", name);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                startActivity(intent);
            }
        });

        Button qrr = (Button) findViewById(R.id.qr);
        qrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Check_in.this, JoinQrcode.class);
                intent2.putExtra("shareid", shareid);
                intent2.putExtra("name", name);
                startActivity(intent2);
            }
        });

        Button chatt = (Button) findViewById(R.id.chat);
        chatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Check_in.this, chat_system.class);
                startActivity(intent3);
            }
        });

        Button yoyakuu = (Button) findViewById(R.id.yoyaku);
        yoyakuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent4 = new Intent(Check_in.this, select_menu_food.class);//予約画面へ
                //socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        //送信
                        socket.emit("menu_request",shareid);
                        Log.d("2","2");
                    }
                }).on("menu_list", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        try {

                            JSONArray datas = (JSONArray)args[0];
                            for (i=0;i<10;++i) {
                                JSONObject data = datas.getJSONObject(i);
                                menu = data.getString("menu");
                                price=data.getInt("price");
                                //image=data.getString("img");
                                //price=100;
                                intent4.putExtra(i+".menu", menu);
                                intent4.putExtra(i+".price", price);
                                //intent4.putExtra(i+".image", image);
                                //textView.setText("");
                                Log.d("1", menu);
                            }
                            intent4.putExtra("price",price);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        socket.disconnect();
                    }
                });
                socket.connect();
                startActivity(intent4);
            }
        });

        Button returnButton = (Button) findViewById(R.id.cancel);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
