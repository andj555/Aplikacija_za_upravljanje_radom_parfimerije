/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.entity.impl;

import com.projekat.backend.entity.enums.TipKorisnika;
import jakarta.persistence.*;


/**
 *
 * @author Anđelija
 */
@Entity
@Table(name = "korisnik")
public class Korisnik
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKorisnik")
    private Long idKorisnik;


    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipKorisnika")
    private TipKorisnika tipKorisnika;
    
    private Boolean potvrdjenEmail = false;
    private String verifikacioniToken;
    

    public Korisnik() {
    }

    public Korisnik(Long idKorisnik, String email, String password, String ime, String prezime, TipKorisnika tipKorisnika) {
        this.idKorisnik = idKorisnik;
        this.email = email;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.tipKorisnika = tipKorisnika;
    }

    public Long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public Boolean getPotvrdjenEmail() {
        return potvrdjenEmail;
    }

    public void setPotvrdjenEmail(Boolean potvrdjenEmail) {
        this.potvrdjenEmail = potvrdjenEmail;
    }

    public String getVerifikacioniToken() {
        return verifikacioniToken;
    }

    public void setVerifikacioniToken(String verifikacioniToken) {
        this.verifikacioniToken = verifikacioniToken;
    }


}
