package com.commandes.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commandes.dao.CommandesDao;
import com.commandes.model.Commande;
import com.commandes.web.exceptions.CommandeNotFoundException;
import com.commandes.web.exceptions.ImpossibleAjouterCommandeException;

@RestController
public class CommandeController {

	@Autowired
	CommandesDao commandesDao;

	@PostMapping(value = "/Commandes")
	public Commande addCommande(@RequestBody Commande commande) {
		Commande nouvelleCommande = commandesDao.save(commande);
		if (nouvelleCommande == null)
			throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");
		return nouvelleCommande;
	}

	@GetMapping(value = "/Commande/{id}")
	public Optional<Commande> findCommandeById(@PathVariable int id) {
		Optional<Commande> commande = commandesDao.findById(id);
		if (!commande.isPresent())
			throw new CommandeNotFoundException("Cette commande n'existe pas");
		return commande;
	}
}
