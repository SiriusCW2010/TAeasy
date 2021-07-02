package com.example.taeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class RecordActivity4 extends AppCompatActivity {

    ImageView ImgView_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record4);

        //For Button Up 3
        Button buttonUp3 = findViewById(R.id.ButtonUp_3);
        buttonUp3.setOnClickListener(V -> openRecordActivity3());

        //For Button Next 4
        Button buttonNext4 = findViewById(R.id.ButtonNext_4);
        buttonNext4.setOnClickListener(V -> openRecordActivity5());

        //For Spinner
        Spinner spinner4 = (Spinner) findViewById(R.id.Veh_Cat_2);
        ImgView_2 = findViewById(R.id.ImgView_2);

        final String[] Veh_Cat_2 = {"請選擇車種", "私家車、的士等", "貨車、搬運車等", "專利巴士、私家巴士等", "電單車、單車等"};
        ArrayAdapter<String> Veh_Cat_2List = new ArrayAdapter<>(RecordActivity4.this,
                android.R.layout.simple_spinner_dropdown_item,
                Veh_Cat_2);
        spinner4.setAdapter(Veh_Cat_2List);

        //For Choosing Vehicle Type from Spinner
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        ImgView_2.setImageResource(R.drawable.car_outline);
                        break;
                    case 2:
                        ImgView_2.setImageResource(R.drawable.lorry_outline);
                        break;
                    case 3:
                        ImgView_2.setImageResource(R.drawable.bus_outline);
                        break;
                    case 4:
                        ImgView_2.setImageResource(R.drawable.motor_outline);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public void openRecordActivity3(){
        Intent intent = new Intent (this, RecordActivity3.class);
        startActivity(intent);
    }

    public void openRecordActivity5(){
        Intent intent = new Intent (this, RecordActivity5.class);
        startActivity(intent);
    }

}