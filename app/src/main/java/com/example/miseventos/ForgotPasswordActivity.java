package com.example.miseventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText username, secretAnswer;
    private Button recoverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        username = findViewById(R.id.username);
        secretAnswer = findViewById(R.id.secret_answer);
        recoverButton = findViewById(R.id.recover_button);

        recoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica de recuperación de contraseña
                String user = username.getText().toString();
                String answer = secretAnswer.getText().toString();

                if(validateRecovery(user, answer)) {
                    // Navegar a la pantalla de cambiar contraseña
                    Intent intent = new Intent(ForgotPasswordActivity.this, ChangePasswordActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateRecovery(String user, String answer) {
        // Implementar lógica de validación
        return true; // Placeholder para simplificación olaaaaa
    }
}
