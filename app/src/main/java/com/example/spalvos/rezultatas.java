package com.example.spalvos;

public class rezultatas {
     String vardas;
     int rezultatas;
     int id = 0;

    public rezultatas() {
    }

    public rezultatas(String vardas, int rezultatas) {
        this.id++;
        this.vardas = vardas;
        this.rezultatas = rezultatas;
    }

    public  int getId() {
        return id;
    }

    public  String getVardas() {
        return vardas;
    }

    public  int getRezultatas() {
        return rezultatas;
    }

    public void setId(int id) {   this.id = id; }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public void setRezultatas(int rezultatas) {
        this.rezultatas = rezultatas;
    }
}
