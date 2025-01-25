package com.example.signup_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.*;

public class signup extends AppCompatActivity {
    AlertDialog.Builder alert;
    Button signup;
    EditText name, mobileno, email, pwd, pwd2;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        mobileno = findViewById(R.id.mobileno);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        pwd2 = findViewById(R.id.pass);
        signup = findViewById(R.id.btn);
        alert = new AlertDialog.Builder(this);
        Id=AndroidId();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean namevalidate = name.getText().toString().matches("[a-zA-Z\\s']+");
                boolean emailvalidate = email.getText().toString().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                boolean mobilevalidate = mobileno.getText().toString().length() == 10;
                boolean pwdvalidate = pwd.getText().toString().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$");
                boolean pwdmatch = pwd.getText().toString().equals(pwd2.getText().toString());
                if (name.getText().toString().isEmpty() || mobileno.getText().toString().isEmpty() || email.getText().toString().isEmpty() || pwd.getText().toString().isEmpty() || pwd2.getText().toString().isEmpty()) {
                    alert.setTitle("Kavach Says!").setMessage("\nKindly Fill all the Fields...").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                } else if (!namevalidate) {
                    alert.setTitle("Kavach Says!").setMessage("\nOnly Characters Are Allowed!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                } else if (!emailvalidate) {
                    alert.setTitle("Kavach Says!").setMessage("\nKindly Enter Valid Email Address!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                } else if (!mobilevalidate) {
                    alert.setTitle("Kavach Says!").setMessage("\nKindly Enter 10 Digit Mobile Number!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                } else if (!pwdvalidate) {
                    alert.setTitle("Kavach Says!").setMessage("\nYour password should consist of at least one lowercase letter.\n" +
                            "one uppercase letter.\n" +
                            "one digit.\n" +
                            "one special character\n" +
                            "and should have a length between 8 and 12 characters.!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                } else if (!pwdmatch) {
                    alert.setTitle("Kavach Says!").setMessage("\nBoth password fields data are not matching check again!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                } else {
                    registerUser ob = new registerUser(signup.this);
                    boolean isInserted = ob.addData(
                            name.getText().toString(),
                            mobileno.getText().toString(),
                            email.getText().toString(),
                            pwd.getText().toString(),
                            Id
                    );
                    if (isInserted) {
                            sendSMS();
                        //Toast.makeText(signup.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(signup.this, MainActivity.class);
                        startActivity(i);
                    }
                }
            }
        });

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
    public void sendSMS() {
        SmsManager sms = SmsManager.getDefault();
        String phone = mobileno.getText().toString();
        String msg = "Hello Dear "+name.getText()+" your account created!";
        try {
            sms.sendTextMessage(phone, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), String.valueOf(e), Toast.LENGTH_LONG).show();
        }
    }
}

