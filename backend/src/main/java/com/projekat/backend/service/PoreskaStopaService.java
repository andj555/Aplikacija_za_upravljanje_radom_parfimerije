/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.service;

import com.projekat.backend.entity.impl.PoreskaStopa;
import com.projekat.backend.repository.PoreskaStopaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anđelija
 */
@Service
public class PoreskaStopaService {
    
    @Autowired
    private PoreskaStopaRepository poreskaStopaRepository;

    public List<PoreskaStopa> getAll() {
        return poreskaStopaRepository.findAll(); 
    }
    public PoreskaStopa getById(Long id) { 
        
        return poreskaStopaRepository.findById(id).orElse(null);
    }
    public PoreskaStopa save(PoreskaStopa p) { 
        
        return poreskaStopaRepository.save(p);
    }
    public void delete(Long id) { 
        
        poreskaStopaRepository.deleteById(id); 
    }
    
    
}
