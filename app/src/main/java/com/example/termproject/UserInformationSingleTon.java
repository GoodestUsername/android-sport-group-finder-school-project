package com.example.termproject;

import java.util.ArrayList;

public class UserInformationSingleTon {
    private static UserInformationSingleTon instance = null;

    // variable of type String
    private static String username;
    private static String firstName;
    private static String lastName;
    private static String userUID;
    private static String email;
    private static String preferredSport;
    private static Boolean isVaccinated;
    private static String hostID;
    private static ArrayList<String> joinedGroups = null;

    public static ArrayList<String> getJoinedGroups() {
        return joinedGroups;
    }

    public static void setJoinedGroups(ArrayList<String> joinedGroups) {
        UserInformationSingleTon.joinedGroups = joinedGroups;
    }

    public static String getHostID() {
        return hostID;
    }

    public static void setHostID(String hostID) {
        UserInformationSingleTon.hostID = hostID;
    }

    public String getPreferredSport() {
        return preferredSport;
    }

    public void setPreferredSport(String preferredSport) {
        UserInformationSingleTon.preferredSport = preferredSport;
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
        UserInformationSingleTon.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        UserInformationSingleTon.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        UserInformationSingleTon.lastName = lastName;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        UserInformationSingleTon.userUID = userUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        UserInformationSingleTon.email = email;
    }

    private UserInformationSingleTon() {
        username = null;
        firstName = null;
        lastName = null;
        userUID = null;
        email = null;
        preferredSport = null;
        isVaccinated = null;
    }

    public static UserInformationSingleTon getInstance()
    {
        if (instance == null) instance = new UserInformationSingleTon();

        return instance;
    }
}
