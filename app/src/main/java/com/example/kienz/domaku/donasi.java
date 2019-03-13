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


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getDonatur() {
        return donatur;
    }

    public void setDonatur(String donatur) {
        this.donatur = donatur;
    }

    public String getWaktuBuat() {
        return waktuBuat;
    }

    public void setWaktuBuat(String waktuBuat) {
        this.waktuBuat = waktuBuat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
