/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.dto.impl;

import com.projekat.backend.entity.enums.Status;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author Anđelija
 */
public class OtpremnicaDto {
    
    
    private Long idOtpremnica;
    private LocalDate datumIzdavanja;
    private String brojOtpremnice;
    private Double iznos;
    private Double pdv;
    private Double iznosBezPdv;
    private String status;
    
    @NotNull(message = " Otpremnica je obavezna ")
    private Long idPorudzbina;
   
    private List<StavkaOtpremniceDto> stavke= new ArrayList<>();

    public OtpremnicaDto() {
    }

    public OtpremnicaDto(Long idOtpremnica, LocalDate datumIzdavanja, String brojOtpremnice, Double iznos, Double pdv, Double iznosBezPdv, String status, Long idPorudzbina) {
        this.idOtpremnica = idOtpremnica;
        this.datumIzdavanja = datumIzdavanja;
        this.brojOtpremnice = brojOtpremnice;
        this.iznos = iznos;
        this.pdv = pdv;
        this.iznosBezPdv = iznosBezPdv;
        this.status = status;
        this.idPorudzbina = idPorudzbina;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Long getIdPorudzbina() {
        return idPorudzbina;
    }

    public void setIdPorudzbina(Long idPorudzbina) {
        this.idPorudzbina = idPorudzbina;
    }

    public List<StavkaOtpremniceDto> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaOtpremniceDto> stavke) {
        this.stavke = stavke;
    }
    
    
}
