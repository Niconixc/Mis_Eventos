package com.example.miseventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, password, secretQuestion, secretAnswer;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        secretQuestion = findViewById(R.id.secret_question);
        secretAnswer = findViewById(R.id.secret_answer);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica de registro
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String question = secretQuestion.getText().toString();
                String answer = secretAnswer.getText().toString();

                if(validateRegistration(user, pass, question, answer)) {
                    // Guardar usuario en SQLite
                    // TODO: Implementar lógica de guardado en SQLite
                    Toast.makeText(RegisterActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();

                    // Navegar a la pantalla de login
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateRegistration(String user, String pass, String question, String answer) {
        // Implementar lógica de validación
        return true; // Placeholder para simplificación
    }
}
