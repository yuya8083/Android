package com.example.yuya0817.ReviveSeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class map_window extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_window);
    }
    public void onBackButtonTapped(View view){
        finish();
    }
}
