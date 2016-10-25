package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Confirmation extends Activity {

    private ToggleButton toggleButton1,toggleButton2,toggleButton3,toggleButton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView titletext = (TextView)findViewById(R.id.titletext);
        TextView categorytext = (TextView)findViewById(R.id.categorytext);
        TextView timetext = (TextView)findViewById(R.id.timetext);
        TextView hosokutext = (TextView)findViewById(R.id.hosokutext);

        // インテントを取得
        Intent data = getIntent();
        // インテントに保存されたデータを取得
        final String title = data.getStringExtra("title");
        final String item = data.getStringExtra("item");
        final String hour = data.getStringExtra("hour");
        final String minute = data.getStringExtra("minute");
        final String text = data.getStringExtra("text");
        String sheet1 = data.getStringExtra("sheet1");
        String sheet2 = data.getStringExtra("sheet2");
        String sheet3 = data.getStringExtra("sheet3");
        String sheet4 = data.getStringExtra("sheet4");

        toggleButton1=(ToggleButton)findViewById(R.id.button1);
            toggleButton1.setTextOn(sheet1);
            toggleButton1.setTextOff(sheet1);
            toggleButton1.setChecked(true);



        toggleButton2=(ToggleButton)findViewById(R.id.button2);
        toggleButton2.setTextOn(sheet2);
        toggleButton2.setTextOff(sheet2);
        toggleButton2.setChecked(true);

        toggleButton3=(ToggleButton)findViewById(R.id.button3);
        toggleButton3.setTextOn(sheet3);
        toggleButton3.setTextOff(sheet3);
        toggleButton3.setChecked(true);

        toggleButton4=(ToggleButton)findViewById(R.id.button4);
        toggleButton4.setTextOn(sheet4);
        toggleButton4.setTextOff(sheet4);
        toggleButton4.setChecked(true);


        titletext.setText(title);
        categorytext.setText(item);
        timetext.setText(hour + "時" + minute + "分");
        hosokutext.setText(text);

        Button myButton=(Button)findViewById(R.id.next);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Confirmation.this, wait.class);
                startActivity(intent);
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
//    public void onbackButtontapped(View view){
//        finish();
//    }
}
