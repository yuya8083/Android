package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class select_share_sheet_host extends Activity  {

    private String title,item,hour,minute,text,seat1,seat2,seat3,seat4;
    private boolean sheet1,sheet2,sheet3,sheet4;
    private ToggleButton toggleButton1,toggleButton2,toggleButton3,toggleButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_share_sheet_host);

        // インテントを取得
        Intent data = getIntent();
        // インテントに保存されたデータを取得
        title = data.getStringExtra("title");
        item = data.getStringExtra("item");
        hour = data.getStringExtra("hour");
        minute = data.getStringExtra("minute");
        text = data.getStringExtra("text");

        seat1="0";
        seat2="0";
        seat3="0";
        seat4="0";

//        Toast.makeText(select_share_sheet_host.this, title, Toast.LENGTH_SHORT).show();
//        Toast.makeText(select_share_sheet_host.this, item, Toast.LENGTH_SHORT).show();
//        Toast.makeText(select_share_sheet_host.this, hour, Toast.LENGTH_SHORT).show();
//        Toast.makeText(select_share_sheet_host.this, minute, Toast.LENGTH_SHORT).show();
//        Toast.makeText(select_share_sheet_host.this, text, Toast.LENGTH_SHORT).show();

        toggleButton1 = (ToggleButton) findViewById(R.id.button1);
        toggleButton1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton1.isChecked()) {
                    sheet1 = true;
                    seat1="1";
                } else {
                    sheet1 = false;
                    seat1="0";
                }

            }
        }));


        toggleButton2 = (ToggleButton) findViewById(R.id.button2);
        toggleButton2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton2.isChecked()) {
                    sheet2 = true;
                    seat2="1";
                } else {
                    sheet2 = false;
                    seat2="0";
                }

            }
        }));
        toggleButton3 = (ToggleButton) findViewById(R.id.button3);
        toggleButton3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton3.isChecked()) {
                    sheet3 = true;
                    seat3="1";
                } else {
                    sheet3 = false;
                    seat3="0";
                }


            }
        }));

        toggleButton4 = (ToggleButton) findViewById(R.id.button4);
        toggleButton4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton4.isChecked()) {
                    sheet4 = true;
                    seat4="1";
                } else {
                    sheet4 = false;
                    seat4="0";
                }

            }
        }));


        Button next = (Button) findViewById(R.id.button7);
        next.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(select_share_sheet_host.this, select_share_sheet_guest.class);

                // intentへ添え字付で値を保持させる
                intent.putExtra("title", title);
                intent.putExtra("item", item);
                intent.putExtra("hour", hour);
                intent.putExtra("minute", minute);
                intent.putExtra("text", text);
                intent.putExtra("sheet1", sheet1);
                intent.putExtra("sheet2", sheet2);
                intent.putExtra("sheet3", sheet3);
                intent.putExtra("sheet4", sheet4);
                intent.putExtra("seat1", seat1);
                intent.putExtra("seat2", seat2);
                intent.putExtra("seat3", seat3);
                intent.putExtra("seat4", seat4);


                startActivity(intent);
            }
        }));

        Button returnButton = (Button) findViewById(R.id.back);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//    public void onbackButtontapped(View view){
//        finish();
//    }


    }


}
