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

public class share_table_list extends Activity {

    public Socket socket;
    TextView titletext0,titletext1,titletext2,titletext3,titletext4,titletext5,
            titletext6,titletext7,titletext8,titletext9,
            category_idtext0,category_idtext1,category_idtext2,
            category_idtext3,category_idtext4,category_idtext5,category_idtext6,
            category_idtext7,category_idtext8,category_idtext9,
            shopnametext0,shopnametext1,shopnametext2,shopnametext3,shopnametext4,
            shopnametext5,shopnametext6,shopnametext7,shopnametext8,shopnametext9;

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
        category_idtext0 = (TextView)findViewById(R.id.category0);
        category_idtext1 = (TextView)findViewById(R.id.category1);
        category_idtext2 = (TextView)findViewById(R.id.category2);
        category_idtext3 = (TextView)findViewById(R.id.category3);
        category_idtext4 = (TextView)findViewById(R.id.category4);
        category_idtext5 = (TextView)findViewById(R.id.category5);
        category_idtext6 = (TextView)findViewById(R.id.category6);
        category_idtext7 = (TextView)findViewById(R.id.category7);
        category_idtext8 = (TextView)findViewById(R.id.category8);
        category_idtext9 = (TextView)findViewById(R.id.category9);

        shopnametext0 = (TextView)findViewById(R.id.shopname0);
        shopnametext1 = (TextView)findViewById(R.id.shopname1);
        shopnametext2 = (TextView)findViewById(R.id.shopname2);
        shopnametext3 = (TextView)findViewById(R.id.shopname3);
        shopnametext4 = (TextView)findViewById(R.id.shopname4);
        shopnametext5 = (TextView)findViewById(R.id.shopname5);
        shopnametext6 = (TextView)findViewById(R.id.shopname6);
        shopnametext7 = (TextView)findViewById(R.id.shopname7);
        shopnametext8 = (TextView)findViewById(R.id.shopname8);
        shopnametext9 = (TextView)findViewById(R.id.shopname9);

        titletext0 = (TextView)findViewById(R.id.title0);
        titletext1 = (TextView)findViewById(R.id.title1);
        titletext2 = (TextView)findViewById(R.id.title2);
        titletext3 = (TextView)findViewById(R.id.title3);
        titletext4 = (TextView)findViewById(R.id.title4);
        titletext5 = (TextView)findViewById(R.id.title5);
        titletext6 = (TextView)findViewById(R.id.title6);
        titletext7 = (TextView)findViewById(R.id.title7);
        titletext8 = (TextView)findViewById(R.id.title8);
        titletext9 = (TextView)findViewById(R.id.title9);


        TextView category_idtext[] = {category_idtext0,category_idtext1,category_idtext2,
                category_idtext3,category_idtext4,category_idtext5,category_idtext6,
                category_idtext7, category_idtext8,category_idtext9};

        TextView shopnametext[] = {shopnametext0,shopnametext1,shopnametext2,shopnametext3,
                shopnametext4,shopnametext5,shopnametext6,shopnametext7,
                shopnametext8,shopnametext9};

        TextView titletext[] = {titletext0,titletext1,titletext2,titletext3,titletext4,
                titletext5,titletext6,titletext7,titletext8,titletext9};

        for (i=0; i<10; i++){
            category_idtext[i].setText(category_id[i]);
            shopnametext[i].setText(shopname[i]);
            titletext[i].setText(title[i]);
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


