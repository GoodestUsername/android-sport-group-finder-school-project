package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    Button next_part;
    RadioButton isVaccinated;
    RadioGroup preferredSportGroup;
    RegistrationInformationSingleTon informationSingleTon = RegistrationInformationSingleTon.getInstance();

    // source: https://developer.android.com/guide/topics/ui/controls/radiobutton
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButtonBaseball:
                if (checked)
                    informationSingleTon.setPreferredSport(String.valueOf(R.string.baseball_title));
                break;
            case R.id.radioButtonFootball:
                if (checked)
                    informationSingleTon.setPreferredSport(String.valueOf(R.string.football_title));
                break;
            case R.id.radioButtonGolf:
                if (checked)
                    informationSingleTon.setPreferredSport(String.valueOf(R.string.golf_title));
                break;
            case R.id.radioButtonHockey:
                if (checked)
                    informationSingleTon.setPreferredSport(String.valueOf(R.string.hockey_title));
                break;
            case R.id.radioButtonSoccer:
                if (checked)
                    informationSingleTon.setPreferredSport(String.valueOf(R.string.soccer_title));
                break;
            case R.id.radioButtonVolleyball:
                if (checked)
                    informationSingleTon.setPreferredSport(String.valueOf(R.string.volleyball_title));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        isVaccinated = findViewById(R.id.isVaccinatedRadioBtn);
        preferredSportGroup = findViewById(R.id.preferedSportRadioGroup);

        next_part = findViewById(R.id.next_btn);

        next_part.setOnClickListener(v -> {
            if (preferredSportGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(SignUpActivity.this, "Please choose a preferred sport", Toast.LENGTH_LONG).show();
                return;
            }
            informationSingleTon.setVaccinated(isVaccinated.isChecked());

            new Intent(getApplicationContext(), RegisterActivity.class);
        });
    }
}
