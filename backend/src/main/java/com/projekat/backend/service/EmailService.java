/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.service;

import com.projekat.backend.entity.impl.EmailStavka;
import com.projekat.backend.entity.impl.Otpremnica;
import com.projekat.backend.entity.impl.Porudzbina;
import com.projekat.backend.entity.impl.StavkaOtpremnice;
import com.projekat.backend.entity.impl.StavkaPorudzbine;
import com.projekat.backend.repository.EmailStavkaRepository;
import com.projekat.backend.repository.OtpremnicaRepository;
import com.projekat.backend.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anđelija
 */
 @Service
public class EmailService {

    @Autowired
    private EmailStavkaRepository emailStavkaRepository;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    
    @Autowired
    private OtpremnicaRepository otpremnicaRepository;

    public void posaljiVerifikacioniEmail(String email, String token) {
        System.out.println("VERIFIKUJE SE EMAIL: " + email);
        String link = "http://localhost:5173/verifikacija?token=" + token;
        EmailStavka stavka = new EmailStavka();
        stavka.setPrimalac(email);
        stavka.setNaslov("Verfikacija emaila");
        stavka.setSadrzaj(
            "Poštovani,\n\n" +
            "Kliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\n" +
            link + "\n\n" +
            "Ukoliko niste Vi započeli registraciju, ignorišite ovaj mejl."
        );
        stavka.setPoslato(false);    
        emailStavkaRepository.save(stavka);
    }

    public void posaljiPotvrduOPorudzbini(String email, Long idPorudzbina, Double ukupanIznos) {
        Porudzbina porudzbina = porudzbinaRepository.findById(idPorudzbina).orElse(null);
        String proizvodiString = "Naručili ste:\n";
        for (StavkaPorudzbine s : porudzbina.getStavke()) {
        double iznosStavke = s.getIznos();
        proizvodiString += "- " + s.getProizvod().getNaziv() + " (x" + s.getKolicina() + ") — " + iznosStavke + " Rsd\n";
            }
        EmailStavka stavka = new EmailStavka();
        stavka.setPrimalac(email);
        stavka.setNaslov("Potvrda porudžbine #" + idPorudzbina);
        stavka.setSadrzaj(
            "Poštovani,\n\n" +
            "Vaša porudžbina #" + idPorudzbina + " je uspešno kreirana\n\n" +
            "Ukupan iznos: " + ukupanIznos + " RSD\n\n" +
            "Bićete obavešteni kada Vaša porudžbina bude poslata\n\n" +proizvodiString);
      
        stavka.setPoslato(false);
        emailStavkaRepository.save(stavka);
    }

    public void posaljiOtpremnicu(String email, String brojOtpremnice, Long idOtpremnica) {
        Otpremnica otpremnica = otpremnicaRepository.findById(idOtpremnica).orElse(null);
        String StringOtp = "Proizvodi: ";
        for (StavkaOtpremnice s : otpremnica.getStavke()) {
        StringOtp += "- " + s.getProizvod().getNaziv() + " , kom: " + s.getKolicina() + "\n";
        }
        EmailStavka stavka = new EmailStavka();
        stavka.setPrimalac(email);
        stavka.setNaslov("Otpremnica " + brojOtpremnice);
        stavka.setSadrzaj(
            "Poštovani,\n\n" +
            "Deo Vaše porudžbine je poslat! Broj otpremnice: " + brojOtpremnice + ".\n\n" +StringOtp
        + "Iznos: "+otpremnica.getIznos()
        );
        
        stavka.setPoslato(false);
        emailStavkaRepository.save(stavka);
    }

    public void posaljiObavestenjeOOtkazivanju(String email, String brojOtpremnice) {
        EmailStavka stavka = new EmailStavka();
        stavka.setPrimalac(email);
        stavka.setNaslov("Ispravka otpremnice " + brojOtpremnice);
        stavka.setSadrzaj(
            "Poštovani,\n\n" +
            "Obaveštavamo vas da je otpremnica " + brojOtpremnice + " koju ste ranije dobili " +
            "imala grešku u sebi i otkazana je. Uskoro ćete dobiti ispravnu otpremnicu sa tačnim podacima.\n\n" +
            "Izvinjavamo Vam se na neprijatnosti"
        );
        stavka.setPoslato(false);
        emailStavkaRepository.save(stavka);
    }
}
