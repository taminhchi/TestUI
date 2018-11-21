package com.example.microsoftwindows.testui;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Customer {

    public String username;
    public String phoneNumber;
    public String position;

    public Customer() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Customer(String username, String phoneNumber, String position) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.position = position;
    }

}