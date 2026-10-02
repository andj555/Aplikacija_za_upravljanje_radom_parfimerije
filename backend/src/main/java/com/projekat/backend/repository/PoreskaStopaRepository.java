/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.projekat.backend.repository;

import com.projekat.backend.entity.impl.PoreskaStopa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anđelija
 */
@Repository
public interface PoreskaStopaRepository extends JpaRepository<PoreskaStopa, Long> {
}