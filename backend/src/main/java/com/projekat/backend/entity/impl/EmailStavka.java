/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author Anđelija
 */
@Entity
@Table(name = "emailStavka")
public class EmailStavka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmailStavka")
    private Long id;

    @Column(name = "naslov")
    private String naslov;

    @Column(name = "primalac")
    private String primalac;

    @Column(name = "sadrzaj", columnDefinition = "TEXT")
    private String sadrzaj;

    @Column(name = "poslato")
    private Boolean poslato = false;

    @Column(name = "vremeKreiranja")
    private LocalDateTime vremeKreiranja = LocalDateTime.now();

    @Column(name = "vremeSlanja")
    private LocalDateTime vremeSlanja;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getPrimalac() {
        return primalac;
    }

    public void setPrimalac(String primalac) {
        this.primalac = primalac;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Boolean getPoslato() {
        return poslato;
    }

    public void setPoslato(Boolean poslato) {
        this.poslato = poslato;
    }

    public LocalDateTime getVremeKreiranja() {
        return vremeKreiranja;
    }

    public void setVremeKreiranja(LocalDateTime vremeKreiranja) {
        this.vremeKreiranja = vremeKreiranja;
    }

    public LocalDateTime getVremeSlanja() {
        return vremeSlanja;
    }

    public void setVremeSlanja(LocalDateTime vremeSlanja) {
        this.vremeSlanja = vremeSlanja;
    }


}