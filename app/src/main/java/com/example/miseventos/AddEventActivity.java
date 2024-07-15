package com.example.miseventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEventActivity extends AppCompatActivity {

    private EditText title, date, observation, place, reminderTime;
    private Spinner importance;
    private Button addEventButton;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        observation = findViewById(R.id.observation);
        place = findViewById(R.id.place);
        reminderTime = findViewById(R.id.reminder_time);
        importance = findViewById(R.id.importance);
        addEventButton = findViewById(R.id.add_event_button);

        userId = getIntent().getIntExtra("userId", -1);

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventTitle = title.getText().toString();
                String eventDate = date.getText().toString();
                String eventImportance = importance.getSelectedItem().toString();
                String eventObservation = observation.getText().toString();
                String eventPlace = place.getText().toString();
                String eventReminderTime = reminderTime.getText().toString();

                Event event = new Event(0, eventTitle, eventDate, eventImportance, eventObservation, eventPlace, eventReminderTime, userId);

                DatabaseHelper dbHelper = new DatabaseHelper(AddEventActivity.this);
                dbHelper.addEvent(event);

                Toast.makeText(AddEventActivity.this, "Evento añadido con éxito", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
