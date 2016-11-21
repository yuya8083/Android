package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Objects;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static com.example.yuya0817.ReviveSeat.R.id.pass;

public class NewUserSet extends Activity {

    private String username,password,password2;
    EditText editname,editpass,editpass2;
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_set);

        editname = (EditText)findViewById(R.id.username);
        editpass = (EditText)findViewById(pass);
        editpass2 = (EditText)findViewById(R.id.pass2);

        Button button=(Button)findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = editname.getText().toString();
                password = editpass.getText().toString();
                password2 = editpass2.getText().toString();

                Log.d("-","ero");

                if (username.isEmpty() && (password.isEmpty() || password2.isEmpty())) {
                    // 入力必須エラー
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewUserSet.this);
                    builder.setMessage("ユーザ名とパスワードが入力されていません");
                    builder.show();
                }else if (username.isEmpty()){
                    // 入力必須エラー
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewUserSet.this);
                    builder.setMessage("ユーザ名が入力されていません");
                    builder.show();
                }else if (password.isEmpty() || password2.isEmpty()){
                    // 入力必須エラー
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewUserSet.this);
                    builder.setMessage("パスワードが入力されていません");
                    builder.show();
                }else {
                    if(Objects.equals(password, password2)){
                        if(7 < password.length()) {
                            Intent intent = new Intent(NewUserSet.this, Top.class);
                            try {
                                socket = IO.socket("https://reviveseatserver.herokuapp.com/");
                                Log.d("1","1");
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                                @Override
                                public void call(Object... args) {
                                    Log.d("2","2");
                                    // Sending an object
                                    JSONObject obj = new JSONObject();
                                    try {
                                        obj.put("username", username);
                                        obj.put("password", password);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    socket.emit("newuser", obj);
                                    //socket.disconnect();
                                }

                            }).on("newuser_back", new Emitter.Listener() {

                                @Override
                                public void call(Object... args) {
                                    Log.d("3","3");
                                    System.out.println(String.valueOf(args[0]));
//                        JSONObject obj2 = (JSONObject)args[0];
//                        try {
//                            share_id = obj2.getInt("share_id");
//                        } catch (JSONException e) {
//                            return;
//                        }
                                    //Log.d("recieve", String.valueOf(share_id));
                                    //socket.on("",);
                                    socket.disconnect();
                                }

                            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                                @Override
                                public void call(Object... args) {
                                    Log.d("4","4");
                                }

                            });
                            socket.connect();
                            startActivity(intent);
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(NewUserSet.this);
                            builder.setMessage("パスワードの文字数が足りません(8文字以上)");
                            builder.show();
                        }
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(NewUserSet.this);
                        builder.setMessage("パスワードが一致しません");
                        builder.show();
                    }
                }

//                if(Objects.equals(password, password2)){
//                    if(7 < password.length()) {
//                        if (username.isEmpty()) {
//                            // 入力必須エラー
//                            AlertDialog.Builder builder = new AlertDialog.Builder(NewUserSet.this);
//                            builder.setMessage("ユーザ名が入力されていません");
//                            builder.show();
//                        }else {
//                            Intent intent = new Intent(NewUserSet.this, Top.class);
//                            try {
//                                socket = IO.socket("https://reviveseatserver.herokuapp.com/");
//                                Log.d("1","1");
//                            } catch (URISyntaxException e) {
//                                e.printStackTrace();
//                            }
//                            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
//
//                                @Override
//                                public void call(Object... args) {
//                                    Log.d("2","2");
//                                    // Sending an object
//                                    JSONObject obj = new JSONObject();
//                                    try {
//                                        obj.put("username", username);
//                                        obj.put("password", password);
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                    socket.emit("newuser", obj);
//                                    //socket.disconnect();
//                                }
//
//                            }).on("newuser_back", new Emitter.Listener() {
//
//                                @Override
//                                public void call(Object... args) {
//                                    Log.d("3","3");
//                                    System.out.println(String.valueOf(args[0]));
////                        JSONObject obj2 = (JSONObject)args[0];
////                        try {
////                            share_id = obj2.getInt("share_id");
////                        } catch (JSONException e) {
////                            return;
////                        }
//                                    //Log.d("recieve", String.valueOf(share_id));
//                                    //socket.on("",);
//                                    socket.disconnect();
//                                }
//
//                            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
//
//                                @Override
//                                public void call(Object... args) {
//                                    Log.d("4","4");
//                                }
//
//                            });
//                            socket.connect();
//                            startActivity(intent);
//                        }
//                    }else{
//                        AlertDialog.Builder builder = new AlertDialog.Builder(NewUserSet.this);
//                        builder.setMessage("パスワードの文字数が足りません(8文字以上)");
//                        builder.show();
//                    }
//                }else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(NewUserSet.this);
//                    builder.setMessage("パスワードが一致しません");
//                    builder.show();
//                }
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
}
