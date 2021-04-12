package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
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

                        if (getActivity()!=null){
                            GroupListAdapter adapter = new GroupListAdapter(getActivity(),
                                    groupList);
                            groupsLV.setAdapter(adapter);
                            v.findViewById(R.id.loadingPanelHome).setVisibility(View.GONE);
                        }

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

        groupsLV.setOnItemClickListener((adapterView, v, i, id) -> {
            Group current = groupList.get(i);
            String groupID = current.getGroupID();
            int numPeople = current.getParticipantIDs().size();

            mDatabase.child("Groups").child(groupID).child("participantIDs")
                    .child(String.valueOf(numPeople)).setValue(uid);

            mDatabase.child("Users").child(uid).child("joinedGroups").push().setValue(groupID);

            // source: https://stackoverflow.com/questions/1397361/how-to-restart-activity-in-android
            new Handler().post(new Runnable() {

                @Override
                public void run()
                {
                    Intent intent = getActivity().getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    getActivity().overridePendingTransition(0, 0);
                    getActivity().finish();

                    getActivity().overridePendingTransition(0, 0);
                    startActivity(intent);
                }
            });
        });
    }
}