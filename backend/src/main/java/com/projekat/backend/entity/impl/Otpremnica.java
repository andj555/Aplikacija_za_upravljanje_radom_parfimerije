/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.entity.impl;

import com.projekat.backend.entity.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Anđelija
 */
@Entity
@Table(name = "otpremnica")
public class Otpremnica 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOtpremnica")
    private Long idOtpremnica;
    
    @Column(name = "datumIzdavanja")
     private LocalDate datumIzdavanja;
    
    @Column(name = "brojOtpremnice")
    private String brojOtpremnice;
    
    @Column(name = "iznos")
    private Double iznos;
    
    @Column(name = "pdv")
    private Double pdv;
    
    @Column(name = "iznosBezPdv")
    private Double iznosBezPdv;
    
   @ManyToOne
    @JoinColumn(name = "idPorudzbina")
    private Porudzbina porudzbina;
   
    @Enumerated(EnumType.STRING)
    private Status status;
   
   @OneToMany(mappedBy = "otpremnica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StavkaOtpremnice> stavke = new ArrayList<>();
   
    public Otpremnica() {
    }

    public Otpremnica(Long idOtpremnica, LocalDate datumIzdavanja, String brojOtpremnice, Double iznos, Double pdv, Double iznosBezPdv, Porudzbina porudzbina, Status status) {
        this.idOtpremnica = idOtpremnica;
        this.datumIzdavanja = datumIzdavanja;
        this.brojOtpremnice = brojOtpremnice;
        this.iznos = iznos;
        this.pdv = pdv;
        this.iznosBezPdv = iznosBezPdv;
        this.porudzbina = porudzbina;
        this.status = status;
    }

  

    public Long getIdOtpremnica() {
        return idOtpremnica;
    }

    public void setIdOtpremnica(Long idOtpremnica) {
        this.idOtpremnica = idOtpremnica;
    }

    public LocalDate getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDate datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public String getBrojOtpremnice() {
        return brojOtpremnice;
    }

    public void setBrojOtpremnice(String brojOtpremnice) {
        this.brojOtpremnice = brojOtpremnice;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public Double getPdv() {
        return pdv;
    }

    public void setPdv(Double pdv) {
        this.pdv = pdv;
    }

    public Double getIznosBezPdv() {
        return iznosBezPdv;
    }

    public void setIznosBezPdv(Double iznosBezPdv) {
        this.iznosBezPdv = iznosBezPdv;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public List<StavkaOtpremnice> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaOtpremnice> stavke) {
        this.stavke = stavke;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
