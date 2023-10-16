package com.example.mindbooost;

import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

class aactivity_floresta extends AppCompatActivity {

    private Button playButton;
    private Button pauseButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zen);

        playButton = findViewById(R.id.btn_play);
        pauseButton = findViewById(R.id.btn_pause);

        mediaPlayer = MediaPlayer.create(this, R.raw.som_da_floresta);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}
