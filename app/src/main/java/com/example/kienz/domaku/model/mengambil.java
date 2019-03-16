package com.example.kienz.domaku.model;

public class mengambil {
    public String uid;
    public String donasiId;

    public mengambil() {
    }

    public mengambil(String uid, String donasiId) {
        this.uid = uid;
        this.donasiId = donasiId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDonasiId() {
        return donasiId;
    }

    public void setDonasiId(String donasiId) {
        this.donasiId = donasiId;
    }
}
