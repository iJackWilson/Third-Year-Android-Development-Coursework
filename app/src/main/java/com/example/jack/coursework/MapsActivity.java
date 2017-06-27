package com.example.jack.coursework;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private final static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private static boolean newInstance() {
        return false;
    }

    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        List<Address> addressList = null;
        Bundle bundle = getIntent().getExtras();
        //KEY1 is event name
        String VALUE_REC1 = bundle.getString("KEY1");
        //KEY4 is event address
        String VALUE_REC2 = bundle.getString("KEY4");

        //Toast test to show event name and location
        Toast.makeText(getApplicationContext(), VALUE_REC1 + " at " + VALUE_REC2, Toast.LENGTH_LONG).show();

        // This code isn't working, null object reference when starting activity
        Geocoder geocoder = new Geocoder(this);

        try {
            //Get the first result from the addressList array
            addressList = geocoder.getFromLocationName(VALUE_REC2, 1);
        } catch (IOException e) {
            e.printStackTrace();
            //Show error message and close map screen if input is bad
            Toast.makeText(getApplicationContext(), "Bad input", Toast.LENGTH_LONG).show();
            this.finish();
        }
            Address address = addressList.get(0);
            LatLng LatLng = new LatLng(address.getLatitude(), address.getLongitude());
            //Add marker with event name as title
            mMap.addMarker(new MarkerOptions().position(LatLng).title(VALUE_REC1));
            //Move camera to pin
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng, 12));


        //Below code with hardcoded co-ordinates works
        /*
        LatLng Dundee = new LatLng(56.46, -2.96);
        GoogleMap mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Dundee, 12));
        mMap.addMarker(new MarkerOptions().position(Dundee).title("Dundee"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        else {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } */
        /* ******************************************* */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                    else {
                        requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
}
