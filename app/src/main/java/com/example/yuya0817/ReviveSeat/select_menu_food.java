package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.common.api.GoogleApiClient;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu_food);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }


        final int priceall = 0, price[] = new int[12];
        int figureall = 12;
        int i = 0;
        final TextView pricetext[] = new TextView[12];
        final TextView foodname[]=new TextView[12];
        final ToggleButton food[] = new ToggleButton[12];

        TextView totalprice = (TextView) findViewById(R.id.totalprice);
        totalprice.setText(String.valueOf(priceall));

        TextView totalfigure = (TextView) findViewById(R.id.totalfigure);
        totalfigure.setText(String.valueOf(figureall));

        for (i=0;i<12;++i){
            price[i]=0;
        }

        foodname[0]=(TextView)findViewById(R.id.textView1);
        pricetext[0] = (TextView) findViewById(R.id.textView2);
        pricetext[0].setText(String.valueOf(price[0]));



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
                select_menu_food.super.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        foodname[0].setText(String.valueOf(args[0]));
                        pricetext[0].setText(String.valueOf(args[0]));

                        socket.disconnect();
                    }
                });
            }




        });
        socket.connect();
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

/*
        food[0] = (ToggleButton) findViewById(R.id.food1);
        if (food[0].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }

        food[1] = (ToggleButton) findViewById(R.id.food2);
        if (food[1].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }

        food[2] = (ToggleButton) findViewById(R.id.food3);
        if (food[2].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }

        food[3] = (ToggleButton) findViewById(R.id.food4);
        if (food[3].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }

        food[4] = (ToggleButton) findViewById(R.id.food5);
        if (food[4].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }

        food[5] = (ToggleButton) findViewById(R.id.food6);
        if (food[5].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }

        food[6] = (ToggleButton) findViewById(R.id.food7);
        if (food[6].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }

        food[7] = (ToggleButton) findViewById(R.id.food8);
        if (food[7].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }

        food[8] = (ToggleButton) findViewById(R.id.food9);
        if (food[8].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }

        food[9] = (ToggleButton) findViewById(R.id.food10);
        if (food[9].isChecked()) {
            ++figureall;
        } else {
            --figureall;
        }
*/
    }



}
