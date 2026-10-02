/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.controller;

import com.projekat.backend.ZahtevPorudzbina;
import com.projekat.backend.dto.impl.PorudzbinaDto;
import com.projekat.backend.entity.enums.StatusPorudzbine;
import com.projekat.backend.entity.impl.Porudzbina;
import com.projekat.backend.entity.impl.StavkaPorudzbine;
import com.projekat.backend.mapper.impl.PorudzbinaMapper;
import com.projekat.backend.mapper.impl.StavkaPorudzbineMapper;
import com.projekat.backend.service.PorudzbinaService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anđelija
 */
@RestController
@RequestMapping("/api/porudzbine")
@CrossOrigin(origins = "http://localhost:5173")
public class PorudzbinaController {
    
    @Autowired
    private PorudzbinaService porudzbinaService;
    private PorudzbinaMapper porudzbinaMapper = new PorudzbinaMapper();
    private StavkaPorudzbineMapper stavkaMapper = new StavkaPorudzbineMapper();
    
    @GetMapping
    public List<PorudzbinaDto> getAll() {
        return porudzbinaService.getAll().stream().map(porudzbinaMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PorudzbinaDto getById(@PathVariable Long id) {
        return porudzbinaMapper.toDto(porudzbinaService.getById(id));
    }

    @GetMapping("/korisnik/{korisnikId}")
    public List<PorudzbinaDto> getByKorisnik(@PathVariable Long korisnikId) {
        return porudzbinaService.getByKorisnik(korisnikId).stream().map(porudzbinaMapper::toDto).collect(Collectors.toList());
    }
    
    @PostMapping("/poruci")
    public ResponseEntity<?> poruci(@RequestBody ZahtevPorudzbina zahtev) {
        
        System.out.println("ZAHTEV : " + zahtev);
    if (zahtev != null) {
        System.out.println("Porudzbina: " + zahtev.getPorudzbina());
    }
        Porudzbina nova = porudzbinaMapper.toEntity(zahtev.getPorudzbina());
        List<StavkaPorudzbine> stavke = (zahtev.getStavke() != null) ? zahtev.getStavke().stream()
            .map(stavkaMapper::toEntity)
            .collect(Collectors.toList()) : new ArrayList<>();
        Porudzbina sacuvana = porudzbinaService.saveWithStavke(nova, stavke);
        
        return ResponseEntity.ok(porudzbinaMapper.toDto(sacuvana));
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PorudzbinaDto> izmeniPopust(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Double popust = Double.valueOf(body.get("popust").toString());
        Porudzbina izmenjena = porudzbinaService.izmeniPopust(id, popust);
        return ResponseEntity.ok(porudzbinaMapper.toDto(izmenjena));
    }
    
    @PostMapping("/otkazi/{id}")
public ResponseEntity<?> otkazi(@PathVariable Long id) {
    Porudzbina sacuvana = porudzbinaService.otkazi(id);
    if (sacuvana == null) {
        Map<String, String> error = new HashMap<>();
        error.put("Greska", "Porudzbina ne postoji");
        return ResponseEntity.status(404).body(error);
    }
    return ResponseEntity.ok(porudzbinaMapper.toDto(sacuvana));
}
}
