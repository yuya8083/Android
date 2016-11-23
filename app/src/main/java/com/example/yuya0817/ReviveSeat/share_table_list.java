package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.socket.client.Socket;

public class share_table_list extends Activity {

    public Socket socket;
    TextView titletext0,titletext1,titletext2,titletext3,titletext4,titletext5,
            titletext6,titletext7,titletext8,titletext9,
            category_idtext0,category_idtext1,category_idtext2,
            category_idtext3,category_idtext4,category_idtext5,category_idtext6,
            category_idtext7,category_idtext8,category_idtext9,
            shopnametext0,shopnametext1,shopnametext2,shopnametext3,shopnametext4,
            shopnametext5,shopnametext6,shopnametext7,shopnametext8,shopnametext9;

    private int i;

//    TextView category_idtext[] = new TextView[10];
//    TextView shopnametext[] = new TextView[10];
//    TextView titletext[] = new TextView[10];

    String category_id[] = new String[10];
    String shopname[] = new String[10];
    String title[] = new String[10];
    String shareid[] = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_table_list);

        // インテントを取得
        Intent data = getIntent();
        // インテントに保存されたデータを取得
        for (i=0; i<10; i++){
            category_id[i] = data.getStringExtra(i+".category_id");
            shopname[i] = data.getStringExtra(i+".shopname");
            title[i] = data.getStringExtra(i+".title");
            shareid[i] = data.getStringExtra(i+".shareid");
        }
        category_idtext0 = (TextView)findViewById(R.id.category0);
        category_idtext1 = (TextView)findViewById(R.id.category1);
        category_idtext2 = (TextView)findViewById(R.id.category2);
        category_idtext3 = (TextView)findViewById(R.id.category3);
        category_idtext4 = (TextView)findViewById(R.id.category4);
        category_idtext5 = (TextView)findViewById(R.id.category5);
        category_idtext6 = (TextView)findViewById(R.id.category6);
        category_idtext7 = (TextView)findViewById(R.id.category7);
        category_idtext8 = (TextView)findViewById(R.id.category8);
        category_idtext9 = (TextView)findViewById(R.id.category9);

        shopnametext0 = (TextView)findViewById(R.id.shopname0);
        shopnametext1 = (TextView)findViewById(R.id.shopname1);
        shopnametext2 = (TextView)findViewById(R.id.shopname2);
        shopnametext3 = (TextView)findViewById(R.id.shopname3);
        shopnametext4 = (TextView)findViewById(R.id.shopname4);
        shopnametext5 = (TextView)findViewById(R.id.shopname5);
        shopnametext6 = (TextView)findViewById(R.id.shopname6);
        shopnametext7 = (TextView)findViewById(R.id.shopname7);
        shopnametext8 = (TextView)findViewById(R.id.shopname8);
        shopnametext9 = (TextView)findViewById(R.id.shopname9);

        titletext0 = (TextView)findViewById(R.id.title0);
        titletext1 = (TextView)findViewById(R.id.title1);
        titletext2 = (TextView)findViewById(R.id.title2);
        titletext3 = (TextView)findViewById(R.id.title3);
        titletext4 = (TextView)findViewById(R.id.title4);
        titletext5 = (TextView)findViewById(R.id.title5);
        titletext6 = (TextView)findViewById(R.id.title6);
        titletext7 = (TextView)findViewById(R.id.title7);
        titletext8 = (TextView)findViewById(R.id.title8);
        titletext9 = (TextView)findViewById(R.id.title9);


        TextView category_idtext[] = {category_idtext0,category_idtext1,category_idtext2,
                category_idtext3,category_idtext4,category_idtext5,category_idtext6,
                category_idtext7, category_idtext8,category_idtext9};

        TextView shopnametext[] = {shopnametext0,shopnametext1,shopnametext2,shopnametext3,
                shopnametext4,shopnametext5,shopnametext6,shopnametext7,
                shopnametext8,shopnametext9};

        TextView titletext[] = {titletext0,titletext1,titletext2,titletext3,titletext4,
                titletext5,titletext6,titletext7,titletext8,titletext9};

        for (i=0; i<10; i++){
            category_idtext[i].setText(category_id[i]);
            shopnametext[i].setText(shopname[i]);
            titletext[i].setText(title[i]);
        }

        Button list0=(Button)findViewById(R.id.list0);
        list0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent1);
            }
        });

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

        Button list6=(Button)findViewById(R.id.list6);
        list6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent1);
            }
        });

        Button list7=(Button)findViewById(R.id.list7);
        list7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent1);
            }
        });

        Button list8=(Button)findViewById(R.id.list8);
        list8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent1);
            }
        });

        Button list9=(Button)findViewById(R.id.list9);
        list9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(share_table_list.this, JoinConfirmation.class);
                startActivity(intent1);
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
}
