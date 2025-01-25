package com.example.signup_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class notification extends AppCompatActivity {
    Button btn;
    EditText tb1;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btn=findViewById(R.id.btn);
        tb1=findViewById(R.id.tb1);
        alert=new AlertDialog.Builder(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb1.getText().toString().isEmpty()) {
                    alert.setTitle("Kavach Says!").setMessage("Kindly fill the field!!!").show();
                } else {
                    administratorDB ob = new administratorDB(notification.this);
                    boolean res = ob.notify(tb1.getText().toString());
                    if (res) {
                        alert.setTitle("Kavach Says!").setMessage("Alert Posted Successfully!").show();
                        createNotificationChannel();
                        showNotification();
                        tb1.setText("");
                    } else {
                        alert.setTitle("Kavach Says!").setMessage("Error Occured!!!").show();
                    }
                }
            }
        });
    }

    // Create a notification channel (required for Android 8.0 and above)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Notification Channel";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Create and show the notification
    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.kavach_logo)
                .setContentTitle("Kavach Alerts")
                .setContentText(tb1.getText())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    }
}
