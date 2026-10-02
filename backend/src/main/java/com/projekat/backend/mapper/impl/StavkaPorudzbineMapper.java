/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.mapper.impl;

import com.projekat.backend.dto.impl.StavkaPorudzbineDto;
import com.projekat.backend.entity.impl.Porudzbina;
import com.projekat.backend.entity.impl.Proizvod;
import com.projekat.backend.entity.impl.StavkaPorudzbine;

/**
 *
 * @author Anđelija
 */
public class StavkaPorudzbineMapper {
    
     public StavkaPorudzbineDto toDto(StavkaPorudzbine e) {
        if (e == null) return null;

        StavkaPorudzbineDto dto = new StavkaPorudzbineDto();
        dto.setRb(e.getRb());
        dto.setKolicina(e.getKolicina());
        dto.setIznos(e.getIznos());
        dto.setCenaPoKomadu(e.getCenaPoKomadu());
        dto.setPdv(e.getPdv());
        dto.setIznosBezPdv(e.getIznosBezPdv());

        if (e.getPorudzbina() != null) {
            dto.setIdPorudzbina(e.getPorudzbina().getIdPorudzbina());
        }

            if (e.getProizvod() != null) {
            dto.setNazivProizvoda(e.getProizvod().getNaziv());
            dto.setSlikaProizvoda(e.getProizvod().getSlika());
            dto.setIdProizvod(e.getProizvod().getIdProizvod());
        }
        return dto;
    }

    public StavkaPorudzbine toEntity(StavkaPorudzbineDto dto) {
        if (dto == null) return null;

        StavkaPorudzbine s = new StavkaPorudzbine();
        s.setRb(dto.getRb());
        s.setKolicina(dto.getKolicina());
        s.setIznos(dto.getIznos());
        s.setCenaPoKomadu(dto.getCenaPoKomadu());
        s.setPdv(dto.getPdv());
        s.setIznosBezPdv(dto.getIznosBezPdv());

        if (dto.getIdPorudzbina() != null) {
        Porudzbina p = new Porudzbina();
        p.setIdPorudzbina(dto.getIdPorudzbina());
        s.setPorudzbina(p);
        }

       if (dto.getIdProizvod() != null) {
        Proizvod pr = new Proizvod();
        pr.setIdProizvod(dto.getIdProizvod());
        s.setProizvod(pr);
        }

        return s;
    }

}
