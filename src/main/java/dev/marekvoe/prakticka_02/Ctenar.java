package dev.marekvoe.prakticka_02;

public class Ctenar {

    private int id_ctenar;
    private String jmeno;
    private String prijmeni;
    private String email;
    private String telefon;
    private String mesto;
    private String ulice;
    private String psc;

    public Ctenar(int id_ctenar, String jmeno, String prijmeni, String email, String telefon, String mesto, String ulice, String psc) {
        this.id_ctenar = id_ctenar;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.telefon = telefon;
        this.mesto = mesto;
        this.ulice = ulice;
        this.psc = psc;
    }

    public int getId_ctenar() {
        return id_ctenar;
    }

    public void setId_ctenar(int id_ctenar) {
        this.id_ctenar = id_ctenar;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }


}
