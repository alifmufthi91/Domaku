package com.example.kienz.domaku;

public class donasi {
    String eventname;
    int jumlah;
    String gambarUrl;

    public donasi(String eventname, int jumlah, String gambarUrl) {
        this.eventname = eventname;
        this.jumlah = jumlah;
        this.gambarUrl = gambarUrl;
    }

    public String getEventname() {
        return eventname;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getGambarUrl() {
        return gambarUrl;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
    }
}
