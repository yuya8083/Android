package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.common.api.GoogleApiClient;

import java.net.URISyntaxException;

import io.socket.client.IO;

public class select_menu_food extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public io.socket.client.Socket socket;
    int priceall = 0, price[] = new int[12];
    int figureall = 0;
    int i = 0,id;
    TextView pricetext[] = new TextView[12];
    TextView foodname[]=new TextView[12];
    ToggleButton food[] = new ToggleButton[12];
    public TextView totalfigure,totalprice;
    String name[]=new String[10],image[]=new String[10],priceres[]=new String[10];
    Intent getdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu_food);

        totalfigure = (TextView) findViewById(R.id.totalfigure);
        totalprice = (TextView) findViewById(R.id.totalprice);
        totalprice.setText(String.valueOf(priceall));
        getdata=getIntent();


        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }


        for (i=0;i<10;++i){
            price[i]=100;
        }

        for (i=0;i<10;++i) {
            name[i] = getdata.getStringExtra(i+".menu");
            priceres[i] = getdata.getStringExtra(i+".price");
            image[i] = getdata.getStringExtra(i+".img");
        }



        food[0]=(ToggleButton)findViewById(R.id.food1);
        foodname[0]=(TextView)findViewById(R.id.textView1);
        pricetext[0] = (TextView) findViewById(R.id.textView2);
        pricetext[0].setText(String.valueOf(price[0]));

        pricetext[1] = (TextView) findViewById(R.id.textView4);
        pricetext[1].setText(String.valueOf(price[1]));
        food[1]=(ToggleButton)findViewById(R.id.food2);

        pricetext[2] = (TextView) findViewById(R.id.textView6);
        pricetext[2].setText(String.valueOf(price[2]));
        food[2]=(ToggleButton)findViewById(R.id.food3);

        pricetext[3] = (TextView) findViewById(R.id.textView8);
        pricetext[3].setText(String.valueOf(price[3]));
        food[3]=(ToggleButton)findViewById(R.id.food4);

        pricetext[4] = (TextView) findViewById(R.id.textView10);
        pricetext[4].setText(String.valueOf(price[4]));
        food[4]=(ToggleButton)findViewById(R.id.food5);

        pricetext[5] = (TextView) findViewById(R.id.textView12);
        pricetext[5].setText(String.valueOf(price[5]));
        food[5]=(ToggleButton)findViewById(R.id.food6);

        pricetext[6] = (TextView) findViewById(R.id.textView14);
        pricetext[6].setText(String.valueOf(price[6]));
        food[6]=(ToggleButton)findViewById(R.id.food7);

        pricetext[7] = (TextView) findViewById(R.id.textView16);
        pricetext[7].setText(String.valueOf(price[7]));
        food[7]=(ToggleButton)findViewById(R.id.food8);

        pricetext[8] = (TextView) findViewById(R.id.textView18);
        pricetext[8].setText(String.valueOf(price[8]));
        food[8]=(ToggleButton)findViewById(R.id.food9);

        pricetext[9] = (TextView) findViewById(R.id.textView20);
        pricetext[9].setText(String.valueOf(price[9]));
        food[9]=(ToggleButton)findViewById(R.id.food10);


        foodname[0].setText(name[0]);
        pricetext[0].setText(priceres[0]);

        /*socket.connect();
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //送信
                id=1;
                socket.emit("menu_request", id);

            }
        }).on("menu_list", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {

                try {
                    JSONArray array = (JSONArray)args[0];
                    for (i=0;i<10;++i) {
                        JSONObject json = array.getJSONObject(i);
                        fn = json.getString("menu");
                        price[i] = json.getInt("price");
                        //in=json.getString("img");
                        foodname[i].setText(fn);
                        pricetext[i].setText(String.valueOf(price[i]));
                        //food[0].setText(in);
                    }
                } catch (JSONException e) {
                    Log.d("1","1");
                    e.printStackTrace();
                }
                socket.disconnect();
            }






        });*/




        food[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[0].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });


        food[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[1].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });



        food[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[2].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });


        food[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[3].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });


        food[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[4].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        food[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[5].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });


        food[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[6].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });


        food[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[7].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });


        food[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[8].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
            }
        });


        food[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food[9].isChecked()) {
                    ++figureall;
                    priceall=priceall+Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                } else {
                    --figureall;
                    priceall=priceall-Integer.parseInt(pricetext[0].getText().toString());
                    totalprice.setText(String.valueOf(priceall));
                }
                totalfigure.setText(String.valueOf(figureall));
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
