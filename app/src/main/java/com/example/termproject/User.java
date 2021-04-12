package com.example.termproject;

import java.util.ArrayList;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String userUID;
    private String email;
    private String preferredSport;
    private Boolean isVaccinated;
    private ArrayList<String> joinedGroups;

    public Boolean addToJoinedGroups(String groupID) {
        if (this.joinedGroups.contains(groupID)) {
            return false;
        }

        this.joinedGroups.add(groupID);
        return true;
    }

    public ArrayList<String> getJoinedGroups() {
        return joinedGroups;
    }

    public void setJoinedGroups(ArrayList<String> joinedGroups) {
        this.joinedGroups = joinedGroups;
    }

    public String getPreferredSport() {
        return preferredSport;
    }

    public void setPreferredSport(String preferredSport) {
        this.preferredSport = preferredSport;
    }

    public Boolean getVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {}

    public User(String username, String firstName, String lastName, String userUID, String email,
                Boolean isVaccinated, String preferredSport, ArrayList<String> joinedGroups) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userUID = userUID;
        this.email = email;
        this.preferredSport = preferredSport;
        this.isVaccinated = isVaccinated;
        this.joinedGroups = joinedGroups;
    }
}
