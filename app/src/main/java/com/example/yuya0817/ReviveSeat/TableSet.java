package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TableSet extends Activity {

    private EditText titletext,hosoku;
    private TextView category,time;
    private Button button;
    private String text,outhour,outminute,title;
    private static String item;
    //private ImageView imageview;

    private DialogFragment dialogFragment;
    private FragmentManager flagmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_set);

        /*imageview = (ImageView)findViewById(R.id.imageView);
        imageview.setImageResource(R.drawable.backbutton);
        // ImageViewオブジェクトにクリックイベントを追加する
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // イメージ画像がクリックされたときに実行される処理
                Intent intent2 = new Intent(TableSet.this, MainActivity.class);
                startActivity(intent2);
            }
        });*/
        category = (TextView)findViewById(R.id.categoryset);
        time = (TextView)findViewById(R.id.timeset);
        category.setClickable(true);
        time.setClickable(true);
        category.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flagmentManager = getFragmentManager();

                // DialogFragment を継承したAlertDialogFragmentのインスタンス
                dialogFragment = new AlertDialogFragment();
                // DialogFragmentの表示
                dialogFragment.show(flagmentManager, "test alert dialog");
            }
        });

        final Calendar calender = Calendar.getInstance();
        final int hour = calender.get(Calendar.HOUR_OF_DAY);
        final int minute = calender.get(Calendar.MINUTE);


        time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final TimePickerDialog dialog = new TimePickerDialog(TableSet.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Toast.makeText(Main2Activity.this, String.valueOf(hourOfDay) + ":" + String.valueOf(minute),Toast.LENGTH_SHORT).show();
                        outhour=String.valueOf(hourOfDay);
                        outminute=String.valueOf(minute);
                        time.setText("シェア終了時刻　" + outhour + "時" + outminute + "分");
                    }
                },hour,minute,true);
                dialog.show();
            }
        });

        titletext = (EditText) findViewById(R.id.editText);
        hosoku = (EditText)findViewById(R.id.editText2);

        button=(Button)findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = titletext.getText().toString();
                text = hosoku.getText().toString();
                Intent intent = new Intent(TableSet.this, select_share_sheet_host.class);
                // intentへ添え字付で値を保持させる
                intent.putExtra( "title", title);
                intent.putExtra( "item", item );
                intent.putExtra( "text", text );
                intent.putExtra( "hour", outhour);
                intent.putExtra( "minute", outminute);
                // 遷移先から返却されてくる際の識別コード
                int requestCode = 1001;
                // 返却値を考慮したActivityの起動を行う
                startActivityForResult( intent, requestCode );
            }
        });

        Button returnButton = (Button) findViewById(R.id.back);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TableSet.this, MainActivity.class);
                startActivity(intent);
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
                title = intent.getStringExtra("title");
                item = intent.getStringExtra( "item" );
                text = intent.getStringExtra("text");
                outhour = intent.getStringExtra("hour");
                outminute = intent.getStringExtra("minute");
            }
        }
    }

    public void setTextView(String message){
        category.setText(message);
    }

    // DialogFragment を継承したクラス
    public static class AlertDialogFragment extends DialogFragment {
        // 選択肢のリスト
        private String[] menulist = {"1","2","3","4","5"};

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

            // タイトル
            alert.setTitle("カテゴリを選択してください");
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
                    //選択５
                    else if (idx==4){
                        setMassage(menulist[4]);
                        item=menulist[4];
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
            TableSet tableSet = (TableSet) getActivity();
            tableSet.setTextView(message);
        }
    }

//    public void onbackButtontapped(View view){
//        finish();
//    }
}
