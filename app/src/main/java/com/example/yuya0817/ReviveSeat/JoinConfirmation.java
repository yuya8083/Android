package com.example.yuya0817.ReviveSeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static com.example.yuya0817.ReviveSeat.R.id.map;

public class JoinConfirmation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
//    LatLng oldPos = null;

    private Socket socket;
    String hname,titles,endtime,explain,shop_address,shop_name
            ,seatinfo_st,seat1,seat2,seat3,seat4,userid;
    private int i,flag,huserid,hyoka,seatinfo,seatnum,count,shareid;
    private double shop_x,shop_y;
    TextView guest,cafename,title,category,time,hosoku,num,address;
    ToggleButton toggleButton1,toggleButton2,toggleButton3,toggleButton4;
    int decide[] = new int[2];
//    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_confirmation);

        guest = (TextView)findViewById(R.id.geust);
        cafename = (TextView)findViewById(R.id.cafenametext);
        title = (TextView)findViewById(R.id.titletext);
        category = (TextView)findViewById(R.id.categorytext);
        time = (TextView)findViewById(R.id.timetext);
        hosoku = (TextView)findViewById(R.id.hosokutext);
        num = (TextView)findViewById(R.id.number);
        address = (TextView)findViewById(R.id.addresstext);

        toggleButton1 = (ToggleButton)findViewById(R.id.tb0);
        toggleButton2 = (ToggleButton)findViewById(R.id.tb1);
        toggleButton3 = (ToggleButton)findViewById(R.id.tb2);
        toggleButton4 = (ToggleButton)findViewById(R.id.tb3);

        toggleButton1.setChecked(false);
        toggleButton2.setChecked(false);
        toggleButton3.setChecked(false);
        toggleButton4.setChecked(false);

        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // インテントを取得
        Intent data = getIntent();
        // インテントに保存されたデータを取得
        hname = data.getStringExtra("hname");//string
        huserid = data.getIntExtra("huserid", 0);//int
        hyoka = data.getIntExtra("hyoka", 0);//int
        titles = data.getStringExtra("title");//string
        endtime = data.getStringExtra("endtime");//string
        explain = data.getStringExtra("explain");//string
        seatinfo = data.getIntExtra("seatinfo", 0);//int
        seatnum = data.getIntExtra("seatnum", 0);//int
        shop_address = data.getStringExtra("shop_address");//string
        shop_name = data.getStringExtra("shop_name");//string
        shop_x = data.getDoubleExtra("shop_x", 0);//double
        shop_y = data.getDoubleExtra("shop_y", 0);//double
        shareid = data.getIntExtra("shareid", 0);

        seatinfo_st = String.valueOf(seatinfo);
        if (seatinfo_st.length() == 4){
            seat1 = seatinfo_st.substring(0,1);
            seat2 = seatinfo_st.substring(1,2);
            seat3 = seatinfo_st.substring(2,3);
            seat4 = seatinfo_st.substring(3,4);
        }else if (seatinfo_st.length() == 3){
            seat1 = "0";
            seat2 = seatinfo_st.substring(0,1);
            seat3 = seatinfo_st.substring(1,2);
            seat4 = seatinfo_st.substring(2,3);
        }else if(seatinfo_st.length() == 2){
            seat1 = "0";
            seat2 = "0";
            seat3 = seatinfo_st.substring(0,1);
            seat4 = seatinfo_st.substring(1,2);
        }else {
            seat1 = "0";
            seat2 = "0";
            seat3 = "0";
            seat4 =seatinfo_st.substring(0,1);
        }

        count = 0;

        if (Objects.equals(seat1, "0")){
            toggleButton1.setText("空席");
            toggleButton1.setTextOn("空席");
            toggleButton1.setTextOff("空席");
        }else if (Objects.equals(seat1, "1")){
            toggleButton1.setText("ホスト");
            toggleButton1.setTextOn("ホスト");
            toggleButton1.setTextOff("ホスト");
        }else {
            toggleButton1.setText("ゲスト");
            toggleButton1.setTextOn("ゲスト");
            toggleButton1.setTextOff("ゲスト");
            count++;
        }

        if (Objects.equals(seat2, "0")){
            toggleButton2.setText("空席");
            toggleButton2.setTextOn("空席");
            toggleButton2.setTextOff("空席");
        }else if (Objects.equals(seat2, "1")){
            toggleButton2.setText("ホスト");
            toggleButton2.setTextOn("ホスト");
            toggleButton2.setTextOff("ホスト");
        }else {
            toggleButton2.setText("ゲスト");
            toggleButton2.setTextOn("ゲスト");
            toggleButton2.setTextOff("ゲスト");
            count++;
        }

        if (Objects.equals(seat3, "0")){
            toggleButton3.setText("空席");
            toggleButton3.setTextOn("空席");
            toggleButton3.setTextOff("空席");
        }else if (Objects.equals(seat3, "1")){
            toggleButton3.setText("ホスト");
            toggleButton3.setTextOn("ホスト");
            toggleButton3.setTextOff("ホスト");
        }else {
            toggleButton3.setText("ゲスト");
            toggleButton3.setTextOn("ゲスト");
            toggleButton3.setTextOff("ゲスト");
            count++;
        }

        if (Objects.equals(seat4, "0")){
            toggleButton4.setText("空席");
            toggleButton4.setTextOn("空席");
            toggleButton4.setTextOff("空席");
        }else if (Objects.equals(seat4, "1")){
            toggleButton4.setText("ホスト");
            toggleButton4.setTextOn("ホスト");
            toggleButton4.setTextOff("ホスト");
        }else {
            toggleButton4.setText("ゲスト");
            toggleButton4.setTextOn("ゲスト");
            toggleButton4.setTextOff("ゲスト");
            count++;
        }

        guest.setText(hname);
        cafename.setText(shop_name);
        title.setText(titles);
