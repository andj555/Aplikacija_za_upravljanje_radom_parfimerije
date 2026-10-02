/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.mapper.impl;

import com.projekat.backend.dto.impl.PorudzbinaDto;
import com.projekat.backend.entity.enums.StatusIsporuke;
import com.projekat.backend.entity.enums.StatusPorudzbine;
import com.projekat.backend.entity.impl.Korisnik;
import com.projekat.backend.entity.impl.Porudzbina;
import com.projekat.backend.entity.impl.StavkaPorudzbine;
import com.projekat.backend.repository.KorisnikRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Anđelija
 */
public class PorudzbinaMapper {
    
    private StavkaPorudzbineMapper stavkaPorudzbineMapper = new StavkaPorudzbineMapper();
    
    @Autowired
    private KorisnikRepository KorisnikRepository;

    public PorudzbinaDto toDto(Porudzbina e) {
        if (e == null) return null;

       PorudzbinaDto dto = new PorudzbinaDto();
        dto.setIdPorudzbina(e.getIdPorudzbina());
        dto.setDatum(e.getDatum());
        dto.setPopust(e.getPopust());
        dto.setUkupanIznos(e.getUkupanIznos());
        dto.setStatusPorudzbine(e.getStatusPorudzbine() != null ? e.getStatusPorudzbine().name() : null);
        dto.setStatusIsporuke(e.getStatusIsporuke() != null ? e.getStatusIsporuke().name() : null);
        dto.setAdresaIsporuke(e.getAdresaIsporuke());
        
        if (e.getKorisnik() != null) {
            dto.setIdKorisnik(e.getKorisnik().getIdKorisnik());
            dto.setEmailKorisnika(e.getKorisnik().getEmail());
        }
        
      if (e.getStavke() != null) {
            dto.setStavke(e.getStavke().stream().map(stavkaPorudzbineMapper::toDto).collect(Collectors.toList()));
        }
        return dto;
       
    }

    public Porudzbina toEntity(PorudzbinaDto dto) {
        if (dto == null) return null;

        Porudzbina p = new Porudzbina();
        p.setIdPorudzbina(dto.getIdPorudzbina());
        p.setDatum(dto.getDatum());
        p.setPopust(dto.getPopust());
        p.setUkupanIznos(dto.getUkupanIznos());
        p.setStatusPorudzbine(dto.getStatusPorudzbine() != null ? StatusPorudzbine.valueOf(dto.getStatusPorudzbine()) : StatusPorudzbine.KREIRANA);
        p.setStatusIsporuke(dto.getStatusIsporuke() != null ? StatusIsporuke.valueOf(dto.getStatusIsporuke()) : StatusIsporuke.NEISPORUCENO);
        p.setAdresaIsporuke(dto.getAdresaIsporuke());

        if (dto.getIdKorisnik() != null) {
            Korisnik k = KorisnikRepository.findById(dto.getIdKorisnik()).orElse(null);
            p.setKorisnik(k);
        }

        if (dto.getStavke() != null) {
        List<StavkaPorudzbine> stavke = dto.getStavke().stream()
            .map(stavkaDto -> {
                StavkaPorudzbine stavka = stavkaPorudzbineMapper.toEntity(stavkaDto);
                stavka.setRb(null); 
                stavka.setPorudzbina(p); 
                return stavka;
            })
            .collect(Collectors.toList());
        p.setStavke(stavke);
    }
        return p;
    }


}
