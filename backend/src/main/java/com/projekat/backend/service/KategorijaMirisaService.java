/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.service;

import com.projekat.backend.entity.impl.KategorijaMirisa;
import com.projekat.backend.repository.KategorijaMirisaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anđelija
 */
@Service
public class KategorijaMirisaService {

   @Autowired
    private KategorijaMirisaRepository kategorijaMirisaRepository;

    public List<KategorijaMirisa> getAll() {
        return kategorijaMirisaRepository.findAll();
    }

    public KategorijaMirisa getById(Long id) {
        return kategorijaMirisaRepository.findById(id).orElse(null);
    }

    public KategorijaMirisa save(KategorijaMirisa kategorija) {
        return kategorijaMirisaRepository.save(kategorija);
    }

    public KategorijaMirisa update(Long id, KategorijaMirisa detalji) {
        return kategorijaMirisaRepository.findById(id).map(k -> {
            k.setNaziv(detalji.getNaziv());
            return kategorijaMirisaRepository.save(k);
        }).orElse(null);
    }

     public void delete(Long id) { 
        
        kategorijaMirisaRepository.deleteById(id); 
    }
    
}
