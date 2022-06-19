package edu.neu.madcourse.numad22su_kanishkasoni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity_Location extends AppCompatActivity implements LocationListener {
    private static final int REQUEST_LOCATION = 1;
    private TextView textViewLatitude;
    private TextView textViewLongitude;
    private LocationManager locationManager;
    private TextView textViewDistance;
    private int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        textViewLatitude = (TextView) findViewById(R.id.textView_latitude);
        textViewLongitude = (TextView) findViewById(R.id.textView_longitude);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        textViewDistance = findViewById(R.id.textView_Distance);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }


        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        onLocationChanged(location);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        float[] results = new float[1];
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        textViewLatitude.setText("Latitude : " + latitude);
        textViewLongitude.setText("Longitude: " + longitude);


        Location.distanceBetween(latitude, longitude, latitude, longitude, results);
        distance = 2;
        textViewDistance.setText("Distance : " + (distance+ results[0]));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    public void onClick_ResetDistance(View view) {
        distance = 0;
        textViewDistance.setText("Distance : " + 0);
    }
}