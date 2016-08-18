package com.example.yuya0817.ReviveSeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Select_find_method extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_find_method);

        //店舗ボタン→位置情報・店舗名
        Button tenpo = (Button) findViewById(R.id.cafe);
        tenpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Select_find_method.this, Location_cafename.class);
                startActivity(intent);
            }
        });

        Button categori = (Button) findViewById(R.id.category);
        categori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Select_find_method.this, Category_list.class);
                startActivity(intent);
            }
        });
    }
}
