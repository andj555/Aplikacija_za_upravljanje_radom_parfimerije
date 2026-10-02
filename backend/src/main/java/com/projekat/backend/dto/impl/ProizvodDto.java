/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 *
 * @author Anđelija
 */
public class ProizvodDto {
    
    private Long idProizvod;

    @NotBlank(message = "Morate uneti naziv")
    private String naziv;

    @NotNull(message = "Morate uneti cenu")
    @Positive(message = "Cena mora biti veca od nule")
    private Double cena;

    @NotNull(message = "Morate uneti kolicinu")
    @PositiveOrZero(message = "Kolicina na stanju ne moze biti negativna")
    private Long kolicinaNaStanju;

    private String opis;

    @NotBlank(message = "Morate uneti marku")
    private String marka;
    
    @Pattern(regexp = "^(m|z|u)$", message = "Izaberite jednu od opcija: 'm', 'z' ili 'u'")
    @NotBlank(message = "Morate uneti kategoriju pola")
    private String kategorijaPol;

    private String slika;

    @NotNull(message = "Morate izabrati kategoriju mirisa")
    private Long idKategorijaMirisa;
    
    private String nazivKategorijeMirisa;

    private Long idPoreskaStopa;
    private  String nazivPoreskeStope; 

    private int rezervisanoStanje;

//
    public ProizvodDto() {
    }

    public ProizvodDto(Long idProizvod, String naziv, Double cena, Long kolicinaNaStanju, String opis, String marka, String kategorijaPol, String slika, Long idKategorijaMirisa, String nazivKategorijeMirisa, Long idPoreskaStopa, String nazivPoreskeStope) {
        this.idProizvod = idProizvod;
        this.naziv = naziv;
        this.cena = cena;
        this.kolicinaNaStanju = kolicinaNaStanju;
        this.opis = opis;
        this.marka = marka;
        this.kategorijaPol = kategorijaPol;
        this.slika = slika;
        this.idKategorijaMirisa = idKategorijaMirisa;
        this.nazivKategorijeMirisa = nazivKategorijeMirisa;
        this.idPoreskaStopa = idPoreskaStopa;
        this.nazivPoreskeStope = nazivPoreskeStope;
    }

    public Long getIdProizvod() {
        return idProizvod;
    }

    public void setIdProizvod(Long idProizvod) {
        this.idProizvod = idProizvod;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public int getRezervisanoStanje() {
        return rezervisanoStanje;
    }

    public void setRezervisanoStanje(int rezervisanoStanje) {
        this.rezervisanoStanje = rezervisanoStanje;
    }

    public String getNazivPoreskeStope() {
        return nazivPoreskeStope;
    }

    public void setNazivPoreskeStope(String nazivPoreskeStope) {
        this.nazivPoreskeStope = nazivPoreskeStope;
    }

    public Long getKolicinaNaStanju() {
        return kolicinaNaStanju;
    }

    public void setKolicinaNaStanju(Long kolicinaNaStanju) {
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Long getIdKategorijaMirisa() {
        return idKategorijaMirisa;
    }

    public void setIdKategorijaMirisa(Long idKategorijaMirisa) {
        this.idKategorijaMirisa = idKategorijaMirisa;
    }

    public String getNazivKategorijeMirisa() {
        return nazivKategorijeMirisa;
    }

    public void setNazivKategorijeMirisa(String nazivKategorijeMirisa) {
        this.nazivKategorijeMirisa = nazivKategorijeMirisa;
    }

    public Long getIdPoreskaStopa() {
        return idPoreskaStopa;
    }

    public void setIdPoreskaStopa(Long idPoreskaStopa) {
        this.idPoreskaStopa = idPoreskaStopa;
    }

    public String getKategorijaPol() {
        return kategorijaPol;
    }

    public void setKategorijaPol(String kategorijaPol) {
        this.kategorijaPol = kategorijaPol;
    }

    
}
