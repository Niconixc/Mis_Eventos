package com.example.miseventos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText newPassword, confirmPassword;
    private Button changePasswordButton;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPassword = findViewById(R.id.new_password);
        confirmPassword = findViewById(R.id.confirm_password);
        changePasswordButton = findViewById(R.id.change_password_button);

        username = getIntent().getStringExtra("username");

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPass = newPassword.getText().toString();
                String confirmPass = confirmPassword.getText().toString();

                if (newPass.equals(confirmPass)) {
                    // Guardar nueva contraseña en SQLite
                    DatabaseHelper dbHelper = new DatabaseHelper(ChangePasswordActivity.this);
                    dbHelper.updateUserPassword(username, newPass);

                    Toast.makeText(ChangePasswordActivity.this, "Contraseña cambiada con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
