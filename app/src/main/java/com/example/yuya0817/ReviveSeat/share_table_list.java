package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class share_table_list extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_table_list);

//        ImageButton back=(ImageButton)findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(share_table_list.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });


        Button list1=(Button)findViewById(R.id.list1);
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent1);
            }
        });

        Button list2=(Button)findViewById(R.id.list2);
        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent2);
            }
        });

        Button list3=(Button)findViewById(R.id.list3);
        list3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent3);
            }
        });

        Button list4=(Button)findViewById(R.id.list4);
        list4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent4);
            }
        });

        Button list5=(Button)findViewById(R.id.list5);
        list5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent5);
            }
        });
    }

    public void onBackButtonTapped(View view){
        finish();
    }
}
