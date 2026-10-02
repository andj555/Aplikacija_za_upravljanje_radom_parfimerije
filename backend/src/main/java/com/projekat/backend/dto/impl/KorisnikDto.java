/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.dto.impl;

import com.projekat.backend.entity.enums.TipKorisnika;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Anđelija
 */
public class KorisnikDto {
    private Long idKorisnik;

    @NotBlank(message = "Morate uneti email")
    @Email(message = "Email nije validan")
    private String email;

    @NotBlank(message = "Morate uneti lozinku")
    @Size(min = 6, message = "Lozinka mora da ima bar 6 karaktera")
    private String password;

    @NotBlank(message = "Morate uneti ime")
    private String ime;

    @NotBlank(message = "Morate uneti prezime")
    private String prezime;

    @NotNull(message = "Morate da izaberete tip korisnika")
    private TipKorisnika tipKorisnika;

    public KorisnikDto() {
    }

    public KorisnikDto(Long idKorisnik, String email, String password, String ime, String prezime, TipKorisnika tipKorisnika) {
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
    
}
