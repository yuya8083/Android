package com.example.yuya0817.ReviveSeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public io.socket.client.Socket socket;
    private GoogleMap mMap;
    private TextView ct;
    private TextView sn;
    public double xy;
    int shareid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent data = getIntent();
        shareid = data.getIntExtra("shareid", 0);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");//http://133.25.196.30:2010
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }



        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //送信
                JSONObject obj = new JSONObject();
                socket.emit("test", "接続してやー");
                System.out.println("ぜろ");
            }
//            private Emitter.Listener 受け取る変数！ = new Emitter.Listener() {
//
//            };

      }).on("shop_address", new Emitter.Listener() {//shop_address
           @Override
           public void call(final Object... args) {//detail_back.shop_address
               MapsActivity.super.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       //住所
                       System.out.println("ねみぃ");
                       System.out.println(String.valueOf(args[0])); //うけとった文字列が何か調べてる！
                       ct = (TextView) findViewById(R.id.cafenametext);
                       ct.setText((CharSequence) args[0]);
                       System.out.println("いち");
                   }
               });
           }

      }).on("shop_name", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {//detail_back.shop_name
                MapsActivity.super.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(String.valueOf(args[0])); //うけとった文字列が何か調べてる！
                    //店名

                        System.out.println("に");
                        sn=(TextView)findViewById(R.id.adresstext);
                        System.out.println("さん");
                        sn.setText((CharSequence) args[0]);
                        socket.disconnect();
                    }
                });
            }

        });
        socket.connect();


        Button myButton=(Button)findViewById(R.id.next);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, JoinQrcode.class);
                intent.putExtra("shareid", shareid);
                startActivity(intent);
            }
        });

        Button returnButton = (Button) findViewById(R.id.back);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    socket = IO.socket("https://reviveseatserver.herokuapp.com/");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        //送信
                        socket.emit("detail",0);
                        socket.disconnect();
                    }
                });
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
        System.out.println("ご");
        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");//http://133.25.196.30:2010
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on("shop_x", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                MapsActivity.super.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(String.valueOf(args[0])); //うけとった文字列が何か調べてる！
                        sn=(TextView)findViewById(R.id.adresstext);
                        sn.setText((CharSequence) args[0]);
                        xy = (double)args[0];
                    }
                });
            }
        }).on("shop_y", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                MapsActivity.super.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(String.valueOf(args[0])); //うけとった文字列が何か調べてる！
                        //店名
// Add a marker in Sydney and move the camera
                        LatLng cafe = new LatLng(xy, (double)args[0]);
                        mMap.addMarker(new MarkerOptions().position(cafe).title("shop_locate"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(cafe));

                        CameraUpdate cUpdate = CameraUpdateFactory.newLatLngZoom( new LatLng(41.84, 140.77), 15);
                        mMap.moveCamera(cUpdate);
                        socket.disconnect();
                    }
                });
            }

        });
        socket.connect();

    }
}
