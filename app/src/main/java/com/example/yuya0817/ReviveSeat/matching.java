package com.example.yuya0817.ReviveSeat;

import android.os.Bundle;
import android.app.Activity;
import android.widget.RatingBar;

public class matching extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar);
    }
}
