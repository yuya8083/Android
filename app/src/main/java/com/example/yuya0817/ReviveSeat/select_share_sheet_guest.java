package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class select_share_sheet_guest extends Activity {


    private ToggleButton toggleButton1,toggleButton2,toggleButton3,toggleButton4;
    String horg1,horg2,horg3,horg4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_share_sheet_guest);


        boolean sheet[];
        // インテントを取得
        Intent data = getIntent();
        // インテントに保存されたデータを取得
        final String title = data.getStringExtra("title");
        final String item = data.getStringExtra("item");
        final String hour = data.getStringExtra("hour");
        final String minute = data.getStringExtra("minute");
        final String text = data.getStringExtra("text");
        boolean sheet1 = data.getBooleanExtra("sheet1",true);
        boolean sheet2 = data.getBooleanExtra("sheet2",true);
        boolean sheet3 = data.getBooleanExtra("sheet3",true);
        boolean sheet4 = data.getBooleanExtra("sheet4",true);




        toggleButton1=(ToggleButton)findViewById(R.id.button1);
        if (sheet1) {
            toggleButton1.setTextOn("ホスト");
            toggleButton1.setTextOff("ホスト");
            toggleButton1.setChecked(true);
            horg1="host";
        }else {
            if (toggleButton1.isChecked()){
                horg1="guest";
        }else {
                horg1="vacant";
        }}

        toggleButton2=(ToggleButton)findViewById(R.id.button2);
        if (sheet2) {
            toggleButton2.setTextOn("ホスト");
            toggleButton2.setTextOff("ホスト");
            toggleButton2.setChecked(true);
            horg2="host";
        }else {
            if (toggleButton2.isChecked()){
                horg2="guest";
            }else {
                horg1="vacant";
            }}

        toggleButton3=(ToggleButton)findViewById(R.id.button3);
        if (sheet3) {
            toggleButton3.setTextOn("ホスト");
            toggleButton3.setTextOff("ホスト");
            toggleButton3.setChecked(true);
            horg3="host";
        }else {
            if (toggleButton3.isChecked()){
                horg3="guest";
            }else {
                horg3="vacant";
            }}

        toggleButton4=(ToggleButton)findViewById(R.id.button4);
        if (sheet4) {
            toggleButton4.setTextOn("ホスト");
            toggleButton4.setTextOff("ホスト");
            toggleButton4.setChecked(true);
            horg4="host";
        }else {
            if (toggleButton4.isChecked()){
                horg4="guest";
            }else {
                horg4="vacant";
            }}

        Button myButton=(Button)findViewById(R.id.button7);
        final boolean finalSheet = sheet1;
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(select_share_sheet_guest.this, Confirmation.class);

                // intentへ添え字付で値を保持させる
                intent.putExtra( "title", title);
                intent.putExtra( "item", item );
                intent.putExtra( "hour", hour);
                intent.putExtra( "minute", minute);
                intent.putExtra( "text", text );
                intent.putExtra("sheet1",horg1);
                intent.putExtra("sheet2",horg2);
                intent.putExtra("sheet3",horg3);
                intent.putExtra("sheet4",horg4);
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
