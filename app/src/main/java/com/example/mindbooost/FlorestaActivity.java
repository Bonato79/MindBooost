package com.example.mindbooost;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

class FlorestaActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zen);

        Button playButton = findViewById(R.id.btn_play);
        Button pauseButton = findViewById(R.id.btn_pause);

        mediaPlayer = MediaPlayer.create(this, R.raw.som_da_floresta);

        playButton.setOnClickListener(view -> mediaPlayer.start());

        pauseButton.setOnClickListener(view -> mediaPlayer.pause());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}
