package com.example.termproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class GroupListAdapter extends ArrayAdapter<Group> {
    private Activity context;
    private List<Group> groupList;

    public GroupListAdapter(Activity context, List<Group> groupList) {
        super(context, R.layout.group_list_layout, groupList);
        this.context = context;
        this.groupList = groupList;
    }

    public GroupListAdapter(Context context, int resource, List<Group> objects, Activity context1,
                            List<Group> groupList) {
        super(context, resource, objects);
        this.context = context1;
        this.groupList = groupList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.group_list_layout, null, true);

        TextView title = listViewItem.findViewById(R.id.titleView);
        TextView desc = listViewItem.findViewById(R.id.descView);
        TextView location = listViewItem.findViewById(R.id.locationView);
        TextView numPeople = listViewItem.findViewById(R.id.numPeopleView);
        TextView sport = listViewItem.findViewById(R.id.sportView);

        Group current_group = groupList.get(position);

        title.setText("Title: " + current_group.getTitle());
        desc.setText("About: " + current_group.getDesc());
        location.setText("Location: " + current_group.getLocation());
        numPeople.setText("Number of people: " + current_group.getNumberOfPeople());
        sport.setText("Sport: " + current_group.getSport());

        return listViewItem;
    }
}
