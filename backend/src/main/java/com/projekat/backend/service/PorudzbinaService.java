/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.service;

import com.projekat.backend.entity.enums.StatusIsporuke;
import com.projekat.backend.entity.enums.StatusPorudzbine;
import com.projekat.backend.entity.impl.Korisnik;
import com.projekat.backend.entity.impl.Porudzbina;
import com.projekat.backend.entity.impl.Proizvod;
import com.projekat.backend.entity.impl.StavkaPorudzbine;
import com.projekat.backend.repository.KorisnikRepository;
import com.projekat.backend.repository.PorudzbinaRepository;
import com.projekat.backend.repository.ProizvodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anđelija
 */

@Service
@RequiredArgsConstructor
public class PorudzbinaService {
   
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private ProizvodRepository proizvodRepository;
    @Autowired
    private EmailService emailService;

    public List<Porudzbina> getAll() {
        return porudzbinaRepository.findAll(); 
    }
    public Porudzbina getById(Long id) {
        return porudzbinaRepository.findById(id).orElse(null); 
    }
    public List<Porudzbina> getByKorisnik(Long idKorisnik) { 
        return porudzbinaRepository.findByKorisnik_idKorisnik(idKorisnik);
    }
    public void delete(Long id) {
        porudzbinaRepository.deleteById(id); 
    }

    public Porudzbina saveWithStavke(Porudzbina porudzbina, List<StavkaPorudzbine> stavke) {
            
        String trenutniEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Korisnik korisnik = korisnikRepository.findByEmail(trenutniEmail);
    
        if (korisnik != null) {
            porudzbina.setKorisnik(korisnik);
        }
        double ukupanIznos = 0;
        for (StavkaPorudzbine s : stavke) {
        s.setRb(null);
        s.setPorudzbina(porudzbina);
        
        if (s.getProizvod() != null && s.getProizvod().getIdProizvod() != null) {
            Proizvod trenutniProizvod = proizvodRepository.findById(s.getProizvod().getIdProizvod()).orElseThrow(() -> new RuntimeException());
            
            if ((trenutniProizvod.getKolicinaNaStanju() - trenutniProizvod.getRezervisanoStanje()) < s.getKolicina()) {
                throw new RuntimeException();
            }
            trenutniProizvod.setRezervisanoStanje(trenutniProizvod.getRezervisanoStanje() + s.getKolicina());
            proizvodRepository.save(trenutniProizvod);

            s.setProizvod(trenutniProizvod);
            
            double cenaPoKomadu = trenutniProizvod.getCena() != null ? trenutniProizvod.getCena() : 0.0;
            
            double procenatPdva = 20.0;
            if (trenutniProizvod.getPoreskaStopa() != null && trenutniProizvod.getPoreskaStopa().getProcenat() != null) {
                procenatPdva =trenutniProizvod.getPoreskaStopa().getProcenat();
            }
            double iznosBezPdv = cenaPoKomadu * s.getKolicina();
           double cenaSaPdv = cenaPoKomadu * (1 + procenatPdva / 100.0);
            double iznos = cenaSaPdv * s.getKolicina();
            double pdv = iznos - iznosBezPdv;
            
            s.setIznosBezPdv(iznosBezPdv);
            s.setCenaPoKomadu(cenaPoKomadu);
            s.setIznos(iznos);
            s.setPdv(pdv);
        
        ukupanIznos += iznos;
        
        }
        }
        if (porudzbina.getPopust() != null && porudzbina.getPopust()>0) {
            
            ukupanIznos = ukupanIznos *(1 - porudzbina.getPopust()/100.0);
        }
        
        porudzbina.setStavke(stavke);
        porudzbina.setUkupanIznos(ukupanIznos);
        porudzbina.setStatusIsporuke(StatusIsporuke.NEISPORUCENO);
        
        System.out.println("id korisnika : " + (porudzbina.getKorisnik() != null ? porudzbina.getKorisnik().getIdKorisnik() : "NEMA KORISNIKA"));
        Porudzbina p = porudzbinaRepository.save(porudzbina);
        
        if (p.getKorisnik() != null && p.getKorisnik().getEmail() != null) {
            try {
                emailService.posaljiPotvrduOPorudzbini(p.getKorisnik().getEmail(), p.getIdPorudzbina(),
                        p.getUkupanIznos());
            } catch (Exception e) {
            }
        
        }
       return p;
        }
    
    public Porudzbina izmeniPopust(Long id, Double popust) {

        Porudzbina porudzbina = porudzbinaRepository.findById(id).orElse(null);
        if (porudzbina != null) {
            porudzbina.setPopust(popust);
          
            double pocetniIznos = 0.0;
            if (porudzbina.getStavke() != null) {
                for (StavkaPorudzbine s : porudzbina.getStavke()) {
                    if (s.getIznos() != null) {
                        pocetniIznos += s.getIznos();
                    }
                }
            }
            double noviUkupanIznos = pocetniIznos;
            if (popust != null && popust > 0) {
                noviUkupanIznos = pocetniIznos * (1 - popust / 100.0);
            }
            porudzbina.setUkupanIznos(noviUkupanIznos);
            return porudzbinaRepository.save(porudzbina);
            
        }
        return null;
    }
    

    public Porudzbina otkazi(Long id) {
        Porudzbina p = getById(id);
        if (p == null) {
            return null;
        }

        if (p.getStavke() != null) {
            
            for (StavkaPorudzbine s : p.getStavke()) {
                if (s.getProizvod() != null && s.getProizvod().getIdProizvod() != null) {
                    Proizvod proizvod = proizvodRepository.findById(s.getProizvod().getIdProizvod()).orElse(null);
                    if (proizvod != null) {
                        proizvod.setRezervisanoStanje(proizvod.getRezervisanoStanje() - s.getKolicina());
                        proizvodRepository.save(proizvod);
                    }
                }
            }
        }
        p.setStatusPorudzbine(StatusPorudzbine.OTKAZANA);
        return porudzbinaRepository.save(p);
    }

}
