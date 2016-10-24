package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class select_share_sheet_host extends Activity {

    private String title,item,hour,minute,text,host;

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

        Button button1=(Button)findViewById(R.id.button1);
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
