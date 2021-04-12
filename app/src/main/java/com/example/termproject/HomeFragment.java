package com.example.termproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    List<Group> groupList;
    ListView groupsLV;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_home, container, false);
        v.findViewById(R.id.loadingPanelHome).setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        groupList = new ArrayList<Group>();
        groupsLV = v.findViewById(R.id.groupsLV);
        user = FirebaseAuth.getInstance().getCurrentUser();
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        String uid = user.getUid();
        Query joinedGroupsQuery = mDatabase.child("Users").child(uid).child("joinedGroups");

        joinedGroupsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> groups = new ArrayList<>();
                for (DataSnapshot caseSnapShot : snapshot.getChildren()) {
                    groups.add((String) caseSnapShot.getValue());
                }

                Query groupsQuery = mDatabase.child("Groups");
                groupsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        groupList.clear();
                        for (DataSnapshot caseSnapshot : snapshot.getChildren()) {
                            Group currentGroup = caseSnapshot.getValue(Group.class);
                            if (!groups.contains(currentGroup.getGroupID())) {
                                groupList.add(currentGroup);
                            }
                        }
                        GroupListAdapter adapter = new GroupListAdapter(getActivity(),
                                groupList);
                        groupsLV.setAdapter(adapter);
                        v.findViewById(R.id.loadingPanelHome).setVisibility(View.GONE);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}