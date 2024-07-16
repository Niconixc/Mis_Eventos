package com.example.miseventos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, password, secretQuestion, secretAnswer;
    private Button registerButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        secretQuestion = findViewById(R.id.secret_question);
        secretAnswer = findViewById(R.id.secret_answer);
        registerButton = findViewById(R.id.register_button);

        dbHelper = new DatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String question = secretQuestion.getText().toString();
                String answer = secretAnswer.getText().toString();

                if (user.isEmpty() || pass.isEmpty() || question.isEmpty() || answer.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    User newUser = new User(0, user, pass, question, answer);
                    dbHelper.addUser(newUser);
                    Toast.makeText(RegisterActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
