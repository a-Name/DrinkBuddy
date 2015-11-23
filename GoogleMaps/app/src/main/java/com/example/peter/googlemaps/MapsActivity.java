package com.example.peter.googlemaps;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.location.LocationManager;
import android.util.Log;

import org.w3c.dom.Comment;

import java.io.Console;
import java.text.ParseException;
import java.util.List;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setUpMapIfNeeded();

        Parse.initialize(this, "BQ3cRHeIT4rHnJimfSUkAkLSrvS5RByhoyrSLFb3", "aQtcCKiPlpG40ZDR7E09LkZmdCGlkEnlNYN53l7v");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("drinkBelem");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(48.8794511, 2.2991192))
                                .title("Hello world"));
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(48.8908561, 2.2407049))
                                .title("Hello world"));
                    }

                } else {
                    System.out.println("-----------------------------------------------");
                    Log.d("App", "Error: " + e.getMessage());
                }
            }
        });

    }

    @Override

    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);



        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Paris, 15));





    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            /*if (mMap != null) {
                setUpMap();
            }*/
        }
    }


}
