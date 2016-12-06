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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public io.socket.client.Socket socket;
    private GoogleMap mMap;
    private TextView shop,address;
    public double xy;
    int shareid;
    String name,shop_name,shop_address;
    private double shop_x,shop_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent data = getIntent();
        shareid = data.getIntExtra("shareid", 0);
        name = data.getStringExtra("name");
        shop_name = data.getStringExtra("shop_name");
        shop_address = data.getStringExtra("shop_address");
        shop_x = data.getDoubleExtra("shop_x", 0);
        shop_y = data.getDoubleExtra("shop_y", 0);

        shop = (TextView)findViewById(R.id.cafenametext);
        address = (TextView)findViewById(R.id.addresstext);

        shop.setText(shop_name);
        address.setText(shop_address);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button myButton = (Button) findViewById(R.id.next);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, JoinQrcode.class);
                intent.putExtra("shareid", shareid);
                intent.putExtra("name", name);
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

        CameraUpdate cUpdate = CameraUpdateFactory.newLatLngZoom( new LatLng(shop_y, shop_x), 15);
        mMap.addMarker(new MarkerOptions().position(new LatLng(shop_y, shop_x)).title(shop_name));
        mMap.moveCamera(cUpdate);
    }
}
