/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.service;

import com.projekat.backend.entity.enums.Status;
import com.projekat.backend.entity.enums.StatusIsporuke;
import com.projekat.backend.entity.enums.StatusPorudzbine;
import com.projekat.backend.entity.impl.Otpremnica;
import com.projekat.backend.entity.impl.Porudzbina;
import com.projekat.backend.entity.impl.Proizvod;
import com.projekat.backend.entity.impl.StavkaOtpremnice;
import com.projekat.backend.entity.impl.StavkaPorudzbine;
import com.projekat.backend.repository.OtpremnicaRepository;
import com.projekat.backend.repository.PorudzbinaRepository;
import com.projekat.backend.repository.ProizvodRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anđelija
 */
@Service
public class OtpremnicaService {
    
    @Autowired
    private OtpremnicaRepository otpremnicaRepository;
    @Autowired
    private ProizvodRepository proizvodRepository;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
 
    @Autowired
    private EmailService emailService;
    
    public List<Otpremnica> getAll() { 
        return otpremnicaRepository.findAll(); 
    }
    public Otpremnica getById(Long id) {
        return otpremnicaRepository.findById(id).orElse(null); 
    }
    
    public static class ZahtevStavka {
        public Long idProizvod;
        public int kolicina;
    }

    public OtpremnicaRepository getOtpremnicaRepository() {
        return otpremnicaRepository;
    }

    public void setOtpremnicaRepository(OtpremnicaRepository otpremnicaRepository) {
        this.otpremnicaRepository = otpremnicaRepository;
    }

    public ProizvodRepository getProizvodRepository() {
        return proizvodRepository;
    }

    public void setProizvodRepository(ProizvodRepository proizvodRepository) {
        this.proizvodRepository = proizvodRepository;
    }

    public PorudzbinaRepository getPorudzbinaRepository() {
        return porudzbinaRepository;
    }

