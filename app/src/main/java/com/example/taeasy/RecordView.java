package com.example.taeasy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taeasy.databinding.ActivityRecordViewBinding;

public class RecordView extends AppCompatActivity {

    ActivityRecordViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){

            String location = intent.getStringExtra("location");
            String DIV_RN = intent.getStringExtra("DIV_RN");
            String Traffic_RN = intent.getStringExtra("Traffic_RN");
            int imageId = intent.getIntExtra("imageId", R.drawable.success_icon);
            int qrcodeId = intent.getIntExtra("qrcodeId", R.drawable.tsw21012345);

            binding.location.setText(location);
            binding.DIVRN.setText(DIV_RN);
            binding.TrafficRN.setText(Traffic_RN);
            binding.statusIcon.setImageResource(imageId);
            binding.QRDisplay.setImageResource(qrcodeId);

        }
    }
}