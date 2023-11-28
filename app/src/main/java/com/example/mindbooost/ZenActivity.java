package com.example.mindbooost;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ZenActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private RadioGroup audioSelection;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zen);

        Button playButton = findViewById(R.id.btn_play);
        Button pauseButton = findViewById(R.id.btn_pause);
        audioSelection = findViewById(R.id.radioGroup);

        mediaPlayer = new MediaPlayer();

        // Define o áudio inicial
        mediaPlayer = MediaPlayer.create(this, R.raw.som_de_fogueira);
        audioSelection.check(R.id.audio1);

        playButton.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = new MediaPlayer();

                int selectedAudioId = audioSelection.getCheckedRadioButtonId();
                if (selectedAudioId == R.id.audio1) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.som_praia);
                } else if (selectedAudioId == R.id.audio2) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.som_de_fogueira);
                }

                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
            } else {
                assert mediaPlayer != null;
                mediaPlayer.start();
            }
        });

        pauseButton.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release(); // Libera os recursos do MediaPlayer
        }
    }
}
