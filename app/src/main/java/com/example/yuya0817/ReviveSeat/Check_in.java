package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Check_in extends Activity {
    int shareid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        Intent data = getIntent();
        shareid = data.getIntExtra("shareid", 0);

        Button mapp = (Button) findViewById(R.id.map);
        mapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check_in.this, MapsActivity.class);
                intent.putExtra("shareid", shareid);
                startActivity(intent);
            }
        });

        Button qrr = (Button) findViewById(R.id.qr);
        qrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Check_in.this, JoinQrcode.class);
                intent2.putExtra("shareid", shareid);
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

        Button yoyakuu = (Button) findViewById(R.id.yoyaku);
        yoyakuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Check_in.this, select_menu_food.class);//予約画面へ
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
