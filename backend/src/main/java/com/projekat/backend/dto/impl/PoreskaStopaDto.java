/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
/**
 *
 * @author Anđelija
 */
public class PoreskaStopaDto {
    
    private Long idPoreskaStopa;

    @PositiveOrZero(message = "Procenat ne moze biti negativan")
    @NotNull(message = "Morate uneti procenat")
    private Double procenat;

    @NotBlank(message = "Morate uneti naziv")
    private String naziv;

    public PoreskaStopaDto() {
    }

    public PoreskaStopaDto(Long idPoreskaStopa, Double procenat, String naziv) {
        this.idPoreskaStopa = idPoreskaStopa;
        this.procenat = procenat;
        this.naziv = naziv;
    }

    public Long getIdPoreskaStopa() {
        return idPoreskaStopa;
    }

    public void setIdPoreskaStopa(Long idPoreskaStopa) {
        this.idPoreskaStopa = idPoreskaStopa;
    }

    public Double getProcenat() {
        return procenat;
    }

    public void setProcenat(Double procenat) {
        this.procenat = procenat;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
