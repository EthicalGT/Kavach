package com.example.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class video_tutorials extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    Button btn1,btn2,btn3,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_tutorials);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), videoplayer.class);
                i.putExtra("path","2131755008");
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), videoplayer.class);
                i.putExtra("path","2131755009");
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), videoplayer.class);
                i.putExtra("path","2131755010");
                startActivity(i);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), videoplayer.class);
                i.putExtra("path","2131755011");
                startActivity(i);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), videoplayer.class);
                i.putExtra("path","2131755012");
                startActivity(i);
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