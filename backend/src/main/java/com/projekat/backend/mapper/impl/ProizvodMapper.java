/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.mapper.impl;

import com.projekat.backend.dto.impl.ProizvodDto;
import com.projekat.backend.entity.impl.KategorijaMirisa;
import com.projekat.backend.entity.impl.PoreskaStopa;
import com.projekat.backend.entity.impl.Proizvod;



/**
 *
 * @author Anđelija
 */
public class ProizvodMapper {
        public ProizvodDto toDto(Proizvod e) {
        if (e == null) return null;

        ProizvodDto dto = new ProizvodDto();
        dto.setIdProizvod(e.getIdProizvod());
        dto.setNaziv(e.getNaziv());
        dto.setCena(e.getCena());
        dto.setKolicinaNaStanju(e.getKolicinaNaStanju());
        dto.setOpis(e.getOpis());
        dto.setMarka(e.getMarka());
        dto.setSlika(e.getSlika());
        dto.setKategorijaPol(e.getKategorijaPol());
        dto.setRezervisanoStanje(e.getRezervisanoStanje());
        
        if (e.getKategorijaMirisa() != null) {
            
            
            dto.setIdKategorijaMirisa(e.getKategorijaMirisa().getIdKategorijaMirisa());
            dto.setNazivKategorijeMirisa(e.getKategorijaMirisa().getNaziv());
        }

        if (e.getPoreskaStopa() != null) {
            
            dto.setIdPoreskaStopa(e.getPoreskaStopa().getIdPoreskaStopa());
            dto.setNazivPoreskeStope(e.getPoreskaStopa().getNaziv());
            
        }

        return dto;
    }

    public Proizvod toEntity(ProizvodDto dto) {
        if (dto == null) return null;

        Proizvod p = new Proizvod();
        p.setIdProizvod(dto.getIdProizvod());
        p.setNaziv(dto.getNaziv());
        p.setCena(dto.getCena());
        p.setKolicinaNaStanju(dto.getKolicinaNaStanju());
        p.setOpis(dto.getOpis());
        p.setMarka(dto.getMarka());
        p.setSlika(dto.getSlika());
        p.setKategorijaPol(dto.getKategorijaPol());
        p.setRezervisanoStanje(dto.getRezervisanoStanje());

        if (dto.getIdKategorijaMirisa() != null) {
            
            KategorijaMirisa km = new KategorijaMirisa();
            km.setIdKategorijaMirisa(dto.getIdKategorijaMirisa());
            p.setKategorijaMirisa(km);
            
        }
        if (dto.getIdPoreskaStopa() != null) {
            
            PoreskaStopa ps = new PoreskaStopa();
            ps.setIdPoreskaStopa(dto.getIdPoreskaStopa());
            p.setPoreskaStopa(ps);
        }

        return p;
    }
}
