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
@Table(name = "poreskastopa")
public class PoreskaStopa 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPoreskaStopa")
    private Long idPoreskaStopa;
    
    @Column(name = "procenat")
    private Double procenat;
    
    @Column(name = "naziv")
    private String naziv;

    public PoreskaStopa() {
    }

    public PoreskaStopa(Long idPoreskaStopa, Double procenat, String naziv) {
        this.idPoreskaStopa = idPoreskaStopa;
        this.procenat = procenat;
        this.naziv = naziv;
    }

    public Long getIdPoreskaStopa() {
        return idPoreskaStopa;
    }

    public void setIdPoreskaStopa(Long idPoreskaStopa) {
        this.idPoreskaStopa = idPoreskaStopa;
    }

    public Double getProcenat() {
        return procenat;
    }

    public void setProcenat(Double procenat) {
        this.procenat = procenat;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
