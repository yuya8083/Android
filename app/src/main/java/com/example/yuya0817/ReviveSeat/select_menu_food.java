package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

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
    int figureall = 0,foodfigure[]=new int[8];
    int i = 0,id,priceres;
    TextView pricetext[] = new TextView[12];
    TextView foodname[]=new TextView[12];
    ToggleButton food[] = new ToggleButton[12];
    public TextView totalfigure,totalprice;
    String name[]=new String[10],image[]=new String[10],menu;
    JSONObject list[]=new JSONObject[10];
    JSONArray array=new JSONArray();
    Button buttonplus[]=new Button[8],buttonminas[]=new Button[8];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu_food);

        totalfigure = (TextView) findViewById(R.id.totalfigure);
        totalprice = (TextView) findViewById(R.id.totalprice);
        totalprice.setText(String.valueOf(priceall));
        Intent data=getIntent();


        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }

        for (i=0;i<8;++i){
            foodfigure[i]=0;
        }

        for (i=0;i<8;++i) {
            name[i] = data.getStringExtra(i+".menu");
            price[i] = data.getIntExtra(i+".price",0);
            //image[i] = data.getStringExtra(i+".img");
            //Log.d("5", String.valueOf(price[i]));
            //Log.d("6",name[i]);
            //Log.d("7",image[i]);
        }



        foodname[0]=(TextView)findViewById(R.id.textView1);
        foodname[0].setText(name[0]);
        pricetext[0] = (TextView) findViewById(R.id.textView2);
        pricetext[0].setText(String.valueOf(price[0]));
        food[0]=(ToggleButton)findViewById(R.id.food1);
        //food[0].setText(image[0]);
        buttonplus[0]=(Button)findViewById(R.id.food1plus);
        buttonminas[0]=(Button)findViewById(R.id.food1minas);


        foodname[1]=(TextView)findViewById(R.id.textView3);
        foodname[1].setText(name[1]);
        pricetext[1] = (TextView) findViewById(R.id.textView4);
        pricetext[1].setText(String.valueOf(price[1]));
        food[1]=(ToggleButton)findViewById(R.id.food2);
        buttonplus[1]=(Button)findViewById(R.id.food2plus);
        buttonminas[1]=(Button)findViewById(R.id.food2minas);



        foodname[2]=(TextView)findViewById(R.id.textView5);
        foodname[2].setText(name[2]);
        pricetext[2] = (TextView) findViewById(R.id.textView6);
        pricetext[2].setText(String.valueOf(price[2]));
        food[2]=(ToggleButton)findViewById(R.id.food3);
        buttonplus[2]=(Button)findViewById(R.id.food3plus);
        buttonminas[2]=(Button)findViewById(R.id.food3minas);


        foodname[3]=(TextView)findViewById(R.id.textView7);
        foodname[3].setText(name[3]);
        pricetext[3] = (TextView) findViewById(R.id.textView8);
        pricetext[3].setText(String.valueOf(price[3]));
        food[3]=(ToggleButton)findViewById(R.id.food4);
        buttonplus[3]=(Button)findViewById(R.id.food4plus);
        buttonminas[3]=(Button)findViewById(R.id.food4minas);


        foodname[4]=(TextView)findViewById(R.id.textView9);
        foodname[4].setText(name[4]);
        pricetext[4] = (TextView) findViewById(R.id.textView10);
        pricetext[4].setText(String.valueOf(price[4]));
        food[4]=(ToggleButton)findViewById(R.id.food5);
        buttonplus[4]=(Button)findViewById(R.id.food5plus);
        buttonminas[4]=(Button)findViewById(R.id.food5minas);


        foodname[5]=(TextView)findViewById(R.id.textView11);
        foodname[5].setText(name[5]);
        pricetext[5] = (TextView) findViewById(R.id.textView12);
        pricetext[5].setText(String.valueOf(price[5]));
        food[5]=(ToggleButton)findViewById(R.id.food6);
        buttonplus[5]=(Button)findViewById(R.id.food6plus);
        buttonminas[5]=(Button)findViewById(R.id.food6minas);



        foodname[6]=(TextView)findViewById(R.id.textView13);
        foodname[6].setText(name[6]);
        pricetext[6] = (TextView) findViewById(R.id.textView14);
        pricetext[6].setText(String.valueOf(price[6]));
        food[6]=(ToggleButton)findViewById(R.id.food7);
        buttonplus[6]=(Button)findViewById(R.id.food7plus);
        buttonminas[6]=(Button)findViewById(R.id.food7minas);



        foodname[7]=(TextView)findViewById(R.id.textView15);
        foodname[7].setText(name[7]);
        pricetext[7] = (TextView) findViewById(R.id.textView16);
        pricetext[7].setText(String.valueOf(price[7]));
        food[7]=(ToggleButton)findViewById(R.id.food8);
        buttonplus[7]=(Button)findViewById(R.id.food8plus);
        buttonminas[7]=(Button)findViewById(R.id.food8minas);



        buttonplus[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++foodfigure[0];
                ++figureall;
                priceall=priceall+price[0];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });
        buttonminas[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --foodfigure[0];
                --figureall;
                priceall=priceall-price[0];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        buttonplus[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++foodfigure[1];
                ++figureall;
                priceall=priceall+price[1];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });
        buttonminas[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --foodfigure[1];
                --figureall;
                priceall=priceall-price[1];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        buttonplus[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++foodfigure[2];
                ++figureall;
                priceall=priceall+price[2];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });
        buttonminas[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --foodfigure[2];
                --figureall;
                priceall=priceall-price[2];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        buttonplus[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++foodfigure[3];
                ++figureall;
                priceall=priceall+price[3];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });
        buttonminas[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --foodfigure[3];
                --figureall;
                priceall=priceall-price[3];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        buttonplus[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++foodfigure[4];
                ++figureall;
                priceall=priceall+price[4];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });
        buttonminas[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --foodfigure[4];
                --figureall;
                priceall=priceall-price[4];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        buttonplus[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++foodfigure[5];
                ++figureall;
                priceall=priceall+price[5];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });
        buttonminas[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --foodfigure[5];
                --figureall;
                priceall=priceall-price[5];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        buttonplus[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++foodfigure[6];
                ++figureall;
                priceall=priceall+price[6];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });
        buttonminas[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --foodfigure[6];
                --figureall;
                priceall=priceall-price[6];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });

        buttonplus[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++foodfigure[7];
                ++figureall;
                priceall=priceall+price[7];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });
        buttonminas[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --foodfigure[7];
                --figureall;
                priceall=priceall-price[7];
                totalprice.setText(String.valueOf(priceall));
                totalfigure.setText(String.valueOf(figureall));
            }
        });


        /*Button decide=(Button)findViewById(R.id.decide);
        decide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i=0;i<8;++i){
                    try {
                        //list[i].put("manu",name[i]);
                        list[i].put("num",foodfigure[i]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    array.put(list[i]);
                }
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        //送信
                        socket.emit("menu_reserve", array);
                        Log.d("2", "2");
                        socket.disconnect();
                    }
                });
                socket.connect();
                finish();

            }
        });*/

        Button returnButton = (Button) findViewById(R.id.back);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
