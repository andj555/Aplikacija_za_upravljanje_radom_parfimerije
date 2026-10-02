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


/**
 *
 * @author Anđelija
 */

@Entity
@Table(name = "kategorijamirisa")
public class KategorijaMirisa 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKategorijaMirisa")
    private Long idKategorijaMirisa;
    
    @Column(name = "naziv")
    private String naziv;

    public KategorijaMirisa(Long idKategorijaMirisa, String naziv) {
        this.idKategorijaMirisa = idKategorijaMirisa;
        this.naziv = naziv;
    }

    public KategorijaMirisa() {
    }

    public Long getIdKategorijaMirisa() {
        return idKategorijaMirisa;
    }

    public void setIdKategorijaMirisa(Long idKategorijaMirisa) {
        this.idKategorijaMirisa = idKategorijaMirisa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
}