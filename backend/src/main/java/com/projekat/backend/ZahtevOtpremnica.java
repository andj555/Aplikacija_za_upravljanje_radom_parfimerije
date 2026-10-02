/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend;

import java.util.List;

/**
 *
 * @author Anđelija
 */
public class ZahtevOtpremnica {
   
     private Long idPorudzbina;
    private List<ZahtevStavkaOtpremniceDto> stavke;

    public static class ZahtevStavkaOtpremniceDto {
        private Long idProizvod;
        private int kolicina;

        public Long getIdProizvod(){
            return idProizvod;
        }
      public void setProizvodId(Long idProizvod) {
          
            this.idProizvod = idProizvod; 
        }
        public int getKolicina() {
            
            return kolicina; 
        }
        
        public void setKolicina(int kolicina) {
            this.kolicina = kolicina;
        }
        
    }

    public Long getIdPorudzbina() {
        return idPorudzbina; 
    }
    public void setIdPorudzbina(Long idPorudzbina) {
        
        this.idPorudzbina = idPorudzbina; 
    }
    public List<ZahtevStavkaOtpremniceDto> getStavke() {
        return stavke; 
    }
    public void setStavke(List<ZahtevStavkaOtpremniceDto> stavke) { 
        this.stavke = stavke; 
    }
 
}