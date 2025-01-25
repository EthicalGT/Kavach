package com.example.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class about_dev extends AppCompatActivity {
    Button instagram,snapchat,whatsapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_dev);
        instagram=findViewById(R.id.instagram);
        whatsapp=findViewById(R.id.whatsapp);
        snapchat=findViewById(R.id.snapchat);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.instagram.com/the_gt_official/?igsh=MW10azA5aTBkaTYxbA%3D%3D"));
                startActivity(i);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://api.whatsapp.com/qr/TTCPPLO4MHO3B1?autoload=1&app_absent=0"));
                startActivity(i);
            }
        });
        snapchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.snapchat.com/add/ganeshtelore04?share_id=rBIqcTujtXo&locale=en-US"));
                startActivity(i);
            }
        });
    }
}