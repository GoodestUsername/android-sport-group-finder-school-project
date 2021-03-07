package com.example.termproject;

import java.util.ArrayList;

public class Group {
    String groupID;
    String sport;

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public ArrayList<String> getParticipantIdList() {
        return participantIdList;
    }

    public void setParticipantIdList(ArrayList<String> participantIdList) {
        this.participantIdList = participantIdList;
    }
    
    public boolean addToParticipantList(String idToAdd) {
        return participantIdList.add(idToAdd);
    }

    public boolean RemoveFromParticipantList(String idToRemove) {
        return participantIdList.add(idToRemove);
    }

    String location;
    String date;
    int numberOfPeople;



    ArrayList<String> participantIdList;

    public Group() {}

    public Group(String groupID, String sport, String location, String date,
                 int numberOfPeople, ArrayList<String> participantIdList) {
        this.groupID = groupID;
        this.sport = sport;
        this.location = location;
        this.date = date;
        this.numberOfPeople = numberOfPeople;
        this.participantIdList = participantIdList;
    }
}
