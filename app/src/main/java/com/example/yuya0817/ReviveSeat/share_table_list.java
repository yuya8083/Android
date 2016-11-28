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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static com.example.yuya0817.ReviveSeat.R.id.textView1;

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
    private TextView titletext, category_id, shopname;
    public int i, refine;
    private String title;

    List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

    //    {
//        try {
//            IO.Options opts = new IO.Options();
//            // IO.socket("サーバーから提示提供されたURL");
//            mSocket = IO.socket("https://reviveseatserver.herokuapp.com/");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_table_list);

        category_id = (TextView) findViewById(R.id.textView);
        titletext = (TextView) findViewById(textView1);
        shopname = (TextView) findViewById(R.id.textView2);

        refine = 0;

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
//            socket = IO.socket("http://133.25.196.30:2010");
            Log.d("1", "1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("-1", "-1");
        }
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
            public void call(final Object ... args) {//detail_back.shop_address
                share_table_list.super.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //住所

//                        String tmp = "";
//                        tmp = String.valueOf(args[0]);
//                        System.out.println(tmp);
//                        String[] tmp2 = tmp.split("}");
//                        System.out.println(tmp2[3]);
//                        for(i=0;i<tmp2.length;i++) {
////                            String shareid = tmp2[i].substring(tmp2[i].indexOf("shareid") + 8, tmp2[i].indexOf("title") - 2);
//                            String title = tmp2[i].substring(tmp2[i].indexOf("title") + 7, tmp2[i].indexOf("category_id") - 3);
//                            String category_id = tmp2[i].substring(tmp2[i].indexOf("category_id") + 12, tmp2[i].indexOf("explain") - 2);
//                            String explain = tmp2[i].substring(tmp2[i].indexOf("explain") + 9, tmp2[i].indexOf("shopname") - 3);
//                            String shopname = tmp2[i].substring(tmp2[i].indexOf("shopname") + 10, tmp2[i].indexOf("\"") - 1);

//                            HashMap<String, String> hm = new HashMap<String, String>();
//                            hm.put("shareid", shareid);
//                            hm.put("title", title);
//                            hm.put("category_id", category_id);
//                            hm.put("explain", explain);
//                            hm.put("shopname", shopname);
//                            list.add(hm);
//                        }

//                        System.out.println(list.get(2)); //うけとった文字列が何か調べてる！
//                        System.out.println(list.get(5));
                        System.out.println("チェック0");

                        socket.disconnect();
                    }
                });
            }
        });
        socket.connect();


        Button list1 = (Button) findViewById(R.id.list1);
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent1);
            }
        });

        Button list2 = (Button) findViewById(R.id.list2);
        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(share_table_list.this, chat_system.class);
                startActivity(intent2);
            }
        });

        Button list3 = (Button) findViewById(R.id.list3);
        list3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent3);
            }
        });

        Button list4 = (Button) findViewById(R.id.list4);
        list4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent4);
            }
        });

        Button list5 = (Button) findViewById(R.id.list5);
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

    public void onBackButtonTapped(View view) {
        finish();
    }
}


