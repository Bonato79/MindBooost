package com.example.mindbooost;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {
    private Spinner doctorSpinner;
    private DatePicker datePicker;
    private ImageView doctorImage;
    private TextView doctorDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        doctorSpinner = findViewById(R.id.doctorSpinner);
        datePicker = findViewById(R.id.datePicker);
        Button scheduleButton = findViewById(R.id.scheduleButton);
        doctorImage = findViewById(R.id.doctorImage);
        doctorDescription = findViewById(R.id.doctorDescription);

        // Simulando uma lista de médicos com imagens e descrições
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Smith", R.drawable.img_medico, "Descrição do Dr. Smith"));
        doctors.add(new Doctor("Dr. Johnson", R.drawable.img_medico, "Descrição do Dr. Johnson"));
        doctors.add(new Doctor("Dr. Davis", R.drawable.img_medico, "Descrição do Dr. Davis"));

        ArrayAdapter<Doctor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doctors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doctorSpinner.setAdapter(adapter);

        scheduleButton.setOnClickListener(view -> {
            Doctor selectedDoctor = (Doctor) doctorSpinner.getSelectedItem();
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();

            String selectedDate = day + "/" + month + "/" + year;

            // Agora você pode implementar a lógica para agendar a consulta
            // e fornecer um link para a videochamada.
            // Neste exemplo, apenas exibimos uma mensagem.

            String message = "Consulta agendada com " + selectedDoctor.getName() +
                    " para " + selectedDate + "\nDescrição: " + selectedDoctor.getDescription();
            Toast.makeText(ConsultaActivity.this, message, Toast.LENGTH_LONG).show();
        });
    }

    private class Doctor {
        private String name;
        private int imageResource;
        private String description;

        public Doctor(String name, int imageResource, String description) {
            this.name = name;
            this.imageResource = imageResource;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public int getImageResource() {
            return imageResource;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
