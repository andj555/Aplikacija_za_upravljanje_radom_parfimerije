/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.mapper.impl;


import com.projekat.backend.dto.impl.KorisnikDto;
import com.projekat.backend.entity.enums.TipKorisnika;
import com.projekat.backend.entity.impl.Korisnik;
import java.util.Collections;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Anđelija
 */
@Component
public class KorisnikMapper 
{
    public KorisnikDto toDto(Korisnik e) {
        if (e == null) return null;

        KorisnikDto dto= new KorisnikDto();
        
        dto.setIdKorisnik(e.getIdKorisnik());
        dto.setPassword(e.getPassword());
        dto.setEmail(e.getEmail());
        dto.setIme(e.getIme());
        dto.setPrezime(e.getPrezime());
        dto.setTipKorisnika(e.getTipKorisnika());
        
        return dto;
    }

    public Korisnik toEntity(KorisnikDto dto) {
        
       if (dto == null) return null;

        Korisnik k = new Korisnik();
        
        dto.setIdKorisnik(dto.getIdKorisnik());
        k.setPassword(dto.getPassword() );
        k.setEmail(dto.getEmail());
        k.setIme(dto.getIme());
        k.setPrezime(dto.getPrezime() );
        k.setTipKorisnika(dto.getTipKorisnika() != null ? dto.getTipKorisnika() : TipKorisnika.KUPAC);
        
        return k;
    }

  
}
