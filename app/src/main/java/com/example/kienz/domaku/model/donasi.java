package com.example.kienz.domaku.model;

public class donasi {
    public String judul;
    public String id;
    public int qty;
    public int sisa;
    public String status;
    public String deskripsi;
    public String gambar;
    public String donatur;
    public String waktuBuat;
    public String alamat;
    public koordinat koordinattempat;



    public donasi(String eventname, int jumlah, String gambarUrl, String alamat, String donatur) {

        this.judul = eventname;
        this.qty = jumlah;
        this.gambar = gambarUrl;
        this.alamat = alamat;
        this.donatur = donatur;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

}
