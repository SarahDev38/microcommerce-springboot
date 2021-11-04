package com.paiement.web.controller;

import com.paiement.dao.PaiementDao;
import com.paiement.model.Paiement;
import com.paiement.web.exceptions.PaiementExistantException;
import com.paiement.web.exceptions.PaiementImpossibleException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaiementController {

	@Autowired
	PaiementDao paiementDao;

	@PostMapping(value = "/Paiement")
	public ResponseEntity<Paiement> payACommand(@RequestBody Paiement paiement) {
		if (paiementDao.findByIdCommande(paiement.getIdCommande()) != null)
			throw new PaiementExistantException("Cette commande est déjà payée");

		Paiement nouveauPaiement = paiementDao.save(paiement);
		if (nouveauPaiement == null)
			throw new PaiementImpossibleException("Erreur, impossible d'établir le paiement, réessayez plus tard");

		// TODO Nous allons appeler le Microservice Commandes ici pour lui signifier que
		// le paiement est accepté

		return new ResponseEntity<Paiement>(nouveauPaiement, HttpStatus.CREATED);
	}

}
