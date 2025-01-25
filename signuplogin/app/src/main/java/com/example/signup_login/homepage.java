package com.example.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class homepage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
Button contact,sos,audio,fire,ambulance,police,tutorials;
TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        contact=findViewById(R.id.contacts);
        user=findViewById(R.id.user);
        sos=findViewById(R.id.sos);
        audio=findViewById(R.id.audio);
        ambulance=findViewById(R.id.ambulance);
        fire=findViewById(R.id.fire);
        police=findViewById(R.id.police);
        tutorials=findViewById(R.id.tutorials);
        registerUser ob=new registerUser(homepage.this);
        String username=ob.validateLoggedInUser();
        user.setText("Hello,\n"+username);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),addRelative.class);
                startActivity(i);
            }
        });
        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),sos.class);
                startActivity(i);
            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + "108"));
                startActivity(call);
            }
        });
        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + "02226677555"));
                startActivity(call);
            }
        });
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),audio_recording.class);
                startActivity(i);
            }
        });
        tutorials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), video_tutorials.class);
                startActivity(i);
            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + "8010486885"));
                startActivity(call);
            }
        });
    }
    public void show_menu(View v){
        PopupMenu popm=new PopupMenu(this,v);
        popm.setOnMenuItemClickListener(this);
        popm.inflate(R.menu.navmenu);
        popm.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.m1) {
            Toast.makeText(this, "menu 1 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.m2) {
            Intent i=new Intent(getApplicationContext(), about_dev.class);
            startActivity(i);
            return true;
        } else if (itemId == R.id.m3) {
            Intent f=new Intent(getApplicationContext(), feedback.class);
            startActivity(f);
            return true;
        } else if (itemId == R.id.m4) {
            Intent i=new Intent(getApplicationContext(), admin_login.class);
            startActivity(i);
            return true;
        } else {
            return false;
        }
    }
}
