/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.mapper.impl;

import com.projekat.backend.dto.impl.StavkaOtpremniceDto;
import com.projekat.backend.entity.impl.Otpremnica;
import com.projekat.backend.entity.impl.StavkaOtpremnice;

/**
 *
 * @author Anđelija
 */
public class StavkaOtpremniceMapper {
    
      public StavkaOtpremniceDto toDto(StavkaOtpremnice e) {
        if (e == null) return null;

        StavkaOtpremniceDto dto = new StavkaOtpremniceDto();
        dto.setRb(e.getRb());
        dto.setKolicina(e.getKolicina());
        dto.setIznos(e.getIznos());
        dto.setCenaPoKomadu(e.getCenaPoKomadu());
        dto.setPdv(e.getPdv());
        dto.setIznosBezPdv(e.getIznosBezPdv());

        if (e.getIdOtpremnica() != null) {
          
           dto.setIdOtpremnica(e.getIdOtpremnica().getIdOtpremnica());
        }
        
         if (e.getProizvod() != null) {
            dto.setIdProizvod(e.getProizvod().getIdProizvod());
            dto.setNazivProizvoda(e.getProizvod().getNaziv());
        }
         
          return dto;
    }
      
      public StavkaOtpremnice toEntity(StavkaOtpremniceDto dto) {
          
        if (dto == null) return null;

        StavkaOtpremnice so = new StavkaOtpremnice();
        so.setRb(dto.getRb());
        so.setKolicina(dto.getKolicina());
        so.setIznos(dto.getIznos());
        so.setCenaPoKomadu(dto.getCenaPoKomadu());
        so.setPdv(dto.getPdv());
        so.setIznosBezPdv(dto.getIznosBezPdv());

        if (dto.getIdOtpremnica() != null) {
            
            Otpremnica o = new Otpremnica();
            o.setIdOtpremnica(dto.getIdOtpremnica());
           so.setIdOtpremnica(o);
            
        }

        return so;
    }
      
}
