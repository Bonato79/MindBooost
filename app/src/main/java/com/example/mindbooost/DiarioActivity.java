package com.example.mindbooost;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DiarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        findViewById(R.id.edtDiaryEntry);
        Button saveButton = findViewById(R.id.btnSaveEntry);

        saveButton.setOnClickListener(view -> {
            // Implemente a lógica para salvar a entrada do diário aqui
        });
    }
}
