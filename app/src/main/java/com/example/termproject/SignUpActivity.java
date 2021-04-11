package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    Button next_part;
    CheckBox isVaccinated;
    RadioGroup preferredSportGroup;
    RegistrationInformationSingleTon informationSingleTon = RegistrationInformationSingleTon.getInstance();

    // source: https://developer.android.com/guide/topics/ui/controls/radiobutton
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButtonBaseball:
                if (checked){
                    informationSingleTon.setPreferredSport(getString(R.string.baseball_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                }
                break;
            case R.id.radioButtonFootball:
                if (checked) {
                    informationSingleTon.setPreferredSport(getString(R.string.football_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                }
                break;
            case R.id.radioButtonGolf:
                if (checked) {
                    informationSingleTon.setPreferredSport(getString(R.string.golf_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                }
                break;
            case R.id.radioButtonHockey:
                if (checked) {
                    informationSingleTon.setPreferredSport(getString(R.string.hockey_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                }
                break;
            case R.id.radioButtonSoccer:
                if (checked) {
                    informationSingleTon.setPreferredSport(getString(R.string.soccer_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                }

                break;
            case R.id.radioButtonVolleyball:
                if (checked) {
                    informationSingleTon.setPreferredSport(getString(R.string.volleyball_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        isVaccinated = findViewById(R.id.isVaccinatedCheckBox);
        preferredSportGroup = (RadioGroup) findViewById(R.id.preferredSportRadioGroup);

        next_part = findViewById(R.id.next_btn);

        next_part.setOnClickListener(v -> {
            if (preferredSportGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(SignUpActivity.this, "Please choose a preferred sport", Toast.LENGTH_LONG).show();
                return;
            }
            informationSingleTon.setVaccinated(isVaccinated.isChecked());

            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });
    }
}
