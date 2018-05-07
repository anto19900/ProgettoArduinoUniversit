package com.example.ababo.progettoarduinouniversit.datamodel;

import java.io.Serializable;

public class Stanza implements Serializable {
    // Attributi
    private String matricola;
   //private String cognome;
    private String nome;
    private int dispositivi;

    public Stanza() {
    }

    public Stanza(String matricola, String cognome, String nome, int dispositivi) {
        this.matricola = matricola;
        //this.cognome = cognome;
        this.nome = nome;
        this.dispositivi = dispositivi;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

   /* public String getCognome() {
        return cognome;
    }*/

    /*public void setCognome(String cognome) {
        this.cognome = cognome;
    }*/

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDispositivi() {
        return dispositivi;
    }

    public void setDispositivi(int dispositivi) {
        this.dispositivi = dispositivi;
    }
}
