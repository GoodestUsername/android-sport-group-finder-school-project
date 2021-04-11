package com.example.termproject;

import java.util.ArrayList;

public class RegistrationInformationSingleTon {
    private static RegistrationInformationSingleTon instance = null;

    // variable of type String
    private String username;
    private String firstName;
    private String lastName;
    private String userUID;
    private String email;
    private String preferredSport;
    private Boolean isVaccinated;
    private final ArrayList<String> joinedGroups = null;


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

    private RegistrationInformationSingleTon() {
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.userUID = null;
        this.email = null;
        this.preferredSport = null;
        this.isVaccinated = null;
    }

    public static RegistrationInformationSingleTon getInstance()
    {
        if (instance == null) instance = new RegistrationInformationSingleTon();

        return instance;
    }
}