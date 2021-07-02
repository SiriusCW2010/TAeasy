package com.example.taeasy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecordActivity5 extends AppCompatActivity {

    //For F6 Check Button
    ImageButton veh_check;
    EditText V1_Reg_No;

    //For QR Code scanner
    ImageButton qr_scan;

    //For V4 Check Button
    ImageButton valid4_check;
    EditText D1_driving_lic;

    //For OCR Scan Button
    ImageButton card_scan;

    //For Speech To Text Button
    ImageButton speech_text_1;
    EditText D1_Statement;

    //For TextWatcher
    EditText D1_name;
    TextView text_watch_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record5);


        //For F6 Check Button
        veh_check = findViewById(R.id.veh_check);
        V1_Reg_No = findViewById(R.id.V1_Reg_No);

        String V1_Reg = getIntent().getStringExtra("keyV1No");
        V1_Reg_No.setText(V1_Reg);

        //For QR Code scanner
        qr_scan = findViewById(R.id.qr_scan);
        qr_scan.setOnClickListener(v -> openQRCodeActivity());

        //For V4 Check Button
        valid4_check = findViewById(R.id.valid4_check);
        D1_driving_lic = findViewById(R.id.D1_driving_lic);

        //For OCR Scan
        card_scan = findViewById(R.id.card_scan);
        card_scan.setOnClickListener(v -> openOCRScanActivity());

        //For D1 Statement EditText get from STT function
        D1_Statement = findViewById(R.id.D1_statement);
        String Statement = getIntent().getStringExtra("keyStatement");
        D1_Statement.setText(Statement);

        //For STT Function Button
        speech_text_1 = findViewById(R.id.speech_text_1);
        speech_text_1.setOnClickListener(v -> openSpeechToTextActivity());

        //For TextWatcher
        text_watch_1 = (TextView) findViewById(R.id.text_watch_1);
        D1_name = (EditText) findViewById(R.id.D1_name);

        //Type of Veh 1
        Spinner spinner3 = (Spinner) findViewById(R.id.Veh_Type_1);
        final String[] Veh_Type_1 = {"P/C", "LGV", "MGV", "HGV", "PLB", "PB", "M/C"};
        ArrayAdapter<String> Veh_Type_1List = new ArrayAdapter<>(RecordActivity5.this,
                android.R.layout.simple_spinner_dropdown_item,
                Veh_Type_1);
        spinner3.setAdapter(Veh_Type_1List);

        //Police Station
        Spinner spinner4 = (Spinner) findViewById(R.id.Pol_Stn);
        final String[] Pol_Stn_1 = {"屯門", "青山", "元朗", "天水圍", "八鄉", "大埔", "上水", "落馬洲", "打鼓嶺", "沙頭角"};
        ArrayAdapter<String> Pol_Stn_1List = new ArrayAdapter<>(RecordActivity5.this,
                android.R.layout.simple_spinner_dropdown_item,
                Pol_Stn_1);
        spinner4.setAdapter(Pol_Stn_1List);

        //For TextWatcher
        D1_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = D1_name.getText().toString();
                text_watch_1.setText(name);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //For Validate the values of V1_Reg_No
        veh_check.setOnClickListener(v -> {
            if (V1_Reg_No.getText().toString().equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("留意!");
                builder.setMessage("請輸入車牌號碼");
                builder.setPositiveButton("重新輸入", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("CC4車輛查核");
                builder.setMessage("車輛無被通緝");
                builder.setPositiveButton("好", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        //For Validate the values of Valid4_check
        valid4_check.setOnClickListener(v -> {
            if (D1_driving_lic.getText().toString().equals("") || D1_name.getText().toString().equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("留意!");
                builder.setMessage("請輸入司機姓名及駕駛執照號碼");
                builder.setPositiveButton("重新輸入", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("VALID V 駕駛執照查核");
                builder.setMessage("司機無被停牌/手令");
                builder.setPositiveButton("好", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }


    //For Open QR Code Scanner
    public void openQRCodeActivity() {
        Intent intent = new Intent(this, QRCodeActivity.class);
        startActivity(intent);
    }

    //For Open OCR Scan
    public void openOCRScanActivity() {
        Intent intent = new Intent(this, OCRScanActivity.class);
        startActivity(intent);
    }

    //For Open STT Function
    public void openSpeechToTextActivity() {
        Intent intent = new Intent(this, SpeechToTextActivity.class);
        startActivity(intent);
    }



}
