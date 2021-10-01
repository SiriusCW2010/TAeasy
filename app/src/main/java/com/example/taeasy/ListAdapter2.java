package com.example.taeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter2 extends ArrayAdapter<PhoneList> {

    public ListAdapter2(Context context, ArrayList<PhoneList> phoneListArrayList){

        super (context, R.layout.list_phone_item, phoneListArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        PhoneList phoneList = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_phone_item,parent,false);

        }

        ImageView imageView = convertView.findViewById(R.id.phone_icon);
        TextView number_owner = convertView.findViewById(R.id.number_owner);
        TextView phone_number = convertView.findViewById(R.id.phone_number);

        imageView.setImageResource(phoneList.imageId);
        number_owner.setText(phoneList.number_owner);
        phone_number.setText(phoneList.phone_number);

        return convertView;

    }
}
