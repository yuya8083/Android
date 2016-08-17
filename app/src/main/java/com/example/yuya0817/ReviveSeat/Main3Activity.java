package com.example.yuya0817.ReviveSeat;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{
    static final String TAG = "ListViewTest";
    ListView listView;
    Button addButton;
    static List<String> dataList = new ArrayList<String>();
    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        findViews();
        setListeners();
        //View showButton = findViewById(R.id.cancel);
        //showButton.setOnClickListener(this);
        setAdapters();

        // リストをクリック
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // リストビューの項目を取得
                ListView listview = (ListView) parent;
                String item = (String) listview.getItemAtPosition(position);
                //Intent intent = new Intent(MainActivity.this, starbucks.class);
                //startActivity(intent);

                switch (position) {
                    case 0:
                        startActivity(new Intent(Main3Activity.this, starbucks.class));
                        break;
                    /*case 1:
                        startActivity(new Intent(this, ThirdActivity.class));
                        break;*/
                }
            }

        });



        setTitle("テーブル参加");

    }

    protected void findViews(){
        listView = (ListView)findViewById(R.id.listView);
        addButton = (Button)findViewById(R.id.cancel);
    }

    protected void setListeners(){
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cancel:
                cancel();
                break;
        }
    }

    protected void setAdapters(){
        adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        adapter.add("スターバックス/勉強");
        adapter.add("ドトール/雑談");
        adapter.add("サンマルク/恋愛");
        adapter.add("上島珈琲/趣味");
    }
    protected void cancel(){
        //Toast.makeText(Main3Activity.this, "キャンセルされました！", Toast.LENGTH_SHORT).show();
        finish();
    }
}
