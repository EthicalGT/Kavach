package com.example.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class audio_recording extends AppCompatActivity {
    ImageView mic;
    Button stop;
    MediaRecorder mr;
    String recordingFilePath;
    private static final String TAG = "AudioRecordingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recording);
        mic = findViewById(R.id.mic);
        stop = findViewById(R.id.stop);

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });
    }

    private void startRecording() {
        try {
            if (mr == null) {
                mr = new MediaRecorder();
                mr.setAudioSource(MediaRecorder.AudioSource.MIC);
                mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mr.setMaxDuration(30000);

                File directory = new File(getFilesDir(), "Kavach");
                if (!directory.exists()) {
                    boolean directoryCreated = directory.mkdirs();
                    Log.d(TAG, "Kavach directory created: " + directoryCreated);
                    if (!directoryCreated) {
                        Log.e(TAG, "Failed to create Kavach directory");
                        Toast.makeText(getApplicationContext(), "Failed to create Kavach directory", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                String timeStamp = String.valueOf(System.currentTimeMillis());
                recordingFilePath = directory.getAbsolutePath() + "/recording_" + timeStamp + ".3gp";
                Log.d(TAG, "Recording file path: " + recordingFilePath);
                mr.setOutputFile(recordingFilePath);
                mr.prepare();
                mr.start();
                Toast.makeText(getApplicationContext(), "Recording Started", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), String.valueOf(e), Toast.LENGTH_LONG).show();
        }
    }

    private void stopRecording() {
        if (mr != null) {
            mr.stop();
            mr.release();
            mr = null;
            Toast.makeText(getApplicationContext(), "Recording stopped.\nFile saved at: " + recordingFilePath, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mr != null) {
            mr.release();
            mr = null;
        }
    }
}
