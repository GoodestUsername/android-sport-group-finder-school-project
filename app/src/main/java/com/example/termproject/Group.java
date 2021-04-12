/*
  Module containing group class.
 */
package com.example.termproject;

import java.util.ArrayList;

public class Group {
    String groupID;
    String location;
    String createdDate;
    String lastUpdatedDate;
    String eventDate;
    String hostID;
    String title;
    String desc;
    int numberOfPeople;
    String sport;
    ArrayList<String> participantIDs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHostID() {
        return hostID;
    }

    public void setHostID(String hostID) {
        this.hostID = hostID;
    }
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public ArrayList<String> getParticipantIDs() {
        return participantIDs;
    }

    public void setParticipantIDs(ArrayList<String> participantIDs) {
        this.participantIDs = participantIDs;
    }

    public boolean addToParticipantList(String idToAdd) {
        return participantIDs.add(idToAdd);
    }

    public boolean RemoveFromParticipantList(String idToRemove) {
        return participantIDs.add(idToRemove);
    }

    public Group() {}

    public Group(String groupID, String sport, String location, String createdDate,
                 int numberOfPeople, ArrayList<String> participantIDs, String hostID, String title,
                 String desc) {
        this.groupID = groupID;
        this.sport = sport;
        this.location = location;
        this.createdDate = createdDate;
        this.numberOfPeople = numberOfPeople;
        this.participantIDs = participantIDs;
        this.hostID = hostID;
        this.title = title;
        this.desc = desc;
    }
}
