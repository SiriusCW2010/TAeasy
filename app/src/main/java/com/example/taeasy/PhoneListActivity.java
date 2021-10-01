package com.example.taeasy;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taeasy.databinding.ActivityPhoneListBinding;

import java.util.ArrayList;

public class PhoneListActivity extends AppCompatActivity {

    ActivityPhoneListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //For ListView Display Item
        int[] imageId = {R.drawable.n_amber, R.drawable.s_blue, R.drawable.w_red, R.drawable.echo, R.drawable.h_brown,
                         R.drawable.emsd_logo, R.drawable.emsd_logo, R.drawable.emsd_logo, R.drawable.emsd_logo,
                         R.drawable.tow_truck, R.drawable.tow_truck};
        String[] number_owner = {"新界北總區交通部", "新界南總區交通部", "西九龍總區交通部", "東九龍總區交通部", "港島總區交通部",
                                 "機電工程署-屯門","機電工程署-芬園","機電工程署-九龍灣","機電工程署-香港",
                                 "恆輝拖車", "人字拖"};
        String[] phone_number = {"3661 3800", "3661 1700", "3661 9000", "3661 0205", "3661 6800",
                                 "2441 2417","2670 4033","3155 4166","3113 0401",
                                 "2668 2999", "9027 2295"};

        ArrayList<PhoneList> phoneListArrayList = new ArrayList<>();

        for (int i = 0; i < imageId.length; i++) {

            PhoneList phoneList = new PhoneList(number_owner[i], phone_number[i], imageId[i]);
            phoneListArrayList.add(phoneList);

        }

        ListAdapter2 listAdapter2 = new ListAdapter2(PhoneListActivity.this, phoneListArrayList);

        binding.phoneList.setAdapter(listAdapter2);
        binding.phoneList.setClickable(true);
        binding.phoneList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

}