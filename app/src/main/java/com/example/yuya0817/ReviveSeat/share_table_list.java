package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Objects;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class share_table_list extends Activity {

    public Socket socket;

    TextView category_idtext[] = new TextView[10];
    TextView shopnametext[] = new TextView[10];
    TextView titletext[] = new TextView[10];

    String category_id[] = new String[10];
    String shopname[] = new String[10];
    String title[] = new String[10];
    String share_id[] = new String[10];

    String hname,titles,endtime,explain,shop_address,shop_name;
    private int i,flag,huserid,hyoka,seatinfo,seatnum,shareid;
    private double shop_x,shop_y;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_table_list);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
            Log.d("1","1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // インテントを取得
        Intent data = getIntent();
        // インテントに保存されたデータを取得
        for (i=0; i<10; i++){
            category_id[i] = data.getStringExtra(i+".category_id");
            shopname[i] = data.getStringExtra(i+".shopname");
            title[i] = data.getStringExtra(i+".title");
            share_id[i] = data.getStringExtra(i+".shareid");
        }
        category_idtext[0] = (TextView)findViewById(R.id.category0);
        category_idtext[1] = (TextView)findViewById(R.id.category1);
        category_idtext[2] = (TextView)findViewById(R.id.category2);
        category_idtext[3] = (TextView)findViewById(R.id.category3);
        category_idtext[4] = (TextView)findViewById(R.id.category4);
        category_idtext[5] = (TextView)findViewById(R.id.category5);
        category_idtext[6] = (TextView)findViewById(R.id.category6);
        category_idtext[7] = (TextView)findViewById(R.id.category7);
        category_idtext[8] = (TextView)findViewById(R.id.category8);
        category_idtext[9] = (TextView)findViewById(R.id.category9);

        shopnametext[0] = (TextView)findViewById(R.id.shopname0);
        shopnametext[1] = (TextView)findViewById(R.id.shopname1);
        shopnametext[2] = (TextView)findViewById(R.id.shopname2);
        shopnametext[3] = (TextView)findViewById(R.id.shopname3);
        shopnametext[4] = (TextView)findViewById(R.id.shopname4);
        shopnametext[5] = (TextView)findViewById(R.id.shopname5);
        shopnametext[6] = (TextView)findViewById(R.id.shopname6);
        shopnametext[7] = (TextView)findViewById(R.id.shopname7);
        shopnametext[8] = (TextView)findViewById(R.id.shopname8);
        shopnametext[9] = (TextView)findViewById(R.id.shopname9);

        titletext[0] = (TextView)findViewById(R.id.title0);
        titletext[1] = (TextView)findViewById(R.id.title1);
        titletext[2] = (TextView)findViewById(R.id.title2);
        titletext[3] = (TextView)findViewById(R.id.title3);
        titletext[4] = (TextView)findViewById(R.id.title4);
        titletext[5] = (TextView)findViewById(R.id.title5);
        titletext[6] = (TextView)findViewById(R.id.title6);
        titletext[7] = (TextView)findViewById(R.id.title7);
        titletext[8] = (TextView)findViewById(R.id.title8);
        titletext[9] = (TextView)findViewById(R.id.title9);

        for (i=0; i<10; i++){
            category_idtext[i].setText(category_id[i]);
            shopnametext[i].setText(shopname[i]);
            titletext[i].setText(title[i]);
            if (Objects.equals(category_id[i], "1")){
                category_idtext[i].setBackgroundColor(Color.rgb(25,135,229)); //青
            }else if (Objects.equals(category_id[i], "2")){
                category_idtext[i].setBackgroundColor(Color.rgb(11,218,81)); //緑
            }else if (Objects.equals(category_id[i], "3")){
                category_idtext[i].setBackgroundColor(Color.rgb(243,213,26)); //黄色
            }else if (Objects.equals(category_id[i], "4")){
                category_idtext[i].setBackgroundColor(Color.rgb(249,37,0)); //赤
            }else {
                category_idtext[i].setBackgroundColor(Color.rgb(255,183,76)); //オレンジ
            }
        }

        Button list0=(Button)findViewById(R.id.list0);
        list0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[0]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {
                    intent = new Intent(share_table_list.this, JoinConfirmation.class);
                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
            }
        });

        Button list1=(Button)findViewById(R.id.list1);
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[1]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {

                    intent = new Intent(share_table_list.this, chat_system.class);

                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
            }
        });

        Button list2 = (Button) findViewById(R.id.list2);
        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[2]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {

                    intent = new Intent(share_table_list.this, select_menu_food.class);

                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
            }
        });

        Button list3 = (Button) findViewById(R.id.list3);
        list3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[3]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {
                    intent = new Intent(share_table_list.this, JoinConfirmation.class);
                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
            }
        });

        Button list4 = (Button) findViewById(R.id.list4);
        list4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[4]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {
                    intent = new Intent(share_table_list.this, JoinConfirmation.class);
                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
            }
        });

        Button list5 = (Button) findViewById(R.id.list5);
        list5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[5]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {
                    intent = new Intent(share_table_list.this, JoinConfirmation.class);
                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
            }
        });

        Button list6=(Button)findViewById(R.id.list6);
        list6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[6]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {
                    intent = new Intent(share_table_list.this, JoinConfirmation.class);
                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
            }
        });

        Button list7=(Button)findViewById(R.id.list7);
        list7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[7]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {
                    intent = new Intent(share_table_list.this, JoinConfirmation.class);
                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
            }
        });

        Button list8=(Button)findViewById(R.id.list8);
        list8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[8]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {
                    intent = new Intent(share_table_list.this, JoinConfirmation.class);
                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
            }
        });

        Button list9=(Button)findViewById(R.id.list9);
        list9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                shareid = Integer.valueOf(share_id[9]);
                System.out.println(shareid);
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("detail", shareid);
                    }

                }).on("detail_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONObject json = (JSONObject) args[0];
                            hname = json.getString("hname");//string
                            huserid = json.getInt("huserid");//int
                            hyoka = json.getInt("hyoka");//int
                            titles = json.getString("title");//string
                            endtime = json.getString("endtime");//string
                            explain = json.getString("explain");//string
                            seatinfo = json.getInt("seatinfo");//int
                            seatnum = json.getInt("seatnum");//int
                            shop_address = json.getString("shop_address");//string
                            shop_name = json.getString("shop_name");//string
                            shop_x = json.getDouble("shop_x");//double
                            shop_y = json.getDouble("shop_y");//double
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

                while (flag == 0) {
                    intent = new Intent(share_table_list.this, JoinConfirmation.class);
                }

                intent.putExtra("hname", hname);
                intent.putExtra("huserid", huserid);
                intent.putExtra("hyoka", hyoka);
                intent.putExtra("title", titles);
                intent.putExtra("endtime", endtime);
                intent.putExtra("explain", explain);
                intent.putExtra("seatinfo", seatinfo);
                intent.putExtra("seatnum", seatnum);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                intent.putExtra("shareid", shareid);

                startActivity(intent);
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


