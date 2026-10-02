/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.mapper.impl;

import com.projekat.backend.dto.impl.PoreskaStopaDto;
import com.projekat.backend.entity.impl.PoreskaStopa;

/**
 *
 * @author Anđelija
 */
public class PoreskaStopaMapper {
      
    public PoreskaStopaDto toDto(PoreskaStopa e) {
        
        if (e == null) return null;

        PoreskaStopaDto dto = new PoreskaStopaDto();
        dto.setIdPoreskaStopa(e.getIdPoreskaStopa());
        dto.setProcenat(e.getProcenat());
        dto.setNaziv(e.getNaziv());

        return dto;
        
    }
    
        public PoreskaStopa toEntity(PoreskaStopaDto dto) {
            
            if (dto == null) return null;

        PoreskaStopa ps = new PoreskaStopa();
        
        ps.setIdPoreskaStopa(dto.getIdPoreskaStopa());
        ps.setProcenat(dto.getProcenat());
        ps.setNaziv(dto.getNaziv());

        return ps;
    }
}
