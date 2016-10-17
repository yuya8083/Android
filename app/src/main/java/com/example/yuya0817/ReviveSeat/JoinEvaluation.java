package com.example.yuya0817.ReviveSeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class JoinEvaluation extends AppCompatActivity {
    private String comment;
    private Button button;
    int stringToValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_evaluation);

        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar);
        /*// リスナー登録
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                str = String.valueOf(ratingBar.getRating());
                stringToValue = Integer.parseInt(str);
            }
        });*/

        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                // レートが変更された際の処理
                stringToValue = (int)(ratingBar.getRating());
                //tv = (TextView)findViewById(R.id.textView3);
                //tv.setText("今のレート：" + stringToValue);
                //stringToValue = Integer.parseInt(str);
                button=(Button)findViewById(R.id.top);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText text = (EditText) findViewById(R.id.comment);
                        comment = text.getText().toString();
                        Intent intent = new Intent(JoinEvaluation.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
