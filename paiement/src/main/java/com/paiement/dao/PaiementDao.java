package com.paiement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paiement.model.Paiement;

@Repository
public interface PaiementDao extends JpaRepository<Paiement, Integer>{

    Paiement findByIdCommande(int idCommande);
}
