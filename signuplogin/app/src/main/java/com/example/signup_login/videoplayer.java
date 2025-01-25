package com.example.signup_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class videoplayer extends AppCompatActivity {
    VideoView video;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        String fpath="android.resource://" + getPackageName() + "/" +getIntent().getStringExtra("path");
        alert=new AlertDialog.Builder(this);
        video = findViewById(R.id.video);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.self_defence_techniques_1; // Replace "your_video_file" with the name of your video file
        Uri uri = Uri.parse(fpath);
        video.setVideoURI(uri);
        //alert.setMessage(videoPath).show();

        // Create MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);

        // Set MediaController to VideoView
        video.setMediaController(mediaController);

        // Start playing the video
        video.start();
    }
}
