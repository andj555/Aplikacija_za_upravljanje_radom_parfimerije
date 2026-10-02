/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.controller;

import com.projekat.backend.dto.impl.ProizvodDto;
import com.projekat.backend.entity.impl.Proizvod;
import com.projekat.backend.mapper.impl.ProizvodMapper;
import com.projekat.backend.service.ProizvodService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/proizvodi")
@CrossOrigin(origins = "http://localhost:5173")
public class ProizvodController {
    
        @Autowired
        private ProizvodService proizvodService;
        private ProizvodMapper proizvodMapper = new ProizvodMapper();
    
        
    @GetMapping
    public List<ProizvodDto> getAll() {
        
       return proizvodService.getAll().stream().map(proizvodMapper::toDto).collect(Collectors.toList());
    }
    
     @GetMapping("/{id}")
     public ProizvodDto getById(@PathVariable Long id) {
        
         return proizvodMapper.toDto(proizvodService.getById(id));
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProizvodDto dto) {
        Proizvod p = proizvodMapper.toEntity(dto);
        return ResponseEntity.ok(proizvodMapper.toDto(proizvodService.save(p)));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        proizvodService.delete(id);
    }
    
    @PutMapping("/{id}")
    public ProizvodDto update(@PathVariable Long id, @Valid @RequestBody ProizvodDto dto) {
        dto.setIdProizvod(id);
        Proizvod p = proizvodMapper.toEntity(dto);
        return proizvodMapper.toDto(proizvodService.save(p));
    }
}
