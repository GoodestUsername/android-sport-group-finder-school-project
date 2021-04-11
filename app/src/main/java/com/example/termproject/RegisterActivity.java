package com.example.termproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    RegistrationInformationSingleTon singleTon = RegistrationInformationSingleTon.getInstance();
    EditText first_name, last_name, user_name, user_email, user_password;
    ProgressBar bar_progress;
    Button register_btn;
    FirebaseAuth fAuth;
    // source: https://firebase.google.com/docs/database/android/read-and-write
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bar_progress = findViewById(R.id.progress_bar);

        register_btn = findViewById(R.id.register_btn);

        first_name = findViewById(R.id.first_name_edit);
        last_name = findViewById(R.id.last_name_edit);
        user_name = findViewById(R.id.username_edit);
        user_email = findViewById(R.id.email_edit);
        user_password = findViewById(R.id.password_edit);

        first_name.setHintTextColor(Color.WHITE);
        last_name.setHintTextColor(Color.WHITE);
        user_name.setHintTextColor(Color.WHITE);
        user_email.setHintTextColor(Color.WHITE);
        user_password.setHintTextColor(Color.WHITE);



        mDatabase = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();



        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(view -> {
            String first_name = RegisterActivity.this.first_name.getText().toString().trim();
            String last_name = RegisterActivity.this.last_name.getText().toString().trim();
            String user_name = RegisterActivity.this.user_name.getText().toString().trim();
            String email = RegisterActivity.this.user_email.getText().toString().trim();
            String password = RegisterActivity.this.user_password.getText().toString().trim();

            singleTon.setFirstName(first_name);
            singleTon.setLastName(last_name);
            singleTon.setUsername(user_name);
            singleTon.setEmail(email);

            if (password.length() < 8) {
                RegisterActivity.this.user_email.setError("Password must be 8 characters or greater");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                RegisterActivity.this.user_email.setError("Requirement: Personal authorized email");
                return;
            }

            if (TextUtils.isEmpty(first_name)) {
                RegisterActivity.this.user_email.setError("Requirement: first name");
                return;
            }

            if (TextUtils.isEmpty(last_name)) {
                RegisterActivity.this.user_email.setError("Requirement: last name");
                return;
            }

            if (TextUtils.isEmpty(user_name)) {
                RegisterActivity.this.user_email.setError("Requirement: Username required");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                RegisterActivity.this.user_email.setError("Requirement: Password which is greater than or equal to 8 characters");
                return;
            }

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

            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "New user has been created and is logged in.", Toast.LENGTH_LONG).show();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    singleTon.setUserUID(uid);

                    DatabaseReference usersReference = mDatabase.child("Users");
                    usersReference.push().setValue(singleTon.getUserUID());
                    DatabaseReference profile = usersReference.child(singleTon.getUserUID());
                    profile.setValue(singleTon);

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    bar_progress.setVisibility(View.GONE);
                }
            });

        });
    }
}