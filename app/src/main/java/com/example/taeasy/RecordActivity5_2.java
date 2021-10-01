package com.example.taeasy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class RecordActivity5_2 extends AppCompatActivity {

    //For F6 Check Button
    ImageButton veh_check;
    EditText V2_Reg_No;

    //For QR Code scanner
    ImageButton qr_scan;

    //For V4 Check Button
    ImageButton valid4_check;
    EditText D2_driving_lic;

    //For OCR Scan Button
    ImageButton card_scan;

    //For Speech To Text Button
    ImageButton speech_text_2;
    EditText D2_Statement;
    private static final int SPEECH_INPUT=1;

    //For TextWatcher
    EditText D2_name;
    TextView text_watch_2;

    //For Click to Signature
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record5_2);
        //For F6 Check Button
        veh_check = findViewById(R.id.veh_check);
        V2_Reg_No = findViewById(R.id.V2_Reg_No);

        String V2_Reg = getIntent().getStringExtra("keyV2No");
        V2_Reg_No.setText(V2_Reg);

        //For QR Code scanner
        qr_scan = findViewById(R.id.qr_scan);
        qr_scan.setOnClickListener(v -> openQRCodeActivity());

        //For V4 Check Button
        valid4_check = findViewById(R.id.valid4_check);
        D2_driving_lic = findViewById(R.id.D2_driving_lic);

        //For OCR Scan
        card_scan = findViewById(R.id.card_scan);
        card_scan.setOnClickListener(v -> openOCRScanActivity());

        //For D2 Statement EditText get from STT function
        D2_Statement = findViewById(R.id.D2_statement);

        //For STT Function Button
        speech_text_2 = findViewById(R.id.speech_text_2);
        speech_text_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請慢慢講出內容");

                try {
                    startActivityForResult(intent, SPEECH_INPUT);
                } catch (Exception e) {
                    Toast.makeText(RecordActivity5_2.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //For TextWatcher
        text_watch_2 = (TextView) findViewById(R.id.text_watch_2);
        D2_name = (EditText) findViewById(R.id.D2_name);

        //For Click to Signature
        imageView = findViewById(R.id.signature_1);
        imageView.setOnClickListener(v -> openSignaturePad());

        //For Next Button 5
        Button buttonNext6 = findViewById(R.id.ButtonNext_6);
        buttonNext6.setOnClickListener(V -> openRecordActivity2());

        //Type of Veh 1
        Spinner spinner3 = (Spinner) findViewById(R.id.Veh_Type_1);
        final String[] Veh_Type_1 = {"P/C", "LGV", "MGV", "HGV", "PLB", "PB", "M/C"};
        ArrayAdapter<String> Veh_Type_1List = new ArrayAdapter<>(RecordActivity5_2.this,
                android.R.layout.simple_spinner_dropdown_item,
                Veh_Type_1);
        spinner3.setAdapter(Veh_Type_1List);

        //Police Station
        Spinner spinner4 = (Spinner) findViewById(R.id.Pol_Stn);
        final String[] Pol_Stn_1 = {"屯門", "青山", "元朗", "天水圍", "八鄉", "大埔", "上水", "落馬洲", "打鼓嶺", "沙頭角"};
        ArrayAdapter<String> Pol_Stn_1List = new ArrayAdapter<>(RecordActivity5_2.this,
                android.R.layout.simple_spinner_dropdown_item,
                Pol_Stn_1);
        spinner4.setAdapter(Pol_Stn_1List);

        //For TextWatcher
        D2_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = D2_name.getText().toString();
                text_watch_2.setText(name);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //For Validate the values of V1_Reg_No
        veh_check.setOnClickListener(v -> {
            if (V2_Reg_No.getText().toString().equals("")) {
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
            if (D2_driving_lic.getText().toString().equals("") || D2_name.getText().toString().equals("")) {
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== SPEECH_INPUT){
            if(resultCode== RESULT_OK && data !=null){
                ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                D2_Statement.setText(Objects.requireNonNull(result).get(0));
            }
        }

    }

    //For Open Signature Pad
    public void openSignaturePad(){
        Intent intent = new Intent(this, NSignaturePad.class);
        startActivity(intent);
    }

    //For Open RecordActivity2
    public void openRecordActivity2() {
        Intent intent = new Intent(this, RecordActivity2.class);
        startActivity(intent);
    }

}