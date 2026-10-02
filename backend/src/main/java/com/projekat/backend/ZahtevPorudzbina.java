/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend;

import com.projekat.backend.dto.impl.PorudzbinaDto;
import com.projekat.backend.dto.impl.StavkaPorudzbineDto;
import java.util.List;

/**
 *
 * @author AnÄ‘elija
 */
public class ZahtevPorudzbina {
    
  private PorudzbinaDto porudzbina;
  private List<StavkaPorudzbineDto> stavke;
  

   public PorudzbinaDto getPorudzbina() {
       
       return porudzbina; 
   }
   public void setPorudzbina(PorudzbinaDto porudzbina) { 
       
       this.porudzbina = porudzbina; 
   }
   public List<StavkaPorudzbineDto> getStavke() { 
       
       return stavke;
   }
   public void setStavke(List<StavkaPorudzbineDto> stavke) { 
       
       this.stavke = stavke; 
   }
   
}