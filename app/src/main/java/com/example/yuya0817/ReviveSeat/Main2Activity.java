package com.example.yuya0817.ReviveSeat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText editText;
    Button buttoncreat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String compareValue = "カテゴリ";
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (!compareValue.equals(null)) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spinner.setSelection(spinnerPosition);
        }

        //スピナーのクリックイベントを取得する
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //Itemが選択された時
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                //parentのspinnerを指定
                Spinner spinner = (Spinner) parent;
                //選択されたitemを取得
                String item = (String) spinner.getSelectedItem();
                //Toast表示
                Toast.makeText(Main2Activity.this, String.format("%sが選択されました。", item), Toast.LENGTH_SHORT).show();
            }
            //何も選択されなかったとき
            public void onNothingSelected(AdapterView parent) {
                Toast.makeText(Main2Activity.this, "何も選択されませんでした", Toast.LENGTH_SHORT).show();
            }
        });

        editText = (EditText) findViewById(R.id.editText);

        buttoncreat = (Button) findViewById(R.id.creat);
        buttoncreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // エディットテキストのテキストを取得
                String text = editText.getText().toString();
            }
        });
    }

    public void onbackButtontapped(View view){
        finish();
    }
}
