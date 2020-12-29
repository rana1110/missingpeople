package com.ohm.missingpeople.activity.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ohm.missingpeople.R;
import com.ohm.missingpeople.utils.BaseActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentLocation extends BaseActivity {
    TextView tv;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_location_layout);
        tv = (TextView) findViewById(R.id.tv1);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        checkPermission();
    }

    public void checkPermission() {
        if (ActivityCompat.checkSelfPermission(CurrentLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();

        } else {
            ActivityCompat.requestPermissions(CurrentLocation.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            checkPermission();
        }
    }


    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(CurrentLocation.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1);

                        tv.setText("Latitude - " + addresses.get(0).getLatitude() + "\n\n"
                                + "Logitude - " + addresses.get(0).getLongitude() + "\n\n"

                                + "AddressLine1 - " + addresses.get(0).getAddressLine(0) + "\n\n"
                                + "Sub Through Fare - " + addresses.get(0).getSubThoroughfare() + "\n\n"
                                + "Feature Name - " + addresses.get(0).getFeatureName() + "\n\n"
                                + "Postal Code - " + addresses.get(0).getPostalCode() + "\n\n"
                                + "Locality - " + addresses.get(0).getLocality() + "\n\n"
                                + "Sub Admin Area - " + addresses.get(0).getSubAdminArea() + "\n\n"
                                + "Admin Area - " + addresses.get(0).getAdminArea() + "\n\n"
                                + "Country Name - " + addresses.get(0).getCountryName() + "\n\n"
                                + "Country Code - " + addresses.get(0).getCountryCode() + "\n\n\n\n\n\n\n"


                                + "Phone - " + addresses.get(0).getPhone() + "\n\n"
                                + "AdressLine2 - " + addresses.get(0).getAddressLine(1) + "\n\n"
                                + "Premises - " + addresses.get(0).getPremises() + "\n\n"
                                + "Sub Locality - " + addresses.get(0).getSubLocality() + "\n\n"
                                + "URL - " + addresses.get(0).getUrl() + "\n\n"


                        );

                    } catch (IOException e) {

                    }
                }
            }
        });
    }
}
