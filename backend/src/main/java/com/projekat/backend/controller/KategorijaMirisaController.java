/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.controller;

import com.projekat.backend.dto.impl.KategorijaMirisaDto;
import com.projekat.backend.entity.impl.KategorijaMirisa;
import com.projekat.backend.mapper.impl.KategorijaMirisaMapper;
import com.projekat.backend.service.KategorijaMirisaService;
import java.util.List;
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
@RequestMapping("/api/kategorije-mirisa")
@CrossOrigin(origins = "http://localhost:5173")
public class KategorijaMirisaController {

    @Autowired
    private KategorijaMirisaService kategorijaMirisaService;

    @Autowired
    private KategorijaMirisaMapper mapper;

    @GetMapping
    public List<KategorijaMirisaDto> getAll() {
        return kategorijaMirisaService.getAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KategorijaMirisaDto> getKategorijaMirisaById(@PathVariable Long id) {
        KategorijaMirisa kategorija = kategorijaMirisaService.getById(id);
        if (kategorija != null) {
            return ResponseEntity.ok(mapper.toDto(kategorija));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<KategorijaMirisaDto> save(@RequestBody KategorijaMirisaDto dto) {
        KategorijaMirisa entitet = mapper.toEntity(dto);
        KategorijaMirisa sacuvan = kategorijaMirisaService.save(entitet);
        return ResponseEntity.ok(mapper.toDto(sacuvan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KategorijaMirisaDto> update(@PathVariable Long id, @RequestBody KategorijaMirisaDto dto) {
        KategorijaMirisa entitet = mapper.toEntity(dto);
        KategorijaMirisa izmenjen = kategorijaMirisaService.update(id, entitet);
        if (izmenjen != null) {
            return ResponseEntity.ok(mapper.toDto(izmenjen));
        }
        return ResponseEntity.notFound().build();
    }

}
