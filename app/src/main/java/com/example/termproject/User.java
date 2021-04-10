package com.example.termproject;

public class User {
    String username;
    String firstName;
    String lastName;
    String userUID;
    String email;
    Boolean isVaccinated;


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

    public User(String username, String firstName, String lastName,
                String userUID, String email, Boolean isVaccinated) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userUID = userUID;
        this.email = email;
        this.isVaccinated = isVaccinated;
    }
}
