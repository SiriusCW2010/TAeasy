package com.example.taeasy;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class RecordActivity8 extends AppCompatActivity {

    //For STT Function
    ImageButton speech_text_1;
    EditText Officer_statement;
    private static final int SPEECH_INPUT=1;

    //For Next Button
    Button nextButton9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record8);

        //For Officer Statement
        Officer_statement = findViewById(R.id.Officer_statement);

        //For STT Function Button
        speech_text_1 = findViewById(R.id.speech_text_1);
        speech_text_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請慢慢講出內容");

                try {
                    startActivityForResult(intent, SPEECH_INPUT);
                } catch (Exception e) {
                    Toast.makeText(RecordActivity8.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //For Next Button
        nextButton9 = findViewById(R.id.ButtonNext_9);
        nextButton9.setOnClickListener(V -> openRecordActivity9());

    }

    //For STT Function
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== SPEECH_INPUT){
            if(resultCode== RESULT_OK && data !=null){
                ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                Officer_statement.setText(Objects.requireNonNull(result).get(0));
            }
        }

    }

    //For Open RecordActivity9
    public void openRecordActivity9() {
        Intent intent = new Intent(this, RecordActivity9.class);
        startActivity(intent);
    }
}