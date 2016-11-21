package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Check_in extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        Button mapp = (Button) findViewById(R.id.map);
        mapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check_in.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        Button qrr = (Button) findViewById(R.id.qr);
        qrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Check_in.this, JoinQrcode.class);
                startActivity(intent2);
            }
        });

        Button chatt = (Button) findViewById(R.id.chat);
        chatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Check_in.this, chat_system.class);
                startActivity(intent3);
            }
        });

        Button yoyakuu = (Button) findViewById(R.id.chat);
        yoyakuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Check_in.this, chat_system.class);//予約画面へ
                startActivity(intent4);
            }
        });

        Button returnButton = (Button) findViewById(R.id.cancel);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
