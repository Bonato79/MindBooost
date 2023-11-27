package com.example.mindbooost;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button diaryButton = findViewById(R.id.btnDiary);
        Button zenButton = findViewById(R.id.btnZen);
        Button consultaButton = findViewById(R.id.scheduleButton);

        diaryButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DiarioActivity.class)));

        zenButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ZenActivity.class)));

        consultaButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ConsultaActivity.class)));
    }
}
