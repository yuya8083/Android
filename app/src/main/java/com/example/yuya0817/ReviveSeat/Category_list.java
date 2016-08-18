package com.example.yuya0817.ReviveSeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Category_list extends AppCompatActivity{
    static final String TAG = "ListViewTest";
    ListView listView;
    static List<String> dataList = new ArrayList<String>();
    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        findViews();
        setAdapters();
    }

    protected void findViews() {
        listView = (ListView) findViewById(R.id.listView2);
    }

    protected void setAdapters() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        adapter.add("勉強");
        adapter.add("雑談");
        adapter.add("恋愛");
        adapter.add("趣味");
    }
}