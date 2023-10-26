package com.example.mindbooost;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {
    private Spinner doctorSpinner;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        doctorSpinner = findViewById(R.id.doctorSpinner);
        datePicker = findViewById(R.id.datePicker);
        Button scheduleButton = findViewById(R.id.scheduleButton);

        // Simulando uma lista de médicos
        List<String> doctors = new ArrayList<>();
        doctors.add("Dr. Smith");
        doctors.add("Dr. Johnson");
        doctors.add("Dr. Davis");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doctors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doctorSpinner.setAdapter(adapter);

        scheduleButton.setOnClickListener(view -> {
            String selectedDoctor = doctorSpinner.getSelectedItem().toString();
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();

            String selectedDate = day + "/" + month + "/" + year;

            // Agora você pode implementar a lógica para agendar a consulta,
            // talvez usando uma API de videoconferência como o Zoom.
            // Neste exemplo, apenas exibimos uma mensagem.

            String message = "Consulta agendada com " + selectedDoctor + " para " + selectedDate;
            Toast.makeText(ConsultaActivity.this, message, Toast.LENGTH_LONG).show();
        });


    }
}
