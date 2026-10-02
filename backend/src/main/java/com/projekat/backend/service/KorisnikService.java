/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.service;

import com.projekat.backend.entity.impl.Korisnik;
import com.projekat.backend.repository.KorisnikRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anđelija
 */
 @Service
public class KorisnikService {
     
    @Autowired
    private KorisnikRepository korisnikRepository;

    public List<Korisnik> getAll() { 
        return korisnikRepository.findAll(); 
    }

    public Korisnik getById(Long id) { 
        return korisnikRepository.findById(id).orElse(null); 
    }

    public Korisnik findByEmail(String email) { 
        return korisnikRepository.findByEmail(email); 
    }


    public Korisnik save(Korisnik korisnik) { 
        return korisnikRepository.save(korisnik); 
    }

    public void delete(Long id) { 
        korisnikRepository.deleteById(id); 
    }

    public Korisnik findByToken(String token) {
      System.out.println(token+ ", "+korisnikRepository.findByVerifikacioniToken(token) +
              korisnikRepository.findByVerifikacioniToken(token).getEmail());
    return korisnikRepository.findByVerifikacioniToken(token);  
    }
    
}
