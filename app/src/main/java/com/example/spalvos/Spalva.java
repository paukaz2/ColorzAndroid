package com.example.spalvos;

public class Spalva {

    private int spalva;
    private int naudota;
    private String pavadinimas;

    public Spalva(int spalva, String pavadinimas) {
        this.spalva = spalva;
        this.pavadinimas = pavadinimas;
        this.naudota = 0;
    }

    public void addNaudota() {
        this.naudota++;
    }

    public int getNaudota() {
        return naudota;
    }

    public int getSpalva() {
        return spalva;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }
}
