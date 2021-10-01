package com.example.taeasy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Locale;

public class RecordActivity extends AppCompatActivity implements LocationListener {
    ImageButton button_loc;
    TextInputEditText loc_text;
    LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        loc_text = findViewById(R.id.Loc_Text);
        button_loc = findViewById(R.id.Get_loc);

        //For Next Button-1
        Button buttonNext = findViewById(R.id.ButtonNext);
        buttonNext.setOnClickListener(V -> openRecordActivity5());

        //Spinner
        //Regional Traffic RN
        Spinner spinner = (Spinner) findViewById(R.id.RegT);
        final String[] RegT = {"T NTN", "T NTS", "T KW", "T KE", "T HKI"};
        ArrayAdapter<String> RegTList = new ArrayAdapter<>(RecordActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                RegT);
        spinner.setAdapter(RegTList);

        //Divisional RN
        Spinner spinner2 = (Spinner) findViewById(R.id.DIV);
        final String[] DIV = {"CPKDIV", "TMDIV", "YLDIV", "TSWDIV", "PHDIV", "SSDIV", "TPDIV", "LMCDIV", "TKLDIV", "STKDIV"};
        ArrayAdapter<String> DIVList = new ArrayAdapter<>(RecordActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                DIV);
        spinner2.setAdapter(DIVList);

        //Type of Veh 1
        Spinner spinner3 = (Spinner) findViewById(R.id.Veh_Type_1);
        final String[] Veh_Type_1 = {"P/C", "LGV", "MGV", "HGV", "PLB", "PB", "M/C"};
        ArrayAdapter<String> Veh_Type_1List = new ArrayAdapter<>(RecordActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                Veh_Type_1);
        spinner3.setAdapter(Veh_Type_1List);

        //Type of Veh 2
        Spinner spinner4 = (Spinner) findViewById(R.id.Veh_Type_2);
        final String[] Veh_Type_2 = {"P/C", "LGV", "MGV", "HGV", "PLB", "PB", "M/C"};
        ArrayAdapter<String> Veh_Type_2List = new ArrayAdapter<>(RecordActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                Veh_Type_2);
        spinner4.setAdapter(Veh_Type_2List);

        //Runtime Permissions
        if (ContextCompat.checkSelfPermission(RecordActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RecordActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION

            }, 100);

        }

        button_loc.setOnClickListener(v -> {
            //create method
            getLocation();
        });

    }

    //For Next Button-1
    public void openRecordActivity5(){
        Intent intent = new Intent (this, RecordActivity5.class);
        startActivity(intent);
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, RecordActivity.this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Show Long+Lat at the bottom
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "" + location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(RecordActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);

            loc_text.setText(address);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    //For Exit Without Save Message
       public void onBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("警 告!");
            builder.setMessage("確定不儲存就離開?");
            builder.setCancelable(false);
            builder.setPositiveButton("是", (dialog, which) -> finish());
            builder.setNegativeButton("否", (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

}
