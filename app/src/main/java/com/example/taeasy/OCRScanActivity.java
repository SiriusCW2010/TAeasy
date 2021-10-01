package com.example.taeasy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class OCRScanActivity extends AppCompatActivity {

    //For OCR Get String
    SurfaceView SurView_2;
    EditText D1_name;
    EditText D1_DL;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;

    //For Get String to Activity_5
    Button confirm_button2;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(SurView_2.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocrscan);

        //For OCR Function
        SurView_2 = (SurfaceView) findViewById(R.id.SurView_2);
        D1_name = (EditText) findViewById(R.id.D1_name_OCR);
        D1_DL = (EditText) findViewById(R.id.D1_driving_lic);

        //For Confirm Button
        confirm_button2 = findViewById(R.id.confirm_button2);

        //Send Text to RecordActivity5 -> D1_Name with Button
        confirm_button2.setOnClickListener(v -> {

            String D1_name_OCR = D1_name.getText().toString();
            Intent intent = new Intent(OCRScanActivity.this, RecordActivity5.class);
            intent.putExtra("keyD1name", D1_name_OCR);
            startActivity(intent);
        });

        //Send Text to RecordActivity5 -> D1_DL with Button
        confirm_button2.setOnClickListener(v -> {

            String D1_driving_lic = D1_DL.getText().toString();
            Intent intent = new Intent(OCRScanActivity.this, RecordActivity5.class);
            intent.putExtra("keyD1DL", D1_driving_lic);
            startActivity(intent);
        });

        //For OCR Function
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()){
            Log.w("OCRScanActivity", "Detector dependencies are not yet available");
        }
        else{
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(450, 400)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            SurView_2.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(@NonNull SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(OCRScanActivity.this, new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                            return;
                        } cameraSource.start(SurView_2.getHolder());
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

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(@NonNull Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if(items.size() !=0){
                        D1_name.post(() -> {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i=0; i<items.size(); ++i){
                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                stringBuilder.append("\n");
                            }
                            D1_name.setText(stringBuilder.toString());
                        });
                    }
                }
            });
        }

    }
}