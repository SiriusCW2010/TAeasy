package com.example.taeasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    ImageButton button;
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

        button = (ImageButton) findViewById(R.id.list_view_button);
        button.setOnClickListener(v -> openUploadListActivity());

        button = (ImageButton) findViewById(R.id.useful_number);
        button.setOnClickListener(v -> openPhoneListActivity());

        button = (ImageButton) findViewById(R.id.scene_photo);
        button.setOnClickListener(new View.OnClickListener() {
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

        //For App Version Update Info
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(HomeActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.updates_info_checkbox, null);
        CheckBox mCheckBox = mView.findViewById(R.id.CB_no_show);
        mBuilder.setTitle("版本 Prototype 4.8.0 更新資訊");
        mBuilder.setMessage("\n1) 版面次序更新 \n2) 新增拖放功能 \n3) 提升效能 \n4) 修正錯誤");
        mBuilder.setView(mView);
        mBuilder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    storeDialogStatus(true);
                }else{
                    storeDialogStatus(false);
                }
            }
        });

        if(getDialogStatus()) {
            mDialog.hide();
            }else{
            mDialog.show();
            }
        }

    public void openNeedToKnow(){
        Intent intent = new Intent(this, NeedToKnow.class);
        startActivity(intent);
    }
    public void openMapsActivity2(){
        Intent intent = new Intent(this, MapsActivity2.class);
        startActivity(intent);
    }
    public void openUploadListActivity() {
        Intent intent = new Intent(this, UploadListActivity.class);
        startActivity(intent);
    }
    public void openPhoneListActivity() {
        Intent intent = new Intent(this, PhoneListActivity.class);
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

    //For App Version Update Info
    private void storeDialogStatus(boolean isChecked){
        SharedPreferences mSharedPreferences = getSharedPreferences("CheckItem", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean("item", isChecked);
        mEditor.apply();
    }
    private boolean getDialogStatus(){
        SharedPreferences mSharedPreferences = getSharedPreferences("CheckItem", MODE_PRIVATE);
        return mSharedPreferences.getBoolean("item", false);
    }

}