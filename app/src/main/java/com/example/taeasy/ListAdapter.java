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

public class ListAdapter extends ArrayAdapter<INList> {

    public ListAdapter(Context context, ArrayList<INList> inListArrayList){

        super (context, R.layout.list_view_item, inListArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        INList inList = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item,parent,false);

        }

        ImageView imageView = convertView.findViewById(R.id.status_icon);
        TextView location = convertView.findViewById(R.id.location);
        TextView DIV_RN = convertView.findViewById(R.id.DIV_RN);
        TextView Traffic_RN = convertView.findViewById(R.id.Traffic_RN);
        TextView uploadTime = convertView.findViewById(R.id.uploadTime);

        imageView.setImageResource(inList.imageId);
        location.setText(inList.location);
        DIV_RN.setText(inList.DIV_RN);
        Traffic_RN.setText(inList.Traffic_RN);
        uploadTime.setText(inList.uploadTime);

        return convertView;

    }
}
