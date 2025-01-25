package com.example.signup_login;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {
    EditText tb1,tb2;
    Button btn;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        administratorDB ob = new administratorDB(admin_login.this);
        alert = new AlertDialog.Builder(this);
        tb1 = findViewById(R.id.tb1);
        tb2 = findViewById(R.id.tb2);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alert.setMessage(val1+val2).show();
                boolean namevalidate = tb1.getText().toString().matches("^[a-z]");
                boolean pwdvalidate = tb2.getText().toString().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)");
                if (!namevalidate && !pwdvalidate && !tb1.getText().toString().isEmpty() && !tb2.getText().toString().isEmpty()) {
                    try {
                        boolean res = ob.validateadmin(tb1.getText().toString(), tb2.getText().toString());
                        if (res) {
                            Log.d("Admin_Login", "Admin validated successfully");
                            Intent i = new Intent(getApplicationContext(), admin.class);
                            startActivity(i);
                        } else {
                            Log.d("Admin_Login", "Invalid credentials entered");
                            alert.setTitle("Kavach Says!").setMessage("You have entered Invalid Credentials GT!").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                        }
                    } catch (Exception e) {
                        Log.e("Admin_Login", "Exception occurred: " + e.getMessage());
                        e.printStackTrace();
                        Toast.makeText(admin_login.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    alert.setTitle("Kavach Says!").setMessage("Kindly fill all the fields!\npassword must contain 1 uppercase\none special character\none digit\nlength should be between 6 to 12 character!\nadmin name should contain text only!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
            }
        });
    }
}