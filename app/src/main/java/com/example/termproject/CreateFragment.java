package com.example.termproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateFragment extends Fragment {

    EditText titleEdit, aboutEdit, sportEdit, locationEdit, gameSizeEdit;
    Button create_group;
    FirebaseAuth fAuth;

    // source: https://firebase.google.com/docs/database/android/read-and-write
    private DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleEdit = view.findViewById(R.id.title_edit);
        aboutEdit = view.findViewById(R.id.about_edit);
        sportEdit = view.findViewById(R.id.sport_edit);
        locationEdit = view.findViewById(R.id.location_edit);
        gameSizeEdit = view.findViewById(R.id.game_size_edit);

        titleEdit.setHintTextColor(Color.WHITE);
        aboutEdit.setHintTextColor(Color.WHITE);
        sportEdit.setHintTextColor(Color.WHITE);
        locationEdit.setHintTextColor(Color.WHITE);
        gameSizeEdit.setHintTextColor(Color.WHITE);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();

        create_group = view.findViewById(R.id.create_group_btn);
        create_group.setOnClickListener( v -> {
            String title = this.titleEdit.getText().toString().trim();
            String desc = this.aboutEdit.getText().toString().trim();
            String sport = this.sportEdit.getText().toString().trim();
            String location =this.locationEdit.getText().toString().trim();
            String num_people =this.gameSizeEdit.getText().toString().trim();

            if (TextUtils.isEmpty(title)) {
                this.titleEdit.setError("Requirement: Group title required.");
                return;
            }
            if (TextUtils.isEmpty(desc)) {
                this.aboutEdit.setError("Requirement: Group description required.");
                return;
            }
            if (TextUtils.isEmpty(sport)) {
                this.sportEdit.setError("Requirement: Sport/ activity required.");
                return;
            }
            if (TextUtils.isEmpty(location)) {
                this.locationEdit.setError("Requirement: location description required");
                return;
            }
            if (TextUtils.isEmpty(num_people)) {
                this.gameSizeEdit.setError("Requirement: estimate of the number of people required.");
                return;
            }
            // source: https://stackabuse.com/how-to-get-current-date-and-time-in-java/
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            ArrayList<String> IDs = new ArrayList<>();
            IDs.add(uid);
            String mGroupId = mDatabase.child("Groups").push().getKey();
            Group newGroup = new Group(formatter.format(date), desc, mGroupId, uid, location,
                    Integer.parseInt(num_people), IDs, sport, title);

            mDatabase.child("Groups").child(mGroupId).setValue(newGroup);
            mDatabase.child("Users").child(uid).child("joinedGroups").push().setValue(mGroupId);

            ((MainActivity) getActivity()).replaceFragments(new GroupsFragment());
        });
    }
}