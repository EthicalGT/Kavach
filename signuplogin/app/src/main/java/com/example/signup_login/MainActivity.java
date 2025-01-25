package com.example.signup_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
TextView signup,signup2,tb1,tb2;
Button btn;
AlertDialog.Builder alert;
String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        tb1=findViewById(R.id.tb1);
        tb2=findViewById(R.id.tb2);
        btn=findViewById(R.id.button);
        signup2=findViewById(R.id.textView2);
        alert=new AlertDialog.Builder(this);
        ID=AndroidId();
        registerUser ob=new registerUser(MainActivity.this);
        boolean login=ob.validateLogin(ID);
        if(ob.CheckUserTable()){
            Intent in=new Intent(getApplicationContext(),signup.class);
            startActivity(in);
        }
        else if(login){
            Intent i=new Intent(getApplicationContext(), homepage.class);
            startActivity(i);
        }else {
            signup2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), signup.class);
                    startActivity(i);
                }

            });
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), signup.class);
                    startActivity(i);
                }

            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tb1.getText().toString().isEmpty() || tb2.getText().toString().isEmpty()) {
                        alert.setTitle("Kavach Says!").setMessage("Kindly fill all the fields!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    } else {
                        registerUser obj = new registerUser(MainActivity.this);
                        boolean res = obj.validateLogin(tb1.getText().toString(), tb2.getText().toString());
                        //SQLiteDatabase db = obj.getReadableDatabase();
                        if (res) {
                            Intent in = new Intent(getApplicationContext(), homepage.class);
                            startActivity(in);
                        } else {
                            Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            if (vb.hasVibrator()) {
                                vb.vibrate(100);
                            }
                            alert.setTitle("KAVACH Says!").setMessage("Invalid Credentials").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                        }
                    }
                }
            });
        }
    }
    public void init(){
        signup=findViewById(R.id.signup);
    }
    public String AndroidId() {
        try {
            String androidid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            return androidid;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}