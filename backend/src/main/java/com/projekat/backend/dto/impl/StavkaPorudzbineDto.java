/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.dto.impl;

import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anđelija
 */
public class StavkaPorudzbineDto {
    
       private Long rb;

    @NotNull(message = "Morate uneti kolicinu")
    @Min(value= 1, message ="Kolicina mora da bude bar 1")
    private Integer kolicina;

    private Double iznos;

    @NotNull(message = "Morate uneti cenu po komadu ")
    @Positive(message = "Cena po komadu mora da bude veca od nule")
    private Double cenaPoKomadu;

    private Double pdv;
    
     private Double iznosBezPdv;

    @NotNull(message = " Proizvod je obavezan ")
    private Long idProizvod;
    
    private String nazivProizvoda;
    private String slikaProizvoda;
    
    private Long idPorudzbina;


    public Long getRb() {
        return rb;
    }

    public void setRb(Long rb) {
        this.rb = rb;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
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

   public Long getIdPorudzbina() {
        return idPorudzbina;
    }

    public void setIdPorudzbina(Long idPorudzbina) {
        this.idPorudzbina = idPorudzbina;
    }

    public Long getIdProizvod() {
        return idProizvod;
    }

    public void setIdProizvod(Long idProizvod) {
        this.idProizvod = idProizvod;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public String getSlikaProizvoda() {
        return slikaProizvoda;
    }

    public void setSlikaProizvoda(String slikaProizvoda) {
        this.slikaProizvoda = slikaProizvoda;
    }
    
}
