package com.example.yuya0817.ReviveSeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;

public class JoinQrcode extends Activity {
    private CompoundBarcodeView BarcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_qrcode);

        BarcodeView = (CompoundBarcodeView)findViewById(R.id.barcodeView);
        BarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult barcodeResult) {

                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText(barcodeResult.getText());
                Intent intent = new Intent(JoinQrcode.this, JoinSharing.class);
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
