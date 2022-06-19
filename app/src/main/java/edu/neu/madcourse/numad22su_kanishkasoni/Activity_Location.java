package edu.neu.madcourse.numad22su_kanishkasoni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Activity_Location extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    private TextView textViewLatitude;
    private TextView textViewLongitude;
    private LocationRequest locationRequest;
    private TextView textViewDistance;
    private double distance;
    private double startLatitude;
    private double startLongitude;
    private double endLongitude;
    private double endLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        textViewLatitude = (TextView) findViewById(R.id.textView_latitude);
        textViewLongitude = (TextView) findViewById(R.id.textView_longitude);

        textViewDistance = findViewById(R.id.textView_Distance);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        getCurrentLocation();

    }


    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }


        if (ActivityCompat.checkSelfPermission(Activity_Location.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (isGPSEnabled()) {


                LocationServices.getFusedLocationProviderClient(Activity_Location.this)
                        .requestLocationUpdates(locationRequest, new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                super.onLocationResult(locationResult);

                                LocationServices.getFusedLocationProviderClient(Activity_Location.this);


                                if (locationResult.getLocations().size() > 0) {

                                    int index = locationResult.getLocations().size() - 1;
                                    locationResult.getLocations().get(index).getAccuracy();
                                    if (startLatitude == 0.0 && startLongitude == 0.0) {
                                        startLatitude = locationResult.getLocations().get(index).getLatitude();
                                        startLongitude = locationResult.getLocations().get(index).getLongitude();
                                        endLatitude = startLatitude;
                                        endLongitude = startLongitude;
                                        float[] results = new float[1];

                                        textViewLatitude.setText("Latitude : " + endLatitude);
                                        textViewLongitude.setText("Longitude: " + endLongitude);

                                        Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results);


                                        distance = distance + results[0];


                                        textViewDistance.setText("Distance : " + distance);

                                    } else {
                                        startLongitude = endLongitude;
                                        startLatitude = endLatitude;
                                        endLatitude = locationResult.getLocations().get(index).getLatitude();
                                        endLongitude = locationResult.getLocations().get(index).getLongitude();
                                        float[] results = new float[1];

                                        textViewLatitude.setText("Latitude : " + endLatitude);
                                        textViewLongitude.setText("Longitude: " + endLongitude);

                                        Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results);
                                        distance = distance + results[0];


                                        textViewDistance.setText("Distance : " + distance);

                                    }

                                }
                            }
                        }, Looper.getMainLooper());

            } else {
                turnOnGPS();
            }

        }
    }


    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }


    public void onClick_ResetDistance(View view) {
        distance = 0.0;
        startLatitude = endLatitude;
        startLongitude = endLongitude;
        textViewDistance.setText("Distance : " + 0.0);
    }

    private void turnOnGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(Activity_Location.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(Activity_Location.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            }
        });

    }

    public void onClick_Precise(View view) {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        getCurrentLocation();

    }

    public void onClick_Approx(View view) {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        getCurrentLocation();

    }

}