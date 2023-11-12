package com.example.mindbooost;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsultaActivity extends AppCompatActivity {
    private Spinner doctorSpinner;
    private DatePicker datePicker;
    private ImageView doctorImage;
    private TextView doctorDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        datePicker = findViewById(R.id.datePicker);
        Button scheduleButton = findViewById(R.id.scheduleButton);
        doctorImage = findViewById(R.id.doctorImage);

        // Simulando uma lista de médicos com imagens e descrições
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Smith", R.drawable.img_medico, "Descrição do Dr. Smith"));
        doctors.add(new Doctor("Dr. Johnson", R.drawable.img_medico, "Descrição do Dr. Johnson"));
        doctors.add(new Doctor("Dr. Davis", R.drawable.img_medico, "Descrição do Dr. Davis"));

        ArrayAdapter<Doctor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doctors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doctorSpinner.setAdapter(adapter);

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Seleciona o médico selecionado do spinner
                Doctor selectedDoctor = (Doctor) doctorSpinner.getSelectedItem();

                // Obtém o dia, mês e ano da data selecionada
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();

                // Concatena o dia, mês e ano para formar a data selecionada
                String selectedDate = day + "/" + month + "/" + year;

                // Gera um ID de consulta exclusivo
                String appointmentID = UUID.randomUUID().toString();

                // Cria um link para chamada de vídeo usando a API do Google Meet
                String videoCallLink = "https://meet.google.com/meetingID/" + appointmentID;

                // Salva os detalhes da consulta em um banco de dados ou servidor
                Appointment appointment = new Appointment(appointmentID, selectedDoctor.getName(), selectedDate, videoCallLink);
                saveAppointment(appointment);

                // Conecta ao banco de dados
                DatabaseConnection connection = new DatabaseConnection();

                // Prepara a instrução SQL
                String sql = "INSERT INTO appointments (appointmentID, doctorName, appointmentDate, videoCallLink) VALUES (?, ?, ?, ?)";
                try {
                    PreparedStatement statement = connection.prepareStatement(sql);

                    // Define os parâmetros da instrução
                    statement.setString(1, appointment.getAppointmentID());
                    statement.setString(2, appointment.getDoctorName());
                    statement.setString(3, appointment.getAppointmentDate());
                    statement.setString(4, appointment.getVideoCallLink());

                    // Executa a instrução SQL
                    statement.executeUpdate();

                    // Fecha a instrução e a conexão
                    statement.close();
                    connection.close();

                    // Exibe uma mensagem de confirmação para o usuário
                    String message = "Consulta agendada com " + selectedDoctor.getName() +
                            " para " + selectedDate + "\nDescrição: " + selectedDoctor.getDescription() +
                            "\nLink da videochamada: " + videoCallLink;
                    Toast.makeText(ConsultaActivity.this, message, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ConsultaActivity.this, "Erro ao agendar a consulta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveAppointment(Appointment appointment) {
        // Lógica para salvar a consulta no banco de dados ou servidor
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
