package com.paiement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Paiement {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private Integer idCommande;
    private Double montant;
    private Long numeroCarte;

    public Paiement() {
    }

    public Paiement(int id, Integer idCommande, Double montant, Long numeroCarte) {
        this.id = id;
        this.idCommande = idCommande;
        this.montant = montant;
        this.numeroCarte = numeroCarte;
    }
}
