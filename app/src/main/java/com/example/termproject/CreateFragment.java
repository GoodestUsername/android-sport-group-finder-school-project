package com.example.termproject;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CreateFragment extends Fragment {

    EditText sportEdit, locationEdit, dateEdit, gameSizeEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sportEdit = view.findViewById(R.id.sport_edit);
        locationEdit = view.findViewById(R.id.location_edit);
        dateEdit = view.findViewById(R.id.date_edit);
        gameSizeEdit = view.findViewById(R.id.game_size_edit);

        sportEdit.setHintTextColor(Color.WHITE);
        locationEdit.setHintTextColor(Color.WHITE);
        dateEdit.setHintTextColor(Color.WHITE);
        gameSizeEdit.setHintTextColor(Color.WHITE);

    }
}