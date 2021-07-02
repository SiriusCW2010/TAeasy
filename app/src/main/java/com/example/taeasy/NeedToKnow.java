package com.example.taeasy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NeedToKnow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_to_know);

        Button buttonNext = findViewById(R.id.ButtonNext);
        buttonNext.setOnClickListener(V -> openRecordActivity());
    }
    public void openRecordActivity(){
        Intent intent = new Intent (this, RecordActivity.class);
        startActivity(intent);
    }
}