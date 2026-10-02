/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.projekat.backend.repository;

import com.projekat.backend.entity.impl.EmailStavka;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Anđelija
 */
public interface EmailStavkaRepository extends JpaRepository<EmailStavka, Long> {

 List<EmailStavka> findByPoslatoFalse();    
}
