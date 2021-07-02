package com.example.taeasy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private ImageButton button;
    private long backPressedTime;

    //For Image Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (ImageButton) findViewById(R.id.e284);
        button.setOnClickListener(v -> openNeedToKnow());

        button = (ImageButton) findViewById(R.id.location);
        button.setOnClickListener(v -> openMapsActivity2());
    }
    public void openNeedToKnow(){
        Intent intent = new Intent(this, NeedToKnow.class);
        startActivity(intent);
    }
    public void openMapsActivity2(){
        Intent intent = new Intent(this, MapsActivity2.class);
        startActivity(intent);
    }

    //For Bottom Exit Message
    @Override
    public void onBackPressed () {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "再按一次即離開", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}