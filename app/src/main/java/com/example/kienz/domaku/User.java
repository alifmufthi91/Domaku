package com.example.kienz.domaku;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(com.example.kienz.domaku.User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}