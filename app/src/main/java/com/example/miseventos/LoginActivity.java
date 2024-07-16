package com.example.miseventos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button loginButton;
    private TextView registerLink, forgotPasswordLink;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        registerLink = findViewById(R.id.register_link);
        forgotPasswordLink = findViewById(R.id.forgot_password_link);

        dbHelper = new DatabaseHelper(this);

        SharedPreferences preferences = getSharedPreferences("MisEventosPrefs", MODE_PRIVATE);
        String lastUser = preferences.getString("lastUser", "");
        if (!lastUser.isEmpty()) {
            username.setText(lastUser);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (validateLogin(user, pass)) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("lastUser", user);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userId", dbHelper.getUser(user, pass).getId()); // Pasar el ID del usuario a la siguiente actividad
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateLogin(String user, String pass) {
        User loggedInUser = dbHelper.getUser(user, pass);
        return loggedInUser != null;
    }
}

//hola//
