/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.controller;

import com.projekat.backend.ZahtevOtpremnica;
import com.projekat.backend.dto.impl.OtpremnicaDto;
import com.projekat.backend.entity.impl.Otpremnica;
import com.projekat.backend.mapper.impl.OtpremnicaMapper;
import com.projekat.backend.service.OtpremnicaService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anđelija
 */
@RestController
@RequestMapping("/api/otpremnice")
@CrossOrigin(origins = "http://localhost:5173")
public class OtpremnicaController {
    
    @Autowired
    private OtpremnicaService otpremnicaService;
    @Autowired
    private OtpremnicaMapper otpremnicaMapper = new OtpremnicaMapper();
    
    @GetMapping
    public List<OtpremnicaDto> getAll() {
        return otpremnicaService.getAll().stream().map(otpremnicaMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OtpremnicaDto getById(@PathVariable Long id) {
        return otpremnicaMapper.toDto(otpremnicaService.getById(id));
    }
    
    @PostMapping("/generisi")
    public ResponseEntity<?> generisi(@RequestBody ZahtevOtpremnica zahtev) {
        List<OtpremnicaService.ZahtevStavka> stavke = zahtev.getStavke().stream().map(s -> {
            OtpremnicaService.ZahtevStavka zs = new OtpremnicaService.ZahtevStavka();
            zs.idProizvod = s.getIdProizvod();
            zs.kolicina = s.getKolicina();
            
            return zs;
        }).collect(Collectors.toList());

        Otpremnica otpremnica = otpremnicaService.generisiOtpremnicu(zahtev.getIdPorudzbina(), stavke);
        return ResponseEntity.ok(otpremnicaMapper.toDto(otpremnica));
    }

    @PostMapping("/{id}/otkazi")
        public ResponseEntity<?> otkazi(@PathVariable Long id) {
    try {
        Otpremnica otpremnica = otpremnicaService.otkazi(id);
        return ResponseEntity.ok(otpremnicaMapper.toDto(otpremnica));
    } catch (RuntimeException e) {
        Map<String, String> error = new HashMap<>();
        error.put("greska", e.getMessage());
        return ResponseEntity.status(400).body(error);
    }
}
        
}
