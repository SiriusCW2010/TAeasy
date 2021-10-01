package com.example.taeasy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QRCodeActivity extends AppCompatActivity {

    SurfaceView SurView_1;
    TextView TV_Reg;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    Button confirm_button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        SurView_1 = findViewById(R.id.SurView_1);
        TV_Reg = findViewById(R.id.TV_Reg);
        confirm_button1 = findViewById(R.id.confirm_button1);

        //Send Text to RecordActivity5 -> V1_Reg_No with Button
        confirm_button1.setOnClickListener(v -> {

            String V1_Reg = TV_Reg.getText().toString();

            Intent intent = new Intent(QRCodeActivity.this, RecordActivity5.class);
            intent.putExtra("keyV1No", V1_Reg);
            startActivity(intent);
        });

        //Send Text to RecordActivity5_2 -> V2_Reg_No with Button
        confirm_button1.setOnClickListener(v -> {

            String V2_Reg = TV_Reg.getText().toString();

            Intent intent = new Intent(QRCodeActivity.this, RecordActivity5_2.class);
            intent.putExtra("keyV2No", V2_Reg);
            startActivity(intent);
        });

        //Grant Permission for using Camera
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},1);
        }
        TV_Reg.setMovementMethod(ScrollingMovementMethod.getInstance());
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this,barcodeDetector)
                .setRequestedPreviewSize(300,300)
                .setAutoFocusEnabled(true).build();

        SurView_1.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)
                        !=PackageManager.PERMISSION_GRANTED)
                    return;
                try {
                    cameraSource.start(holder);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes=detections.getDetectedItems();
                if(qrCodes.size()!=0){
                    TV_Reg.post(() -> TV_Reg.setText(
                            qrCodes.valueAt(0).displayValue
                    ));

                }
            }
        });

    }

}