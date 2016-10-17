package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class check_in extends Activity {

    //int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);


        Button mapp = (Button) findViewById(R.id.map);
        mapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(check_in.this, map_window.class);
                startActivity(intent);
            }
        });

        Button qrr = (Button) findViewById(R.id.qr);
        qrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(check_in.this, JoinQrcode.class);
                startActivity(intent2);
            }
        });

        Button chatt = (Button) findViewById(R.id.chat);
        chatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(check_in.this, chat_system.class);
                startActivity(intent3);
            }
        });
    }
    public void onBackButtonTapped(View view){
        finish();
    }




}