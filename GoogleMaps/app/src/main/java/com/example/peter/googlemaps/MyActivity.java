package com.example.peter.googlemaps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.*;
import com.parse.SaveCallback;

import java.text.ParseException;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    public String name;
    public String number;
    public double latitude;
    public double longitude;
    public String parseObjectId;
    public ParseObject userProfile;
    boolean gps_enabled=false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "BQ3cRHeIT4rHnJimfSUkAkLSrvS5RByhoyrSLFb3", "aQtcCKiPlpG40ZDR7E09LkZmdCGlkEnlNYN53l7v");
        ParseInstallation.getCurrentInstallation().saveInBackground();

                    final EditText editText = (EditText) findViewById(R.id.editText);
                    final EditText numberField = (EditText) findViewById(R.id.numberField);
                    final Button button = (Button) findViewById(R.id.button);
                    final Switch sw1 = (Switch) findViewById(R.id.switch1);
                    button.setEnabled(false);
                    sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                       @Override
                                                       public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                           if (sw1.isChecked()) {
                                                               name = String.valueOf(editText.getText());
                                                               number = String.valueOf(numberField.getText());

                                                               LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                                                               try {
                                                                   gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                                                               } catch (Exception ex) {
                                                               }


                                                               if (gps_enabled) {
                                                                   Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                                               }


                                                               final LocationListener locationListener = new LocationListener() {
                                                                   public void onLocationChanged(Location location) {
                                                                       latitude = location.getLatitude();
                                                                       longitude = location.getLongitude();
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
                                                               };


                                                               button.setEnabled(true);
                                                               final ParseObject testObject = new ParseObject("drinkBelem");
                                                               testObject.put("name", name);
                                                               testObject.put("phone", number);
                                                               testObject.put("Latitude", String.valueOf(latitude));
                                                               testObject.put("Longitude", String.valueOf(longitude));
                                                               testObject.saveInBackground(new

                                                                                                   SaveCallback() {
                                                                                                       @Override
                                                                                                       public void done(com.parse.ParseException e) {

                                                                                                       }

                                                                                                       public void done(ParseException e) {
                                                                                                           if (e == null) {
                                                                                                               parseObjectId = testObject.getObjectId();
                                                                                                               userProfile = testObject;
                                                                                                           } else {
                                                                                                           }
                                                                                                       }
                                                                                                   });
                                                           } else {
                                                               button.setEnabled(false);
                                                               userProfile.deleteInBackground();
                                                               userProfile = null;
                                                           }
                                                       }
                                                   }

                    );
                    button.setOnClickListener(new View.OnClickListener()

                    {
                        public void onClick(View v) {
                            name = String.valueOf(editText.getText());
                            Intent intent = new Intent(MyActivity.this, PlacesActivity.class);
                            intent.putExtra("name", name);
                            startActivity(intent);
                        }
                    });
                }
            }