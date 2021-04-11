package com.example.termproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText edit_email;
    EditText edit_password;
    ProgressBar progress_bar;
    Button login_btn;
    Button register_btn;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_email = findViewById(R.id.email_edit);
        edit_password = findViewById(R.id.password_edit);
        progress_bar = findViewById(R.id.progress_bar);
        login_btn = findViewById(R.id.login_btn);
        register_btn = findViewById(R.id.btn_reg_login);

        fAuth = FirebaseAuth.getInstance();

        edit_email.setHintTextColor(Color.WHITE);

        login_btn.setOnClickListener(view -> {
            String email = edit_email.getText().toString().trim();
            String password = edit_password.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                edit_email.setError("You must have an email");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                edit_email.setError("You must have a password");
                return;
            }

            if (password.length() <6) {
                edit_email.setError("Password must 6+ characters");
                return;
            }

            progress_bar.setVisibility(View.VISIBLE);


            //This is how the user is authenticated
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login was successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    progress_bar.setVisibility(View.GONE);
                }
            });

        });

        register_btn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));
    }
}