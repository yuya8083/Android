package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Top extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        //作成ボタン
        Button myButton=(Button)findViewById(R.id.sharebutton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Top.this, qrcode.class);
//                Intent intent = new Intent(Top.this, JoinEvaluation.class);
                startActivity(intent);
            }
        });
        //参加ボタン
        Button myButton2=(Button)findViewById(R.id.joinbutton);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent2 = new Intent(MainActivity.this, Select_find_method.class);
                Intent intent2 = new Intent(Top.this, share_table_list.class);
                startActivity(intent2);
            }
        });
    }
}
