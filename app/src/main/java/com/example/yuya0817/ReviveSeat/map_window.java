package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.os.Bundle;

public class map_window extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_window);

//        connect();
    }
//    public void onBackButtonTapped(View view){
//        finish();
//    }
//
//    private void connect() {
//        TextView tv = (TextView) findViewById(R.id.textView17);
//        Socket connection = null;
//        BufferedReader reader = null;
//        try {
//            // サーバーへ接続
//            connection = new Socket("pop.mail.yahoo.co.jp", 110);
//
//            // メッセージ取得オブジェクトのインスタンス化
//            reader = new BufferedReader(new InputStreamReader(connection
//                    .getInputStream()));
//
//            // サーバーからのメッセージを受信
//            String message = reader.readLine();
//
//            // 接続確認
//            if (!(message.matches("^\\+OK.*$"))) {
//                tv.setText("サーバーからのメッセージ：" + message);
//                Toast.makeText(this, "サーバーとの接続に失敗しました。",
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                tv.setText("サーバーからのメッセージ：" + message);
//                Toast.makeText(this, "サーバーとの接続に成功しました。",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            tv.setText("エラー内容：" + e.toString());
//            Toast.makeText(this, "サーバーとの接続に失敗しました。",
//                    Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            tv.setText("エラー内容：" + e.toString());
//            Toast.makeText(this, "サーバーとの接続に失敗しました。",
//                    Toast.LENGTH_SHORT).show();
//        } finally {
//            try {
//                // 接続終了処理
//                reader.close();
//                connection.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                tv.setText("エラー内容：" + e.toString());
//                Toast.makeText(this, "サーバーとの接続に失敗しました。",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}
