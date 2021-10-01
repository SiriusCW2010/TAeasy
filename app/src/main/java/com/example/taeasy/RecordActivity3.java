package com.example.taeasy;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class RecordActivity3 extends AppCompatActivity {

    ImageView ImgView_1;
    ImageButton imageButton_cam;
    ImageView RedCross_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record3);

        //For Red Cross Function
        RedCross_1 = (ImageView) findViewById(R.id.Red_cross_1);

        //For Camera Image Button
        imageButton_cam = (ImageButton) findViewById(R.id.Camera_button);
        imageButton_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        //For Button Up 2
        Button buttonUp2 = findViewById(R.id.ButtonUp_2);
        buttonUp2.setOnClickListener(V -> openRecordActivity2());

        //For Button Next 3
        Button buttonNext3 = findViewById(R.id.ButtonNext_3);
        buttonNext3.setOnClickListener(V -> openRecordActivity4());

        //For Spinner
        Spinner spinner3 = (Spinner) findViewById(R.id.Veh_Cat_1);
        ImgView_1 = findViewById(R.id.ImgView_1);

        final String[] Veh_Cat_1 = {"請選擇車種", "私家車、的士等", "貨車、搬運車等", "專利巴士、私家巴士等", "電單車、單車等"};
        ArrayAdapter<String> Veh_Cat_1List = new ArrayAdapter<>(RecordActivity3.this,
                android.R.layout.simple_spinner_dropdown_item,
                Veh_Cat_1);
        spinner3.setAdapter(Veh_Cat_1List);

        //For Choosing Vehicle Type from Spinner
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        ImgView_1.setImageResource(R.drawable.car_outline);
                        break;
                    case 2:
                        ImgView_1.setImageResource(R.drawable.lorry_outline);
                        break;
                    case 3:
                        ImgView_1.setImageResource(R.drawable.bus_outline);
                        break;
                    case 4:
                        ImgView_1.setImageResource(R.drawable.motor_outline);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    //For Red Cross-1
    float x, y;
    float dx, dy;

    @Override
    public boolean onTouchEvent(MotionEvent event){

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            x = event.getX();
            y = event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE){
            dx = event.getX() - x;
            dy = event.getY() - y;

            //For Red Cross 1
            RedCross_1.setX(RedCross_1.getX() + dx);
            RedCross_1.setY(RedCross_1.getY() + dy);

            x = event.getX();
            y = event.getY();

        }

        return super.onTouchEvent(event);

    }

    //For Open RecordActivity2
    public void openRecordActivity2(){
        Intent intent = new Intent (this, RecordActivity2.class);
        startActivity(intent);
    }

    //For Open RecordActivity4
    public void openRecordActivity4(){
        Intent intent = new Intent (this, RecordActivity4.class);
        startActivity(intent);
    }

}