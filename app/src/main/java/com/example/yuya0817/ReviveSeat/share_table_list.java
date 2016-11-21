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
    private TextView titletext,category_id,shopname;
    public int i,refine;
    private String title;

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

        category_id = (TextView)findViewById(R.id.textView);
        titletext = (TextView)findViewById(textView1);
        shopname = (TextView)findViewById(R.id.textView2);

        refine = 0;

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
//            socket = IO.socket("http://133.25.196.30:2010");
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
//                System.out.println(String.valueOf(args[0]));
                System.out.println(String.valueOf(args[0].toString()));
//                JSONObject json = (JSONObject)args[0];
////                JSONObject json = new JSONObject();
//                try {
//                    JSONArray datas = json.getJSONArray(Arrays.toString(args));
//
////                    JSONObject item = json.getJSONObject("message");
//
//                    JSONObject data = datas.getJSONObject(0);
//                    title = data.getString("[0].title");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                titletext.setText(title);

//                for (i=0; i<10; i++){
//                    try {
//                        shareid = json.getString("ユーザID");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }

//                title.setText(String.valueOf(args[0]));


//                titletext.setText("こんにちは");
//                try {
//                    JSONObject jsonObject = new JSONObject(String.valueOf(args[0]));
//                    title.setText((Integer) jsonObject.get("[0].title"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                //title.setText(String.valueOf(args[0]).getChars("[0].title"));
                socket.disconnect();
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("4","4");
            }

        });
        socket.connect();



//        textView1=(TextView)findViewById(R.id.textView1) ;



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
        });2

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

//        try {
//            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
//            Log.d("1","1");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            Log.e("-1","-1");
//        }
//        socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
//
//            @Override
//            public void call(Object... args) {
//                Log.d("2","2");
//                //socket.emit("sharetable_list", "hi");
//                // Sending an object
//                JSONObject obj = new JSONObject();
//                try {
//                    obj.put("hello", "server");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                //socket.emit("sharetable_list", obj);
//                socket.emit("sharetable_test_back",obj);
//                textView1.setText((CharSequence) obj);
//                socket.disconnect();
//            }
//
//        }).on("sharetable_list_back", new Emitter.Listener() {
//
//
//            @Override
//            public void call(Object... args) {
//                Log.d("3","3");
//                JSONObject obj = (JSONObject)args[0];
//                textView1.setText((CharSequence) obj);
//            }
//
//        }).on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
//
//            @Override
//            public void call(Object... args) {
//                Log.d("4","4");
//            }
//
//        });
//        socket.connect();

    }
    public void onBackButtonTapped(View view){
        finish();
    }
}
