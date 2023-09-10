package com.example.mindbooost;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DiarioActivity extends AppCompatActivity {

    private EditText diaryEntry;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        diaryEntry = findViewById(R.id.edtDiaryEntry);
        saveButton = findViewById(R.id.btnSaveEntry);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implemente a lógica para salvar a entrada do diário aqui
            }
        });
    }
}
