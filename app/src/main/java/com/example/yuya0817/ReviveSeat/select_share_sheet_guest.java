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

        Button myButton=(Button)findViewById(R.id.button7);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(select_share_sheet_guest.this, Confirmation.class);
                startActivity(intent);
            }
        });
    }
    public void onBackButtonTapped(View view){
        finish();
    }
}
