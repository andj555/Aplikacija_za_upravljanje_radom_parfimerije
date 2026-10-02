/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.controller;

import com.projekat.backend.dto.impl.KorisnikDto;
import com.projekat.backend.entity.impl.Korisnik;
import com.projekat.backend.mapper.impl.KorisnikMapper;
import com.projekat.backend.service.KorisnikService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anđelija
 */
@RestController
@RequestMapping("/api/korisnici")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;
    
    private KorisnikMapper mapper = new KorisnikMapper();

    @GetMapping
    public List<KorisnikDto> getAll() {
       
        return korisnikService.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    
    }

    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDto> getKorisnikById(@PathVariable Long id) {
       Korisnik korisnik = korisnikService.getById(id);
        if (korisnik != null) {
            return ResponseEntity.ok(mapper.toDto(korisnik));
        }
        return ResponseEntity.notFound().build();
}
}
