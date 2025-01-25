package com.example.signup_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class sos extends AppCompatActivity {
    ImageView sos;
    Button contacts,home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        sos = findViewById(R.id.mysos);
        home = findViewById(R.id.home);
        contacts = findViewById(R.id.contacts);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), homepage.class);
                startActivity(i);
            }
        });
        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Vibrator vb=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if(vb.hasVibrator()){
                        vb.vibrate(200);
                    }
                    registerUser ob = new registerUser(sos.this);
                    String userMobile = ob.getLoggedUserMobile();
                    List<String> relativeMobiles = ob.sendRelativeSMS(userMobile);
                    String user = ob.validateLoggedInUser();

                    // Check if permissions are granted
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED &&
                            checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                        // Get current location
                        Location currentLocation = getCurrentLocation();

                        // Convert location to URL
                        String locationURL = convertLocationToURL(currentLocation);

                        SmsManager sms = SmsManager.getDefault();
                        for (String relativeMobile : relativeMobiles) {
                            String message = "Urgent! Your friend/Relative " + user +
                                    " may be in danger. " +
                                    "Location: " + String.valueOf(locationURL);
                            Toast.makeText(getApplicationContext(), locationURL, Toast.LENGTH_LONG).show();
                            sms.sendTextMessage(relativeMobile, null, message, null, null);
                        }

                        // Start phone call from the UI thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent call = new Intent(Intent.ACTION_CALL);
                                call.setData(Uri.parse("tel:" + "9322840021"));
                                startActivity(call);
                            }
                        });

                        Toast.makeText(getApplicationContext(), "SOS Sent, Waiting for Response!", Toast.LENGTH_SHORT).show();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE}, 1);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + String.valueOf(e), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private Location getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                return location;
            } else {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                return location;
            }
        }
        return null;
    }

    private String convertLocationToURL(Location location) {
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String url = "https://www.google.com/maps?q=" + latitude + "," + longitude;
            return url;
        }
        return null;
    }

}
