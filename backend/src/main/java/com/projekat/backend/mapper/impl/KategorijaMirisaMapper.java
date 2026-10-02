/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.mapper.impl;

import com.projekat.backend.dto.impl.KategorijaMirisaDto;
import com.projekat.backend.entity.impl.KategorijaMirisa;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anđelija
 */
@Component
public class KategorijaMirisaMapper {
    
    public KategorijaMirisaDto toDto(KategorijaMirisa e) {
        if (e == null) return null;

        KategorijaMirisaDto dto = new KategorijaMirisaDto();
        dto.setIdKategorijaMirisa(e.getIdKategorijaMirisa());
        dto.setNaziv(e.getNaziv());
        return dto;
    }

    public KategorijaMirisa toEntity(KategorijaMirisaDto dto) {
        if (dto == null) return null;

        KategorijaMirisa km = new KategorijaMirisa();
        km.setIdKategorijaMirisa(dto.getIdKategorijaMirisa());
        km.setNaziv(dto.getNaziv());
        return km;
    }
    
}
