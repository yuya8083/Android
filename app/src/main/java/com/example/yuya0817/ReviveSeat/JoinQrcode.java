package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.net.URISyntaxException;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class JoinQrcode extends Activity {
    private CompoundBarcodeView BarcodeView;
    private Socket socket;
    Intent intent,data;
    int i,flag,shareid;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_qrcode);

        data = getIntent();
        shareid = data.getIntExtra("shareid", 0);
        name = data.getStringExtra("name");

        try {
            socket = IO.socket("https://reviveseatserver.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        BarcodeView = (CompoundBarcodeView)findViewById(R.id.barcodeView);
        BarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult barcodeResult) {

//                TextView textView = (TextView)findViewById(R.id.textView);
//                textView.setText(barcodeResult.getText());
                i = 1;
                flag = 0;
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        System.out.println(i);
                        socket.emit("gcheck", i);
                        socket.disconnect();
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        Log.d("4","4");
                        flag = 1;
                    }

                });
                socket.connect();
                do {
                    intent = new Intent(JoinQrcode.this, JoinSharing.class);
                }while (flag == 0);
                intent.putExtra("shareid", shareid);
                intent.putExtra("name", name);
                startActivity(intent);
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> list) {}
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        BarcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        BarcodeView.pause();
    }
}
