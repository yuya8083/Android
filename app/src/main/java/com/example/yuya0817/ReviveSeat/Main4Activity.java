package com.example.yuya0817.ReviveSeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        TextView categorytext = (TextView)findViewById(R.id.categorytext);
        TextView bikotext = (TextView)findViewById(R.id.bikotext);

        // インテントを取得
        Intent intent = getIntent();
        // インテントに保存されたデータを取得
        final String item = intent.getStringExtra("item");
        final String text = intent.getStringExtra("text");

        categorytext.setText(item);
        bikotext.setText(text);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // インテントのインスタンス作成
                Intent data = new Intent();
                // インテントに値をセット
                data.putExtra("item", item );
                data.putExtra("text",text);
                // 結果を設定
                setResult(RESULT_OK, data);
                // サブ画面の終了
                finish();
            }
        });
    }
}
