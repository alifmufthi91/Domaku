package com.example.kienz.domaku.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class user {

    public String alamat;

    public user() {
        // Default constructor required for calls to DataSnapshot.getValue(com.example.kienz.domaku.model.user.class)
    }

    public user(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}