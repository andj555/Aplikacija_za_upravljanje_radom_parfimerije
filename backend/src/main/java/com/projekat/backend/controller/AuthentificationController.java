/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.controller;

import com.projekat.backend.JwtUtil;
import com.projekat.backend.entity.enums.TipKorisnika;
import com.projekat.backend.entity.impl.Korisnik;
import com.projekat.backend.service.EmailService;
import com.projekat.backend.service.KorisnikService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthentificationController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private JwtUtil jwtUtil;
    
     @Autowired
    private EmailService emailService;
  
    @PostMapping("/prijava")
    public ResponseEntity<?> prijava(@RequestBody Map<String, String> request) {
        
        String email = request.get("email");
        String password = request.get("password");
        
        Korisnik korisnik = korisnikService.findByEmail(email);
    
    if (korisnik == null || !korisnik.getPassword().equals(password)) {
        
        Map<String, String> error = new HashMap<>();
        error.put("error", "Pogresan email/lozinka");
        return ResponseEntity.status(401).body(error);
    }

    String token = jwtUtil.generateToken(korisnik.getEmail(), korisnik.getTipKorisnika().name());
    
    Map<String, Object> response = new HashMap<>();
    
    response.put("token", token);
    response.put("idKorisnik", korisnik.getIdKorisnik());
    response.put("email", korisnik.getEmail());
    response.put("ime", korisnik.getIme());
    response.put("prezime", korisnik.getPrezime());
    response.put("tipKorisnika", korisnik.getTipKorisnika());
    
    return ResponseEntity.ok(response);
}
 
    @PostMapping("/posalji-verifikaciju")
    public ResponseEntity<?> posaljiVerifikaciju(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Korisnik postoji = korisnikService.findByEmail(email);
        if (postoji != null) {
            if (postoji.getPotvrdjenEmail()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Ovaj email je vec registrovan");
                return ResponseEntity.status(400).body(error);
            }
        }

        String token = UUID.randomUUID().toString();
        
        Korisnik privremeni = korisnikService.findByEmail(email); 
        if (privremeni == null) {
            privremeni = new Korisnik();
            privremeni.setEmail(email);
            privremeni.setTipKorisnika(TipKorisnika.KUPAC);
            privremeni.setPotvrdjenEmail(false);
        }
        privremeni.setVerifikacioniToken(token);
        korisnikService.save(privremeni);
        
    emailService.posaljiVerifikacioniEmail(email, token);
    
    Map<String, String> response = new HashMap<>();
        response.put("Poruka", "Verifikacioni link je poslat na Vas email");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/verifikacija/{token}")
    public ResponseEntity<?> verifikacija(@PathVariable String token) {
        System.out.println("Token: " + token);
        Korisnik korisnik = korisnikService.findByToken(token);
        if (korisnik == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Nevazeci ili istekao verifikacioni link");
            return ResponseEntity.status(400).body(error);
        }
        
        korisnik.setPotvrdjenEmail(true);
        korisnikService.save(korisnik);
        Map<String, String> response = new HashMap<>();
        response.put("Poruka", "Vas token je validan, unesite preostale podatke");
        response.put("Email", korisnik.getEmail());
        return ResponseEntity.ok(response);
    }
    
      @PostMapping("/zavrsi-registraciju")
    public ResponseEntity<?> zavrsiRegistraciju(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        Korisnik korisnik = korisnikService.findByToken(token);

        if (korisnik == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Nevazeci ili istekao token.");
            return ResponseEntity.status(400).body(error);
        }

        String password = request.get("password");
        String ime = request.get("ime");
        String prezime = request.get("prezime");
        
        if (password == null || password.length() < 5) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Lozinka mora imati bar 5 karaktera");
            return ResponseEntity.status(400).body(error);
        }
        if (ime == null || ime.trim().length() < 2) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Ime mora imati bar 2 karaktera");
            return ResponseEntity.status(400).body(error);
        }
        if (prezime == null || prezime.trim().length() < 2) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Prezime mora imati bar 2 karaktera");
            return ResponseEntity.status(400).body(error);
        }
    
    korisnik.setPassword(password);
    korisnik.setIme(ime);
    korisnik.setPrezime(prezime);
        
    korisnikService.save(korisnik);

        Map<String, String> response = new HashMap<>();
        response.put("Poruka", "Registracija je uspesno zavrsena, prijavite se");
        return ResponseEntity.ok(response);
    }
   
}
    
