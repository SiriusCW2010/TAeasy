package com.example.taeasy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RecordActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record2);

        //For Next Button 2
        Button buttonNext2 = findViewById(R.id.ButtonNext_2);
        buttonNext2.setOnClickListener(V -> openRecordActivity3());

        //For Up Button 1
        Button buttonUp1 = findViewById(R.id.ButtonUp_1);
        buttonUp1.setOnClickListener(V -> openRecordActivity());
    }
    public void openRecordActivity3(){
        Intent intent = new Intent (this, RecordActivity3.class);
        startActivity(intent);
    }
    public void openRecordActivity(){
        Intent intent = new Intent (this, RecordActivity.class);
        startActivity(intent);
    }
}