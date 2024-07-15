package com.example.miseventos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button deleteUserButton, backupButton;
    private ListView eventsListView;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteUserButton = findViewById(R.id.delete_user_button);
        backupButton = findViewById(R.id.backup_button);
        eventsListView = findViewById(R.id.events_list);

        userId = getIntent().getIntExtra("userId", -1);

        loadEvents();

        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Eliminar Usuario")
                        .setMessage("¿Estás seguro de que deseas eliminar este usuario y todos sus eventos?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
                                dbHelper.deleteUser(userId);
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backupData();
            }
        });
    }

    private void loadEvents() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Event> events = dbHelper.getEventsByUserId(userId);

        ArrayAdapter<Event> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);
        eventsListView.setAdapter(adapter);
    }

    private void backupData() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Event> events = dbHelper.getEventsByUserId(userId);

        String backupFileName = "event_backup.txt";
        File backupFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), backupFileName);

        try (FileWriter writer = new FileWriter(backupFile)) {
            for (Event event : events) {
                writer.write(event.toString() + "\n");
            }
            Toast.makeText(this, "Respaldo completado: " + backupFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error al crear el respaldo", Toast.LENGTH_SHORT).show();
        }
    }
}
