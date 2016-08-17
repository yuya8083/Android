package com.example.yuya0817.ReviveSeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class starbucks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starbucks);

        final Button seat1=(Button)findViewById(R.id.seat1);
        /*seat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(true){
                    seat1.setBackgroundColor(Color.rgb(255,0,0));
                    flag=false;
                }
                else{
                    seat1.setBackgroundColor(Color.rgb(0,255,0));
                    flag=true;
                }
            }
        });*/

        setTitle("テーブル参加確認");
    }

    public void onbackButtontapped(View view){
        finish();
    }
}
