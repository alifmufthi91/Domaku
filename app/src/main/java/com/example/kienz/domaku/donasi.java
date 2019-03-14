package com.example.kienz.domaku;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class donasi {
    String judul;
    String id;
    int qty;
    int sisa;
    String gambar;
    String donatur;
    String waktuBuat;
    String alamat;
    koordinat koordinattempat;



    public donasi(String eventname, int jumlah, String gambarUrl, String alamat, String donatur) {

        this.waktuBuat= "test";
        this.judul = eventname;
        this.qty = jumlah;
        this.gambar = gambarUrl;
        this.alamat = "test";
        this.donatur = "test";
        this.koordinattempat = new koordinat(-6.6232204,107.17919);
    }

    public donasi() {}


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

    public koordinat getKoordinattempat() {
        return koordinattempat;
    }

    public void setKoordinattempat(koordinat koordinattempat) {
        this.koordinattempat = koordinattempat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSisa() {
        return sisa;
    }

    public void setSisa(int sisa) {
        this.sisa = sisa;
    }
}
