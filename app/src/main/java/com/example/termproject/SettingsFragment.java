package com.example.termproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsFragment extends Fragment {
    private DatabaseReference mDatabase;
    View myView;
    RadioGroup preferredSportGroup;
    Button save;
    RegistrationInformationSingleTon informationSingleTon = RegistrationInformationSingleTon.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_settings, container, false);
        preferredSportGroup = myView.findViewById(R.id.changePreferenceGroup);
//        RadioGroup radioGroup = (RadioGroup) myView.findViewById(R.id.changePreferenceGroup);

        preferredSportGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (preferredSportGroup.getCheckedRadioButtonId()) {
                case R.id.radioButtonBaseball:
                    informationSingleTon.setPreferredSport(getString(R.string.baseball_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                    break;
                case R.id.radioButtonFootball:
                    informationSingleTon.setPreferredSport(getString(R.string.football_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                    break;
                case R.id.radioButtonGolf:
                    informationSingleTon.setPreferredSport(getString(R.string.golf_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                    break;
                case R.id.radioButtonHockey:
                    informationSingleTon.setPreferredSport(getString(R.string.hockey_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                    break;
                case R.id.radioButtonSoccer:
                    informationSingleTon.setPreferredSport(getString(R.string.soccer_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                    break;
                case R.id.radioButtonVolleyball:
                    informationSingleTon.setPreferredSport(getString(R.string.volleyball_title));
                    System.out.println(informationSingleTon.getPreferredSport() + "asdadasdas");
                    break;
            }
        });
        return myView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        save = view.findViewById(R.id.save_btn);
        save.setOnClickListener(v -> {
            if (preferredSportGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getActivity(), "Please choose a preferred sport", Toast.LENGTH_LONG).show();
                return;
            }
            mDatabase = FirebaseDatabase.getInstance().getReference();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference usersReference = mDatabase.child("Users").child(user.getUid()).child("preferredSport");
            usersReference.setValue(informationSingleTon.getPreferredSport());
        });
    }
}