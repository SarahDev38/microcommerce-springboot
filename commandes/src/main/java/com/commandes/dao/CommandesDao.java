package com.commandes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commandes.model.Commande;

@Repository
public interface CommandesDao extends JpaRepository<Commande, Integer> {
}
