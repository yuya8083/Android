package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //作成ボタン
        Button myButton=(Button)findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, qrcode.class);
                startActivity(intent);
            }
        });
        //参加ボタン
        Button myButton2=(Button)findViewById(R.id.button2);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent2 = new Intent(MainActivity.this, Select_find_method.class);
                Intent intent2 = new Intent(MainActivity.this, share_table_list.class);
                startActivity(intent2);
            }
        });

        Button myButton3=(Button)findViewById(R.id.button3);
        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, TableSet.class);
                startActivity(intent3);
            }
        });

        Button myButton4=(Button)findViewById(R.id.button4);
        myButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this, Top.class);
                //Intent intent4 = new Intent(MainActivity.this, JoinConfirmation.class);



                startActivity(intent4);
            }
        });
    }
}
