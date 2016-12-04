package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class select_menu_food extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public io.socket.client.Socket socket;
    int priceall = 0, price[] = new int[12];
    int figureall = 0;
    int i = 0;
    final TextView pricetext[] = new TextView[12];
    TextView foodname[]=new TextView[12];
    ToggleButton food[] = new ToggleButton[12];
    public TextView totalfigure,totalprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu_food);

        totalfigure = (TextView) findViewById(R.id.totalfigure);
        totalprice = (TextView) findViewById(R.id.totalprice);
        totalprice.setText(String.valueOf(priceall));


        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }


        for (i=0;i<12;++i){
            price[i]=0;
        }

        foodname[0]=(TextView)findViewById(R.id.textView1);
        pricetext[0] = (TextView) findViewById(R.id.textView2);
        pricetext[0].setText(String.valueOf(price[0]));



        socket.connect();
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //送信
                JSONObject obj = new JSONObject();
                    socket.emit("menu_request", 1);

            }
        }).on("men_list", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {

                        JSONObject json = (JSONObject) args[0];
                        try {
                            foodname[0].setText(json.getString("menu"));
                            pricetext[0].setText(json.getInt("price"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        socket.disconnect();
                    }
                





        });

/*
        pricetext[1] = (TextView) findViewById(R.id.textView4);
        pricetext[1].setText(String.valueOf(price[1]));

        pricetext[2] = (TextView) findViewById(R.id.textView6);
        pricetext[2].setText(String.valueOf(price[2]));

        pricetext[3] = (TextView) findViewById(R.id.textView8);
        pricetext[3].setText(String.valueOf(price[3]));

        pricetext[4] = (TextView) findViewById(R.id.textView10);
        pricetext[4].setText(String.valueOf(price[4]));

        pricetext[5] = (TextView) findViewById(R.id.textView12);
        pricetext[5].setText(String.valueOf(price[5]));

        pricetext[6] = (TextView) findViewById(R.id.textView14);
        pricetext[6].setText(String.valueOf(price[6]));

        pricetext[7] = (TextView) findViewById(R.id.textView16);
        pricetext[7].setText(String.valueOf(price[7]));

        pricetext[8] = (TextView) findViewById(R.id.textView18);
        pricetext[8].setText(String.valueOf(price[8]));

        pricetext[9] = (TextView) findViewById(R.id.textView20);
        pricetext[9].setText(String.valueOf(price[9]));

        pricetext[10] = (TextView) findViewById(R.id.textView22);
        pricetext[10].setText(String.valueOf(price[10]));

        pricetext[11] = (TextView) findViewById(R.id.textView24);
        pricetext[11].setText(String.valueOf(price[11]));*/


        food[0] = (ToggleButton) findViewById(R.id.food1);
        food[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[0].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        food[1] = (ToggleButton) findViewById(R.id.food2);
        food[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[1].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });


        food[2] = (ToggleButton) findViewById(R.id.food3);
        food[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[2].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        food[3] = (ToggleButton) findViewById(R.id.food4);
        food[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[3].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        food[4] = (ToggleButton) findViewById(R.id.food5);
        food[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[4].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });
        food[5] = (ToggleButton) findViewById(R.id.food6);
        food[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[5].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        food[6] = (ToggleButton) findViewById(R.id.food7);
        food[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[6].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        food[7] = (ToggleButton) findViewById(R.id.food8);
        food[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[7].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        food[8] = (ToggleButton) findViewById(R.id.food9);
        food[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[8].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        food[9] = (ToggleButton) findViewById(R.id.food10);
        food[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[9].isChecked()) {
                    ++figureall;
                } else {
                    --figureall;
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });
    }



}
