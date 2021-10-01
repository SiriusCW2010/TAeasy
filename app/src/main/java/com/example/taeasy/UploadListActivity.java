package com.example.taeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taeasy.databinding.ActivityUploadListBinding;

import java.util.ArrayList;

public class UploadListActivity extends AppCompatActivity {

    ActivityUploadListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUploadListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //For ListView Display Item
        int[] imageId = {R.drawable.success_icon, R.drawable.success_icon, R.drawable.success_icon,
                         R.drawable.success_icon, R.drawable.success_icon};
        String[] location = {"Lamppost EA9373, Tin Tan Street, TSWDIV","Ping Ha Road J/O Tin Fuk Road, TSWDIV",
                             "Yuen Long Hong Lok Road J/O Hong King Street, YLDIV","Lamppost FB1055, Shap Pat Heung Interchange, YLDIV",
                             "Lamppost FB5697, Kam Tin Road, PHDIV"};
        String[] DIV_RN = {"TSWDIV 21012345","TSWDIV 21012349","YLDIV 21013579", "YLDIV 21013467",
                           "PHDIV 21001234"};
        String[] Traffic_RN = {"T NTN 21010100","T NTN 21010111","T NTN 21010123","T NTN 21010135",
                               "T NTN 21010189"};
        String[] uploadTime = {"16:03","17:18","18:23","19:40","21:23"};
        int[] qrcodeId = {R.drawable.tsw21012345, R.drawable.tsw21012349, R.drawable.yl21013579,
                          R.drawable.yl21013467, R.drawable.ph21001234};

        ArrayList<INList> inListArrayList = new ArrayList<>();

        for (int i=0; i<imageId.length;i++){

            INList inList = new INList(location[i], DIV_RN[i], Traffic_RN[i], uploadTime[i], imageId[i], qrcodeId[i]);
            inListArrayList.add(inList);

        }

        ListAdapter listAdapter = new ListAdapter(UploadListActivity.this,inListArrayList);

        binding.uploadedList.setAdapter(listAdapter);
        binding.uploadedList.setClickable(true);
        binding.uploadedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(UploadListActivity.this, RecordView.class);
                intent.putExtra("location",location[position]);
                intent.putExtra("DIV_RN", DIV_RN[position]);
                intent.putExtra("Traffic_RN", Traffic_RN[position]);
                intent.putExtra("imageId", imageId[position]);
                intent.putExtra("qrcodeId", qrcodeId[position]);
                startActivity(intent);

            }
        });

        }
        
}