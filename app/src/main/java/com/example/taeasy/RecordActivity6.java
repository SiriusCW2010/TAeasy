package com.example.taeasy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RecordActivity6 extends AppCompatActivity {

    //For Open OCR Scan
    ImageButton card_scan_V1P1;
    ImageButton card_scan_V1P2;
    ImageButton card_scan_V1P3;
    ImageButton card_scan_V2P1;
    ImageButton card_scan_V2P2;
    ImageButton card_scan_V2P3;


    //For Next Button
    Button nextButton7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record6);

        //For Next Button
        nextButton7 = findViewById(R.id.ButtonNext_7);
        nextButton7.setOnClickListener(V -> openRecordActivity7());

        //For OCR Scan_1
        card_scan_V1P1 = findViewById(R.id.card_scan_V1P1);
        card_scan_V1P2 = findViewById(R.id.card_scan_V1P2);
        card_scan_V1P3 = findViewById(R.id.card_scan_V1P3);
        card_scan_V2P1 = findViewById(R.id.card_scan_V2P1);
        card_scan_V2P2 = findViewById(R.id.card_scan_V2P2);
        card_scan_V2P3 = findViewById(R.id.card_scan_V2P3);
        card_scan_V1P1.setOnClickListener(v -> openOCRScanActivity());
        card_scan_V1P2.setOnClickListener(v -> openOCRScanActivity());
        card_scan_V1P3.setOnClickListener(v -> openOCRScanActivity());
        card_scan_V2P1.setOnClickListener(v -> openOCRScanActivity());
        card_scan_V2P2.setOnClickListener(v -> openOCRScanActivity());
        card_scan_V2P3.setOnClickListener(v -> openOCRScanActivity());
    }

    //For Open RecordActivity7
    public void openRecordActivity7() {
        Intent intent = new Intent(this, RecordActivity7.class);
        startActivity(intent);
    }

    //For Open OCR Scan
    public void openOCRScanActivity() {
        Intent intent = new Intent(this, OCRScanActivity.class);
        startActivity(intent);
    }
}