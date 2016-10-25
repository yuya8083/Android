package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class select_share_sheet_host extends Activity  {

    private String title,item,hour,minute,text,host;
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

        Toast.makeText(select_share_sheet_host.this, title, Toast.LENGTH_SHORT).show();
        Toast.makeText(select_share_sheet_host.this, item, Toast.LENGTH_SHORT).show();
        Toast.makeText(select_share_sheet_host.this, hour, Toast.LENGTH_SHORT).show();
        Toast.makeText(select_share_sheet_host.this, minute, Toast.LENGTH_SHORT).show();
        Toast.makeText(select_share_sheet_host.this, text, Toast.LENGTH_SHORT).show();

        toggleButton1=(ToggleButton)findViewById(R.id.button1);
        toggleButton1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton1.isChecked()) {
                    sheet1=true;
                    toggleButton1.setTextOn("ホスト");
                }else {
                    sheet1=false;
                    toggleButton1.setTextOff("off");
                }

            }
        }));


        toggleButton2=(ToggleButton)findViewById(R.id.button2);
        toggleButton2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton2.isChecked()) {
                    sheet2=true;
                    toggleButton2.setTextOn("ホスト");
                }else {
                    sheet2=false;
                    toggleButton2.setTextOff("off");
                }

            }
        }));
        toggleButton3=(ToggleButton)findViewById(R.id.button3);
        toggleButton3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton3.isChecked()) {
                    sheet3=true;
                    toggleButton3.setTextOn("ホスト");
                }else {
                    sheet3=false;
                    toggleButton3.setTextOff("off");
                }


            }
        }));

        toggleButton4=(ToggleButton)findViewById(R.id.button4);
        toggleButton4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton4.isChecked()) {
                    sheet4=true;
                    toggleButton4.setTextOn("ホスト");
                }else {
                    sheet4=false;
                    toggleButton4.setTextOff("off");
                }

            }
        }));


        /*Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                host="R.id.Button1";
            }
        }));

        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                host="R.id.Button2";
            }
        }));

        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                host="R.id.Button3";
            }
        }));

        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                host="R.id.Button4";
            }
             }));
*/
        Button next=(Button)findViewById(R.id.button7);
        next.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(select_share_sheet_host.this, select_share_sheet_guest.class);

                // intentへ添え字付で値を保持させる
                intent.putExtra( "title", title);
                intent.putExtra( "item", item );
                intent.putExtra( "hour", hour);
                intent.putExtra( "minute", minute);
                intent.putExtra( "text", text );
                intent.putExtra("host",host);
                intent.putExtra("sheet1",sheet1);
                intent.putExtra("sheet2",sheet2);
                intent.putExtra("sheet3",sheet3);
                intent.putExtra("sheet4",sheet4);


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

//        ImageButton back=(ImageButton)findViewById(R.id.back);
//        back.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(select_share_sheet_host.this, Confirmation.class);
//                startActivity(intent1);
//            }
//        }));
    }
//    public void onbackButtontapped(View view){
//        finish();
//    }





}
