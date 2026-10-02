/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.mapper.impl;

import com.projekat.backend.dto.impl.OtpremnicaDto;
import com.projekat.backend.entity.enums.Status;
import com.projekat.backend.entity.impl.Otpremnica;
import com.projekat.backend.entity.impl.Porudzbina;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anđelija
 */
@Component
public class OtpremnicaMapper {
    
    private StavkaOtpremniceMapper stavkaOtpremniceMapper = new StavkaOtpremniceMapper();
    public OtpremnicaDto toDto(Otpremnica e) {
     
       if (e == null) return null;

        OtpremnicaDto dto = new OtpremnicaDto();
        dto.setIdOtpremnica(e.getIdOtpremnica());
        dto.setDatumIzdavanja(e.getDatumIzdavanja());
        dto.setBrojOtpremnice(e.getBrojOtpremnice());
        dto.setIznos(e.getIznos());
        dto.setPdv(e.getPdv());
        dto.setStatus(e.getStatus() != null ? e.getStatus().name() : null);
        dto.setIznosBezPdv(e.getIznosBezPdv());
    
        if (e.getPorudzbina() != null) {
             dto.setIdPorudzbina(e.getPorudzbina().getIdPorudzbina()); 
        }

        
          if (e.getPorudzbina() != null) {
            dto.setIdPorudzbina(e.getPorudzbina().getIdPorudzbina());
        }

        if (e.getStavke() != null) {
            
            dto.setStavke(e.getStavke().stream().map(stavkaOtpremniceMapper::toDto).collect(Collectors.toList()));
        }

        return dto;
    }

    public Otpremnica toEntity(OtpremnicaDto dto) {
        if (dto == null) return null;

        Otpremnica o = new Otpremnica();
        o.setIdOtpremnica(dto.getIdOtpremnica());
        o.setDatumIzdavanja(dto.getDatumIzdavanja());
        o.setBrojOtpremnice(dto.getBrojOtpremnice());
        o.setIznos(dto.getIznos());
        o.setStatus(dto.getStatus() != null ? Status.valueOf(dto.getStatus()) : Status.KREIRANA);
        o.setPdv(dto.getPdv());
        o.setIznosBezPdv(dto.getIznosBezPdv());

        if (dto.getIdPorudzbina() != null) {
            
            Porudzbina p = new Porudzbina();
            p.setIdPorudzbina(dto.getIdPorudzbina());
            o.setPorudzbina(p);
        }

        if (dto.getStavke() != null) {
            
            o.setStavke(dto.getStavke().stream().map(stavkaOtpremniceMapper::toEntity).collect(Collectors.toList()));
        }

        return o;
    }
    
}
