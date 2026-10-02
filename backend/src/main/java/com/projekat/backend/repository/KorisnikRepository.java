/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.projekat.backend.repository;

import com.projekat.backend.entity.impl.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anđelija
 */
@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Korisnik findByEmail(String email);
    Korisnik findByVerifikacioniToken(String verifikacioniToken);
}
