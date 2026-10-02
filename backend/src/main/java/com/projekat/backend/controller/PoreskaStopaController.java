/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.controller;

import com.projekat.backend.dto.impl.PoreskaStopaDto;
import com.projekat.backend.entity.impl.PoreskaStopa;
import com.projekat.backend.mapper.impl.PoreskaStopaMapper;
import com.projekat.backend.service.PoreskaStopaService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anđelija
 */
@RestController
@RequestMapping("/api/poreske-stope")
@CrossOrigin(origins = "http://localhost:5173")
public class PoreskaStopaController {
    
    @Autowired
    private PoreskaStopaService poreskaStopaService;
    private PoreskaStopaMapper PStopaMapper = new PoreskaStopaMapper();

    @GetMapping
    public List<PoreskaStopaDto> getAll() {
        
        return poreskaStopaService.getAll().stream().map(PStopaMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PoreskaStopaDto dto) {
        
        PoreskaStopa ps = PStopaMapper.toEntity(dto);
        return ResponseEntity.ok(PStopaMapper.toDto(poreskaStopaService.save(ps)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        
        poreskaStopaService.delete(id);
    }
}
