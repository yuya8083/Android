package com.example.yuya0817.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"こんにちは",Toast.LENGTH_LONG).show();
        Toast.makeText(this,"PBLだー!",Toast.LENGTH_SHORT).show();
    }

}
