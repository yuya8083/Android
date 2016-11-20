package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class chat_system extends Activity {

    private ArrayAdapter<String> adapter;
    private Socket socket;
//    private Handler handler = new Handler();
    private String text;
    EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat_system);

        // ListViewの設定
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        editText = (EditText)findViewById(R.id.editText1);
        //editText = (EditText)findViewById(R.id.editText);

        Button button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = editText.getText().toString();
                System.out.println(text);
                try {
                    socket = IO.socket("https://reviveseatserver.herokuapp.com/");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
//                        socket.emit("chat_send",text);
                        socket.emit("test",text);
                    }

                }).on("test_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        JSONObject obj = (JSONObject)args[0];
                        if((CharSequence) obj != null) {
                            // メッセージが空でなければ追加
                            adapter.insert(String.valueOf((CharSequence) obj), 0);
                        }
//                textView1.setText((CharSequence) obj);
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
            }
        });

//        try {
//            connect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private void connect() throws MalformedURLException {
//        SocketIO socket = new SocketIO("https://cmapp0001.herokuapp.com/");
//        socket.connect(iocallback);
//    }
//
//
//
//
//    private IOCallback iocallback = new IOCallback() {
//
//
//        @Override
//        public void onDisconnect() {
//
//        }
//
//        @Override
//        public void onConnect() {
//            System.out.println("onConnect");
//        }
//
//        @Override
//        public void onMessage(String s, IOAcknowledge ioAcknowledge) {
//
//        }
//
//        @Override
//        public void onMessage(JSONObject json, IOAcknowledge ack) {
//            System.out.println("onMessage");
//        }
//
//        @Override
//        public void on(String event, IOAcknowledge ack, Object... args) {
//            final JSONObject message = (JSONObject)args[0];
//
//            new Thread(new Runnable() {
//                public void run() {
//                    handler.post(new Runnable() {
//                        public void run() {
//                            try {
//                                if(message.getString("message") != null) {
//                                    // メッセージが空でなければ追加
//                                    adapter.insert(message.getString("message"), 0);
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                }
//            }).start();
//        }
//        @Override
//        public void onError(SocketIOException socketIOException) {
//            System.out.println("onError");
//            socketIOException.printStackTrace();
//        }
//    };
//    public void sendEvent(View view){
//        // 文字が入力されていなければ何もしない
//        if (editText.getText().toString().length() == 0) {
//            return;
//        }
//
//
//        try {
//            // イベント送信
//            JSONObject json = new JSONObject();
//            json.put("message", editText.getText().toString());
//            socket.emit("message:send", json);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        // テキストフィールドをリセット
        editText.setText("");
    }

}
