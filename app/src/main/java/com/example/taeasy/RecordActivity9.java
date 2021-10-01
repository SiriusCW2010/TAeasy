package com.example.taeasy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class RecordActivity9 extends AppCompatActivity {

    Button uploadButton;
    Dialog doneDialog;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record9);

        //Set TextView Scrollable
        textView = findViewById(R.id.textview_review);
        textView.setMovementMethod(new ScrollingMovementMethod());

        //Show Custom Dialog
        doneDialog = new Dialog (RecordActivity9.this);
        doneDialog.setContentView(R.layout.upload_done);
        doneDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        doneDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        doneDialog.setCancelable(false);
        doneDialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button successButton = doneDialog.findViewById(R.id.success_button);
        successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
                doneDialog.dismiss();
                moveTaskToBack(false);
            }
        });

        uploadButton = findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doneDialog.show();
            }
        });

    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}