package com.example.termproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    TextView login_btn;
    ProgressBar bar_progress;
    FirebaseAuth fAuth;
    EditText user_email, user_password;
    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login_btn = findViewById(R.id.reg_login_btn);
        register_btn = findViewById(R.id.btn_reg_login);

        user_email = findViewById(R.id.email_edit);
        user_password = findViewById(R.id.password_edit);

        fAuth = FirebaseAuth.getInstance();
        bar_progress = findViewById(R.id.progress_bar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = RegisterActivity.this.user_email.getText().toString().trim();
                String password = RegisterActivity.this.user_password.getText().toString().trim();
                if (password.length() < 8) {
                    RegisterActivity.this.user_email.setError("Password must be 8 characters or greater");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    RegisterActivity.this.user_email.setError("Requirement: Personal authorized email");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    RegisterActivity.this.user_email.setError("Requirement: Password which is greater than or equal to 8 characters");
                    return;
                }

                bar_progress.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "New user has been created and is logged in.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            bar_progress.setVisibility(View.GONE);
                        }
                    }
                });

            }

        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}