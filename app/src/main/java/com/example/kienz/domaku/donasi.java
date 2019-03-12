package com.example.kienz.domaku;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class donasi {
    String judul;
    int qty;
    String gambar;
    String donatur;
    String waktuBuat;
    String alamat;


    public donasi(String eventname, int jumlah, String gambarUrl, String alamat, String donatur) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.waktuBuat= dateFormat.format(date);
        this.judul = eventname;
        this.qty = jumlah;
        this.gambar = gambarUrl;
        this.alamat = alamat;
        this.donatur = donatur;
    }

    public String getEventname() {
        return judul;
    }

    public int getJumlah() {
        return qty;
    }

    public String getGambarUrl() {
        return gambar;
    }

    public void setEventname(String eventname) {
        this.judul = eventname;
    }

    public void setJumlah(int jumlah) {
        this.qty = jumlah;
    }

    public void setGambarUrl(String gambarUrl) {
        this.gambar = gambarUrl;
    }
}
