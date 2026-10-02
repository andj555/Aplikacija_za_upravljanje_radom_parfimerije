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
@Table(name = "proizvod")
public class Proizvod 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProizvod")
    private Long idProizvod;
    
    @Column(name = "naziv")
    private String naziv;
    
    @Column(name = "cena")
    private Double cena;
    
    @Column(name = "kolicinaNaStanju")
    private Long kolicinaNaStanju;
    
    @Column(name = "opis")
    private String opis;
    
    @Column(name = "marka")
    private String marka;
    
    @Column(name = "slika")
    private String slika;
    
    @Column(name = "kategorijaPol")
    private String kategorijaPol;
    
    @ManyToOne
    @JoinColumn(name = "idKategorijaMirisa")
    private KategorijaMirisa kategorijaMirisa;
    
    @ManyToOne
    @JoinColumn(name = "idPoreskaStopa")
    private PoreskaStopa poreskaStopa;
    
    @Column(name="rezervisanoStanje")
    private int rezervisanoStanje=0;

    public Proizvod() {
    }

    public Proizvod(Long idProizvod, String naziv, Double cena, Long kolicinaNaStanju, String opis, String marka, String slika, String kategorijaPol, KategorijaMirisa kategorijaMirisa, PoreskaStopa poreskaStopa) {
        this.idProizvod = idProizvod;
        this.naziv = naziv;
        this.cena = cena;
        this.kolicinaNaStanju = kolicinaNaStanju;
        this.opis = opis;
        this.marka = marka;
        this.slika = slika;
        this.kategorijaPol = kategorijaPol;
        this.kategorijaMirisa = kategorijaMirisa;
        this.poreskaStopa = poreskaStopa;
    }

    public Proizvod(Long idProizvod, String naziv, Double cena, Long kolicinaNaStanju, String opis, String marka, String slika, String kategorijaPol, 
            KategorijaMirisa kategorijaMirisa, PoreskaStopa poreskaStopa, int rs) {
        this.idProizvod = idProizvod;
        this.naziv = naziv;
        this.cena = cena;
        this.kolicinaNaStanju = kolicinaNaStanju;
        this.opis = opis;
        this.marka = marka;
        this.slika = slika;
        this.kategorijaPol = kategorijaPol;
        this.kategorijaMirisa = kategorijaMirisa;
        this.poreskaStopa = poreskaStopa;
        this.rezervisanoStanje=rs;
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

    public KategorijaMirisa getKategorijaMirisa() {
        return kategorijaMirisa;
    }

    public void setKategorijaMirisa(KategorijaMirisa kategorijaMirisa) {
        this.kategorijaMirisa = kategorijaMirisa;
    }

    public PoreskaStopa getPoreskaStopa() {
        return poreskaStopa;
    }

    public void setPoreskaStopa(PoreskaStopa poreskaStopa) {
        this.poreskaStopa = poreskaStopa;
    }

    public String getKategorijaPol() {
        return kategorijaPol;
    }

    public void setKategorijaPol(String kategorijaPol) {
        this.kategorijaPol = kategorijaPol;
    }

    public Integer getRezervisanoStanje() {
        return rezervisanoStanje;
    }

    public void setRezervisanoStanje(Integer rezervisanoStanje) {
        this.rezervisanoStanje = rezervisanoStanje;
    }
    
}