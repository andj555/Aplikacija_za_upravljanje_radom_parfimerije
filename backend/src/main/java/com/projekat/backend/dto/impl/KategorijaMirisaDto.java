/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.dto.impl;

/**
 *
 * @author Anđelija
 */
public class KategorijaMirisaDto {
    
    private Long idKategorijaMirisa;
        private String naziv;

    public KategorijaMirisaDto(Long idKategorijaMirisa, String naziv) {
        this.idKategorijaMirisa = idKategorijaMirisa;
        this.naziv = naziv;
    }

        public KategorijaMirisaDto() {
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
