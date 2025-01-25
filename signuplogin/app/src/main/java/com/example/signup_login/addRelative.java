package com.example.signup_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addRelative extends AppCompatActivity {
    Button addbtn,sos,contact,home;
    EditText tw1, tw2;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_relative);
        tw1 = findViewById(R.id.tw1);
        tw2 = findViewById(R.id.tw2);
        addbtn = findViewById(R.id.addContact);
        home = findViewById(R.id.home);
        sos = findViewById(R.id.sos);
        contact = findViewById(R.id.contact);
        alert = new AlertDialog.Builder(this);
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
                Intent i=new Intent(getApplicationContext(), sos.class);
                startActivity(i);
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean match=tw1.getText().toString().matches("[a-zA-Z\\s']+");
                if (tw1.getText().toString().isEmpty() || tw2.getText().toString().isEmpty()) {
                    alert.setTitle("Kavach Says!").setMessage("Kindly fill all the fields!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                } else if(!match) {
                    alert.setTitle("Kavach Says!").setMessage("Only alphabets allowed!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
                else if(tw2.getText().toString().length()!=10){
                    alert.setTitle("Kavach Says!").setMessage("Please enter 10 digit number!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }else{
                    registerUser ob=new registerUser(addRelative.this);
                    String mno=ob.getLoggedUserMobile();
                    //alert.setTitle("Mobile No").setMessage(mno).show();
                    boolean res=ob.addRelative(tw1.getText().toString(),tw2.getText().toString(),mno);
                    if(res){
                        Toast.makeText(getApplicationContext(),"Contact Added Successfully!",Toast.LENGTH_SHORT);
                    }
                }
            }
        });
    }
}