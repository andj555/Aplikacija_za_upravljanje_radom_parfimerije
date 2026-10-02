/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.dto.impl;

import com.projekat.backend.entity.enums.StatusIsporuke;
import com.projekat.backend.entity.enums.Status;
import com.projekat.backend.entity.enums.StatusPorudzbine;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author Anđelija
 */
public class PorudzbinaDto {
    
    private Long idPorudzbina;

    @NotNull(message = "Morate uneti datum")
    private LocalDate datum;

    private Double ukupanIznos;

   
    private String statusPorudzbine;

    private String statusIsporuke;

    @NotBlank(message = "Morate uneti adresu isporuke")
    private String adresaIsporuke;

    @NotNull(message = " Korisnik je obavezan ")
    private Long idKorisnik;
    
    @NotNull(message = "Morate uneti popust")
    @DecimalMin(value = "0.0", message = "Popust ne moze biti manji od 0")
     @DecimalMax(value = "100.0", message = "Popust ne moze biti veci od 100")
    private Double popust;
    
    private String emailKorisnika;

     private List<StavkaPorudzbineDto> stavke = new ArrayList<>();

    public PorudzbinaDto() {
    }

    public PorudzbinaDto(Long idPorudzbina, LocalDate datum, Double ukupanIznos, String statusPorudzbine, String statusIsporuke, String adresaIsporuke, Long idKorisnik, String emailKorisnika) {
        this.idPorudzbina = idPorudzbina;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.statusPorudzbine = statusPorudzbine;
        this.statusIsporuke = statusIsporuke;
        this.adresaIsporuke = adresaIsporuke;
        this.idKorisnik = idKorisnik;
        this.emailKorisnika = emailKorisnika;
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

    public Double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(Double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public String getStatusPorudzbine() {
        return statusPorudzbine;
    }

    public void setStatusPorudzbine(String statusPorudzbine) {
        this.statusPorudzbine = statusPorudzbine;
    }

    public String getStatusIsporuke() {
        return statusIsporuke;
    }

    public void setStatusIsporuke(String statusIsporuke) {
        this.statusIsporuke = statusIsporuke;
    }

    public Double getPopust() {
        return popust;
    }

    public void setPopust(Double popust) {
        this.popust = popust;
    }

    public String getAdresaIsporuke() {
        return adresaIsporuke;
    }

    public void setAdresaIsporuke(String adresaIsporuke) {
        this.adresaIsporuke = adresaIsporuke;
    }


    public Long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getEmailKorisnika() {
        return emailKorisnika;
    }

    public void setEmailKorisnika(String emailKorisnika) {
        this.emailKorisnika = emailKorisnika;
    }


    public List<StavkaPorudzbineDto> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPorudzbineDto> stavke) {
        this.stavke = stavke;
    }
    
    
}
