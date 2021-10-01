package com.example.taeasy;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class NSignaturePad extends AppCompatActivity {

    //Signature Pad
    Bitmap bitmap;
    Button clear, save;
    SignatureView signatureView;
    int path;
    private static final String Image_Directory="/signDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signature_pad);

        //For Signature Pad
        signatureView = findViewById(R.id.signature_pad);
        save = findViewById(R.id.Save_sign);
        clear = findViewById(R.id.Clear_sign);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clearCanvas();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap=signatureView.getSignatureBitmap();
                path=Integer.parseInt(saveImage(bitmap));

            }
        });

    }

    //For Signature Pad
    private String saveImage(Bitmap myBitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File FileDirectory = new File(Environment.getExternalStorageDirectory()+Image_Directory);
        //Have the Object Build the Directory structure if needed
        if (!FileDirectory.exists()){
            FileDirectory.mkdirs();
            Log.d("hhhh", FileDirectory.toString());
        }
        File f = new File(FileDirectory, Calendar.getInstance().getTimeInMillis()+".jpg");
        try {
            f.createNewFile();
            FileOutputStream fo=new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(NSignaturePad.this, new String[]{f.getPath()},new String[]{"image/jpeg"}, null);
            fo.close();
            f.getAbsolutePath();
            return f.getAbsolutePath();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

}