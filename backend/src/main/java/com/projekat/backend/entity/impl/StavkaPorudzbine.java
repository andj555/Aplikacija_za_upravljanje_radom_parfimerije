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
@Table(name = "stavkaporudzbine")
public class StavkaPorudzbine 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rb")
    private Long rb;
    
    @Column(name = "kolicina")
    private Integer kolicina;
    
    @Column(name = "iznos")
    private Double iznos;
    
    @Column(name = "cenaPoKomadu")
    private Double cenaPoKomadu;
   
    @Column(name = "pdv")
    private Double pdv;
    
    @Column(name = "iznosBezPdv")
    private Double iznosBezPdv;
    
   @ManyToOne
   @JoinColumn(name = "idPorudzbina")
    private Porudzbina porudzbina;
        
    @ManyToOne
    @JoinColumn(name = "idProizvod")
    private Proizvod proizvod;

        public StavkaPorudzbine() {
        }

    public StavkaPorudzbine(Long rb, Integer kolicina, Double iznos, Double cenaPoKomadu, Double pdv, Double iznosBezPdv, Porudzbina porudzbina, Proizvod proizvod) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.cenaPoKomadu = cenaPoKomadu;
        this.pdv = pdv;
        this.iznosBezPdv = iznosBezPdv;
        this.porudzbina = porudzbina;
        this.proizvod = proizvod;
    }

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

        public Porudzbina getPorudzbina() {
            return porudzbina;
        }

        public void setPorudzbina(Porudzbina porudzbina) {
            this.porudzbina = porudzbina;
        }
        

        public Proizvod getProizvod() {
            return proizvod;
        }

        public void setProizvod(Proizvod proizvod) {
            this.proizvod = proizvod;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            StavkaPorudzbine that = (StavkaPorudzbine) o;
            return rb != null && rb.equals(that.rb);
        }

        @Override
        public int hashCode() {
            return getClass().hashCode();
        }
        
}