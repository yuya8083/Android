package com.example.yuya0817.ReviveSeat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class TableSearch extends FragmentActivity implements OnMapReadyCallback {

    private Socket socket;

    private GoogleMap mMap;
    private static TextView choose, category, distance;
    private static EditText shopName;
    String shop_name="";

    String category_id[] = new String[10];
    String shopname[] = new String[10];
    String title[] = new String[10];
    String shareid[] = new String[10];

    private static int flag = 0,refine;

    private static String item;
    private static int radius;
    private int categoryId,i,j;
    private double latitude,longitude;

    private DialogFragment dialogFragment_choose, dialogFragment_category, dialogFragment_distance;
    private FragmentManager flagmentManager;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_search);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
            Log.d("1","1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        choose = (TextView) findViewById(R.id.choose);
        category = (TextView) findViewById(R.id.category);
        distance = (TextView) findViewById(R.id.distance);

        shopName = (EditText) findViewById(R.id.shopname);

//        shopname.setFocusable(false);
//        shopname.setEnabled(false);

        choose.setClickable(true);
//        shopname.setClickable(true);
        category.setClickable(true);
        distance.setClickable(true);

        choose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flag = 1;
                flagmentManager = getFragmentManager();

                // DialogFragment を継承したAlertDialogFragmentのインスタンス
                dialogFragment_choose = new TableSearch.AlertDialogFragment();
                // DialogFragmentの表示
                dialogFragment_choose.show(flagmentManager, "test alert dialog");
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (refine == 1) {
                    flag = 2;
                    flagmentManager = getFragmentManager();

                    // DialogFragment を継承したAlertDialogFragmentのインスタンス
                    dialogFragment_category = new TableSearch.AlertDialogFragment();
                    // DialogFragmentの表示
                    dialogFragment_category.show(flagmentManager, "test alert dialog");
//                    category_id = Integer.valueOf(item);
                }
            }
        });
