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
        audioSelection = findViewById(R.id.audio1);

        mediaPlayer = new MediaPlayer();

        // Defina o áudio inicial (você pode definir a seleção padrão como desejar)
        mediaPlayer = MediaPlayer.create(this, R.raw.som_de_fogueira);
        audioSelection.check(R.id.audio1);

        // Defina o áudio inicial (você pode definir a seleção padrão como desejar)
        mediaPlayer = MediaPlayer.create(this, R.raw.som_de_cachoera);
        audioSelection.check(R.id.audio2);

        // Defina o áudio inicial (você pode definir a seleção padrão como desejar)
        mediaPlayer = MediaPlayer.create(this, R.raw.som_da_floresta);
        audioSelection.check(R.id.audio3);

        // Defina o áudio inicial (você pode definir a seleção padrão como desejar)
        mediaPlayer = MediaPlayer.create(this, R.raw.som_praia);
        audioSelection.check(R.id.audio4);

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
                } else if (selectedAudioId == R.id.audio3) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.som_de_cachoera);
                } else if (selectedAudioId == R.id.audio4) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.som_da_floresta);
                }

                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
            }
            assert mediaPlayer != null;
            mediaPlayer.start();
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
        }
    }
}
