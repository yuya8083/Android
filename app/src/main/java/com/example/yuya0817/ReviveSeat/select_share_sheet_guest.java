package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class select_share_sheet_guest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_share_sheet_guest);

        // インテントを取得
        final Intent data = getIntent();
        // インテントに保存されたデータを取得
        final String title = data.getStringExtra("title");
        final String item = data.getStringExtra("item");
        final String hour = data.getStringExtra("hour");
        final String minute = data.getStringExtra("minute");
        final String text = data.getStringExtra("text");


        Button myButton=(Button)findViewById(R.id.button7);
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
