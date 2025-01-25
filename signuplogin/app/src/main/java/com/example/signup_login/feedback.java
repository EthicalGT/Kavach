package com.example.signup_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class feedback extends AppCompatActivity {
    EditText tb1,tb2,tb3;
    Button btn;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        tb1=findViewById(R.id.tb1);
        tb2=findViewById(R.id.tb2);
        tb3=findViewById(R.id.tb3);
        btn=findViewById(R.id.btn);
        alert=new AlertDialog.Builder(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean match = tb1.getText().toString().matches("[a-zA-Z\\s']+");
                    if (tb1.getText().toString().isEmpty() || tb2.getText().toString().isEmpty() || tb3.toString().isEmpty()) {
                        alert.setTitle("Kavach Says!").setMessage("Kindly fill all the fields!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    } else if (!match) {
                        alert.setTitle("Kavach Says!").setMessage("Only alphabets allowed!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    } else if (tb2.getText().toString().length() != 10) {
                        alert.setTitle("Kavach Says!").setMessage("Please enter 10 digit number!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    } else if (tb3.getText().toString().length() <= 35) {
                        alert.setTitle("Kavach Says!").setMessage("Minimum 35 characters are required for sending feedback message!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    } else {
                        registerUser ob = new registerUser(feedback.this);
                        boolean res = ob.send_feedback(tb1.getText().toString(), tb2.getText().toString(), tb3.getText().toString());
                        if (res) {
                            Toast.makeText(getApplicationContext(), "Feedback Successfull!", Toast.LENGTH_SHORT).show();
                            tb1.setText("");
                            tb2.setText("");
                            tb3.setText("");
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error"+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}