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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    private EditText editText;
    private TextView category,time;
    private Button button;
    private String stringMessage = "カテゴリを選択してください";
    private String text,outhour,outminute;
    private static String item;
    private ArrayList<String> Radiobottunword;

    private DialogFragment dialogFragment,dialogFragment2;
    private FragmentManager flagmentManager,flagmentManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RadioGroup radiogroup= (RadioGroup) findViewById(R.id.radioButtonGroup);



        category = (TextView)findViewById(R.id.category_select);
        time = (TextView)findViewById(R.id.time);
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
                final TimePickerDialog dialog = new TimePickerDialog(Main2Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Toast.makeText(Main2Activity.this, String.valueOf(hourOfDay) + ":" + String.valueOf(minute),Toast.LENGTH_SHORT).show();
                        outhour=String.valueOf(hourOfDay);
                        outminute=String.valueOf(minute);
                        time.setText(outhour + ":" + outminute);
                    }
                },hour,minute,true);
                dialog.show();
            }
        });

        editText = (EditText) findViewById(R.id.biko);

        button=(Button)findViewById(R.id.creat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Radiobottunword=new ArrayList<String>();
                text = editText.getText().toString();
                Radiobottunword.add(text);
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                // intentへ添え字付で値を保持させる
                intent.putExtra( "item", item );
                intent.putExtra( "text", text );
                //intent.putExtra( "time", time );
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
        category.setText(message);
    }

    // DialogFragment を継承したクラス
    public static class AlertDialogFragment extends DialogFragment {
        // 選択肢のリスト
        private String[] menulist = {"勉強","趣味","恋愛","雑談"};

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
