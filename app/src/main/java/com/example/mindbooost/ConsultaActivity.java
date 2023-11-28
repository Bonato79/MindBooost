package com.example.mindbooost;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsultaActivity extends AppCompatActivity {
    private Spinner doctorSpinner;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        doctorSpinner = findViewById(R.id.doctorImage);
        datePicker = findViewById(R.id.datePicker);
        Button scheduleButton = findViewById(R.id.scheduleButton);

        // Simulando uma lista de médicos com imagens e descrições
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Smith", "Descrição do Dr. Smith"));
        doctors.add(new Doctor("Dr. Johnson", "Descrição do Dr. Johnson"));
        doctors.add(new Doctor("Dr. Davis", "Descrição do Dr. Davis"));

        ArrayAdapter<Doctor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doctors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doctorSpinner.setAdapter(adapter);

        scheduleButton.setOnClickListener(view -> {
            // Chama o método de agendamento
            performAppointmentScheduling();
        });
    }

    private void performAppointmentScheduling() {
        // Seu código de agendamento aqui
        new Thread(() -> {
            // Seu código de agendamento dentro desta thread

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

            // Conecta ao banco de dados
            DatabaseConnection connection = new DatabaseConnection();

            // Verifique se a conexão é bem-sucedida
            if (connection.isConnected()) {
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

                    // Atualiza a UI com uma mensagem de confirmação para o usuário
                    runOnUiThread(() -> {
                        String message = "Consulta agendada com " + selectedDoctor.getName() +
                                " para " + selectedDate + "\nDescrição: " + selectedDoctor.getDescription() +
                                "\nLink da videochamada: " + videoCallLink;
                        Toast.makeText(ConsultaActivity.this, message, Toast.LENGTH_LONG).show();
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    showConfirmationMessage(selectedDoctor, selectedDate, videoCallLink, "Erro ao agendar a consulta: " + e.getMessage());
                }


            } else {
                // Trate a falha na conexão
                runOnUiThread(() -> Toast.makeText(ConsultaActivity.this, "Falha na conexão com o banco de dados", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
    private void showConfirmationMessage(Doctor selectedDoctor, String selectedDate, String videoCallLink, String s) {
        new Handler(Looper.getMainLooper()).post(() -> {
            String message = "Consulta agendada com " + selectedDoctor.getName() +
                    " para " + selectedDate + "\nDescrição: " + selectedDoctor.getDescription() +
                    "\nLink da videochamada: " + videoCallLink;
            Toast.makeText(ConsultaActivity.this, message, Toast.LENGTH_LONG).show();
        });
    }

    private static class Doctor {
        private final String name;
        private final String description;

        public Doctor(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        @NonNull
        @Override
        public String toString() {
            return name;
        }
    }
}