//        shopname.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                if (refine == 2){
//                    shopname.setFocusable(true);
//                    shopname.setEnabled(true);
//                    shopname.setFocusableInTouchMode(true);
////                    shopname.setInputType(InputType.TYPE_CLASS_TEXT);
//                    System.out.println("タッチした？");
//                }
//            }
//        });

        distance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (refine == 3) {
                    flag = 3;
                    flagmentManager = getFragmentManager();

                    // DialogFragment を継承したAlertDialogFragmentのインスタンス
                    dialogFragment_distance = new TableSearch.AlertDialogFragment();
                    // DialogFragmentの表示
                    dialogFragment_distance.show(flagmentManager, "test alert dialog");
                }
            }
        });

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;
                shop_name = shopName.getText().toString();
                socket.connect();

                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("2", "2");
                        JSONObject json = new JSONObject();
                        if (refine == 1) { //カテゴリーID
                            try {
                                json.put("refine", refine);
                                json.put("category_id", categoryId);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (refine == 2) { //店名
                            try {
                                json.put("refine", refine);
                                json.put("shopname", shop_name);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else { //距離
                            try {
                                json.put("refine", refine);
                                json.put("radius", radius);
                                json.put("location_x", latitude);
                                json.put("location_y", longitude);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(json);
                        socket.emit("sharetable_list", json);
                    }

                }).on("sharetable_list_back", new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(String.valueOf(args[0]));
                        try {
                            JSONArray datas = (JSONArray) args[0];
                            for (j = 0; j < 10; j++) {
                                JSONObject data = datas.getJSONObject(j);
                                category_id[j] = data.getString("category_id");
                                shopname[j] = data.getString("shopname");
                                title[j] = data.getString("title");
                                shareid[j] = data.getString("shareid");
//                                    System.out.println(title[i]);
                            }
//                                JSONArray datas = (JSONArray)args[0];
//                                JSONObject data = datas.getJSONObject(0);
//                                title = data.getString("title");
//                                System.out.println(title);

//                                for (i=0; i<10; i++){
//                                    JSONObject data = datas.getJSONObject(i);
////                                    category_id[i] = data.getString("category_id");
////                                    shopname[i] = data.getString("shopname");
//                                    title[i] = data.getString("title");
////                                    shareid[i] = data.getString("shareid");
//                                    System.out.println(title[i]);
//                                }
//                                System.out.println(title);
//                                flag = 1;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        socket.disconnect();
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        i = 1;
                    }

                });
                while (i == 0){
                    intent = new Intent(TableSearch.this, share_table_list.class);
                }
                for (j=0; j<10; j++) {
                    intent.putExtra(j + ".category_id", category_id[j]);
                    intent.putExtra(j + ".shopname", shopname[j]);
                    intent.putExtra(j + ".title", title[j]);
                    intent.putExtra(j + ".shareid", shareid[j]);
                }
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

    // DialogFragment を継承したクラス
    public static class AlertDialogFragment extends DialogFragment {
        // 選択肢のリスト
        private String[] menulist = {"カテゴリー", "店名", "距離"};
        private String[] categorylist = {"1", "2", "3", "4", "5"};
        private String[] distancelist = {"1km", "5km", "10km"};

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

            if (flag == 1) {
                // タイトル
                alert.setTitle("選択項目を選択してください");
                alert.setItems(menulist, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int idx) {
                        // 選択１
                        if (idx == 0) {
                            setMassage(menulist[0]);
                            item = menulist[0];
                            refine = 1;
                            System.out.println(refine);
                            category.setTextColor(Color.BLACK);
                            shopName.setTextColor(Color.rgb(190,190,190));
                            if (shopName.hasFocusable()){
                                shopName.setFocusable(false);
                                shopName.setFocusableInTouchMode(false);
                            }
                            distance.setTextColor(Color.rgb(190,190,190));
                        }
                        // 選択２
                        else if (idx == 1) {
                            setMassage(menulist[1]);
                            item = menulist[1];
                            refine = 2;
                            System.out.println(refine);
                            category.setTextColor(Color.rgb(190,190,190));
                            shopName.setTextColor(Color.BLACK);
                            if (!shopName.hasFocusable()){
                                shopName.setFocusable(true);
                                shopName.setFocusableInTouchMode(true);
                                System.out.println("変わった");
                            }
                            distance.setTextColor(Color.rgb(190,190,190));
                        }
                        // 選択３
                        else {
                            setMassage(menulist[2]);
                            item = menulist[2];
                            refine = 3;
                            System.out.println(refine);
                            category.setTextColor(Color.rgb(190,190,190));
                            shopName.setTextColor(Color.rgb(190,190,190));
                            if (shopName.hasFocusable()){
                                shopName.setFocusable(false);
                                shopName.setFocusableInTouchMode(false);
                            }
                            distance.setTextColor(Color.BLACK);
                        }
                    }
                });
            } else if (flag == 2) {
                // タイトル
                alert.setTitle("カテゴリーを選択してください");
                alert.setItems(categorylist, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int idx) {
                        // 選択１
                        if (idx == 0) {
                            setMassage(categorylist[0]);
                            item = categorylist[0];
                        }
                        // 選択２
                        else if (idx == 1) {
                            setMassage(categorylist[1]);
                            item = categorylist[1];
                        }
                        // 選択３
                        else if (idx == 2) {
                            setMassage(categorylist[2]);
                            item = categorylist[2];
                        }
                        // 選択４
                        else if (idx == 3) {
                            setMassage(categorylist[3]);
                            item = categorylist[3];
                        }
                        // 選択５
                        else {
                            setMassage(categorylist[4]);
                            item = categorylist[4];
                        }
                    }
                });
            } else {
                // タイトル
                alert.setTitle("距離を選択してください");
                alert.setItems(distancelist, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int idx) {
                        // 選択１
                        if (idx == 0) {
                            setMassage(distancelist[0]);
                            item = distancelist[0];
                            radius = 1;
                        }
                        // 選択２
                        else if (idx == 1) {
                            setMassage(distancelist[1]);
                            item = distancelist[1];
                            radius = 5;
                        }
                        // 選択３
                        else {
                            setMassage(distancelist[2]);
                            item = distancelist[2];
                            radius = 10;
                        }
                    }
                });
            }

            return alert.create();
        }

        private void setMassage(String message) {
            TableSearch tableSearch = (TableSearch) getActivity();
            tableSearch.setTextView(message);
        }
    }

    public void setTextView(String message) {
        if (flag == 1) {
            choose.setText(message);
        } else if (flag == 2) {
            category.setText(message);
            categoryId = Integer.valueOf(message);
        } else {
            distance.setText(message);
        }
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

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        //LocationManagerの取得
        LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
//GPSから現在地の情報を取得
        Location myLocate = locationManager.getLastKnownLocation("gps");

        if(myLocate != null){
            //現在地情報取得成功

            //緯度の取得
            latitude = myLocate.getLatitude();// * 1e6;
            //経度の取得
            longitude = myLocate.getLongitude();// * 1e6;

            System.out.println(latitude);
            System.out.println(longitude);
            CameraUpdate cUpdate = CameraUpdateFactory.newLatLngZoom( new LatLng(latitude, longitude), 15);
            mMap.moveCamera(cUpdate);
        }else{
            //現在地情報取得失敗時の処理
            Toast.makeText(this, "現在地の取得ができませんでした", Toast.LENGTH_SHORT).show();
        }

        // Add a marker in Sydney and move the camera

//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//        CameraPosition cameraPos = new CameraPosition.Builder()
//                .target(new LatLng(location, location))
//                .zoom(7.0f).bearing(0).build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
    }
}
