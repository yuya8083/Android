package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private EditText editText;
    private TextView textView,textView2;
    private Button button;
    private String stringMessage = "カテゴリを選んでください";
    private String text;
    private static String item;

    private DialogFragment dialogFragment;
    private FragmentManager flagmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //RadioGroup radiogroup=(RadioGroup)findViewById(R.id.radiogroup);


        textView = (TextView)findViewById(R.id.category_select);
        //textView2 = (TextView)findViewById(R.id.category_select);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flagmentManager = getFragmentManager();

                // DialogFragment を継承したAlertDialogFragmentのインスタンス
                dialogFragment = new AlertDialogFragment();
                // DialogFragmentの表示
                dialogFragment.show(flagmentManager, "test alert dialog");
            }
        });

        /*Spinner spinner = (Spinner) findViewById(R.id.spinner);
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
        });*/

        editText = (EditText) findViewById(R.id.biko);

        button=(Button)findViewById(R.id.creat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = editText.getText().toString();
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                // intentへ添え字付で値を保持させる
                intent.putExtra( "item", item );
                intent.putExtra( "text", text );
                // 遷移先から返却されてくる際の識別コード
                int requestCode = 1001;
                // 返却値を考慮したActivityの起動を行う
                startActivityForResult( intent, requestCode );
            }
        });
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent intent )
    {
        // startActivityForResult()の際に指定した識別コードとの比較
        if( requestCode == 1001 ){

            // 返却結果ステータスとの比較
            if( resultCode == Activity.RESULT_OK ){

                // 返却されてきたintentから値を取り出す
                item = intent.getStringExtra( "item" );
                text = intent.getStringExtra("text");
            }
        }
    }

    public void setTextView(String message){
        textView.setText(message);
    }

    // DialogFragment を継承したクラス
    public static class AlertDialogFragment extends DialogFragment {
        // 選択肢のリスト
        private String[] menulist = {"勉強","趣味","恋愛","雑談"};

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

            // タイトル
            alert.setTitle("カテゴリを選んでください");
            alert.setItems(menulist, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int idx) {
                    // 選択１
                    if (idx == 0) {
                        setMassage(menulist[0]);
                        item=menulist[0];
                    }
                    // 選択２
                    else if (idx == 1) {
                        setMassage(menulist[1]);
                        item=menulist[1];
                    }
                    // 選択３
                    else if (idx == 2) {
                        setMassage(menulist[2]);
                        item=menulist[2];
                    }
                    //選択４
                    else if (idx == 3){
                        setMassage(menulist[3]);
                        item=menulist[3];
                    }
                    // cancel"
                    else {
                        // nothing to do
                    }
                }
            });

            return alert.create();
        }

        private void setMassage(String message) {
            Main2Activity main2Activity = (Main2Activity) getActivity();
            main2Activity.setTextView(message);
        }
    }

    public void onbackButtontapped(View view){
        finish();
    }
}
