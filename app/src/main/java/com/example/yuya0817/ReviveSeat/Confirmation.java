package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Confirmation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Button myButton=(Button)findViewById(R.id.next);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Confirmation.this, wait.class);
                startActivity(intent);
            }
        });
    }
    public void onBackButtonTapped(View view){
        finish();
    }
}
