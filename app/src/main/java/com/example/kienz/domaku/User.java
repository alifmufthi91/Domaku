package com.example.kienz.domaku;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    String alamat;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(com.example.kienz.domaku.User.class)
    }

    public User(String alamat) {
        this.alamat = alamat;
    }

}