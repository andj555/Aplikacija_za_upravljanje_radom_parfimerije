/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.service;

import com.projekat.backend.entity.impl.EmailStavka;
import com.projekat.backend.repository.EmailStavkaRepository;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 *
 * @author Anđelija
 */
@Component
public class EmailScheduler {

    @Autowired
    private EmailStavkaRepository emailStavkaRepository;
    @Autowired
    private JavaMailSender mailSender;
    
    @Scheduled(fixedRate = 10000)
    public void posaljiNeposlateMejlove() {
    List<EmailStavka> mejlovi = emailStavkaRepository.findByPoslatoFalse();
    
    for (EmailStavka stavka : mejlovi) {
            try {
                MimeMessage poruka = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(poruka, true, "UTF-8");

                helper.setTo(stavka.getPrimalac());
                helper.setFrom("juniorke74@gmail.com");
                helper.setSubject(stavka.getNaslov());
                helper.setText(stavka.getSadrzaj(), false); 
                
            mailSender.send(poruka);
            
            stavka.setPoslato(true);
            stavka.setVremeSlanja(LocalDateTime.now());
            emailStavkaRepository.save(stavka);
            
            
          System.out.println("Email je uspešno poslat: " + stavka.getPrimalac() + " - " + stavka.getNaslov());
          
            } catch (Exception e) {
                
           System.err.println("Greška tokom slanja emaila za stavku #" + stavka.getId() + ": " + e.getMessage());
            }
        }
    }
}