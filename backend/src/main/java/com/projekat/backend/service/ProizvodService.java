/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.service;

import com.projekat.backend.entity.impl.Proizvod;
import com.projekat.backend.repository.ProizvodRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anđelija
 */
@Service
public class ProizvodService {
 
    @Autowired
    private ProizvodRepository proizvodRepository;

    public List<Proizvod> getAll() { 
        return proizvodRepository.findAll(); 
    }
    
    public Proizvod getById(Long id) { 
        return proizvodRepository.findById(id).orElse(null); 
    }
    public Proizvod save(Proizvod p) { 
        return proizvodRepository.save(p); 
    }
    public void delete(Long id) { 
        proizvodRepository.deleteById(id); 
    }
}