    public void setPorudzbinaRepository(PorudzbinaRepository porudzbinaRepository) {
        this.porudzbinaRepository = porudzbinaRepository;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
    
    
    @Transactional
    public Otpremnica generisiOtpremnicu(Long idPorudzbina, List<ZahtevStavka> zahtevi) {
        Porudzbina porudzbina = porudzbinaRepository.findById(idPorudzbina).orElseThrow(() -> new RuntimeException("porudzbina ne postoji"));

        Otpremnica otpremnica = new Otpremnica();
        otpremnica.setPorudzbina(porudzbina);
       otpremnica.setBrojOtpremnice(brojOtpremnice());
        otpremnica.setStatus(Status.IZDATA);
        otpremnica.setDatumIzdavanja(LocalDate.now());
    
        double ukupanIznosBezPdv = 0;
        double ukupanIznosPdv=0;
        double ukupanIznos=0;

        for (ZahtevStavka zahtev : zahtevi) {
        Proizvod proizvod = proizvodRepository.findById(zahtev.idProizvod)
            .orElseThrow(() -> new RuntimeException("Proizvod ne postoji"));
        
        StavkaPorudzbine stavkaPorudzbine = porudzbina.getStavke().stream()
            .filter(s -> s.getProizvod().getIdProizvod().equals(zahtev.idProizvod))
            .findFirst().orElseThrow(() -> new RuntimeException("Proizvod nije deo ove porudzbine"));

        double cenaPoKomadu = stavkaPorudzbine.getCenaPoKomadu();
        double poreskaStopa = proizvod.getPoreskaStopa() != null ? proizvod.getPoreskaStopa().getProcenat() : 20.0;
        double iznosBezPdv = cenaPoKomadu *zahtev.kolicina;
        double cenaSaPdv = cenaPoKomadu * (1 + poreskaStopa /100.0);
        double iznos= cenaSaPdv * zahtev.kolicina;
        double pdv = iznos - iznosBezPdv;

        StavkaOtpremnice stavka = new StavkaOtpremnice();
        stavka.setProizvod(proizvod);
        stavka.setKolicina(zahtev.kolicina);
        stavka.setCenaPoKomadu(cenaPoKomadu);
        stavka.setIznosBezPdv(iznosBezPdv);
        stavka.setPdv(pdv);
        stavka.setIznos(iznos);
        stavka.setOtpremnica(otpremnica);
        otpremnica.getStavke().add(stavka);

        long kolNaStanju = proizvod.getKolicinaNaStanju() != null ? proizvod.getKolicinaNaStanju() : 0L;
        int rezStanje = proizvod.getRezervisanoStanje() != null ? proizvod.getRezervisanoStanje() : 0;
        
        if (zahtev.kolicina > kolNaStanju) {
        throw new RuntimeException();
        }
        if (zahtev.kolicina > rezStanje) {
            throw new RuntimeException();
        }

        proizvod.setKolicinaNaStanju(kolNaStanju - zahtev.kolicina);
        proizvod.setRezervisanoStanje(rezStanje - zahtev.kolicina);

        proizvodRepository.save(proizvod);

        ukupanIznosBezPdv += iznosBezPdv;
        ukupanIznosPdv += pdv;
        ukupanIznos += iznos;
        }
        
        if (porudzbina.getPopust() != null && porudzbina.getPopust()> 0) {
        ukupanIznos = ukupanIznos * (1 - porudzbina.getPopust()/100.0);
        }

        otpremnica.setIznosBezPdv(ukupanIznosBezPdv);
        otpremnica.setPdv(ukupanIznosPdv);
        otpremnica.setIznos(ukupanIznos);
        Otpremnica sacuvana = otpremnicaRepository.save(otpremnica);

        azurirajStatusIsporukeIPorudzbine(porudzbina);
        
         try {
            if (porudzbina.getKorisnik() != null && porudzbina.getKorisnik().getEmail() != null) {
               
                emailService.posaljiOtpremnicu(porudzbina.getKorisnik().getEmail(), sacuvana.getBrojOtpremnice(), sacuvana.getIdOtpremnica());
            }
            
        } catch (Exception e) {
            System.err.println("Greska tokom slanja otpremnice " + e.getMessage());
        }

        return sacuvana;
    }
    
        @Transactional
        public Otpremnica otkazi(Long idOtpremnica) {

        Otpremnica otpremnica = otpremnicaRepository.findById(idOtpremnica)
        .orElseThrow(() -> new RuntimeException("Otpremnica ne postoji"));

    if (otpremnica.getStatus() == Status.OTKAZANA) {
        throw new RuntimeException("Otpremnica je vec otkazana");
    }

    for (StavkaOtpremnice s : otpremnica.getStavke()) {
    Proizvod proizvod = proizvodRepository.findById(s.getProizvod().getIdProizvod()).orElseThrow(() -> new RuntimeException("Proizvod ne postoji"));       
    
    long kolNaStanju = proizvod.getKolicinaNaStanju() != null ? proizvod.getKolicinaNaStanju() : 0L;
    int rezStanje = proizvod.getRezervisanoStanje() != null ? proizvod.getRezervisanoStanje() : 0;
    long stavkaKol = s.getKolicina(); 
    
    proizvod.setKolicinaNaStanju(kolNaStanju + stavkaKol);
    proizvod.setRezervisanoStanje((int)(rezStanje + s.getKolicina()));
    proizvodRepository.save(proizvod);
    }

    otpremnica.setStatus(Status.OTKAZANA);
    Otpremnica sacuvana = otpremnicaRepository.save(otpremnica);

    azurirajStatusIsporukeIPorudzbine(sacuvana.getPorudzbina());

    try {
        if (sacuvana.getPorudzbina() != null && sacuvana.getPorudzbina().getKorisnik() != null) {
            emailService.posaljiObavestenjeOOtkazivanju(
                sacuvana.getPorudzbina().getKorisnik().getEmail(),
                sacuvana.getBrojOtpremnice()
            );
        }
    } catch (Exception e) {
        System.err.println("Greska pri slanju obavestenja: " + e.getMessage());
    }

    return sacuvana;
}
        private void azurirajStatusIsporukeIPorudzbine(Porudzbina porudzbina) {
       if (porudzbina.getStavke() == null || porudzbina.getStavke().isEmpty()) {

        return;
    }
    List<Otpremnica> aktivneOtpremnice = porudzbina.getOtpremnice() != null ? porudzbina.getOtpremnice().stream()
            .filter(o -> o.getStatus() != Status.OTKAZANA).toList() : List.of();

    boolean potpuno = true;
    boolean imaPoslato = false;

    for (StavkaPorudzbine sp : porudzbina.getStavke()) {
        double isporuceno = aktivneOtpremnice.stream().flatMap(o -> o.getStavke().stream()).filter(s -> s.getProizvod().getIdProizvod().equals(sp.getProizvod().getIdProizvod()))
            .mapToDouble(StavkaOtpremnice::getKolicina).sum();

        if (isporuceno > 0) imaPoslato = true;
        if (isporuceno < sp.getKolicina()) potpuno = false;
    }

    if (potpuno && imaPoslato) {
        porudzbina.setStatusIsporuke(StatusIsporuke.POTPUNO_ISPORUCENO);
        porudzbina.setStatusPorudzbine(StatusPorudzbine.ISPORUCENA);
    } else if (imaPoslato) {
        porudzbina.setStatusIsporuke(StatusIsporuke.DELIMICNO_ISPORUCENO);
        porudzbina.setStatusPorudzbine(StatusPorudzbine.KREIRANA);
    } else {
        porudzbina.setStatusIsporuke(StatusIsporuke.NEISPORUCENO);
        porudzbina.setStatusPorudzbine(StatusPorudzbine.KREIRANA);
    }
    
    porudzbinaRepository.save(porudzbina);
}
        private String brojOtpremnice() {
            
        long broj = otpremnicaRepository.count() + 1;
        
        return String.format( "OTP-%d-%03d", LocalDate.now().getYear(), broj);
    }
    
        public void delete(Long id) { 
            
            otpremnicaRepository.deleteById(id);
        }

}
