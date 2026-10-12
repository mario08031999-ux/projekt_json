package com.example.projektjson;

import com.google.gson.annotations.SerializedName;

public class Ogloszenia {
    @SerializedName("tytul")
    private String tytul;

    @SerializedName("tresc")
    private String tresc;

    @SerializedName("rok")
    private int rok;

    @SerializedName("miesiąc")
    private int miesiac;

    @SerializedName("dzien")
    private int dzien;

    public Ogloszenia(String tresc, String tytul, int rok, int miesiac, int dzien) {
        this.tresc = tresc;
        this.tytul = tytul;
        this.rok = rok;
        this.miesiac = miesiac;
        this.dzien = dzien;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(int miesiac) {
        this.miesiac = miesiac;
    }

    public int getDzien() {
        return dzien;
    }

    public void setDzien(int dzien) {
        this.dzien = dzien;
    }
}
