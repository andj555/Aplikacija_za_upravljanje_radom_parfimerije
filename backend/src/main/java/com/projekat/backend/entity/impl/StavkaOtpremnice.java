/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.entity.impl;

import jakarta.persistence.*;
/**
 *
 * @author Anđelija
 */
@Entity
@Table(name = "stavkaotpremnice")
public class StavkaOtpremnice 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rb")
    private Long rb;
    
    @Column(name = "kolicina")
    private int kolicina;
    
    @Column(name = "iznos")
    private Double iznos;
    
    @Column(name = "cenaPoKomadu")
    private Double cenaPoKomadu;
    
    @Column(name = "pdv")
    private Double pdv;
    
    @Column(name = "iznosBezPdv")
    private Double iznosBezPdv;
    
   @ManyToOne
    @JoinColumn(name = "idOtpremnica")
    private Otpremnica otpremnica;
   
   @ManyToOne
    @JoinColumn(name = "idProizvod")
    private Proizvod proizvod;

    public StavkaOtpremnice() {
    }

    public StavkaOtpremnice(Long rb, int kolicina, Double iznos, Double cenaPoKomadu, Double pdv, Double iznosBezPdv, Otpremnica otpremnica, Proizvod proizvod) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.cenaPoKomadu = cenaPoKomadu;
        this.pdv = pdv;
        this.iznosBezPdv = iznosBezPdv;
        this.otpremnica = otpremnica;
        this.proizvod = proizvod;
    }

    public Long getRb() {
        return rb;
    }

    public void setRb(Long rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public Double getCenaPoKomadu() {
        return cenaPoKomadu;
    }

    public void setCenaPoKomadu(Double cenaPoKomadu) {
        this.cenaPoKomadu = cenaPoKomadu;
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

    public Otpremnica getIdOtpremnica() {
        return otpremnica;
    }

    public void setIdOtpremnica(Otpremnica idOtpremnica) {
        this.otpremnica = idOtpremnica;
    }

    public Otpremnica getOtpremnica() {
        return otpremnica;
    }

    public void setOtpremnica(Otpremnica otpremnica) {
        this.otpremnica = otpremnica;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }
    
    
}