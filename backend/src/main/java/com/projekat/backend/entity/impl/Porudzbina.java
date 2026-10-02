/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.entity.impl;

import com.projekat.backend.entity.enums.StatusPorudzbine;
import com.projekat.backend.entity.enums.StatusIsporuke;
import com.projekat.backend.entity.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;
/**
 *
 * @author Anđelija
 */
@Entity
@Table(name = "porudzbina")
public class Porudzbina 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPorudzbina")
    private Long idPorudzbina;
    
    @Column(name = "datum")
    private LocalDate datum;
    
    @Column(name = "ukupanIznos")
    private Double ukupanIznos;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "statusPorudzbine")
    private StatusPorudzbine statusPorudzbine;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "statusIsporuke")
    private StatusIsporuke statusIsporuke;
    
    @Column(name = "adresaIsporuke")
    private String adresaIsporuke;
    
    @Column(name = "popust")
    private Double popust;

    @ManyToOne
    @JoinColumn(name = "idKorisnik")
    private Korisnik korisnik;
    
    @OneToMany(mappedBy = "porudzbina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StavkaPorudzbine> stavke = new ArrayList<>();

    @OneToMany(mappedBy = "porudzbina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Otpremnica> otpremnice = new ArrayList<>();

    public Porudzbina() {
        
    }

    public Porudzbina(Long idPorudzbina, LocalDate datum, Double ukupanIznos, StatusPorudzbine statusPorudzbine, StatusIsporuke statusIsporuke, String adresaIsporuke, Double popust, Korisnik korisnik) {
        this.idPorudzbina = idPorudzbina;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.statusPorudzbine = statusPorudzbine;
        this.statusIsporuke = statusIsporuke;
        this.adresaIsporuke = adresaIsporuke;
        this.popust = popust;
        this.korisnik = korisnik;
    }

  
    public Long getIdPorudzbina() {
        return idPorudzbina;
    }

    public void setIdPorudzbina(Long idPorudzbina) {
        this.idPorudzbina = idPorudzbina;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Double getPopust() {
        return popust;
    }

    public void setPopust(Double popust) {
        this.popust = popust;
    }

    public Double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(Double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public StatusPorudzbine getStatusPorudzbine() {
        return statusPorudzbine;
    }

    public void setStatusPorudzbine(StatusPorudzbine statusPorudzbine) {
        this.statusPorudzbine = statusPorudzbine;
    }

    public StatusIsporuke getStatusIsporuke() {
        return statusIsporuke;
    }

    public void setStatusIsporuke(StatusIsporuke statusIsporuke) {
        this.statusIsporuke = statusIsporuke;
    }

    public String getAdresaIsporuke() {
        return adresaIsporuke;
    }

    public void setAdresaIsporuke(String adresaIsporuke) {
        this.adresaIsporuke = adresaIsporuke;
    }

      public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<StavkaPorudzbine> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPorudzbine> stavke) {
        this.stavke = stavke;
    }

    public List<Otpremnica> getOtpremnice() {
        return otpremnice;
    }

    public void setOtpremnice(List<Otpremnica> otpremnice) {
        this.otpremnice = otpremnice;
    }

}