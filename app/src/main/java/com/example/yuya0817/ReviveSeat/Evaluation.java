package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class Evaluation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        Button button=(Button)findViewById(R.id.top);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text=(EditText)findViewById(R.id.comment);
                String comment = text.getText().toString();
                Intent intent = new Intent(Evaluation.this, MainActivity.class);
                startActivity(intent);
            }
        });

        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar);
        // リスナー登録
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String str = String.valueOf(ratingBar.getRating());
            }
        });
    }
}