//        category.setText();
        time.setText(endtime);
        hosoku.setText(explain);
        num.setText("シェア人数　"+count+"人");
        address.setText(shop_address);
        bar.setRating(hyoka);

        userid = String.valueOf(TempDataUtil.load(this));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

//        scrollView = (ScrollView)findViewById(R.id.scrollview);

//        scrollView.setOnTouchListener( new View.OnTouchListener(){
//            @Override
//            public boolean onTouch( View v, MotionEvent event ) {
//                if (onCameraChange){
//                    return true;
//                }else{
//                    return false;
//                }
//            }
//        });

        Button myButton=(Button)findViewById(R.id.join);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decide[0] = shareid;
                decide[1] = Integer.valueOf(userid);//ユーザID送る
                System.out.println(Arrays.toString(decide));
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        socket.emit("decide", (Object) decide);
                        socket.disconnect();
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                    }
                });
                Intent intent = new Intent(JoinConfirmation.this, orner_wait.class);
                intent.putExtra("shareid", decide[0]);
                intent.putExtra("name", hname);
                intent.putExtra("shop_name", shop_name);
                intent.putExtra("shop_address", shop_address);
                intent.putExtra("shop_x", shop_x);
                intent.putExtra("shop_y", shop_y);
                startActivity(intent);
            }
        });

        Button returnButton = (Button) findViewById(R.id.back);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        scrollView.onInterceptTouchEvent(){
//        }
//        scrollView.setOnClickListener(new View.OnTouchListener(){
//            @Override
//            public boolean OnTouch(View v, MotionEvent event){
//                return false;
//            }
//        });

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        CameraUpdate cUpdate = CameraUpdateFactory.newLatLngZoom( new LatLng(shop_y, shop_x), 15);
        mMap.addMarker(new MarkerOptions().position(new LatLng(shop_y, shop_x)).title(shop_name));
        mMap.moveCamera(cUpdate);

//        //カメラ位置変更のイベントが呼ばれる
//        mMap.setOnCameraMoveListener((GoogleMap.OnCameraMoveListener) JoinConfirmation.this);
    }

//    public void onCameraMoveListener(CameraPosition pos) {
//        if(oldPos != null){
//            LatLng newPos = pos.target;
//
//            //カメラが動いたらスクロールと判定
//            if(!oldPos.equals(newPos)){
//                //スクロールした時の処理
//            }
//        }
//        oldPos = pos.target;
//    }
}
