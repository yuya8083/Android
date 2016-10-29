//package com.example.yuya0817.ReviveSeat;
//
//import android.app.IntentService;
//import android.content.Intent;
//import android.os.Handler;
//import android.util.Log;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import io.socket.SocketIO;
//
///**
// * Created by yuya0817 on 2016/10/26.
// */
//
//
//
//public class MyIntentService extends IntentService {
//
//    private Handler handler = new Handler();
//    private SocketIO socket;
//
//    public MyIntentService(String name) {
//        super(name);
//        // TODO 自動生成されたコンストラクター・スタブ
//    }
//
//    public MyIntentService() {
//        // ActivityのstartService(intent);で呼び出されるコンストラクタはこちら
//        super("MyIntentService");
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        // 非同期処理を行うメソッド。タスクはonHandleIntentメソッド内で実行する
//        socket = Confirmation.getsocket();
//        try {
//            Log.e("intent", "0");
//            // イベント送信
//            JSONObject json = new JSONObject();
//            json.put("title", intent.getStringExtra("title"));
//            json.put("category_id", intent.getStringExtra("categoryid"));
//            json.put("endtime", intent.getStringExtra("hour") + ":" + intent.getStringExtra("minute"));
//            json.put("explain", intent.getStringExtra("text"));
//            json.put("shopid", intent.getStringExtra("shopid"));
//            json.put("tableid", intent.getStringExtra("tableid"));
//            json.put("userid", intent.getStringExtra("userid"));
//            json.put("seatinfo", intent.getStringExtra("seatinfo"));
//
//            Log.e("intent", "1");
////            socket.emit("sharetable_start", json);
//            socket.emit("test", json);
//            Log.e("intent", "2");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.e("intent", "3");
//        }
//
//    }
//}

