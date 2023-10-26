package com.example.mindbooost;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DiarioActivity extends AppCompatActivity {

    private DiaryDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        dbHelper = new DiaryDbHelper(this);

        EditText diaryEntryEditText = findViewById(R.id.edtDiaryEntry);
        Button saveButton = findViewById(R.id.btnSaveEntry);

        // Use um OnClickListener externo
        saveButton.setOnClickListener(view -> {
            String entryText = diaryEntryEditText.getText().toString().trim();

            if (!entryText.isEmpty()) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DiaryDbHelper.COLUMN_ENTRY, entryText);

                long newRowId = db.insert(DiaryDbHelper.TABLE_DIARY_ENTRIES, null, values);

                if (newRowId != -1) {
                    // A inserção foi bem-sucedida
                    diaryEntryEditText.setText(""); // Limpa o campo de texto
                }

                db.close();
            }
        });
    }
}
